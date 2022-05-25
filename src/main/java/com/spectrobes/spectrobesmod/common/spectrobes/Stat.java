package com.spectrobes.spectrobesmod.common.spectrobes;

import net.minecraft.nbt.CompoundNBT;

public class Stat {
    private int baseValue;
    private int increment;
    private int maxValue;
    private int currentValue;

    public Stat(int baseValue, int increment, int maxValue, int currentValue) {
        this.currentValue = currentValue;
        this.baseValue = baseValue;
        this.increment = increment;
        this.maxValue = maxValue;
    }

    public Stat(int baseValue, int increment, int maxValue) {
        this.currentValue = baseValue;
        this.baseValue = baseValue;
        this.increment = increment;
        this.maxValue = maxValue;
    }

    public void levelUp() {
        this.currentValue = this.currentValue + this.increment;
        if(this.currentValue > this.maxValue) this.currentValue = this.maxValue;
    }

    public void add(int statBoost) {
        this.currentValue = this.currentValue + statBoost;
        if(this.currentValue > this.maxValue) this.currentValue = this.maxValue;
    }

    public int value() {
        return this.currentValue;
    }

    public int max() {
        return this.maxValue;
    }

    public int base() {
        return this.baseValue;
    }


    public CompoundNBT write() {
        CompoundNBT statsNbt = new CompoundNBT();

        statsNbt.putInt("base", baseValue);
        statsNbt.putInt("increment", increment);
        statsNbt.putInt("max", maxValue);
        statsNbt.putInt("current", currentValue);

        return statsNbt;
    }

    public static Stat read(CompoundNBT statsNbt) {
        int baseVal = statsNbt.getInt("base");
        int inc = statsNbt.getInt("increment");
        int max = statsNbt.getInt("max");
        int current = statsNbt.getInt("current");

        return new Stat(baseVal, inc, max, current);
    }

    public Stat copy() {
        return new Stat(baseValue, increment, maxValue, currentValue);
    }

    public void set(int value) {
        if(value >= max()) this.currentValue = max();
        else this.currentValue = value;
    }

    public void set(Stat stat) {
        if(stat.currentValue > this.currentValue) {
            this.currentValue = stat.currentValue;
        }
        this.maxValue = stat.maxValue;
        this.baseValue = stat.baseValue;
        this.increment = stat.increment;
    }
}
