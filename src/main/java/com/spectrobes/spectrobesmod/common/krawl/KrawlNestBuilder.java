package com.spectrobes.spectrobesmod.common.krawl;

import com.spectrobes.spectrobesmod.common.config.KrawlConfig;
import com.spectrobes.spectrobesmod.common.save_data.KrawlNest;
import com.spectrobes.spectrobesmod.common.save_data.KrawlNest.NestJob;
import com.spectrobes.spectrobesmod.common.save_data.KrawlNest.NestOp;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Geometry for nest expansion: the tier plan, spiral shafts between tiers and domed chambers.
 * <p>
 * Shafts are helical ramps (3 wide, 4 tall) around a vertical axis that drop one block for every two
 * walked, so players and krawl can walk up and down them. Chambers are domes with flat membrane floors.
 */
public final class KrawlNestBuilder {
    /** Shaft walkway spans this band of radii around the axis. */
    private static final double SHAFT_INNER_RADIUS = 3.5D;
    private static final double SHAFT_OUTER_RADIUS = 6.5D;
    private static final double SHAFT_MID_RADIUS = 5.0D;
    /** Radians walked per one-block drop: two blocks of arc at the walkway's middle radius. */
    private static final double ANGLE_PER_STEP = 2.0D / SHAFT_MID_RADIUS;
    private static final int HEADROOM = 4;
    /** Length of the corridors joining chambers to shafts. */
    private static final int CORRIDOR_LENGTH = 4;

    public static final int GRAND_RADIUS = 20;
    public static final int GRAND_HEIGHT = 12;

    private KrawlNestBuilder() {
    }

    // ---------------------------------------------------------------- tier plan

    /** Number of chamber tiers below the surface heart; the last one is the grand chamber. */
    public static int tierCount(KrawlNest nest) {
        int diff = surfaceFloorY(nest) - KrawlConfig.NEST_GRAND_CHAMBER_Y.get();

        if (diff < 20) {
            return 1;
        }

        return Math.max(2, Math.round(diff / (float) KrawlConfig.NEST_TIER_SPACING.get()));
    }

    public static int floorY(KrawlNest nest, int tier) {
        int top = surfaceFloorY(nest);
        int diff = top - KrawlConfig.NEST_GRAND_CHAMBER_Y.get();
        return top - Math.round(diff * tier / (float) tierCount(nest));
    }

    public static int chamberRadius(int tier, boolean grand) {
        return grand ? GRAND_RADIUS : Math.min(15, 7 + tier * 2);
    }

    public static int chamberHeight(int tier, boolean grand) {
        return grand ? GRAND_HEIGHT : Math.min(9, 5 + tier);
    }

    public static int miniXellesCount(int tier, boolean grand) {
        return grand ? 5 : Math.min(5, tier + 1);
    }

    private static int surfaceFloorY(KrawlNest nest) {
        return nest.heart.getY() - 1;
    }

