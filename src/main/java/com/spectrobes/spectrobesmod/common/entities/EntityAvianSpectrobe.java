package com.spectrobes.spectrobesmod.common.entities;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class EntityAvianSpectrobe extends EntitySpectrobe {
    public EntityAvianSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void mate() {

    }
}
