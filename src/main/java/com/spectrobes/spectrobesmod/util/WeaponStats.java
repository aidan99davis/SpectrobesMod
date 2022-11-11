package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;

public class WeaponStats {
    public int AtkDamage;
    public int Speed;
    public int Tier;
    public boolean IsBlaster;
    public boolean IsGlove;
    public SpectrobeProperties.Nature Nature;

    public WeaponStats(int atkDamage, int speed, int tier, boolean isBlaster, boolean isGlove, SpectrobeProperties.Nature nature) {
        this.AtkDamage = atkDamage;
        this.Speed = speed;
        this.Tier = tier;
        this.IsBlaster = isBlaster;
        this.IsGlove = isGlove;
        this.Nature = nature;
    }
}
