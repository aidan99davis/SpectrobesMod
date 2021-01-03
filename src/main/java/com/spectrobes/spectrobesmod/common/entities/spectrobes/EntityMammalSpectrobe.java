package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public abstract class EntityMammalSpectrobe extends EntitySpectrobe {
    public EntityMammalSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.2d));
        this.goalSelector.addGoal(6, new SwimGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 2));
    }

    public abstract int getLitterSize();

    @Override
    public void mate() {
        List<? extends EntityMammalSpectrobe> mates
                = world.getEntitiesWithinAABB(getSpectrobeClass(),
                this.getBoundingBox()
                        .grow(10, 10, 10));
        if(mates.isEmpty()) {
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

        this.dataManager.set(HAS_MATED, true);

        this.setTicksTillMate(16000);
        mate.setTicksTillMate(16000);
        Random random = new Random();
        int litterSize = random.nextInt(getLitterSize());

        for(int i = 0; i < litterSize; i++) {
            EntitySpectrobe spectrobe = getChildForLineage()
                    .create(world);

            this.world.addEntity(spectrobe);

            spectrobe.setPositionAndUpdate(getPosX(), getPosY(), getPosZ());
        }
        //todo: live births, gestation time, litter count.
    }

}
