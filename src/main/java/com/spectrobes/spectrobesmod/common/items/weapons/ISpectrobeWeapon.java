package com.spectrobes.spectrobesmod.common.items.weapons;

import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.util.WeaponStats;

public interface ISpectrobeWeapon extends IHasNature {
    WeaponStats GetWeaponStats();
}
