package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.SpectrobeRandomStrollGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.SpectrobeWaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;

public abstract class EntityMammalSpectrobe extends EntitySpectrobe {
    public EntityMammalSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SpectrobeRandomStrollGoal(this, 0.2d));
//        this.goalSelector.addGoal(2, new SwimGoal(this));
        this.goalSelector.addGoal(6, new SpectrobeWaterAvoidingRandomStrollGoal(this, 0.5d));
    }

    @Override
    protected int getMaxSchoolSize() {
        return 8;
    }

    public abstract int getLitterSize();

    @Override
    public void mate() {
        List<? extends EntityMammalSpectrobe> mates
                = level.getEntitiesOfClass(getSpectrobeClass(),
                this.getBoundingBox()
                        .inflate(10, 10, 10));
        if(mates.isEmpty() || mates.size() == 1) {
            return;
        }

        EntityMammalSpectrobe mate = null;

        for (EntityMammalSpectrobe spec : mates) {
            if(mate == null) {
                if(spec.getTicksTillMate() <= 100) {
                    mate = spec;
                }
            }
        }

        if(mate == null) {
            return;
        }

        this.entityData.set(HAS_MATED, true);

        this.setTicksTillMate(16000);
        mate.setTicksTillMate(16000);
        Random random = new Random();
        int litterSize = random.nextInt(getLitterSize());

        for(int i = 0; i < litterSize; i++) {
            EntitySpectrobe spectrobe = getChildForLineage()
                    .create(level);

            this.level.addFreshEntity(spectrobe);

            spectrobe.teleportTo(getX(), getY(), getZ());
        }
        //todo: live births, gestation time, litter count.
    }

}
