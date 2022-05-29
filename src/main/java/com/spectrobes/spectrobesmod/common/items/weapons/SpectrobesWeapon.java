package com.spectrobes.spectrobesmod.common.items.weapons;

import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.WeaponStats;
import net.minecraft.item.Item;

public abstract class SpectrobesWeapon extends Item implements IHasNature {

    public SpectrobesWeapon(Properties pProperties) {
        super(pProperties);
    }

    public abstract WeaponStats GetWeaponStats();

    public SpectrobeProperties.Nature getNature() {
        return GetWeaponStats().Nature;
    }
}
