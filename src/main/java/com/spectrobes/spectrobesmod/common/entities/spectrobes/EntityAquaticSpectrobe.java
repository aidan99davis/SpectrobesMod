package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
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


public abstract class EntityAquaticSpectrobe extends EntitySpectrobe {
    private static final EntityPredicate field_213810_bA = (new EntityPredicate()).setDistance(10.0D).allowFriendlyFire().allowInvulnerable().setLineOfSiteRequired();
    public EntityAquaticSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        this.setPathPriority(PathNodeType.WATER, 1.0F);
        this.setPathPriority(PathNodeType.WALKABLE, 1.0F);
        this.moveController = new MoveHelperController(this);
        this.lookController = new SpectrobeLookController(this, 10);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();


        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(
                1);
    }



    @Override
    public boolean isPushedByWater() {
        return false;
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.WATER;
    }

    @Override
    public void livingTick() {
        super.livingTick();
    }

    protected PathNavigator createNavigator(World world) {
        return new EntityAquaticSpectrobe.Navigator(this, world);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
//        this.goalSelector.addGoal(0, new SwimWithPlayerGoal(this, 1.0D));
        this.goalSelector.addGoal(0, new RandomSwimmingGoal(this, .5D, 5));
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
                    = world.getEntitiesWithinAABB(getSpectrobeClass(),
                        this.getBoundingBox()
                        .grow(10, 10, 10));
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

        this.dataManager.set(HAS_MATED, true);

        mate.setTicksTillMate(16000);
        Random random = new Random();
        int litterSize = random.nextInt(getMaxLitterSize());

        for(int i = 0; i < litterSize; i++) {
            EntitySpectrobe spectrobe = getChildForLineage()
                    .create(world);
            this.world.addEntity(spectrobe);
            spectrobe.setPositionAndUpdate(getPosX(), getPosY(), getPosZ());
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
            this.setMutexFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        public boolean shouldExecute() {
            this.targetPlayer = this.spectrobe.world.getClosestPlayer(EntityAquaticSpectrobe.field_213810_bA, this.spectrobe);
            return this.targetPlayer == null ? false : this.targetPlayer.isSwimming();
        }

        public boolean shouldContinueExecuting() {
            return this.targetPlayer != null && this.targetPlayer.isSwimming() && this.spectrobe.getDistanceSq(this.targetPlayer) < 256.0D;
        }

        public void startExecuting() {
            this.targetPlayer.addPotionEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 100));
        }

        public void resetTask() {
            this.targetPlayer = null;
            this.spectrobe.getNavigator().clearPath();
        }


        public void tick() {
            this.spectrobe.getLookController().setLookPositionWithEntity(this.targetPlayer, (float)(this.spectrobe.getHorizontalFaceSpeed() + 20), (float)this.spectrobe.getVerticalFaceSpeed());
            if (this.spectrobe.getDistanceSq(this.targetPlayer) < 6.25D) {
                this.spectrobe.getNavigator().clearPath();
            } else {
                this.spectrobe.getNavigator().tryMoveToEntityLiving(this.targetPlayer, this.speed);
            }

            if (this.targetPlayer.isSwimming() && this.targetPlayer.world.rand.nextInt(6) == 0) {
                this.targetPlayer.addPotionEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 100));
            }

        }
    }
    static class MoveHelperController_ONE extends MovementController {
        private final EntityAquaticSpectrobe fish;

        MoveHelperController_ONE(EntityAquaticSpectrobe p_i48857_1_) {
            super(p_i48857_1_);
            this.fish = p_i48857_1_;
        }


        public void tick() {
            if (this.fish.areEyesInFluid(FluidTags.WATER)) {
                this.fish.setMotion(this.fish.getMotion().add(0.0D, 0.005D, 0.0D));
            }

//            if (this.action == Action.MOVE_TO && !this.fish.getNavigator().noPath()) {
                double lvt_1_1_ = this.posX - this.fish.getPosX();
                double lvt_3_1_ = this.posY - this.fish.getPosY();
                double lvt_5_1_ = this.posZ - this.fish.getPosZ();
                double lvt_7_1_ = (double)MathHelper.sqrt(lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_ + lvt_5_1_ * lvt_5_1_);
                lvt_3_1_ /= lvt_7_1_;
                float lvt_9_1_ = (float)(MathHelper.atan2(lvt_5_1_, lvt_1_1_) * 57.2957763671875D) - 90.0F;
                this.fish.rotationYaw = this.limitAngle(this.fish.rotationYaw, lvt_9_1_, 90.0F);
                this.fish.renderYawOffset = this.fish.rotationYaw;
                float lvt_10_1_ = (float)(this.speed * this.fish.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
                this.fish.setAIMoveSpeed(MathHelper.lerp(0.125F, this.fish.getAIMoveSpeed(), lvt_10_1_));
                this.fish.setMotion(this.fish.getMotion().add(0.0D, (double)this.fish.getAIMoveSpeed() * lvt_3_1_ * 0.1D, 0.0D));
//            } else {
//                this.fish.setAIMoveSpeed(0.0F);
//            }
        }
    }

    static class Navigator extends SwimmerPathNavigator {
        Navigator(EntityAquaticSpectrobe p_i48815_1_, World p_i48815_2_) {
            super(p_i48815_1_, p_i48815_2_);
        }

        protected boolean canNavigate() {
            return true;
        }

        protected PathFinder getPathFinder(int p_179679_1_) {
            this.nodeProcessor = new WalkAndSwimNodeProcessor();
            return new PathFinder(this.nodeProcessor, p_179679_1_);
        }

        public boolean canEntityStandOnPos(BlockPos p_188555_1_) {
            if (this.entity instanceof EntityAquaticSpectrobe) {

                return this.world.getBlockState(p_188555_1_).getBlock() == Blocks.WATER
                        || !this.world.getBlockState(p_188555_1_.down()).isAir();
            }

            return !this.world.getBlockState(p_188555_1_.down()).isAir();
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
                this.dolphin.setMotion(this.dolphin.getMotion().add(0.0D, 0.005D, 0.0D));
            }

//            if (this.action == Action.MOVE_TO && !this.dolphin.getNavigator().noPath()) {
                double d0 = this.posX - this.dolphin.getPosX();
                double d1 = this.posY - this.dolphin.getPosY();
                double d2 = this.posZ - this.dolphin.getPosZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < 2.500000277905201E-7D) {
                    this.mob.setMoveForward(0.0F);
                } else {
                    float f = (float)(MathHelper.atan2(d2, d0) * 57.2957763671875D) - 90.0F;
                    this.dolphin.rotationYaw = this.limitAngle(this.dolphin.rotationYaw, f, 10.0F);
                    this.dolphin.renderYawOffset = this.dolphin.rotationYaw;
                    this.dolphin.rotationYawHead = this.dolphin.rotationYaw;
                    float f1 = (float)(this.speed * this.dolphin.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
                    if (this.dolphin.isInWater()) {
                        this.dolphin.setAIMoveSpeed(f1 * 0.5F);
                        float f2 = -((float)(MathHelper.atan2(d1, (double)MathHelper.sqrt(d0 * d0 + d2 * d2)) * 57.2957763671875D));
                        f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
                        this.dolphin.rotationPitch = this.limitAngle(this.dolphin.rotationPitch, f2, 5.0F);
                        float f3 = MathHelper.cos(this.dolphin.rotationPitch * 0.017453292F);
                        float f4 = MathHelper.sin(this.dolphin.rotationPitch * 0.017453292F);
                        this.dolphin.moveForward = f3 * f1;
                        this.dolphin.moveVertical = -f4 * f1;
                    } else {
                        this.dolphin.setAIMoveSpeed(f1);
                    }
                }
        }
    }

    public class SpectrobeLookController extends LookController {
        private final int field_205139_h;

        public SpectrobeLookController(MobEntity p_i48942_1_, int p_i48942_2_) {
            super(p_i48942_1_);
            this.field_205139_h = p_i48942_2_;
        }

        /**
         * Updates look
         */
        public void tick() {
            if (this.isLooking) {
                this.isLooking = false;
                this.mob.rotationYawHead = this.clampedRotate(this.mob.rotationYawHead, this.getTargetYaw() + 20.0F, this.deltaLookYaw);
                this.mob.rotationPitch = this.clampedRotate(this.mob.rotationPitch, this.getTargetPitch() + 10.0F, this.deltaLookPitch);
            } else {
                if (this.mob.getNavigator().noPath()) {
                    this.mob.rotationPitch = this.clampedRotate(this.mob.rotationPitch, 0.0F, 5.0F);
                }

                this.mob.rotationYawHead = this.clampedRotate(this.mob.rotationYawHead, this.mob.renderYawOffset, this.deltaLookYaw);
            }

            float f = MathHelper.wrapDegrees(this.mob.rotationYawHead - this.mob.renderYawOffset);
            if (f < (float)(-this.field_205139_h)) {
                this.mob.renderYawOffset -= 4.0F;
            } else if (f > (float)this.field_205139_h) {
                this.mob.renderYawOffset += 4.0F;
            }

        }
    }
}
