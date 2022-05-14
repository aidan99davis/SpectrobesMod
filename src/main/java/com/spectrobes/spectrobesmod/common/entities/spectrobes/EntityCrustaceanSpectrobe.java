package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;


public abstract class EntityCrustaceanSpectrobe extends EntitySpectrobe {
    private static final EntityPredicate SWIM_WITH_PLAYER_TARGETING = (new EntityPredicate()).range(10.0D).allowSameTeam().allowInvulnerable().allowUnseeable();
    public EntityCrustaceanSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
        this.setPathfindingMalus(PathNodeType.WALKABLE, 1.0F);
        this.moveControl = new MoveHelperController(this);
        this.lookControl = new SpectrobeLookController(this, 10);
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public CreatureAttribute getMobType() {
        return CreatureAttribute.WATER;
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

//    protected PathNavigator createNavigator(World world) {
//        return new EntityCrustaceanSpectrobe.Navigator(this, world);
//    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new BreatheAirGoal(this));
        this.goalSelector.addGoal(2, new FindWaterGoal(this));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
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
        if(mates.isEmpty()) {
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

    protected abstract int getMaxLitterSize();

    static class MoveHelperController extends MovementController {
        private final EntityCrustaceanSpectrobe fish;

        MoveHelperController(EntityCrustaceanSpectrobe p_i48857_1_) {
            super(p_i48857_1_);
            this.fish = p_i48857_1_;
        }


        public void tick() {
            if (this.fish.isEyeInFluid(FluidTags.WATER)) {
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

//            if (this.action == Action.MOVE_TO && !this.fish.getNavigator().noPath()) {
                double lvt_1_1_ = this.wantedX - this.fish.getX();
                double lvt_3_1_ = this.wantedY - this.fish.getY();
                double lvt_5_1_ = this.wantedZ - this.fish.getZ();
                double lvt_7_1_ = (double)MathHelper.sqrt(lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_ + lvt_5_1_ * lvt_5_1_);
                lvt_3_1_ /= lvt_7_1_;
                float lvt_9_1_ = (float)(MathHelper.atan2(lvt_5_1_, lvt_1_1_) * 57.2957763671875D) - 90.0F;
                this.fish.yRot = this.rotlerp(this.fish.yRot, lvt_9_1_, 90.0F);
                this.fish.yBodyRot = this.fish.yRot;
                float lvt_10_1_ = (float)(this.speedModifier * this.fish.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                this.fish.setSpeed(MathHelper.lerp(0.125F, this.fish.getSpeed(), lvt_10_1_));
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, (double)this.fish.getSpeed() * lvt_3_1_ * 0.1D, 0.0D));
        }
    }

    static class Navigator extends SwimmerPathNavigator {
        Navigator(EntityCrustaceanSpectrobe p_i48815_1_, World p_i48815_2_) {
            super(p_i48815_1_, p_i48815_2_);
        }

        protected boolean canUpdatePath() {
            return true;
        }

        protected PathFinder createPathFinder(int p_179679_1_) {
            this.nodeEvaluator = new WalkAndSwimNodeProcessor();
            return new PathFinder(this.nodeEvaluator, p_179679_1_);
        }

        public boolean isStableDestination(BlockPos p_188555_1_) {
            if (this.mob instanceof EntityCrustaceanSpectrobe) {

                return this.level.getBlockState(p_188555_1_).getBlock() == Blocks.WATER
                        || !this.level.getBlockState(p_188555_1_.below()).isAir();
            }

            return !this.level.getBlockState(p_188555_1_.below()).isAir();
        }
    }

    static class MoveHelperController_ONE extends MovementController {
        private final EntityCrustaceanSpectrobe dolphin;

        public MoveHelperController_ONE(EntityCrustaceanSpectrobe dolphinIn) {
            super(dolphinIn);
            this.dolphin = dolphinIn;
        }

        public void tick() {
            if (this.dolphin.isInWater()) {
                this.dolphin.setDeltaMovement(this.dolphin.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

//            if (this.action == Action.MOVE_TO && !this.dolphin.getNavigator().noPath()) {
                double d0 = this.wantedX - this.dolphin.getX();
                double d1 = this.wantedY - this.dolphin.getY();
                double d2 = this.wantedZ - this.dolphin.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < 2.500000277905201E-7D) {
                    this.mob.setZza(0.0F);
                } else {
                    float f = (float)(MathHelper.atan2(d2, d0) * 57.2957763671875D) - 90.0F;
                    this.dolphin.yRot = this.rotlerp(this.dolphin.yRot, f, 10.0F);
                    this.dolphin.yBodyRot = this.dolphin.yRot;
                    this.dolphin.yHeadRot = this.dolphin.yRot;
                    float f1 = (float)(this.speedModifier * this.dolphin.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                    if (this.dolphin.isInWater()) {
                        this.dolphin.setSpeed(f1 * 0.2F);
                        float f2 = -((float)(MathHelper.atan2(d1, (double)MathHelper.sqrt(d0 * d0 + d2 * d2)) * 57.2957763671875D));
                        f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
                        this.dolphin.xRot = this.rotlerp(this.dolphin.xRot, f2, 5.0F);
                        float f3 = MathHelper.cos(this.dolphin.xRot * 0.017453292F);
                        float f4 = MathHelper.sin(this.dolphin.xRot * 0.017453292F);
                        this.dolphin.zza = f3 * f1;
                        this.dolphin.yya = -f4 * f1;
                    } else {
                        this.dolphin.setSpeed(f1);
                    }
                }
//            } else {
//                this.dolphin.setAIMoveSpeed(0.0F);
//                this.dolphin.setMoveStrafing(0.0F);
//                this.dolphin.setMoveVertical(0.0F);
//                this.dolphin.setMoveForward(0.0F);
//            }

        }
    }

    public class SpectrobeLookController extends LookController {
        private final int maxYRotFromCenter;

        public SpectrobeLookController(MobEntity p_i48942_1_, int p_i48942_2_) {
            super(p_i48942_1_);
            this.maxYRotFromCenter = p_i48942_2_;
        }

        /**
         * Updates look
         */
        public void tick() {
            if (this.hasWanted) {
                this.hasWanted = false;
                this.mob.yHeadRot = this.rotateTowards(this.mob.yHeadRot, this.getYRotD() + 20.0F, this.yMaxRotSpeed);
                this.mob.xRot = this.rotateTowards(this.mob.xRot, this.getXRotD() + 10.0F, this.xMaxRotAngle);
            } else {
                if (this.mob.getNavigation().isDone()) {
                    this.mob.xRot = this.rotateTowards(this.mob.xRot, 0.0F, 5.0F);
                }

                this.mob.yHeadRot = this.rotateTowards(this.mob.yHeadRot, this.mob.yBodyRot, this.yMaxRotSpeed);
            }

            float f = MathHelper.wrapDegrees(this.mob.yHeadRot - this.mob.yBodyRot);
            if (f < (float)(-this.maxYRotFromCenter)) {
                this.mob.yBodyRot -= 4.0F;
            } else if (f > (float)this.maxYRotFromCenter) {
                this.mob.yBodyRot += 4.0F;
            }

        }
    }
}
