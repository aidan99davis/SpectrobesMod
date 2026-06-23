package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.movement.AquaticJumpGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.movement.SpectrobeFindWaterGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.movement.SpectrobeRandomSwimmingGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.movement.SpectrobeWaterAvoidingRandomStrollGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.Random;

public abstract class EntityAquaticSpectrobe extends EntitySpectrobe {

    public EntityAquaticSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.setPathfindingMalus(PathType.WALKABLE, 0.0F);
        this.moveControl = new AquaticSpectrobeMoveController(this);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    // -------------------------------------------------------------------------
    // Classification / fluid
    // -------------------------------------------------------------------------

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public MobCategory getClassification(boolean forSpawnCount) {
        return MobCategory.WATER_CREATURE;
    }

    // -------------------------------------------------------------------------
    // Navigation — amphibious so the spectrobe can path on land and in water
    // -------------------------------------------------------------------------

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        return new AmphibiousPathNavigation(this, pLevel);
    }

    protected abstract boolean isShallowSwimmer();

    // -------------------------------------------------------------------------
    // Goals
    //
    // Priority order (lower number = higher priority):
    //   Base class handles: 1 combat/follow/leader, 3 FollowMaster, 8 LookAt
    //
    //   0  FloatGoal          — keeps the entity from sinking when it surfaces
    //   4  FindWater          — wild & tamed; being near water is healthy
    //   5  AquaticJump        — wild only; breaching is a wild behaviour
    //   7  RandomSwimming     — wild only; free-roam in water
    //   8  RandomStroll       — wild only; ground movement when beached
    // -------------------------------------------------------------------------

    @Override
    protected void registerGoals() {
        super.registerGoals();

        // Float goal applies to all — prevents drowning on surface
        this.goalSelector.addGoal(0, new FloatGoal(this));

        // Wild-only aquatic behaviour — guarded so tamed spectrobes don't
        // randomly swim away from their master or beach themselves jumping
        this.goalSelector.addGoal(4, new SpectrobeFindWaterGoal(this) {
            @Override public boolean canUse() { return !isTame() && super.canUse(); }
        });
        this.goalSelector.addGoal(5, new AquaticJumpGoal(this, 10) {
            @Override public boolean canUse() { return !isTame() && super.canUse(); }
        });
        this.goalSelector.addGoal(7, new SpectrobeRandomSwimmingGoal(this, 1D, 10) {
            @Override public boolean canUse() { return !isTame() && super.canUse(); }
        });
        // Ground stroll fallback — lets both tamed and wild navigate on land,
        // but is overridden by FollowMasterGoal (priority 3) when tamed
        this.goalSelector.addGoal(8, new SpectrobeWaterAvoidingRandomStrollGoal(this, 0.5D));
    }

    // -------------------------------------------------------------------------
    // Breeding
    // -------------------------------------------------------------------------

    @Override
    public void mate() {
        List<? extends EntityAquaticSpectrobe> mates =
                level().getEntitiesOfClass(getClass(),
                        this.getBoundingBox().inflate(10, 10, 10));

        if (mates.isEmpty() || mates.size() == 1) {
            this.setTicksTillMate(16000);
            return;
        }

        EntityAquaticSpectrobe mate = null;
        for (EntityAquaticSpectrobe spec : mates) {
            if (mate == null && spec.getTicksTillMate() <= 0) {
                mate = spec;
            }
        }

        if (mate == null) {
            this.setTicksTillMate(16000);
            return;
        }

        this.entityData.set(HAS_MATED, true);
        this.setTicksTillMate(16000);
        mate.setTicksTillMate(16000);

        Random random = new Random();
        int litterSize = random.nextInt(getMaxLitterSize()) + 1;
        for (int i = 0; i < litterSize; i++) {
            EntitySpectrobe child = getChildForLineage().create(level());
            if (child != null) {
                this.level().addFreshEntity(child);
                child.teleportTo(getX(), getY(), getZ());
            }
        }
    }

    @Override
    protected int getMaxSchoolSize() {
        return 8;
    }

    protected abstract int getMaxLitterSize();

    // -------------------------------------------------------------------------
    // Move controller
    //
    // Handles three zones:
    //   IN WATER  — full 3-D swimming with a gentle upward buoyancy nudge so
    //               the spectrobe stays afloat rather than sinking.
    //   ON GROUND — standard walk/jump logic identical to the vanilla ground mob.
    //   IDLE      — speed zeroed to avoid drift.
    // -------------------------------------------------------------------------

    static class AquaticSpectrobeMoveController extends MoveControl {

        private final EntityAquaticSpectrobe spectrobe;

        AquaticSpectrobeMoveController(EntityAquaticSpectrobe pSpectrobe) {
            super(pSpectrobe);
            this.spectrobe = pSpectrobe;
        }

        @Override
        public void tick() {
            // Buoyancy — applied unconditionally in water so the entity
            // naturally floats upward rather than sinking passively.
            if (spectrobe.isInWater()) {
                spectrobe.setDeltaMovement(
                        spectrobe.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == Operation.MOVE_TO && !spectrobe.getNavigation().isDone()) {
                double d0 = this.wantedX - spectrobe.getX();
                double d1 = this.wantedY - spectrobe.getY();
                double d2 = this.wantedZ - spectrobe.getZ();
                double dist = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);

                if (dist < 1.0E-4D) {
                    // Already at destination — stop rather than jitter
                    this.mob.setSpeed(0.0F);
                    return;
                }

                BlockPos blockpos = this.mob.blockPosition();
                BlockState blockstate = this.mob.level().getBlockState(blockpos);
                VoxelShape voxelshape = blockstate.getCollisionShape(this.mob.level(), blockpos);

                // Jump if we need to step up a block or climb out of a shape
                boolean needsJump = d1 > (double) this.mob.maxUpStep()
                        && d0 * d0 + d2 * d2 < (double) Math.max(1.0F, this.mob.getBbWidth());
                boolean insideShape = !voxelshape.isEmpty()
                        && this.mob.getY() < voxelshape.max(Direction.Axis.Y) + blockpos.getY()
                        && !blockstate.is(BlockTags.DOORS)
                        && !blockstate.is(BlockTags.FENCES);

                if (needsJump || insideShape) {
                    this.mob.getJumpControl().jump();
                    this.operation = Operation.JUMPING;
                }

                // Yaw — always steer toward the target
                float yaw = (float) (Mth.atan2(d2, d0) * (180F / (float) Math.PI)) - 90.0F;
                spectrobe.setYRot(this.rotlerp(spectrobe.getYRot(), yaw, 90.0F));
                spectrobe.yBodyRot = spectrobe.getYRot();

                // Speed — lerp smoothly to avoid snapping
                float targetSpeed = (float) (this.speedModifier
                        * spectrobe.getAttributeValue(Attributes.MOVEMENT_SPEED));
                spectrobe.setSpeed(Mth.lerp(0.125F, spectrobe.getSpeed(), targetSpeed));

                // Vertical impulse in water so the entity can navigate upward
                if (spectrobe.isInWater()) {
                    double normD1 = d1 / dist;
                    spectrobe.setDeltaMovement(
                            spectrobe.getDeltaMovement()
                                    .add(0.0D, spectrobe.getSpeed() * normD1 * 0.1D, 0.0D));
                }

            } else if (this.operation == Operation.JUMPING) {
                this.mob.setSpeed(
                        (float) (this.speedModifier
                                * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                if (this.mob.onGround()) {
                    this.operation = Operation.WAIT;
                }
            } else {
                // WAIT / no active path — zero speed to prevent gliding
                spectrobe.setSpeed(0.0F);
            }
        }
    }
}