package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;

public class WeaponStats {
    public enum WeaponType {
        OTHER,
        SWORD,
        GLOVE,
        BLASTER
    }

    public int AtkDamage;
    public int Speed;
    public int Tier;
    public WeaponType Type;
    public SpectrobeProperties.Nature Nature;

    public WeaponStats(int atkDamage, int speed, int tier, WeaponType type, SpectrobeProperties.Nature nature) {
        this.AtkDamage = atkDamage;
        this.Speed = speed;
        this.Tier = tier;
        this.Type = type;
        this.Nature = nature;
    }
}