    /**
     * Plans the next construction job, or returns null when the nest is fully built (or the area is not loaded).
     * <p>
     * Layout per tier: a corridor leaves the chamber above through its side wall, meets the top of a spiral
     * shaft beside the chamber, and the shaft's bottom opens into a corridor that leads into the side of the
     * next chamber.
     */
    public static NestJob planNextJob(ServerLevel level, KrawlNest nest) {
        int tiers = tierCount(nest);
        int deepest = nest.getDeepestChamberTier();

        if (deepest >= tiers) {
            return null;
        }

        RandomSource random = RandomSource.create(nest.id.getLeastSignificantBits() ^ (deepest * 31L + nest.shaftTier));

        if (nest.shaftTier <= deepest) {
            int nextTier = deepest + 1;
            int nextRadius = chamberRadius(nextTier, nextTier == tiers);
            double direction;
            BlockPos helixTop;
            BlockPos corridorStart = null;
            int topY;

            if (deepest == 0) {
                // From the surface: the helix opens in the ground a few blocks from the heart.
                direction = random.nextDouble() * Mth.TWO_PI;
                int x = nest.heart.getX() + (int) Math.round(Math.cos(direction) * 7);
                int z = nest.heart.getZ() + (int) Math.round(Math.sin(direction) * 7);

                if (!level.isLoaded(new BlockPos(x, nest.heart.getY(), z))) {
                    return null;
                }

                topY = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z) - 1;
                helixTop = new BlockPos(x, topY, z);
            } else {
                // Leave the chamber through the wall opposite the corridor that came in (give or take 60 degrees).
                KrawlNest.Chamber chamber = nest.getChamber(deepest);
                BlockPos center = chamber.center;
                double away = Math.atan2(center.getZ() - chamber.entry.getZ(), center.getX() - chamber.entry.getX());
                direction = away + (random.nextDouble() - 0.5D) * (Mth.PI * 2.0D / 3.0D);
                topY = center.getY();
                helixTop = offset(center, direction, chamber.radius + CORRIDOR_LENGTH);
                corridorStart = offset(center, direction, chamber.radius - 2);
            }

            // The helix axis sits beyond the corridor, so the corridor arrives at the helix's top step.
            BlockPos axis = offset(helixTop, direction, SHAFT_MID_RADIUS);
            float topAngle = (float) (direction + Mth.PI);

            return NestJob.shaft(nextTier, new BlockPos(axis.getX(), topY, axis.getZ()), floorY(nest, nextTier),
                    topAngle, nextRadius, corridorStart);
        }

        // Shaft is done: open the chamber its bottom corridor leads into.
        int tier = nest.shaftTier;
        boolean grand = tier == tiers;
        BlockPos center = nest.nextChamberCenter;
        BlockPos entry = nest.nextChamberEntry;

        if (center == null || entry == null) {
            return null;
        }

