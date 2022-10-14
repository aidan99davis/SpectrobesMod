package com.spectrobes.spectrobesmod.common.items.weapons;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.WeaponStats;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;

public class BasicSwordItem extends SpectrobesWeapon {
    public BasicSwordItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, getControllerName(), 1, super::predicate));
    }

    @Override
    public WeaponStats GetWeaponStats() {
        return new WeaponStats(15, 1, 1, false, false, SpectrobeProperties.Nature.OTHER);
    }

    @Override
    public String getControllerName() {
        return "basic_sword";
    }
}
