package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.AquaticJumpGoal;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.pathfinding.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public abstract class EntityAquaticSpectrobe extends EntitySpectrobe {
    private static final EntityPredicate SWIM_WITH_PLAYER_TARGETING = (new EntityPredicate()).range(10.0D).allowSameTeam().allowInvulnerable().allowUnseeable();
    public EntityAquaticSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
        this.setPathfindingMalus(PathNodeType.WALKABLE, 0.0F);
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

    protected PathNavigator createNavigation(World world) {
        return new EntityAquaticSpectrobe.Navigator(this, world);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
//        this.goalSelector.addGoal(0, new SwimWithPlayerGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new RandomSwimmingGoal(this, 1D, 10));
        this.goalSelector.addGoal(4, new FindWaterGoal(this));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
        this.goalSelector.addGoal(5, new AquaticJumpGoal(this, 10));
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public void mate() {
        List<? extends EntityAquaticSpectrobe> mates
                    = level.getEntitiesOfClass(getSpectrobeClass(),
                        this.getBoundingBox()
                        .inflate(10, 10, 10));
        if(mates.isEmpty()) {
            this.setTicksTillMate(16000);
            return;
        }

        EntityAquaticSpectrobe mate = null;

        for (EntityAquaticSpectrobe spec : mates) {
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

    static class Navigator extends SwimmerPathNavigator {
        Navigator(EntityAquaticSpectrobe p_i48815_1_, World p_i48815_2_) {
            super(p_i48815_1_, p_i48815_2_);
        }

        protected boolean canUpdatePath() {
            return true;
        }

        protected PathFinder createPathFinder(int nodes) {
            this.nodeEvaluator = new WalkAndSwimNodeProcessor();
            return new PathFinder(this.nodeEvaluator, nodes);
        }

        public boolean isStableDestination(BlockPos p_188555_1_) {
            return super.isStableDestination(p_188555_1_) || !this.level.getBlockState(p_188555_1_.below()).isAir();
//
//            if (this.mob instanceof EntityAquaticSpectrobe) {
//            }
//
//            return !this.level.getBlockState(p_188555_1_.below()).isAir();
        }
    }

    static class MoveHelperController extends MovementController {
        private final EntityAquaticSpectrobe spectrobe;

        public MoveHelperController(EntityAquaticSpectrobe dolphinIn) {
            super(dolphinIn);
            this.spectrobe = dolphinIn;
        }


        public void tick() {
            if (this.spectrobe.isInWater()) {
                this.spectrobe.setDeltaMovement(this.spectrobe.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == MovementController.Action.MOVE_TO && !this.spectrobe.getNavigation().isDone()) {
                double d0 = this.wantedX - this.spectrobe.getX();
                double d1 = this.wantedY - this.spectrobe.getY();
                double d2 = this.wantedZ - this.spectrobe.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < (double)2.5000003E-7F) {
                    this.mob.setZza(0.0F);
                } else {
                    float f = (float)(MathHelper.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.spectrobe.yRot = this.rotlerp(this.spectrobe.yRot, f, 10.0F);
                    this.spectrobe.yBodyRot = this.spectrobe.yRot;
                    this.spectrobe.yHeadRot = this.spectrobe.yRot;
                    float f1 = (float)(this.speedModifier * this.spectrobe.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.spectrobe.isInWater()) {
                        this.spectrobe.setSpeed(f1 * 0.2F);
                        float f2 = -((float)(MathHelper.atan2(d1, (double)MathHelper.sqrt(d0 * d0 + d2 * d2)) * (double)(180F / (float)Math.PI)));
                        f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
                        this.spectrobe.xRot = this.rotlerp(this.spectrobe.xRot, f2, 5.0F);
                        float f3 = MathHelper.cos(this.spectrobe.xRot * ((float)Math.PI / 180F));
                        float f4 = MathHelper.sin(this.spectrobe.xRot * ((float)Math.PI / 180F));
                        this.spectrobe.zza = f3 * f1;
                        this.spectrobe.yya = -f4 * f1;
                    } else {
                        if (d1 > (double)this.mob.maxUpStep) {
                            this.mob.getJumpControl().jump();
                            this.operation = MovementController.Action.JUMPING;
                        }
                        this.spectrobe.setSpeed(f1 * 0.3f);
                    }

                }
            } else if (this.operation == MovementController.Action.JUMPING) {
                this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                if (this.mob.isOnGround()) {
                    this.operation = MovementController.Action.WAIT;
                }
            } else {
                this.spectrobe.setSpeed(0.0F);
            }
        }

//        public void tick() {
//            if (this.spectrobe.isInWater()) {
//                this.spectrobe.setDeltaMovement(this.spectrobe.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
//            }
//
////            if (this.action == Action.MOVE_TO && !this.dolphin.getNavigator().noPath()) {
//                double d0 = this.wantedX - this.spectrobe.getX();
//                double d1 = this.wantedY - this.spectrobe.getY();
//                double d2 = this.wantedZ - this.spectrobe.getZ();
//                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
//                if (d3 < 2.500000277905201E-7D) {
//                    this.mob.setZza(0.0F);
//                } else {
//                    float f = (float)(MathHelper.atan2(d2, d0) * 57.2957763671875D) - 90.0F;
//                    this.spectrobe.yRot = this.rotlerp(this.spectrobe.yRot, f, 10.0F);
//                    this.spectrobe.yBodyRot = this.spectrobe.yRot;
//                    this.spectrobe.yHeadRot = this.spectrobe.yRot;
//                    float f1 = (float)(this.speedModifier * this.spectrobe.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
//                    if (this.spectrobe.isInWater()) {
//                        this.spectrobe.setSpeed(f1 * 0.5F);
//                        float f2 = -((float)(MathHelper.atan2(d1, (double)MathHelper.sqrt(d0 * d0 + d2 * d2)) * 57.2957763671875D));
//                        f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
//                        this.spectrobe.xRot = this.rotlerp(this.spectrobe.xRot, f2, 5.0F);
//                        float f3 = MathHelper.cos(this.spectrobe.xRot * 0.017453292F);
//                        float f4 = MathHelper.sin(this.spectrobe.xRot * 0.017453292F);
//                        this.spectrobe.zza = f3 * f1;
//                        this.spectrobe.yya = -f4 * f1;
//                    } else {
//                        this.spectrobe.setSpeed(f1);
//                    }
//                }
//        }
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
