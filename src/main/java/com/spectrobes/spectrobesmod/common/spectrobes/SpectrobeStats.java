package com.spectrobes.spectrobesmod.common.spectrobes;

import com.spectrobes.spectrobesmod.common.items.minerals.MineralProperties;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;

public class SpectrobeStats {
    private int atkLevel;
    private int defLevel;
    private int hpLevel;
    private int mineralsEaten;
    private int battlesWon;

    private int level;
    private int xp_required;
    private int xp;

    public SpectrobeStats() {}

    public SpectrobeStats(int hpStat, int atkStat, int defStat) {
        this.hpLevel = hpStat;
        this.atkLevel = atkStat;
        this.defLevel = defStat;
        this.mineralsEaten = 0;
        this.battlesWon = 0;
        this.xp_required = 100;
        this.xp = 0;
        this.level = 1;
    }

    public SpectrobeStats(int hpStat, int atkStat, int defStat, int mineralsEaten, int battlesWon, int xp_required, int xp, int level) {
        this.hpLevel = hpStat;
        this.atkLevel = atkStat;
        this.defLevel = defStat;
        this.mineralsEaten = mineralsEaten;
        this.battlesWon = battlesWon;
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
        if(atkLevel + properties.getAtkOffset() > 0
                && defLevel + properties.getDefOffset() > 0
                && hpLevel + properties.getHpOffset() > 0) {
            atkLevel += properties.getAtkOffset();
            defLevel += properties.getDefOffset();
            hpLevel += properties.getHpOffset();
            mineralsEaten++;
        } else {
            Minecraft.getInstance().player.chat("Your spectrobe cannot eat this mineral");
        }

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

    public int getMineralsEaten() {
        return mineralsEaten;
    }

    public int getBattlesWon() {
        return battlesWon;
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
        statsNbt.putInt("mineralsEaten", getMineralsEaten());
        statsNbt.putInt("battlesWon", getBattlesWon());
        statsNbt.putInt("xp", getXp());
        statsNbt.putInt("xp_required", getXp_required());
        statsNbt.putInt("level", getLevel());

        return statsNbt;
    }

    public static SpectrobeStats read(CompoundNBT statsNbt) {
        SpectrobeStats stats = new SpectrobeStats();
        stats.atkLevel = statsNbt.getInt("atk");
        stats.defLevel = statsNbt.getInt("def");
        stats.hpLevel = statsNbt.getInt("hp");
        stats.mineralsEaten = statsNbt.getInt("mineralsEaten");
        stats.battlesWon = statsNbt.getInt("battlesWon");
        stats.xp = statsNbt.getInt("xp");
        stats.xp_required = statsNbt.getInt("xp_required");
        stats.level = statsNbt.getInt("level");

        return stats;
    }

    public SpectrobeStats copy() {
        return new SpectrobeStats(hpLevel, atkLevel, defLevel, mineralsEaten, battlesWon, xp_required, xp, level);
    }

    public void addStats(SpectrobeStats stats) {
        this.hpLevel += stats.getHpLevel();
        this.atkLevel += stats.getAtkLevel();
        this.defLevel += stats.getDefLevel();
    }

    public void addStats(KrawlProperties stats) {
        this.hpLevel += stats.getHpOffset();
        this.atkLevel += stats.getAtkOffset();
        this.defLevel += stats.getDefOffset();
        this.addXp(stats.getXpWorth());
        battlesWon++;
    }
}
