package com.spectrobes.spectrobesmod.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class EntityKomainu extends EntitySpectrobe {

    public EntityKomainu(EntityType<?> entityTypeIn, World worldIn, SpectrobeProperties spectrobeProperties) {
        super(entityTypeIn, worldIn, spectrobeProperties);
    }

    @Override
    boolean canEvolve() {
        return false;
    }
}
