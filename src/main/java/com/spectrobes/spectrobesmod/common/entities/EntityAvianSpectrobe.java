package com.spectrobes.spectrobesmod.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public abstract class EntityAvianSpectrobe extends EntitySpectrobe {
    public EntityAvianSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void mate() {
        //todo avian mating: eggs, clutch size, gestation time, requirements
    }
}
