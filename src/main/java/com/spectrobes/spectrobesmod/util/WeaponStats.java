package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;

public class WeaponStats {
    public int AtkDamage;
    public int Speed;
    public int Tier;
    public boolean IsBlaster;
    public SpectrobeProperties.Nature Nature = SpectrobeProperties.Nature.OTHER;

    public WeaponStats(int atkDamage, int speed, int tier, boolean isBlaster, SpectrobeProperties.Nature nature) {
        this.AtkDamage = atkDamage;
        this.Speed = speed;
        this.Tier = tier;
        this.IsBlaster = isBlaster;
        this.Nature = nature;
    }
}
