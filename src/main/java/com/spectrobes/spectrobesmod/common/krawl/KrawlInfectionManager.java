package com.spectrobes.spectrobesmod.common.krawl;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.blocks.krawl.MiniXellesBlock;
import com.spectrobes.spectrobesmod.common.blocks.krawl.MiniXellesBlockEntity;
import com.spectrobes.spectrobesmod.common.config.KrawlConfig;
import com.spectrobes.spectrobesmod.common.entities.krawl.bosses.EntityXelles;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.save_data.KrawlNest;
import com.spectrobes.spectrobesmod.common.save_data.KrawlNest.MiniXellesRecord;
import com.spectrobes.spectrobesmod.common.save_data.KrawlNest.NestJob;
import com.spectrobes.spectrobesmod.common.save_data.KrawlNest.NestOp;
import com.spectrobes.spectrobesmod.common.save_data.SpectrobesWorldSaveData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Runs krawl nests: biomass deposits, founding and reactivation, mini xelles revival, construction jobs,
 * infection spread and the Xelles boss.
 */
public final class KrawlInfectionManager {
    /** Free (already correct) operations the job loop may skip per nest update, per unit of block budget. */
    private static final int SKIP_FACTOR = 16;

    private KrawlInfectionManager() {
    }

    // ---------------------------------------------------------------- ticking

    public static void tickLevel(ServerLevel level) {
        if (level.getGameTime() % KrawlConfig.NEST_TICK_INTERVAL.get() != 0) {
            return;
        }

        SpectrobesWorldSaveData data = SpectrobesWorldSaveData.getWorldData(level);

        if (data.getNests().isEmpty()) {
            return;
        }

        boolean dirty = false;

        for (KrawlNest nest : new ArrayList<>(data.getNests())) {
            dirty |= tickNest(level, nest);
        }

        if (dirty) {
            data.setDirty();
        }
    }

    private static boolean tickNest(ServerLevel level, KrawlNest nest) {
        if (!nest.isActive()) {
            return false;
        }

        boolean dirty = processRevivals(level, nest);
        dirty |= tickBoss(level, nest);

        int budget = KrawlConfig.NEST_BLOCKS_PER_TICK.get();
        int infectionBudget = Math.max(1, budget / 4);
        int used = runJob(level, nest, budget - infectionBudget);
        int infected = spreadInfection(level, nest, infectionBudget + (budget - infectionBudget - used));

        return dirty || used > 0 || infected > 0 || nest.job != null;
    }

    // ---------------------------------------------------------------- lookups

    @Nullable
    public static KrawlNest getNest(ServerLevel level, @Nullable UUID id) {
        return SpectrobesWorldSaveData.getWorldData(level).getNest(id);
    }

    /** Nearest nest (any state) within the configured nest range. */
    @Nullable
    public static KrawlNest findNestInRange(ServerLevel level, BlockPos pos) {
        return SpectrobesWorldSaveData.getWorldData(level)
                .findNearest(pos, KrawlConfig.NEST_RANGE.get(), nest -> true);
    }

    public static int infectionRadius(KrawlNest nest) {
        int radius = KrawlConfig.NEST_INFECTION_RADIUS_BASE.get() + (int) (Math.sqrt(nest.totalBiomass) / 2.0D);
        return Math.min(KrawlConfig.NEST_INFECTION_RADIUS_MAX.get(), radius);
    }

    /** Whether {@code pos} is inside the infected area of an active nest (krawl there do not burn in daylight). */
    public static boolean isInsideActiveNest(ServerLevel level, BlockPos pos) {
        for (KrawlNest nest : SpectrobesWorldSaveData.getWorldData(level).getNests()) {
            if (!nest.isActive()) {
                continue;
            }

            int radius = infectionRadius(nest) + 8;

            for (BlockPos anchor : nest.getAnchors()) {
                double dx = anchor.getX() - pos.getX();
                double dz = anchor.getZ() - pos.getZ();

                if (dx * dx + dz * dz <= radius * radius && Math.abs(anchor.getY() - pos.getY()) <= radius) {
                    return true;
                }
            }
        }

        return false;
    }

    // ---------------------------------------------------------------- founding, biomass, reactivation

    public static void depositBiomass(ServerLevel level, KrawlNest nest, double amount) {
        if (!nest.isActive()) {
            reactivate(level, nest);
        }

        nest.addBiomass(amount);
        SpectrobesWorldSaveData.getWorldData(level).setDirty();
    }

