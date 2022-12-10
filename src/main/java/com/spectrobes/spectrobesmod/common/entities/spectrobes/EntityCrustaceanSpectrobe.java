package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.SpectrobeFindWaterGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.SpectrobeRandomStrollGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.Random;


public abstract class EntityCrustaceanSpectrobe extends EntitySpectrobe {
    public EntityCrustaceanSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WALKABLE, 0.0F);
        this.moveControl = new MoveHelperController(this);
        this.lookControl = new LookControl(this);
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public MobType getMobType() {
        return MobType.WATER;
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new SpectrobeFindWaterGoal(this));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new SpectrobeRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(7, new RandomSwimmingGoal(this, 1, 10));
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public void mate() {
        List<? extends EntityCrustaceanSpectrobe> mates
                = level.getEntitiesOfClass(getSpectrobeClass(),
                this.getBoundingBox()
                        .inflate(10, 10, 10));
        if(mates.isEmpty() || mates.size() == 1) {
            this.setTicksTillMate(16000);
            return;
        }

        EntityCrustaceanSpectrobe mate = null;

        for (EntityCrustaceanSpectrobe spec : mates) {
            if(mate == null) {
                if(spec.getTicksTillMate() <= 0) {
                    mate = spec;
                }
            }
        }

        if(mate == null) {
            this.setTicksTillMate(16000);
            return;
        }

        this.entityData.set(HAS_MATED, true);

        mate.setTicksTillMate(16000);
        Random random = new Random();
        int litterSize = random.nextInt(getMaxLitterSize());

        for(int i = 0; i < litterSize; i++) {
            EntitySpectrobe spectrobe = getChildForLineage()
                    .create(level);
            this.level.addFreshEntity(spectrobe);
            spectrobe.teleportTo(getX(), getY(), getZ());
        }
        //todo: aquatic breeding. eggs? livebirth? - livebirth for now, with a litter size.
    }

    @Override
    protected int getMaxSchoolSize() {
        return 5;
    }

    protected abstract int getMaxLitterSize();

    static class MoveHelperController extends MoveControl {
        private final EntityCrustaceanSpectrobe fish;

        MoveHelperController(EntityCrustaceanSpectrobe p_i48857_1_) {
            super(p_i48857_1_);
            this.fish = p_i48857_1_;
        }

        public void tick() {
            if (this.fish.isInWater()) {
                if(this.fish.getTarget() != null && this.fish.getTarget().getY() > this.fish.getY()) {
                    this.fish.getNavigation().setCanFloat(true);
                } else {
                    this.fish.getNavigation().setCanFloat(false);
                }
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == MoveControl.Operation.MOVE_TO && !this.fish.getNavigation().isDone()) {
                float d0 = (float) this.wantedX - (float) this.fish.getX();
                float d1 = (float) this.wantedY - (float) this.fish.getY();
                float d2 = (float) this.wantedZ - (float) this.fish.getZ();
                float lvt_7_1_ = Mth.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                BlockPos blockpos = this.mob.blockPosition();
                BlockState blockstate = this.mob.level.getBlockState(blockpos);
                VoxelShape voxelshape = blockstate.getCollisionShape(this.mob.level, blockpos);
                if (d1 > (double)this.mob.getStepHeight() && d0 * d0 + d2 * d2 < (double)Math.max(1.0F, this.mob.getBbWidth()) || !voxelshape.isEmpty() && this.mob.getY() < voxelshape.max(Direction.Axis.Y) + (double)blockpos.getY() && !blockstate.is(BlockTags.DOORS) && !blockstate.is(BlockTags.FENCES)) {
                    this.mob.getJumpControl().jump();
                    this.operation = MoveControl.Operation.JUMPING;
                }
                d1 /= lvt_7_1_;
                float lvt_9_1_ = (float) (Mth.atan2(d2, d0) * 57.2957763671875D) - 90.0F;
                this.fish.setYRot(this.rotlerp(this.fish.yRotO, lvt_9_1_, 90.0F));
                this.fish.yBodyRot = this.fish.getYRot();
                float lvt_10_1_ = (float) (this.speedModifier * this.fish.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                this.fish.setSpeed(Mth.lerp(0.125F, this.fish.getSpeed(), lvt_10_1_));
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, (double) this.fish.getSpeed() * d1 * 0.1D, 0.0D));
            } else if (this.operation == MoveControl.Operation.JUMPING) {
                this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                if (this.mob.isOnGround()) {
                    this.operation = MoveControl.Operation.WAIT;
                }
            }
        }
    }

//    private static class SpectrobeLookController extends LookControl {
//        private final int maxYRotFromCenter;
//
//        public SpectrobeLookController(Mob p_i48942_1_, int p_i48942_2_) {
//            super(p_i48942_1_);
//            this.maxYRotFromCenter = p_i48942_2_;
//        }
//
//        /**
//         * Updates look
//         */
//        public void tick() {
//            if (this.hasWanted) {
//                this.hasWanted = false;
//                this.mob.yHeadRot = this.rotateTowards(this.mob.yHeadRot, this.getYRotD() + 20.0F, this.yMaxRotSpeed);
//                this.mob.xRot = this.rotateTowards(this.mob.xRot, this.getXRotD() + 10.0F, this.xMaxRotAngle);
//            } else {
//                if (this.mob.getNavigation().isDone()) {
//                    this.mob.xRot = this.rotateTowards(this.mob.xRot, 0.0F, 5.0F);
//                }
//
//                this.mob.yHeadRot = this.rotateTowards(this.mob.yHeadRot, this.mob.yBodyRot, this.yMaxRotSpeed);
//            }
//
////            float f = MathHelper.wrapDegrees(this.mob.yHeadRot - this.mob.yBodyRot);
////            if (f < (float)(-this.maxYRotFromCenter)) {
////                this.mob.yBodyRot -= 4.0F;
////            } else if (f > (float)this.maxYRotFromCenter) {
////                this.mob.yBodyRot += 4.0F;
////            }
//
//        }
//    }
}
