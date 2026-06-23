package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.movement;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

import java.util.EnumSet;

public class FollowMasterGoal extends Goal {
    private final TamableAnimal tamable;
    private LivingEntity owner;
    private final Level level;
    private final double speedModifier;
    private final PathNavigation navigation;
    private int timeToRecalcPath;
    private final float stopDistance;
    private final float startDistance;
    private final boolean canFly;
    // True for aquatic/crustacean types that can safely teleport into water
    private final boolean allowsWater;

    /**
     * @param allowsWater Whether the spectrobe is comfortable in water and may
     *                    be teleported into a water block when catching up to
     *                    its master. Pass true for aquatic and crustacean types,
     *                    false for mammals and avians.
     */
    public FollowMasterGoal(TamableAnimal entity, double followSpeed,
                            float minDist, float maxDist,
                            boolean canFly, boolean allowsWater) {
        this.tamable = entity;
        this.level = entity.level();
        this.speedModifier = followSpeed;
        this.navigation = entity.getNavigation();
        this.startDistance = minDist;
        this.stopDistance = maxDist;
        this.canFly = canFly;
        this.allowsWater = allowsWater;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        if (!(entity.getNavigation() instanceof GroundPathNavigation)
                && !(entity.getNavigation() instanceof FlyingPathNavigation)
                && !(entity.getNavigation() instanceof AmphibiousPathNavigation)) {
            throw new IllegalArgumentException(
                    "Unsupported navigation type for FollowMasterGoal");
        }
    }

    /** Convenience overload: non-aquatic, non-flying (mammals). */
    public FollowMasterGoal(TamableAnimal entity, double followSpeed,
                            float minDist, float maxDist, boolean canFly) {
        this(entity, followSpeed, minDist, maxDist, canFly, false);
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.tamable.getOwner();
        if (livingentity == null) return false;
        if (livingentity.isSpectator()) return false;
        if (this.tamable.isOrderedToSit()) return false;
        if (this.tamable.getTarget() != null) return false;
        if (this.tamable.distanceToSqr(livingentity)
                < (double) (this.startDistance * this.startDistance)) return false;

        this.owner = livingentity;
        return ((EntitySpectrobe) this.tamable).getState() == 0
                && !((EntitySpectrobe) this.tamable).isAttacking();
    }

    @Override
    public boolean canContinueToUse() {
        if (this.navigation.isDone()) return false;
        if (this.tamable.isOrderedToSit()) return false;
        if (this.tamable.getTarget() != null) return false;
        return !(this.tamable.distanceToSqr(this.owner)
                <= (double) (this.stopDistance * this.stopDistance));
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
        this.tamable.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    @Override
    public void stop() {
        this.owner = null;
        this.navigation.stop();
        this.tamable.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    @Override
    public void tick() {
        this.tamable.getLookControl().setLookAt(
                this.owner, 10.0F, (float) this.tamable.getMaxHeadXRot());

        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!this.tamable.isLeashed() && !this.tamable.isPassenger()) {
                if (this.tamable.distanceToSqr(this.owner) >= 144.0D) {
                    this.teleportToOwner();
                } else {
                    this.navigation.moveTo(this.owner, this.speedModifier);
                }
            }
        }
    }

    private void teleportToOwner() {
        BlockPos blockpos = this.owner.blockPosition();
        for (int i = 0; i < 10; ++i) {
            int j = this.randomIntInclusive(-3, 3);
            int k = this.randomIntInclusive(-1, 1);
            int l = this.randomIntInclusive(-3, 3);
            if (this.maybeTeleportTo(blockpos.getX() + j, blockpos.getY() + k, blockpos.getZ() + l)) {
                return;
            }
        }
    }

    private boolean maybeTeleportTo(int pX, int pY, int pZ) {
        if (Math.abs((double) pX - this.owner.getX()) < 2.0D
                && Math.abs((double) pZ - this.owner.getZ()) < 2.0D) {
            return false;
        }
        if (!this.canTeleportTo(new BlockPos(pX, pY, pZ))) {
            return false;
        }
        this.tamable.moveTo((double) pX + 0.5D, pY, (double) pZ + 0.5D,
                this.tamable.yRotO, this.tamable.xRotO);
        this.navigation.stop();
        return true;
    }

    private boolean canTeleportTo(BlockPos pPos) {
        PathType pathnodetype = WalkNodeEvaluator.getPathTypeStatic(
                this.tamable, pPos.mutable());

        // WALKABLE is always acceptable.
        // WATER is only acceptable for spectrobes that can handle being in water.
        if (pathnodetype == PathType.WATER && !this.allowsWater) return false;
        if (pathnodetype != PathType.WALKABLE && pathnodetype != PathType.WATER) return false;

        BlockState blockstate = this.level.getBlockState(pPos.below());
        if (!this.canFly && blockstate.getBlock() instanceof LeavesBlock) {
            return false;
        }
        BlockPos blockpos = pPos.subtract(this.tamable.blockPosition());
        return this.level.noCollision(this.tamable,
                this.tamable.getBoundingBox().move(blockpos));
    }

    private int randomIntInclusive(int pMin, int pMax) {
        return this.tamable.getRandom().nextInt(pMax - pMin + 1) + pMin;
    }
}