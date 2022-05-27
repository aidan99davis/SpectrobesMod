package com.spectrobes.spectrobesmod.common.items.minerals;

public class Mineral {
    public String name;
    public MineralRarity rarity;
    public MineralProperties properties;

    public enum MineralRarity {
        Rare,
        Uncommon,
        Common
    }
}
