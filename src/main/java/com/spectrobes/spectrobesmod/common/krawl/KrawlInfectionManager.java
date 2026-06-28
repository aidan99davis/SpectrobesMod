package com.spectrobes.spectrobesmod.common.krawl;

import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.save_data.KrawlNest;
import com.spectrobes.spectrobesmod.common.save_data.SpectrobesWorldSaveData;
import com.spectrobes.spectrobesmod.common.world.WorldGenUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;

public final class KrawlInfectionManager {
    private static final int TICKS_PER_SECOND = 20;
    private static final int TICKS_PER_DAY = 24000;

    private static final int NEST_TICK_INTERVAL = TICKS_PER_SECOND;
    private static final int VORTEX_ABSORB_INTERVAL = TICKS_PER_SECOND * 5;
    private static final int TERRAIN_SPREAD_INTERVAL = TICKS_PER_SECOND * 8;

    private KrawlInfectionManager() {
    }

    public static void tickLevel(ServerLevel level) {
        if (level.isClientSide()) {
            return;
        }

        long gameTime = level.getGameTime();

        if (gameTime % NEST_TICK_INTERVAL != 0) {
            return;
        }

        SpectrobesWorldSaveData data = SpectrobesWorldSaveData.getWorldData(level);

        /*
         * Rename this if your SpectrobesWorldSaveData accessor has a different name.
         *
         * Expected:
         * public List<KrawlNest> getNests()
         */
        List<KrawlNest> nests = data.getNests();

        if (nests == null || nests.isEmpty()) {
            return;
        }

        boolean dirty = false;

        for (KrawlNest nest : nests) {
            if (nest == null || !nest.isAlive()) {
                continue;
            }

            if (!isNestInLevel(nest, level)) {
                continue;
            }

            dirty |= tickNest(level, data, nest, gameTime);
        }

        if (dirty) {
            data.setDirty();
        }
    }

    public static boolean tryCreateNestFromVortexCluster(EntityVortex sourceVortex) {
        if (sourceVortex == null || sourceVortex.level().isClientSide()) {
            return false;
        }

        if (!(sourceVortex.level() instanceof ServerLevel level)) {
            return false;
        }

        SpectrobesWorldSaveData data = SpectrobesWorldSaveData.getWorldData(level);

        if (!data.canSpawnNest(sourceVortex.blockPosition())) {
            return false;
        }

        AABB searchBox = sourceVortex.getBoundingBox().inflate(15.0D, 3.0D, 15.0D);

        List<EntityVortex> eligibleVortexes = level.getEntitiesOfClass(
                EntityVortex.class,
                searchBox,
                vortex -> vortex != null
                        && vortex.isAlive()
                        && vortex.getAge() >= 1
        );

        if (eligibleVortexes.size() < 3) {
            moveTowardNearestEligibleVortex(sourceVortex, eligibleVortexes);
            return false;
        }

        EntityVortex leader = eligibleVortexes.stream()
                .min(Comparator.comparingInt(Entity::getId))
                .orElse(null);

        if (leader != sourceVortex) {
            return false;
        }

        BlockPos nestPos = findGroundPosition(level, sourceVortex.blockPosition());

        if (!data.canSpawnNest(nestPos)) {
            return false;
        }

        int extraVortexes = Math.max(0, eligibleVortexes.size() - 3);

        for (EntityVortex vortex : eligibleVortexes) {
            vortex.remove(Entity.RemovalReason.DISCARDED);
        }

        WorldGenUtils.generateDome(
                level,
                RandomSource.create(),
                nestPos,
                9,
                8,
                Blocks.AIR.defaultBlockState()
        );

        Entity xelles = KrawlEntities.ENTITY_XELLES.get().spawn(
                level,
                null,
                null,
                nestPos,
                MobSpawnType.MOB_SUMMONED,
                false,
                false
        );

        KrawlNest nest = new KrawlNest(nestPos, level.dimension().toString(), xelles.getUUID());
        nest.absorbVortexes(extraVortexes);

        data.addNest(nest);
        data.setDirty();

        return xelles != null;
    }

    private static boolean tickNest(ServerLevel level, SpectrobesWorldSaveData data, KrawlNest nest, long gameTime) {
        boolean dirty = false;

        dirty |= updateNestStage(nest);

        if (shouldRunNestInterval(nest, gameTime, VORTEX_ABSORB_INTERVAL)) {
            dirty |= absorbNearbyVortexes(level, nest);
        }

        if (shouldRunNestInterval(nest, gameTime, TERRAIN_SPREAD_INTERVAL)) {
            dirty |= spreadNestTerrain(level, nest);
        }

        return dirty;
    }

    /**
     * KrawlInfectionManager.tickLevel only processes nests once every NEST_TICK_INTERVAL ticks.
     *
     * Because of that, exact modulo checks are unsafe unless the nest offset happens
     * to align with the outer tick cadence.
     *
     * This method gives each nest a deterministic offset, but accepts any tick inside
     * the current manager tick window.
     */
    private static boolean shouldRunNestInterval(KrawlNest nest, long gameTime, int interval) {
        if (interval <= 0) {
            return false;
        }

        int offset = getNestTickOffset(nest, interval);
        long shiftedTime = gameTime + offset;
        long remainder = Math.floorMod(shiftedTime, interval);

        return remainder < NEST_TICK_INTERVAL;
    }

    private static int getNestTickOffset(KrawlNest nest, int interval) {
        if (nest.position == null || interval <= 0) {
            return 0;
        }

        return Math.floorMod(nest.position.asLong(), interval);
    }

