package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityAquaticSpectrobe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;

public class AquaticJumpGoal extends JumpGoal {
    private static final int[] STEPS_TO_CHECK = new int[]{0, 1, 4, 5, 6, 7};
    private final EntityAquaticSpectrobe spectrobe;
    private final int interval;
    private boolean breached;

    public AquaticJumpGoal(EntityAquaticSpectrobe p_i50329_1_, int p_i50329_2_) {
        this.spectrobe = p_i50329_1_;
        this.interval = p_i50329_2_;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if (this.spectrobe.getRandom().nextInt(this.interval) != 0) {
            return false;
        } else {
            Direction direction = this.spectrobe.getMotionDirection();
            int i = direction.getStepX();
            int j = direction.getStepZ();
            BlockPos blockpos = this.spectrobe.blockPosition();

            for(int k : STEPS_TO_CHECK) {
                if (!this.waterIsClear(blockpos, i, j, k) || !this.surfaceIsClear(blockpos, i, j, k)) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean waterIsClear(BlockPos pPos, int pDx, int pDz, int pScale) {
        BlockPos blockpos = pPos.offset(pDx * pScale, 0, pDz * pScale);
        return this.spectrobe.level.getFluidState(blockpos).is(FluidTags.WATER) && !this.spectrobe.level.getBlockState(blockpos).getMaterial().blocksMotion();
    }

    private boolean surfaceIsClear(BlockPos pPos, int pDx, int pDz, int pScale) {
        return this.spectrobe.level.getBlockState(pPos.offset(pDx * pScale, 1, pDz * pScale)).isAir() && this.spectrobe.level.getBlockState(pPos.offset(pDx * pScale, 2, pDz * pScale)).isAir();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        double d0 = this.spectrobe.getDeltaMovement().y;
        return (!(d0 * d0 < (double)0.03F) || this.spectrobe.xRotO == 0.0F || !(Math.abs(this.spectrobe.xRotO) < 10.0F) || !this.spectrobe.isInWater()) && !this.spectrobe.isOnGround();
    }

    public boolean isInterruptable() {
        return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        Direction direction = this.spectrobe.getMotionDirection();
        this.spectrobe.setDeltaMovement(this.spectrobe.getDeltaMovement().add((double)direction.getStepX() * 0.6D, 0.7D, (double)direction.getStepZ() * 0.6D));
        this.spectrobe.getNavigation().stop();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.spectrobe.xRotO = 0.0F;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        boolean flag = this.breached;
        if (!flag) {
            FluidState fluidstate = this.spectrobe.level.getFluidState(this.spectrobe.blockPosition());
            this.breached = fluidstate.is(FluidTags.WATER);
        }

        if (this.breached && !flag) {
            this.spectrobe.playSound(SoundEvents.DOLPHIN_JUMP, 1.0F, 1.0F);
        }

        Vec3 vec3 = this.spectrobe.getDeltaMovement();
        if (vec3.y * vec3.y < (double)0.03F && this.spectrobe.getXRot() != 0.0F) {
            this.spectrobe.setXRot(Mth.rotlerp(this.spectrobe.getXRot(), 0.0F, 0.2F));
        } else if (vec3.length() > (double)1.0E-5F) {
            double d0 = vec3.horizontalDistance();
            double d1 = Math.atan2(-vec3.y, d0) * (double)(180F / (float)Math.PI);
            this.spectrobe.setXRot((float)d1);
        }

    }
}
