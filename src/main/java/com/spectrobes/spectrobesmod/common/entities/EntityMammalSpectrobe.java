package com.spectrobes.spectrobesmod.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public abstract class EntityMammalSpectrobe extends EntitySpectrobe {
    public EntityMammalSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void mate() {

    }

}
