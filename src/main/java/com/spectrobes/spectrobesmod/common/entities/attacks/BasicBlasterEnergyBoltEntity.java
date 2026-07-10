package com.spectrobes.spectrobesmod.common.entities.attacks;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.util.GeckoLibUtil;

/** An entity for the basic blaster energy bolt. */
public class BasicBlasterEnergyBoltEntity extends AbstractEnergyBoltEntity {

    public BasicBlasterEnergyBoltEntity(EntityType<? extends AbstractEnergyBoltEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.maxLifetime = 50; // <-- Define how long it takes this projectile to expire like this
    }
}
