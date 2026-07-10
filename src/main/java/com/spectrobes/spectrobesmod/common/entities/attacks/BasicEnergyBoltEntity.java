package com.spectrobes.spectrobesmod.common.entities.attacks;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/** An entity for the basic blaster energy bolt. */
public class BasicEnergyBoltEntity extends AbstractEnergyBoltEntity {

    public BasicEnergyBoltEntity(EntityType<? extends AbstractEnergyBoltEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
//        this.maxLifetime = 50; // <-- Define how long it takes this projectile to expire like this
    }
}
