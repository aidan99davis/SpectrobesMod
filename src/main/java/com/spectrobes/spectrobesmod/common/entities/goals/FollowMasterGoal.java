//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.spectrobes.spectrobesmod.common.entities.goals;

import java.util.EnumSet;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityAquaticSpectrobe;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.Goal.Flag;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class FollowMasterGoal extends Goal {
    private final EntitySpectrobe tameable;
    private LivingEntity owner;
    private final IWorldReader world;
    private final double followSpeed;
    private final PathNavigator navigator;
    private int timeToRecalcPath;
    private final float maxDist;
    private final float minDist;
    private float oldWaterCost;
    private final boolean teleportToLeaves;

    public FollowMasterGoal(EntitySpectrobe p_i225711_1_, double p_i225711_2_, float p_i225711_4_, float p_i225711_5_, boolean p_i225711_6_) {
        this.tameable = p_i225711_1_;
        this.world = p_i225711_1_.level;
        this.followSpeed = p_i225711_2_;
        this.navigator = p_i225711_1_.getNavigation();
        this.minDist = p_i225711_4_;
        this.maxDist = p_i225711_5_;
        this.teleportToLeaves = p_i225711_6_;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        LivingEntity lvt_1_1_ = this.tameable.getOwner();
        if (lvt_1_1_ == null) {
            return false;
        } else if (lvt_1_1_.isSpectator()) {
            return false;
        } else if (this.tameable.isOrderedToSit()) {
            return false;
        } else if (this.tameable.IsAttacking()) {
            return false;
        } else if (this.tameable.isSearching()) {
            return false;
        } else if (this.tameable.distanceToSqr(lvt_1_1_) < (double)(this.minDist * this.minDist)) {
            return false;
        } else {
            this.owner = lvt_1_1_;
            return true;
        }
    }

    public boolean canContinueToUse() {
        if (this.navigator.isDone()) {
            return false;
        } else if (this.tameable.isOrderedToSit()) {
            return false;
        } else if (this.tameable.IsAttacking()) {
            return false;
        } else if (this.tameable.isSearching()) {
            return false;
        } else {
            return this.tameable.distanceToSqr(this.owner) > (double)(this.maxDist * this.maxDist);
        }
    }

    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.tameable.getPathfindingMalus(PathNodeType.WATER);
        this.tameable.setPathfindingMalus(PathNodeType.WATER, 0.0F);
    }

    public void stop() {
        this.owner = null;
        this.navigator.stop();
        this.tameable.setPathfindingMalus(PathNodeType.WATER, this.oldWaterCost);
    }

    public void tick() {
        this.tameable.getLookControl().setLookAt(this.owner, 10.0F, (float)this.tameable.getMaxHeadXRot());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!this.tameable.isLeashed() && !this.tameable.isPassenger()) {
                if (this.tameable.distanceToSqr(this.owner) >= 144.0D) {
                    this.tryToTeleportNearEntity();
                } else {
                    this.navigator.moveTo(this.owner, this.followSpeed);
                }

            }
        }
    }

    private void tryToTeleportNearEntity() {
        BlockPos lvt_1_1_ = this.owner.blockPosition().immutable();

        for(int lvt_2_1_ = 0; lvt_2_1_ < 10; ++lvt_2_1_) {
            int lvt_3_1_ = this.getRandomNumber(-3, 3);
            int lvt_4_1_ = this.getRandomNumber(-1, 1);
            int lvt_5_1_ = this.getRandomNumber(-3, 3);
            boolean lvt_6_1_ = this.tryToTeleportToLocation(lvt_1_1_.getX() + lvt_3_1_, lvt_1_1_.getY() + lvt_4_1_, lvt_1_1_.getZ() + lvt_5_1_);
            if (lvt_6_1_) {
                return;
            }
        }

    }

    private boolean tryToTeleportToLocation(int p_226328_1_, int p_226328_2_, int p_226328_3_) {
        if (Math.abs((double)p_226328_1_ - this.owner.getX()) < 2.0D && Math.abs((double)p_226328_3_ - this.owner.getZ()) < 2.0D) {
            return false;
        } else if (!this.isTeleportFriendlyBlock(new BlockPos(p_226328_1_, p_226328_2_, p_226328_3_))) {
            return false;
        } else {
            this.tameable.moveTo((double)((float)p_226328_1_ + 0.5F), (double)p_226328_2_, (double)((float)p_226328_3_ + 0.5F), this.tameable.yRot, this.tameable.xRot);
            this.navigator.stop();
            return true;
        }
    }

    private boolean isTeleportFriendlyBlock(BlockPos p_226329_1_) {
        PathNodeType lvt_2_1_ = new WalkNodeProcessor().getBlockPathType(this.world, p_226329_1_.getX(), p_226329_1_.getY(), p_226329_1_.getZ());
        if (lvt_2_1_ != PathNodeType.WALKABLE) {
            if(tameable instanceof EntityAquaticSpectrobe && (lvt_2_1_ == PathNodeType.WATER || lvt_2_1_ == PathNodeType.WATER_BORDER)) {
                return true;
            }
            return false;
        } else {
            BlockState lvt_3_1_ = this.world.getBlockState(p_226329_1_.below());
            if (!this.teleportToLeaves && lvt_3_1_.getBlock() instanceof LeavesBlock) {
                return false;
            } else {
                BlockPos lvt_4_1_ = p_226329_1_.subtract(this.tameable.blockPosition().immutable());
                return this.world.noCollision(this.tameable, this.tameable.getBoundingBox().move(lvt_4_1_));
            }
        }
    }

    private int getRandomNumber(int p_226327_1_, int p_226327_2_) {
        return this.tameable.getRandom().nextInt(p_226327_2_ - p_226327_1_ + 1) + p_226327_1_;
    }
}
