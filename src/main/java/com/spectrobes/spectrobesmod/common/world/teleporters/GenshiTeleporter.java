package com.spectrobes.spectrobesmod.common.world.teleporters;

import com.spectrobes.spectrobesmod.common.blocks.machines.PlanetaryTeleporterBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class GenshiTeleporter {

    private static final int DAYLIGHT_DIM_START_Y = 61;
    private static final int SEARCH_RADIUS = 10;
    private static final int SEARCH_VERTICAL_RANGE = 10;
    private static final int MAX_UPWARD_SEARCH = 256;

    private final BlockPos originPos;
    private final boolean isToDaylightDim;

    public GenshiTeleporter(BlockPos pos, boolean isToDaylightDim) {
        this.originPos = pos.immutable();
        this.isToDaylightDim = isToDaylightDim;
    }

    public DimensionTransition createTransition(Entity entity, ServerLevel destLevel) {
        BlockPos destinationBase = getDestinationBasePos();
        BlockPos safePos = findSafeDestination(destLevel, destinationBase);

        Vec3 destination = new Vec3(
                safePos.getX() + 0.5D,
                safePos.getY(),
                safePos.getZ() + 0.5D
        );

        return new DimensionTransition(
                destLevel,
                destination,
                entity.getDeltaMovement(),
                entity.getYRot(),
                entity.getXRot(),
                false,
                DimensionTransition.DO_NOTHING
        );
    }

    private BlockPos getDestinationBasePos() {
        int y = this.isToDaylightDim ? DAYLIGHT_DIM_START_Y : this.originPos.getY();
        return new BlockPos(this.originPos.getX(), y, this.originPos.getZ());
    }

    private BlockPos findSafeDestination(ServerLevel destLevel, BlockPos destinationBase) {
        BlockPos upwardPos = destinationBase;

        for (int i = 0; i < MAX_UPWARD_SEARCH && upwardPos.getY() < destLevel.getMaxBuildHeight() - 2; i++) {
            if (isSafeArrivalPosition(destLevel, upwardPos)) {
                ensureNearbyTeleporter(destLevel, upwardPos);
                return upwardPos;
            }

            upwardPos = upwardPos.above();
        }

        for (BlockPos checkPos : BlockPos.betweenClosed(
                destinationBase.offset(-SEARCH_RADIUS, -SEARCH_VERTICAL_RANGE, -SEARCH_RADIUS),
                destinationBase.offset(SEARCH_RADIUS, SEARCH_VERTICAL_RANGE, SEARCH_RADIUS)
        )) {
            BlockPos immutablePos = checkPos.immutable();

            if (isSafeArrivalPosition(destLevel, immutablePos)) {
                ensureNearbyTeleporter(destLevel, immutablePos);
                return immutablePos;
            }
        }

        ensureNearbyTeleporter(destLevel, destinationBase);
        return destinationBase;
    }

    private boolean isSafeArrivalPosition(ServerLevel level, BlockPos pos) {
        if (pos.getY() <= level.getMinBuildHeight() || pos.getY() >= level.getMaxBuildHeight() - 2) {
            return false;
        }

        BlockPos below = pos.below();

        return isReplaceableForArrival(level.getBlockState(pos))
                && isReplaceableForArrival(level.getBlockState(pos.above()))
                && level.getBlockState(below).isFaceSturdy(level, below, net.minecraft.core.Direction.UP);
    }

    private boolean isReplaceableForArrival(BlockState state) {
        return state.isAir() || state.getFluidState().is(Fluids.WATER);
    }

    private void ensureNearbyTeleporter(ServerLevel destLevel, BlockPos aroundPos) {
        AABB searchBox = new AABB(aroundPos).inflate(SEARCH_RADIUS, SEARCH_VERTICAL_RANGE, SEARCH_RADIUS);

        boolean hasTeleporterNearby = destLevel.getBlockStates(searchBox)
                .anyMatch(blockState -> blockState.getBlock() instanceof PlanetaryTeleporterBlock);

        if (!hasTeleporterNearby) {
            // If you want to generate the destination teleporter, do it here.
            // Example:
            // destLevel.setBlock(
            //         aroundPos.above(3),
            //         SpectrobesBlocks.planetary_teleporter.get().defaultBlockState(),
            //         3
            // );
        }
    }
}