    /**
     * Founds a nest with its first mini xelles at {@code spot} (an air block on solid ground).
     * Returns null if another nest is in range or the spot is unusable.
     */
    @Nullable
    public static KrawlNest foundNest(ServerLevel level, BlockPos spot, double biomass) {
        SpectrobesWorldSaveData data = SpectrobesWorldSaveData.getWorldData(level);

        if (findNestInRange(level, spot) != null || !level.isLoaded(spot)) {
            return null;
        }

        KrawlNest nest = new KrawlNest(spot, level.getGameTime());
        nest.addBiomass(biomass);

        level.setBlock(spot.below(), SpectrobesBlocks.krawl_heart.get().defaultBlockState(), Block.UPDATE_ALL);

        if (!placeMiniXelles(level, nest, spot, 0)) {
            return null;
        }

        for (BlockPos pos : BlockPos.betweenClosed(spot.offset(-2, -2, -2), spot.offset(2, 1, 2))) {
            infectBlock(level, pos.immutable());
        }

        data.addNest(nest);
        level.playSound(null, spot, SoundEvents.SCULK_CATALYST_BLOOM, SoundSource.HOSTILE, 2.0F, 0.6F);
        SpectrobesInfo.LOGGER.debug("Krawl nest {} founded at {}", nest.id, spot);
        return nest;
    }

    /** A sky-visible air block on solid natural ground near {@code near}, or null. */
    @Nullable
    public static BlockPos findFoundingSpot(ServerLevel level, BlockPos near, RandomSource random) {
        for (int i = 0; i < 16; i++) {
            int x = near.getX() + random.nextInt(25) - 12;
            int z = near.getZ() + random.nextInt(25) - 12;

            if (!level.hasChunkAt(new BlockPos(x, near.getY(), z))) {
                continue;
            }

            int y = level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z);
            BlockPos spot = new BlockPos(x, y, z);
            BlockPos ground = spot.below();
            BlockState groundState = level.getBlockState(ground);
            BlockState spotState = level.getBlockState(spot);

            if ((spotState.isAir() || KrawlTerrain.isEdiblePlant(spotState))
                    && spotState.getFluidState().isEmpty()
                    && groundState.getFluidState().isEmpty()
                    && KrawlTerrain.getInfectedState(groundState) != null
                    && level.canSeeSky(spot)) {
                return spot;
            }
        }

