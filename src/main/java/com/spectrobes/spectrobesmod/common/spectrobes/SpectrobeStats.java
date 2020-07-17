package com.spectrobes.spectrobesmod.common.spectrobes;

import com.spectrobes.spectrobesmod.common.items.minerals.MineralProperties;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;

public class SpectrobeStats {
    private int atkLevel;
    private int defLevel;
    private int hpLevel;

    private int level;
    private int xp_required;
    private int xp;

    public SpectrobeStats(int hpStat, int atkStat, int defStat) {
        this.hpLevel = hpStat;
        this.atkLevel = atkStat;
        this.defLevel = defStat;
        this.xp_required = 100;
        this.xp = 0;
        this.level = 1;
    }

    public SpectrobeStats(int hpStat, int atkStat, int defStat, int xp_required, int xp, int level) {
        this.hpLevel = hpStat;
        this.atkLevel = atkStat;
        this.defLevel = defStat;
        this.xp_required = xp_required;
        this.xp = xp;
        this.level = level;
    }

    //returns true if levelled up.
    boolean addXp(int xp_to_add) {
        xp += xp_to_add;
        if(xp > xp_required) {
            xp -= xp_required;
            xp_required *= 1.2;
            level++;
            return true;
        }
        return false;
    }

    public void applyMineral(MineralProperties properties) {
        addXp(properties.getXpWorth());
        atkLevel += properties.getAtkOffset();
        defLevel += properties.getDefOffset();
        hpLevel += properties.getHpOffset();
    }
    public int getAtkLevel() {
        return atkLevel;
    }

    public int getDefLevel() {
        return defLevel;
    }

    public int getHpLevel() {
        return hpLevel;
    }

    public int getLevel() {
        return level;
    }

    public int getXp_required() {
        return xp_required;
    }

    public int getXp() {
        return xp;
    }

    public CompoundNBT write() {
        CompoundNBT statsNbt = new CompoundNBT();

        statsNbt.putInt("atk", getAtkLevel());
        statsNbt.putInt("def", getDefLevel());
        statsNbt.putInt("hp", getHpLevel());
        statsNbt.putInt("xp", getXp());
        statsNbt.putInt("xp_required", getXp_required());
        statsNbt.putInt("level", getLevel());

        return statsNbt;
    }

    public SpectrobeStats copy() {
        return new SpectrobeStats(hpLevel, atkLevel, defLevel, xp_required, xp, level);
    }
}
