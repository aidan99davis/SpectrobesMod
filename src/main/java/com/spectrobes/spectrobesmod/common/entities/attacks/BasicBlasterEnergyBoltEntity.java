package com.spectrobes.spectrobesmod.common.entities.attacks;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/** An entity for the basic blaster energy bolt. */
public class BasicBlasterEnergyBoltEntity extends AbstractEnergyBoltEntity {


    public BasicBlasterEnergyBoltEntity(EntityType<? extends AbstractEnergyBoltEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.maxLifetime = 120; // <-- Define how long it takes this projectile to expire like this
    }
}