        return null;
    }

    public static void reactivate(ServerLevel level, KrawlNest nest) {
        nest.state = KrawlNest.State.ACTIVE;
        long now = level.getGameTime();

        for (MiniXellesRecord record : nest.miniXelles.values()) {
            if (record.dead) {
                // Every dead mini xelles waits the full revive time, so a vortex returning to a nest that was just
                // cleared cannot bring the surface one straight back.
                record.reviveAt = now + reviveTicks(record);
            }
        }

        processRevivals(level, nest);
        SpectrobesWorldSaveData.getWorldData(level).setDirty();
        level.playSound(null, nest.heart, SoundEvents.SCULK_CATALYST_BLOOM, SoundSource.HOSTILE, 2.0F, 0.4F);
    }

    public static void deactivate(ServerLevel level, KrawlNest nest) {
        nest.state = KrawlNest.State.INACTIVE;
        SpectrobesWorldSaveData.getWorldData(level).setDirty();
    }

    private static void checkInactive(ServerLevel level, KrawlNest nest) {
        if (nest.isActive() && nest.allMiniXellesDead() && nest.bossState != KrawlNest.BossState.ALIVE) {
            deactivate(level, nest);
            level.playSound(null, nest.heart, SoundEvents.SCULK_SHRIEKER_SHRIEK, SoundSource.HOSTILE, 3.0F, 0.5F);
        }
    }

    // ---------------------------------------------------------------- mini xelles

    private static long reviveTicks(MiniXellesRecord record) {
        return KrawlConfig.MINI_XELLES_REVIVE_TICKS.get();
    }

    public static void onMiniXellesDefeated(ServerLevel level, @Nullable UUID nestId, BlockPos pos) {
        KrawlNest nest = getNest(level, nestId);

        if (nest == null) {
            return;
        }

        MiniXellesRecord record = nest.miniXelles.get(pos);

        if (record == null || record.dead) {
            return;
        }

        record.dead = true;
        record.reviveAt = level.getGameTime() + reviveTicks(record);
        checkInactive(level, nest);
        SpectrobesWorldSaveData.getWorldData(level).setDirty();
    }

    /** The block was removed (creative break, explosion, piston...). An alive one counts as defeated. */
    public static void onMiniXellesRemoved(ServerLevel level, @Nullable UUID nestId, BlockPos pos) {
        onMiniXellesDefeated(level, nestId, pos);
    }

    @Nullable
    public static MiniXellesRecord getMiniXellesRecord(ServerLevel level, @Nullable UUID nestId, BlockPos pos) {
        KrawlNest nest = getNest(level, nestId);
        return nest == null ? null : nest.miniXelles.get(pos);
    }

    private static boolean placeMiniXelles(ServerLevel level, KrawlNest nest, BlockPos pos, int tier) {
        BlockState state = level.getBlockState(pos);

        if (!(state.isAir() || KrawlTerrain.isEdiblePlant(state)) || !level.getBlockState(pos.above()).isAir()) {
            return false;
        }

        if (!level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)) {
            return false;
        }

        level.setBlock(pos, SpectrobesBlocks.mini_xelles_block.get().defaultBlockState(), Block.UPDATE_ALL);

        if (level.getBlockEntity(pos) instanceof MiniXellesBlockEntity blockEntity) {
            blockEntity.bindToNest(nest.id, tier);
        }

        nest.miniXelles.put(pos.immutable(), new MiniXellesRecord(pos, tier));
        return true;
    }

    private static boolean processRevivals(ServerLevel level, KrawlNest nest) {
        if (!nest.isActive()) {
            return false;
        }

        // While the Xelles is defeated its mini xelles stay dead; they come back once it has regrown.
        if (nest.bossState == KrawlNest.BossState.DEFEATED) {
            return false;
        }

        long now = level.getGameTime();
        boolean changed = false;

        for (MiniXellesRecord record : nest.miniXelles.values()) {
            if (!record.dead || now < record.reviveAt || !level.isLoaded(record.pos)) {
                continue;
            }

            BlockState state = level.getBlockState(record.pos);

            if (state.is(SpectrobesBlocks.mini_xelles_block.get())) {
                if (level.getBlockEntity(record.pos) instanceof MiniXellesBlockEntity blockEntity) {
                    blockEntity.bindToNest(nest.id, record.tier);
                    blockEntity.revive();
                }
            } else {
                // The dead one was mined: regrow it if there is room.
                if (!state.isAir() && !KrawlTerrain.isEdiblePlant(state)) {
                    continue;
                }

                level.setBlock(record.pos, SpectrobesBlocks.mini_xelles_block.get().defaultBlockState(), Block.UPDATE_ALL);

                if (level.getBlockEntity(record.pos) instanceof MiniXellesBlockEntity blockEntity) {
                    blockEntity.bindToNest(nest.id, record.tier);
                }
            }

            record.dead = false;
            level.sendParticles(ParticleTypes.SCULK_SOUL, record.pos.getX() + 0.5D, record.pos.getY() + 1.0D, record.pos.getZ() + 0.5D, 8, 0.3D, 0.3D, 0.3D, 0.02D);
            changed = true;
        }

        return changed;
    }

    // ---------------------------------------------------------------- boss

    private static boolean tickBoss(ServerLevel level, KrawlNest nest) {
        KrawlNest.Chamber grand = nest.getGrandChamber();

        if (grand == null) {
            return false;
        }

        BlockPos spawnPos = grand.center.above();

        if (nest.bossState == KrawlNest.BossState.ALIVE) {
            // Removed without dying (e.g. peaceful difficulty): treat it as defeated so it can regrow.
            if (nest.bossUuid != null && level.isPositionEntityTicking(spawnPos) && level.getEntity(nest.bossUuid) == null) {
                onBossDefeated(level, nest);
                return true;
            }

            return false;
        }

        if (nest.bossState == KrawlNest.BossState.DEFEATED
                && nest.biomass >= KrawlConfig.NEST_BOSS_BIOMASS_COST.get()
                && level.isPositionEntityTicking(spawnPos)) {
            if (spawnBoss(level, nest, spawnPos)) {
                nest.trySpend(KrawlConfig.NEST_BOSS_BIOMASS_COST.get());
                return true;
            }
        }

        return false;
    }

    private static boolean spawnBoss(ServerLevel level, KrawlNest nest, BlockPos pos) {
        if (!level.isPositionEntityTicking(pos)) {
            return false;
        }

        EntityXelles xelles = KrawlEntities.ENTITY_XELLES.get().spawn(level, null, null, pos, MobSpawnType.MOB_SUMMONED, false, false);

        if (xelles == null) {
            return false;
        }

        xelles.bindToNest(nest.id);
        nest.bossState = KrawlNest.BossState.ALIVE;
        nest.bossUuid = xelles.getUUID();
        level.playSound(null, pos, SoundEvents.WARDEN_EMERGE, SoundSource.HOSTILE, 3.0F, 0.6F);
        return true;
    }

    public static void onBossDied(ServerLevel level, @Nullable UUID nestId) {
        KrawlNest nest = getNest(level, nestId);

        if (nest == null) {
            return;
        }

        onBossDefeated(level, nest);
        SpectrobesWorldSaveData.getWorldData(level).setDirty();
    }

    /** The Xelles is gone: every mini xelles in the nest dies with it and stays dead until it regrows. */
    private static void onBossDefeated(ServerLevel level, KrawlNest nest) {
        nest.bossState = KrawlNest.BossState.DEFEATED;
        nest.bossUuid = null;
        long now = level.getGameTime();

        for (MiniXellesRecord record : nest.miniXelles.values()) {
            if (record.dead) {
                continue;
            }

            record.dead = true;
            record.reviveAt = now + reviveTicks(record);

            // Unloaded ones catch up through their sync with the nest once their chunk loads.
            if (level.isLoaded(record.pos) && level.getBlockEntity(record.pos) instanceof MiniXellesBlockEntity blockEntity) {
                blockEntity.kill(level);
            }
        }

        checkInactive(level, nest);
    }

    // ---------------------------------------------------------------- construction

    /** Runs the current construction job. Returns the number of blocks changed. */
    private static int runJob(ServerLevel level, KrawlNest nest, int budget) {
        if (budget <= 0) {
            return 0;
        }

        if (nest.job == null) {
            if (!level.isLoaded(nest.heart)) {
                return 0;
            }

            nest.job = KrawlNestBuilder.planNextJob(level, nest);

            if (nest.job == null) {
                return 0;
            }
        }

        NestJob job = nest.job;

        if (job.ops == null) {
            job.ops = KrawlNestBuilder.generateOps(job);
        }

        double cost = KrawlConfig.NEST_BIOMASS_PER_CARVED_BLOCK.get();
        int changed = 0;
        int iterations = 0;
        int maxIterations = budget * SKIP_FACTOR;

        while (changed < budget && iterations++ < maxIterations && job.progress < job.ops.size()) {
            NestOp op = job.ops.get(job.progress);

            if (!level.isLoaded(op.pos())) {
                break;
            }

            BlockState target = targetState(level, job, op);

            if (target != null) {
                if (nest.biomass < cost) {
                    break;
                }

                level.setBlock(op.pos(), target, Block.UPDATE_ALL);
                nest.trySpend(cost);
                changed++;
            }

            job.progress++;
        }

        if (job.progress >= job.ops.size()) {
            completeJob(level, nest, job);
        }

        return changed;
    }

    /** The block an operation should place, or null if nothing needs to change. */
    @Nullable
    private static BlockState targetState(ServerLevel level, NestJob job, NestOp op) {
        BlockPos pos = op.pos();
        BlockState state = level.getBlockState(pos);

        if (KrawlTerrain.isImmune(level, pos, state)) {
            return null;
        }

        return switch (op.kind()) {
            // Membrane is kept so a shaft's ramp survives when a chamber opens around its bottom.
            case CARVE -> state.isAir() || state.is(SpectrobesBlocks.krawl_membrane.get()) ? null : Blocks.AIR.defaultBlockState();
            case FLOOR -> state.is(SpectrobesBlocks.krawl_membrane.get()) ? null : KrawlTerrain.floorBlock();
            case WALL -> {
                if (KrawlTerrain.isKrawlBlock(state)) {
                    yield null;
                }

                boolean open = state.isAir() || state.canBeReplaced();

                if (open && pos.getY() >= level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ())) {
                    // Do not build tunnel walls up into open air at the surface.
                    yield null;
                }

                if (state.isAir() && isNestSpace(level, pos)) {
                    // Never wall off another part of the nest (a corridor, shaft or chamber already dug).
                    yield null;
                }

                yield KrawlTerrain.wallBlock(pos, job.type == NestJob.Type.CHAMBER);
            }
        };
    }

    private static void completeJob(ServerLevel level, KrawlNest nest, NestJob job) {
        clearDebris(level, job);

        if (job.type == NestJob.Type.SHAFT) {
            nest.shaftTier = job.tier;
            nest.nextChamberCenter = KrawlNestBuilder.shaftExitChamberCenter(job);
            nest.nextChamberEntry = KrawlNestBuilder.shaftExitChamberEntry(job);
        } else {
            nest.chambers.add(new KrawlNest.Chamber(job.tier, job.origin, job.radius, job.height, job.grand, job.entry));
            level.setBlock(job.origin, SpectrobesBlocks.krawl_heart.get().defaultBlockState(), Block.UPDATE_ALL);

            int count = KrawlNestBuilder.miniXellesCount(job.tier, job.grand);

            for (BlockPos spot : KrawlNestBuilder.miniXellesSpots(job, count)) {
                placeChamberMiniXelles(level, nest, spot, job.tier);
            }

            if (job.grand) {
                spawnBoss(level, nest, job.origin.above());
            }

            level.playSound(null, job.origin, SoundEvents.SCULK_CATALYST_BLOOM, SoundSource.HOSTILE, 3.0F, 0.5F);
        }

        nest.job = null;
    }

    /**
     * Air that belongs to the nest: open space with a membrane floor (or a heart) somewhere below it, as in
     * corridors, shafts and chambers. Natural caves have no membrane floor.
     */
    private static boolean isNestSpace(ServerLevel level, BlockPos pos) {
        BlockPos.MutableBlockPos cursor = pos.mutable();

        for (int i = 0; i <= KrawlNestBuilder.GRAND_HEIGHT + 1; i++) {
            cursor.move(Direction.DOWN);
            BlockState below = level.getBlockState(cursor);

            if (below.isAir()) {
                continue;
            }

            return below.is(SpectrobesBlocks.krawl_membrane.get())
                    || below.is(SpectrobesBlocks.krawl_heart.get())
                    || below.is(SpectrobesBlocks.mini_xelles_block.get());
        }

        return false;
    }

    /** Removes gravel, sand and anything else that fell or flowed into the space a job opened. */
    private static void clearDebris(ServerLevel level, NestJob job) {
        List<BlockPos> carved = KrawlNestBuilder.carvedPositions(job);

        if (carved.isEmpty()) {
            return;
        }

        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, minZ = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE, maxZ = Integer.MIN_VALUE;

        for (BlockPos pos : carved) {
            minX = Math.min(minX, pos.getX());
            minY = Math.min(minY, pos.getY());
            minZ = Math.min(minZ, pos.getZ());
            maxX = Math.max(maxX, pos.getX());
            maxY = Math.max(maxY, pos.getY());
            maxZ = Math.max(maxZ, pos.getZ());

            if (!level.isLoaded(pos)) {
                continue;
            }

            BlockState state = level.getBlockState(pos);

            if (!state.isAir()
                    && !state.is(SpectrobesBlocks.krawl_membrane.get())
                    && !KrawlTerrain.isImmune(level, pos, state)) {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
            }
        }

        AABB bounds = new AABB(minX, minY, minZ, maxX + 1, maxY + 1, maxZ + 1);

        for (FallingBlockEntity falling : level.getEntitiesOfClass(FallingBlockEntity.class, bounds)) {
            falling.discard();
        }
    }

    /** Places a chamber mini xelles, clearing debris from its spot and making sure it has floor under it. */
    private static void placeChamberMiniXelles(ServerLevel level, KrawlNest nest, BlockPos spot, int tier) {
        if (!level.isLoaded(spot)) {
            return;
        }

        for (BlockPos pos : new BlockPos[]{spot, spot.above()}) {
            BlockState state = level.getBlockState(pos);

            if (!state.isAir() && !KrawlTerrain.isImmune(level, pos, state)) {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
            }
        }

        BlockPos floor = spot.below();
        BlockState floorState = level.getBlockState(floor);

        if (!floorState.is(SpectrobesBlocks.krawl_heart.get())
                && !floorState.isFaceSturdy(level, floor, Direction.UP)
                && !KrawlTerrain.isImmune(level, floor, floorState)) {
            level.setBlock(floor, KrawlTerrain.floorBlock(), Block.UPDATE_ALL);
        }

        if (!placeMiniXelles(level, nest, spot, tier)) {
            SpectrobesInfo.LOGGER.debug("Could not place a mini xelles at {} for nest {}", spot, nest.id);
        }
    }

    // ---------------------------------------------------------------- infection

    /** Converts natural blocks around the nest's anchors. Returns the number converted. */
    private static int spreadInfection(ServerLevel level, KrawlNest nest, int budget) {
        double cost = KrawlConfig.NEST_BIOMASS_PER_INFECTED_BLOCK.get();
        RandomSource random = level.getRandom();
        List<BlockPos> anchors = nest.getAnchors();
        int radius = infectionRadius(nest);
        int converted = 0;

        for (int attempt = 0; attempt < budget * 4 && converted < budget; attempt++) {
            if (nest.biomass < cost) {
                break;
            }

            BlockPos anchor = anchors.get(random.nextInt(anchors.size()));
            boolean success = anchor.equals(nest.heart)
                    ? spreadOnSurface(level, nest.heart, radius, random)
                    : spreadUnderground(level, anchor, radius, random);

            if (success) {
                nest.trySpend(cost);
                converted++;
            }
        }

        return converted;
    }

    /** Random walk across the infected surface patch until it reaches an uninfected block. */
    private static boolean spreadOnSurface(ServerLevel level, BlockPos heart, int radius, RandomSource random) {
        BlockPos current = heart.below();

        for (int step = 0; step < radius * 2; step++) {
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            int x = current.getX() + direction.getStepX();
            int z = current.getZ() + direction.getStepZ();
            BlockPos column = new BlockPos(x, current.getY(), z);

            if (!level.isLoaded(column)) {
                return false;
            }

            BlockPos top = new BlockPos(x, level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z) - 1, z);
            double dx = x - heart.getX();
            double dz = z - heart.getZ();

            if (dx * dx + dz * dz > radius * radius || Math.abs(top.getY() - current.getY()) > 3) {
                return false;
            }

            BlockState state = level.getBlockState(top);

            if (KrawlTerrain.isKrawlBlock(state)) {
                if (eatPlant(level, top.above())) {
                    return true;
                }

                current = top;
                continue;
            }

            return infectBlock(level, top);
        }

        return false;
    }

    private static boolean spreadUnderground(ServerLevel level, BlockPos anchor, int radius, RandomSource random) {
        BlockPos pos = anchor.offset(
                random.nextInt(radius * 2 + 1) - radius,
                random.nextInt(radius + 1) - radius / 2,
                random.nextInt(radius * 2 + 1) - radius);

        if (!level.isLoaded(pos)) {
            return false;
        }

        for (Direction direction : Direction.values()) {
            if (KrawlTerrain.isKrawlBlock(level.getBlockState(pos.relative(direction)))) {
                return infectBlock(level, pos);
            }
        }

        return false;
    }

    private static boolean infectBlock(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        BlockState infected = KrawlTerrain.getInfectedState(state);

        if (infected == null || KrawlTerrain.isImmune(level, pos, state)) {
            return false;
        }

        level.setBlock(pos, infected, Block.UPDATE_ALL);
        eatPlant(level, pos.above());
        return true;
    }

    private static boolean eatPlant(Level level, BlockPos pos) {
        if (KrawlTerrain.isEdiblePlant(level.getBlockState(pos))) {
            level.destroyBlock(pos, false);
            return true;
        }

        return false;
    }

    // ---------------------------------------------------------------- debug

    public static String describe(ServerLevel level, KrawlNest nest) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Nest %s at %d %d %d: %s, biomass %.1f (total %.1f), radius %d%n",
                nest.id.toString().substring(0, 8), nest.heart.getX(), nest.heart.getY(), nest.heart.getZ(),
                nest.state, nest.biomass, nest.totalBiomass, infectionRadius(nest)));
        builder.append(String.format("Tiers %d/%d, shaft tier %d, mini xelles %d/%d alive, boss %s%n",
                nest.getDeepestChamberTier(), KrawlNestBuilder.tierCount(nest), nest.shaftTier,
                nest.aliveMiniXelles(), nest.miniXelles.size(), nest.bossState));

        if (nest.job != null) {
            int total = nest.job.ops != null ? nest.job.ops.size() : KrawlNestBuilder.generateOps(nest.job).size();
            builder.append(String.format("Building %s for tier %d: %d/%d%n", nest.job.type, nest.job.tier, nest.job.progress, total));
        }

        return builder.toString().trim();
    }

    /** Marks an entity's death with a burst of sculk particles. Used when krawl merge or consume. */
    public static void puff(ServerLevel level, Entity entity) {
        level.sendParticles(ParticleTypes.SCULK_SOUL, entity.getX(), entity.getY() + entity.getBbHeight() / 2.0D, entity.getZ(), 12, 0.4D, 0.4D, 0.4D, 0.02D);
    }
}