        return NestJob.chamber(tier, center, chamberRadius(tier, grand), chamberHeight(tier, grand), grand, entry);
    }

    /** Angle (radians) around the axis at which a shaft's bottom step ends. */
    private static double bottomAngle(NestJob job) {
        int steps = job.origin.getY() - Math.min(job.bottomY, job.origin.getY());
        return job.angle + (steps + 0.5D) * ANGLE_PER_STEP;
    }

    /** Floor centre of the chamber a shaft leads to. */
    public static BlockPos shaftExitChamberCenter(NestJob job) {
        BlockPos axis = new BlockPos(job.origin.getX(), job.bottomY, job.origin.getZ());
        return offset(axis, bottomAngle(job), SHAFT_OUTER_RADIUS + CORRIDOR_LENGTH + job.radius);
    }

    /** Where a shaft's bottom corridor meets the next chamber's edge. */
    public static BlockPos shaftExitChamberEntry(NestJob job) {
        BlockPos axis = new BlockPos(job.origin.getX(), job.bottomY, job.origin.getZ());
        return offset(axis, bottomAngle(job), SHAFT_OUTER_RADIUS + CORRIDOR_LENGTH);
    }

    private static BlockPos offset(BlockPos from, double angle, double distance) {
        return new BlockPos(
                from.getX() + (int) Math.round(Math.cos(angle) * distance),
                from.getY(),
                from.getZ() + (int) Math.round(Math.sin(angle) * distance));
    }

    // ---------------------------------------------------------------- operations

    public static List<NestOp> generateOps(NestJob job) {
        return job.type == NestJob.Type.SHAFT ? shaftOps(job) : chamberOps(job);
    }

    /** A cross-section of walkway: the columns walked at one floor height, carved in order. */
    private record Slice(int floor, Set<Long> columns) {
    }

    private static List<NestOp> shaftOps(NestJob job) {
        int topY = job.origin.getY();
        int bottomY = Math.min(job.bottomY, topY);
        double cx = job.origin.getX() + 0.5D;
        double cz = job.origin.getZ() + 0.5D;
        List<Slice> slices = new ArrayList<>();

        // Corridor from the chamber above into the helix's top step.
        if (job.topCorridor) {
            BlockPos into = offset(job.origin, job.angle, SHAFT_INNER_RADIUS);
            slices.addAll(corridorSlices(job.entry, into, topY));
        }

        // The helix: each step's columns are assigned to only one consecutive step.
        Set<Long> previous = new HashSet<>();

        for (int floor = topY; floor >= bottomY; floor--) {
            int step = topY - floor;
            double a0 = job.angle + step * ANGLE_PER_STEP;
            double a1 = a0 + ANGLE_PER_STEP;
            Set<Long> columns = new LinkedHashSet<>();

            for (double a = a0; a < a1; a += 0.03D) {
                for (double r = SHAFT_INNER_RADIUS; r <= SHAFT_OUTER_RADIUS; r += 0.35D) {
                    long key = BlockPos.asLong(Mth.floor(cx + Math.cos(a) * r), 0, Mth.floor(cz + Math.sin(a) * r));

                    if (!previous.contains(key)) {
                        columns.add(key);
                    }
                }
            }

            slices.add(new Slice(floor, columns));
            previous = columns;
        }

        // Corridor from the bottom step out into the side of the next chamber (two blocks past its wall).
        BlockPos axisBottom = new BlockPos(job.origin.getX(), bottomY, job.origin.getZ());
        double exitAngle = bottomAngle(job);
        slices.addAll(corridorSlices(
                offset(axisBottom, exitAngle, SHAFT_INNER_RADIUS),
                offset(axisBottom, exitAngle, SHAFT_OUTER_RADIUS + CORRIDOR_LENGTH + 2),
                bottomY));

        return slicesToOps(slices);
    }

    /** A straight corridor three blocks wide between two points at one floor height. */
    private static List<Slice> corridorSlices(BlockPos from, BlockPos to, int floor) {
        List<Slice> slices = new ArrayList<>();
        double dx = to.getX() - from.getX();
        double dz = to.getZ() - from.getZ();
        double length = Math.sqrt(dx * dx + dz * dz);

        if (length < 0.5D) {
            return slices;
        }

        double ux = dx / length;
        double uz = dz / length;
        Set<Long> seen = new HashSet<>();

        for (double t = 0; t <= length; t += 0.5D) {
            Set<Long> columns = new LinkedHashSet<>();

            for (double w = -1.2D; w <= 1.21D; w += 0.4D) {
                double x = from.getX() + 0.5D + ux * t - uz * w;
                double z = from.getZ() + 0.5D + uz * t + ux * w;
                long key = BlockPos.asLong(Mth.floor(x), 0, Mth.floor(z));

                if (seen.add(key)) {
                    columns.add(key);
                }
            }

            if (!columns.isEmpty()) {
                slices.add(new Slice(floor, columns));
            }
        }

        return slices;
    }

    /** Per slice, in order: seal the walls around it, lay its floor, then open it. */
    private static List<NestOp> slicesToOps(List<Slice> slices) {
        Set<BlockPos> carved = new HashSet<>();
        Set<BlockPos> floors = new HashSet<>();

        for (Slice slice : slices) {
            for (long key : slice.columns()) {
                int x = BlockPos.getX(key);
                int z = BlockPos.getZ(key);
                floors.add(new BlockPos(x, slice.floor(), z));

                for (int dy = 1; dy <= HEADROOM; dy++) {
                    carved.add(new BlockPos(x, slice.floor() + dy, z));
                }
            }
        }

        floors.removeAll(carved);

        List<NestOp> ops = new ArrayList<>();
        Set<BlockPos> walled = new HashSet<>();
        Set<BlockPos> emitted = new HashSet<>();

        for (Slice slice : slices) {
            List<BlockPos> sliceCarve = new ArrayList<>();
            List<BlockPos> sliceFloor = new ArrayList<>();

            for (long key : slice.columns()) {
                int x = BlockPos.getX(key);
                int z = BlockPos.getZ(key);
                BlockPos floorPos = new BlockPos(x, slice.floor(), z);

                if (floors.contains(floorPos) && emitted.add(floorPos)) {
                    sliceFloor.add(floorPos);
                }

                for (int dy = 1; dy <= HEADROOM; dy++) {
                    BlockPos pos = new BlockPos(x, slice.floor() + dy, z);

                    if (emitted.add(pos)) {
                        sliceCarve.add(pos);
                    }
                }
            }

            for (BlockPos pos : sliceCarve) {
                for (Direction direction : Direction.values()) {
                    BlockPos neighbour = pos.relative(direction);

                    if (!carved.contains(neighbour) && !floors.contains(neighbour) && walled.add(neighbour)) {
                        ops.add(new NestOp(neighbour, NestOp.Kind.WALL));
                    }
                }
            }

            sliceFloor.forEach(pos -> ops.add(new NestOp(pos, NestOp.Kind.FLOOR)));
            sliceCarve.forEach(pos -> ops.add(new NestOp(pos, NestOp.Kind.CARVE)));
        }

        return ops;
    }

    private static List<NestOp> chamberOps(NestJob job) {
        BlockPos center = job.origin;
        int radius = job.radius;
        int height = job.height;
        double rSq = (radius + 0.5D) * (radius + 0.5D);

        Set<BlockPos> carved = new HashSet<>();
        Set<BlockPos> floors = new HashSet<>();

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                double horizontal = (dx * dx + dz * dz) / rSq;

                if (horizontal > 1.0D) {
                    continue;
                }

                floors.add(center.offset(dx, 0, dz));

                for (int dy = 1; dy <= height; dy++) {
                    double vertical = (dy - 0.5D) / height;

                    if (horizontal + vertical * vertical <= 1.0D) {
                        carved.add(center.offset(dx, dy, dz));
                    }
                }
            }
        }

        List<NestOp> ops = new ArrayList<>();
        Set<BlockPos> walled = new HashSet<>();

        for (BlockPos pos : carved) {
            ops.add(new NestOp(pos, NestOp.Kind.CARVE));

            for (Direction direction : Direction.values()) {
                BlockPos neighbour = pos.relative(direction);

                if (!carved.contains(neighbour) && !floors.contains(neighbour) && walled.add(neighbour)) {
                    ops.add(new NestOp(neighbour, NestOp.Kind.WALL));
                }
            }
        }

        for (BlockPos pos : floors) {
            ops.add(new NestOp(pos, NestOp.Kind.FLOOR));
        }

        // Eat outward from the entrance. Within a column the ceiling is sealed before the space under it is
        // opened, so gravel and sand above cannot fall in.
        BlockPos entry = job.entry;
        ops.sort(Comparator
                .comparingInt((NestOp op) -> (int) Math.sqrt(horizontalDistSqr(op.pos(), entry)))
                .thenComparingInt(op -> switch (op.kind()) {
                    case WALL -> 0;
                    case FLOOR -> 1;
                    case CARVE -> 2;
                })
                .thenComparingInt(op -> -op.pos().getY()));

        return ops;
    }

    /**
     * Positions on a finished chamber's floor where mini xelles grow: one on the heart at the centre (except
     * in the grand chamber, where the Xelles stands), the rest in a ring away from the entrance.
     */
    public static List<BlockPos> miniXellesSpots(NestJob job, int count) {
        List<BlockPos> spots = new ArrayList<>();
        BlockPos center = job.origin;

        if (!job.grand) {
            spots.add(center.above());
            count--;
        }

        double baseAngle = (job.origin.asLong() & 0xFF) / 255.0D * Mth.TWO_PI;

        for (int i = 0; i < count; i++) {
            double angle = baseAngle + i * Mth.TWO_PI / count;

            for (double factor : new double[]{0.6D, 0.45D, 0.75D}) {
                BlockPos spot = offset(center, angle, job.radius * factor).above();

                if (horizontalDistSqr(spot, job.entry) > 4 * 4) {
                    spots.add(spot);
                    break;
                }
            }
        }

        return spots;
    }

    /** Every position a job opens up (for clearing debris once it finishes). */
    public static List<BlockPos> carvedPositions(NestJob job) {
        List<NestOp> ops = job.ops != null ? job.ops : generateOps(job);
        List<BlockPos> carved = new ArrayList<>();

        for (NestOp op : ops) {
            if (op.kind() == NestOp.Kind.CARVE) {
                carved.add(op.pos());
            }
        }

        return carved;
    }

    private static double horizontalDistSqr(BlockPos a, BlockPos b) {
        double dx = a.getX() - b.getX();
        double dz = a.getZ() - b.getZ();
        return dx * dx + dz * dz;
    }
}
