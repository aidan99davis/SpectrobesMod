package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.DolphinEntity;
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


import net.minecraft.entity.ai.controller.MovementController.Action;
import net.minecraft.entity.ai.goal.Goal.Flag;

public abstract class EntityAquaticSpectrobe extends EntitySpectrobe {
    private static final EntityPredicate SWIM_WITH_PLAYER_TARGETING = (new EntityPredicate()).range(10.0D).allowSameTeam().allowInvulnerable().allowUnseeable();
    public EntityAquaticSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.setPathfindingMalus(PathNodeType.WATER, 1.0F);
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

    protected PathNavigator createNavigation(World world) {
        return new EntityAquaticSpectrobe.Navigator(this, world);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
//        this.goalSelector.addGoal(0, new SwimWithPlayerGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, .5D, 5));
        this.goalSelector.addGoal(1, new FindWaterGoal(this));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(8, new FollowBoatGoal(this));
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

    static class SwimWithPlayerGoal extends Goal {
        private final EntityAquaticSpectrobe spectrobe;
        private final double speed;
        private PlayerEntity targetPlayer;

        SwimWithPlayerGoal(EntityAquaticSpectrobe spectrobeIn, double speedIn) {
            this.spectrobe = spectrobeIn;
            this.speed = speedIn;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean canUse() {
            this.targetPlayer = this.spectrobe.level.getNearestPlayer(EntityAquaticSpectrobe.SWIM_WITH_PLAYER_TARGETING, this.spectrobe);
            return this.targetPlayer == null ? false : this.targetPlayer.isSwimming();
        }

        public boolean canContinueToUse() {
            return this.targetPlayer != null && this.targetPlayer.isSwimming() && this.spectrobe.distanceToSqr(this.targetPlayer) < 256.0D;
        }

        public void start() {
            this.targetPlayer.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 100));
        }

        public void stop() {
            this.targetPlayer = null;
            this.spectrobe.getNavigation().stop();
        }


        public void tick() {
            this.spectrobe.getLookControl().setLookAt(this.targetPlayer, (float)(this.spectrobe.getMaxHeadYRot() + 20), (float)this.spectrobe.getMaxHeadXRot());
            if (this.spectrobe.distanceToSqr(this.targetPlayer) < 6.25D) {
                this.spectrobe.getNavigation().stop();
            } else {
                this.spectrobe.getNavigation().moveTo(this.targetPlayer, this.speed);
            }

            if (this.targetPlayer.isSwimming() && this.targetPlayer.level.random.nextInt(6) == 0) {
                this.targetPlayer.addEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 100));
            }

        }
    }
    static class MoveHelperController_ONE extends MovementController {
        private final EntityAquaticSpectrobe dolphin;

        public MoveHelperController_ONE(EntityAquaticSpectrobe dolphinIn) {
            super(dolphinIn);
            this.dolphin = dolphinIn;
        }

        public void tick() {
            if (this.dolphin.isInWater()) {
                this.dolphin.setDeltaMovement(this.dolphin.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == Action.MOVE_TO && !this.dolphin.getNavigation().isDone()) {
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
                    float f1 = (float)(this.speedModifier * this.dolphin.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.dolphin.isInWater()) {
                        this.dolphin.setSpeed(f1 * 0.02F);
                        float f2 = -((float)(MathHelper.atan2(d1, (double)MathHelper.sqrt(d0 * d0 + d2 * d2)) * 57.2957763671875D));
                        f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
                        this.dolphin.xRot = this.rotlerp(this.dolphin.xRot, f2, 5.0F);
                        float f3 = MathHelper.cos(this.dolphin.xRot * 0.017453292F);
                        float f4 = MathHelper.sin(this.dolphin.xRot * 0.017453292F);
                        this.dolphin.zza = f3 * f1;
                        this.dolphin.yya = -f4 * f1;
                    } else {
                        this.dolphin.setSpeed(f1 * 0.1F);
                    }
                }
            } else {
                this.dolphin.setSpeed(0.0F);
                this.dolphin.setXxa(0.0F);
                this.dolphin.setYya(0.0F);
                this.dolphin.setZza(0.0F);
            }

        }
    }

    static class Navigator extends SwimmerPathNavigator {
        Navigator(EntityAquaticSpectrobe p_i48815_1_, World p_i48815_2_) {
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
            if (this.mob instanceof EntityAquaticSpectrobe) {

                return this.level.getBlockState(p_188555_1_).getBlock() == Blocks.WATER
                        || !this.level.getBlockState(p_188555_1_.below()).isAir();
            }

            return !this.level.getBlockState(p_188555_1_.below()).isAir();
        }
    }

    static class MoveHelperController extends MovementController {
        private final EntityAquaticSpectrobe dolphin;

        public MoveHelperController(EntityAquaticSpectrobe dolphinIn) {
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
                        this.dolphin.setSpeed(f1 * 0.5F);
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
