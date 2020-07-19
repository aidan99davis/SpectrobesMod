package com.spectrobes.spectrobesmod.common.entities;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public abstract class EntityAquaticSpectrobe extends EntitySpectrobe {
    public EntityAquaticSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void mate() {

    }
}
