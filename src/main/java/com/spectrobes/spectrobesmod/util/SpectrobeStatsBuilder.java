package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeStats;
import com.spectrobes.spectrobesmod.common.spectrobes.Stat;

public class SpectrobeStatsBuilder {
    private Stat health;
    private Stat attack;
    private Stat defence;
    private int increment;
    private int maxValue;

    public SpectrobeStatsBuilder withHealthStat(int base, int increment, int max) {
        this.health = new Stat(base, increment, max);
        return this;
    }
    public SpectrobeStatsBuilder withAttackStat(int base, int increment, int max) {
        this.attack = new Stat(base, increment, max);
        return this;
    }
    public SpectrobeStatsBuilder withDefenceStat(int base, int increment, int max) {
        this.defence = new Stat(base, increment, max);
        return this;
    }


    public SpectrobeStats build() {
        return new SpectrobeStats(health, attack, defence);
    }
}
