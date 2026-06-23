package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.child;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class ChildFormSearchGoal extends Goal {
    private static final int SEARCH_RADIUS = 8;
    private static final int WANDER_HORIZONTAL_RANGE = 12;
    private static final int WANDER_VERTICAL_RANGE = 6;

    private static final int SCAN_INTERVAL_TICKS = 20;
    private static final int WANDER_REPATH_INTERVAL_TICKS = 60;

    private static final double MOVE_SPEED = 0.5D;
    private static final double REACHED_TARGET_DISTANCE_SQR = 2.0D;

    private final EntitySpectrobe entity;

    private BlockPos target;
    private int scanCooldown;
    private int wanderCooldown;

    public ChildFormSearchGoal(EntitySpectrobe spectrobe) {
        this.entity = spectrobe;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return entity.getStage() == SpectrobeProperties.Stage.CHILD && entity.isSearching();
    }

    @Override
    public boolean canContinueToUse() {
        return entity.getStage() == SpectrobeProperties.Stage.CHILD && entity.isSearching();
    }

    @Override
    public void start() {
        target = null;
        scanCooldown = 0;
        wanderCooldown = 0;

        findTargetOrWander();
    }

    @Override
    public void stop() {
        target = null;
        scanCooldown = 0;
        wanderCooldown = 0;
        entity.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (!entity.isSearching()) {
            return;
        }

        if (target != null) {
            if (entity.distanceToSqr(target.getX(), target.getY(), target.getZ()) < REACHED_TARGET_DISTANCE_SQR) {
                target = null;
                entity.getNavigation().stop();

                // Reached the target block — sit and wait for the player.
                entity.getOwner().sendSystemMessage(Component.literal("Your spectrobe has found something!"));
                entity.setState(1);
                return;
            }

            if (entity.getNavigation().isDone()) {
                moveToTarget();
            }

            return;
        }

        if (scanCooldown > 0) {
            scanCooldown--;
        }

        if (wanderCooldown > 0) {
            wanderCooldown--;
        }

        if (scanCooldown <= 0) {
            BlockPos foundTarget = getClosestMineralBlockInArea();

            scanCooldown = SCAN_INTERVAL_TICKS;

            if (foundTarget != null) {
                target = foundTarget;
                moveToTarget();
                return;
            }
        }

        if (entity.getNavigation().isDone() || wanderCooldown <= 0) {
            wanderToRandomPosition();
        }
    }

    private void findTargetOrWander() {
        target = getClosestMineralBlockInArea();
        scanCooldown = SCAN_INTERVAL_TICKS;

        if (target != null) {
            moveToTarget();
        } else {
            wanderToRandomPosition();
        }
    }

    private void moveToTarget() {
        if (target == null) {
            return;
        }

        entity.getNavigation().moveTo(
                target.getX() + 0.5D,
                target.getY(),
                target.getZ() + 0.5D,
                MOVE_SPEED
        );
    }

    private void wanderToRandomPosition() {
        Vec3 wanderTarget = DefaultRandomPos.getPos(
                entity,
                WANDER_HORIZONTAL_RANGE,
                WANDER_VERTICAL_RANGE
        );

        wanderCooldown = WANDER_REPATH_INTERVAL_TICKS;

        if (wanderTarget == null) {
            return;
        }

        entity.getNavigation().moveTo(
                wanderTarget.x,
                wanderTarget.y,
                wanderTarget.z,
                MOVE_SPEED
        );
    }

    private BlockPos getClosestMineralBlockInArea() {
        BlockPos entityPos = entity.blockPosition();

        Iterable<BlockPos> blocks = BlockPos.betweenClosed(
                entityPos.getX() - SEARCH_RADIUS,
                entityPos.getY() - SEARCH_RADIUS,
                entityPos.getZ() - SEARCH_RADIUS,
                entityPos.getX() + SEARCH_RADIUS,
                entityPos.getY() + SEARCH_RADIUS,
                entityPos.getZ() + SEARCH_RADIUS
        );

        BlockPos closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (BlockPos pos : blocks) {
            Block block = entity.level().getBlockState(pos).getBlock();

            if (!isSearchTargetBlock(block)) {
                continue;
            }

            double distance = entity.distanceToSqr(pos.getX(), pos.getY(), pos.getZ());

            if (distance < closestDistance) {
                closestDistance = distance;
                closest = pos.immutable();
            }
        }

        return closest;
    }

    private boolean isSearchTargetBlock(Block block) {
        ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(block);
        String path = blockId.getPath();

        return path.contains("mineral_block")
                || path.contains("fossil_block")
                || path.contains("marble_ore")
                || path.contains("metalium_ore")
                || path.contains("titanium_ore");
    }
}