    private static boolean updateNestStage(KrawlNest nest) {
        int oldStage = nest.stage;
        int absorbed = nest.getAbsorbedVortexes();

        if (absorbed >= 12) {
            nest.setStage(3);
        } else if (absorbed >= 5) {
            nest.setStage(2);
        } else if (nest.stage <= 0) {
            nest.setStage(1);
        }

        return oldStage != nest.stage;
    }

    private static boolean absorbNearbyVortexes(ServerLevel level, KrawlNest nest) {
        int radius = getNestRadius(nest);
        int verticalRadius = Math.max(6, radius / 3);

        AABB absorbBox = AABB.ofSize(
                Vec3.atCenterOf(nest.position),
                radius * 2.0D,
                verticalRadius * 2.0D,
                radius * 2.0D
        );

        List<EntityVortex> vortexes = level.getEntitiesOfClass(
                EntityVortex.class,
                absorbBox,
                vortex -> vortex != null
                        && vortex.isAlive()
                        && vortex.getAge() >= 1
        );

        if (vortexes.isEmpty()) {
            return false;
        }

        int absorbed = 0;

        for (EntityVortex vortex : vortexes) {
            if (vortex.blockPosition().distSqr(nest.position) > radius * radius) {
                continue;
            }

            vortex.remove(Entity.RemovalReason.DISCARDED);
            absorbed++;
        }

        if (absorbed <= 0) {
            return false;
        }

        nest.absorbVortexes(absorbed);
        updateNestStage(nest);
        return true;
    }

    private static boolean spreadNestTerrain(ServerLevel level, KrawlNest nest) {
        RandomSource random = level.getRandom();

        int attempts = getSpreadAttempts(nest);
        int radius = getNestRadius(nest);

        boolean changed = false;

        for (int i = 0; i < attempts; i++) {
            BlockPos target = getRandomPositionNearNest(random, nest.position, radius);

            if (!isValidSpreadTarget(level, target)) {
                continue;
            }

            BlockState infectedState = getInfectedBlockState(level, target);

            if (infectedState == null) {
                continue;
            }

            level.setBlock(target, infectedState, 3);
            changed = true;
        }

        return changed;
    }

    private static BlockState getInfectedBlockState(ServerLevel level, BlockPos target) {
        BlockState currentState = level.getBlockState(target);

        if (currentState.is(Blocks.GRASS_BLOCK)
                || currentState.is(Blocks.DIRT)
                || currentState.is(Blocks.COARSE_DIRT)
                || currentState.is(Blocks.ROOTED_DIRT)
                || currentState.is(Blocks.STONE)
                || currentState.is(Blocks.DEEPSLATE)
                || currentState.is(Blocks.SAND)
                || currentState.is(Blocks.RED_SAND)
                || currentState.is(Blocks.GRAVEL)) {
            return SpectrobesBlocks.krawl_nest.get().defaultBlockState();
        }

        return null;
    }

    private static boolean isValidSpreadTarget(ServerLevel level, BlockPos target) {
        if (!level.isLoaded(target)) {
            return false;
        }

        BlockState state = level.getBlockState(target);

        if (state.isAir()) {
            return false;
        }

        if (state.is(Blocks.BEDROCK)) {
            return false;
        }

        if (state.getDestroySpeed(level, target) < 0.0F) {
            return false;
        }

        return true;
    }

    private static BlockPos getRandomPositionNearNest(RandomSource random, BlockPos origin, int radius) {
        int x = origin.getX() + random.nextInt(radius * 2 + 1) - radius;
        int y = origin.getY() + random.nextInt(13) - 6;
        int z = origin.getZ() + random.nextInt(radius * 2 + 1) - radius;

        return new BlockPos(x, y, z);
    }

    private static int getNestRadius(KrawlNest nest) {
        return switch (nest.stage) {
            case 1 -> 14 + nest.getAbsorbedVortexes();
            case 2 -> 26 + nest.getAbsorbedVortexes();
            case 3 -> 42 + nest.getAbsorbedVortexes();
            default -> 10;
        };
    }

    private static int getSpreadAttempts(KrawlNest nest) {
        return switch (nest.stage) {
            case 1 -> 4;
            case 2 -> 8;
            case 3 -> 16;
            default -> 1;
        };
    }

    private static BlockPos findGroundPosition(Level level, BlockPos position) {
        BlockPos result = position;

        while (result.getY() > level.getMinBuildHeight()
                && level.getBlockState(result.below()).isAir()) {
            result = result.below();
        }

        return result;
    }

    private static void moveTowardNearestEligibleVortex(EntityVortex sourceVortex, List<EntityVortex> eligibleVortexes) {
        if (eligibleVortexes == null || eligibleVortexes.isEmpty()) {
            return;
        }

        EntityVortex target = eligibleVortexes.stream()
                .filter(vortex -> vortex != sourceVortex)
                .min(Comparator.comparingDouble(vortex -> vortex.distanceToSqr(sourceVortex)))
                .orElse(null);

        if (target == null) {
            return;
        }

        sourceVortex.getMoveControl().setWantedPosition(
                target.getX(),
                target.getY(),
                target.getZ(),
                1.0D
        );
    }

    private static boolean isNestInLevel(KrawlNest nest, ServerLevel level) {
        if (nest.dimension == null) {
            return false;
        }

        String oldStyleDimension = level.dimension().toString();
        String cleanDimension = level.dimension().location().toString();

        return nest.dimension.equals(oldStyleDimension) || nest.dimension.equals(cleanDimension);
    }
}
