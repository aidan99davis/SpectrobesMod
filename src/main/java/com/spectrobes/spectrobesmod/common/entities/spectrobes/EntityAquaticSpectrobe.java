package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.AquaticJumpGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.SpectrobeFindWaterGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.SpectrobeRandomSwimmingGoal;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import java.util.List;
import java.util.Random;

public abstract class EntityAquaticSpectrobe extends EntitySpectrobe {
    public EntityAquaticSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.WALKABLE, 0.0F);
        this.moveControl = new AquaticSpectrobeMoveController(this);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
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

    protected PathNavigation createNavigation(Level pLevel) {
        return new AmphibiousPathNavigation(this, pLevel);
    }

    protected abstract boolean isShallowSwimmer();

    @Override
    protected void registerGoals() {
        super.registerGoals();
//        this.goalSelector.addGoal(0, new SwimWithPlayerGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new SpectrobeRandomSwimmingGoal(this, 1D, 10));
        this.goalSelector.addGoal(4, new SpectrobeFindWaterGoal(this));
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


    static class AquaticSpectrobeMoveController extends MoveControl {
        private final EntityAquaticSpectrobe spectrobe;

        AquaticSpectrobeMoveController(EntityAquaticSpectrobe pSpectrobe) {
            super(pSpectrobe);
            this.spectrobe = pSpectrobe;
        }

        private void updateSpeed() {
            if (this.spectrobe.isInWater()) {
                this.spectrobe.setDeltaMovement(this.spectrobe.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }
        }

        public void tick() {
            this.updateSpeed();
            if (this.operation == MoveControl.Operation.MOVE_TO && !this.spectrobe.getNavigation().isDone()) {
                double d0 = this.wantedX - this.spectrobe.getX();
                double d1 = this.wantedY - this.spectrobe.getY();
                double d2 = this.wantedZ - this.spectrobe.getZ();
                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 /= d3;
                float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.spectrobe.setYRot(this.rotlerp(this.spectrobe.getYRot(), f, 90.0F));
                this.spectrobe.yBodyRot = this.spectrobe.getYRot();
                float f1 = (float)(this.speedModifier * this.spectrobe.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.spectrobe.setSpeed(Mth.lerp(0.125F, this.spectrobe.getSpeed(), f1));
                this.spectrobe.setDeltaMovement(this.spectrobe.getDeltaMovement().add(0.0D, (double)this.spectrobe.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.spectrobe.setSpeed(0.0F);
            }
        }
    }
}
