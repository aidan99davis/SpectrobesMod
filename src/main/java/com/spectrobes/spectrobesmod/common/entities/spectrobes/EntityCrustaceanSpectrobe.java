package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.SpectrobeFindWaterGoal;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.pathfinding.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;


public abstract class EntityCrustaceanSpectrobe extends EntitySpectrobe {
    public EntityCrustaceanSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, World worldIn) {
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

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new SpectrobeFindWaterGoal(this));
        this.goalSelector.addGoal(9, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1));
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
            if (this.fish.isInWater()) {
                if(this.fish.getTarget() != null && this.fish.getTarget().getY() > this.fish.getY()) {
                    this.fish.getNavigation().setCanFloat(true);
                } else {
                    this.fish.getNavigation().setCanFloat(false);
                }
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

//            if (this.action == Action.MOVE_TO && !this.fish.getNavigator().noPath()) {
                double lvt_1_1_ = this.wantedX - this.fish.getX();
                double lvt_3_1_ = this.wantedY - this.fish.getY();
                double lvt_5_1_ = this.wantedZ - this.fish.getZ();
                double lvt_7_1_ = MathHelper.sqrt(lvt_1_1_ * lvt_1_1_ + lvt_3_1_ * lvt_3_1_ + lvt_5_1_ * lvt_5_1_);
                lvt_3_1_ /= lvt_7_1_;
                float lvt_9_1_ = (float)(MathHelper.atan2(lvt_5_1_, lvt_1_1_) * 57.2957763671875D) - 90.0F;
                this.fish.yRot = this.rotlerp(this.fish.yRot, lvt_9_1_, 90.0F);
                this.fish.yBodyRot = this.fish.yRot;
                float lvt_10_1_ = (float)(this.speedModifier * this.fish.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
                this.fish.setSpeed(MathHelper.lerp(0.125F, this.fish.getSpeed(), lvt_10_1_));
                this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0.0D, (double)this.fish.getSpeed() * lvt_3_1_ * 0.1D, 0.0D));
        }
    }

    private static class SpectrobeLookController extends LookController {
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
