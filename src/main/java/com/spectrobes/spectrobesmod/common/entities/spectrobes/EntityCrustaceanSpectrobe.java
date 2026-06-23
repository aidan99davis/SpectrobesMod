package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.movement.SpectrobeFindWaterGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.movement.SpectrobeRandomStrollGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.movement.SpectrobeWaterAvoidingRandomStrollGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.Random;

public abstract class EntityCrustaceanSpectrobe extends EntitySpectrobe {

    public EntityCrustaceanSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.setPathfindingMalus(PathType.WALKABLE, 0.0F);
        this.moveControl = new CrustaceanMoveController(this);
        this.lookControl = new LookControl(this);
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
    // Goals
    //
    // Crustaceans are semi-aquatic — equally comfortable on land and in water.
    // Tamed spectrobes follow their master on foot; wild ones seek water and
    // swim/scuttle freely.
    //
    //   0  FloatGoal          — prevents drowning at the surface
    //   2  FindWater (wild)   — wild crustaceans actively seek water
    //   7  RandomSwimming     — wild aquatic roaming
    //   7  RandomStroll       — ground roaming, both tamed & wild
    //                          (overridden by FollowMaster at priority 3)
    //   9  LookAround         — idle behaviour
    // -------------------------------------------------------------------------

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new FloatGoal(this));

        // Wild-only water-seeking
        this.goalSelector.addGoal(2, new SpectrobeFindWaterGoal(this) {
            @Override public boolean canUse() { return !isTame() && super.canUse(); }
        });

        // Wild-only swimming
        this.goalSelector.addGoal(7, new RandomSwimmingGoal(this, 1, 10) {
            @Override public boolean canUse() { return !isTame() && super.canUse(); }
        });

        // Ground stroll — available to all, but superseded by FollowMaster
        this.goalSelector.addGoal(7, new SpectrobeRandomStrollGoal(this, 1));

        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
    }

    // -------------------------------------------------------------------------
    // Breeding
    // -------------------------------------------------------------------------

    @Override
    public void mate() {
        List<? extends EntityCrustaceanSpectrobe> mates =
                level().getEntitiesOfClass(this.getClass(),
                        this.getBoundingBox().inflate(10, 10, 10));

        if (mates.isEmpty() || mates.size() == 1) {
            this.setTicksTillMate(16000);
            return;
        }

        EntityCrustaceanSpectrobe mate = null;
        for (EntityCrustaceanSpectrobe spec : mates) {
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
        return 5;
    }

    protected abstract int getMaxLitterSize();

    // -------------------------------------------------------------------------
    // Move controller
    //
    // Crustaceans walk and can swim when in water.  Unlike the original, all
    // position components use double precision to avoid the float-cast loss that
    // caused jerky movement in the previous version.
    //
    // Zones:
    //   IN WATER  — gentle upward buoyancy nudge; can float and steer vertically
    //   ON GROUND — walk/jump using the standard MoveControl path
    //   IDLE      — speed zeroed
    // -------------------------------------------------------------------------

    static class CrustaceanMoveController extends MoveControl {

        private final EntityCrustaceanSpectrobe crustacean;

        CrustaceanMoveController(EntityCrustaceanSpectrobe entity) {
            super(entity);
            this.crustacean = entity;
        }

        @Override
        public void tick() {
            // Buoyancy in water — same nudge used by vanilla aquatic mobs
            if (crustacean.isInWater()) {
                if (crustacean.getTarget() != null
                        && crustacean.getTarget().getY() > crustacean.getY()) {
                    crustacean.getNavigation().setCanFloat(true);
                } else {
                    crustacean.getNavigation().setCanFloat(false);
                }
                crustacean.setDeltaMovement(
                        crustacean.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == Operation.MOVE_TO && !crustacean.getNavigation().isDone()) {

                // Use double throughout — the original code cast to float here,
                // causing precision loss that showed up as jittery turns
                double d0 = this.wantedX - crustacean.getX();
                double d1 = this.wantedY - crustacean.getY();
                double d2 = this.wantedZ - crustacean.getZ();
                double dist = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);

                if (dist < 1.0E-4D) {
                    crustacean.setSpeed(0.0F);
                    return;
                }

                BlockPos blockpos = this.mob.blockPosition();
                BlockState blockstate = this.mob.level().getBlockState(blockpos);
                VoxelShape voxelshape = blockstate.getCollisionShape(this.mob.level(), blockpos);

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

                double normD1 = d1 / dist;
                float yaw = (float) (Mth.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
                crustacean.setYRot(this.rotlerp(crustacean.yRotO, yaw, 90.0F));
                crustacean.yBodyRot = crustacean.getYRot();

                float targetSpeed = (float) (this.speedModifier
                        * crustacean.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                crustacean.setSpeed(Mth.lerp(0.125F, crustacean.getSpeed(), targetSpeed));

                // Vertical speed component applies in both water and air/ground
                crustacean.setDeltaMovement(
                        crustacean.getDeltaMovement()
                                .add(0.0D, crustacean.getSpeed() * normD1 * 0.1D, 0.0D));

            } else if (this.operation == Operation.JUMPING) {
                this.mob.setSpeed(
                        (float) (this.speedModifier
                                * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                if (this.mob.onGround()) {
                    this.operation = Operation.WAIT;
                }
            } else {
                // WAIT — zero speed so the entity doesn't drift after stopping
                crustacean.setSpeed(0.0F);
            }
        }
    }
}