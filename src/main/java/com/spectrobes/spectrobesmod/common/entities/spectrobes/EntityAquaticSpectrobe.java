package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;


public abstract class EntityAquaticSpectrobe extends EntitySpectrobe {
    public EntityAquaticSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void mate() {
        //todo: aquatic breeding. eggs? livebirth?
    }
}
