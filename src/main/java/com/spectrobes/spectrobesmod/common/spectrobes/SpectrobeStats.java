package com.spectrobes.spectrobesmod.common.spectrobes;

import com.spectrobes.spectrobesmod.common.items.minerals.MineralProperties;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

public class SpectrobeStats {
    private Stat health;
    private Stat attack;
    private Stat defence;
    private int mineralsEaten;
    private int battlesWon;

    private int level;
    private int xp_required;
    private int xp;

    public SpectrobeStats() {}

    public SpectrobeStats(Stat hpStat, Stat atkStat, Stat defStat) {
        this.health = hpStat;
        this.attack = atkStat;
        this.defence = defStat;
        this.mineralsEaten = 0;
        this.battlesWon = 0;
        this.xp_required = 100;
        this.xp = 0;
        this.level = 1;
    }

    public SpectrobeStats(Stat hpStat, Stat atkStat, Stat defStat, int mineralsEaten, int battlesWon, int xp_required, int xp, int level) {
        this.health = hpStat;
        this.attack = atkStat;
        this.defence = defStat;
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
            health.levelUp();
            attack.levelUp();
            defence.levelUp();
            return true;
        }
        return false;
    }

    public void applyMineral(MineralProperties properties) {
        addXp(properties.getXpWorth());
        if(attack.value() + properties.getAtkOffset() > 0
                && defence.value() + properties.getDefOffset() > 0
                && health.value() + properties.getHpOffset() > 0) {
            attack.add(properties.getAtkOffset());
            defence.add(properties.getDefOffset());
            health.add(properties.getHpOffset());
            mineralsEaten++;
        } else {
            if(Minecraft.getInstance().level.isClientSide()) {
                Minecraft.getInstance().player.chat("Your spectrobe cannot eat this mineral");
            }
        }

    }

    public int getAtkLevel() {
        return attack.value();
    }

    public int getDefLevel() {
        return defence.value();
    }

    public int getHpLevel() {
        return health.value();
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

        statsNbt.put("attack", attack.write());
        statsNbt.put("defence", defence.write());
        statsNbt.put("health", health.write());
        statsNbt.putInt("mineralsEaten", getMineralsEaten());
        statsNbt.putInt("battlesWon", getBattlesWon());
        statsNbt.putInt("xp", getXp());
        statsNbt.putInt("xp_required", getXp_required());
        statsNbt.putInt("level", getLevel());

        return statsNbt;
    }

    public static SpectrobeStats read(CompoundNBT statsNbt) {
        SpectrobeStats stats = new SpectrobeStats();
        stats.attack = Stat.read(statsNbt.getCompound("attack"));
        stats.defence = Stat.read(statsNbt.getCompound("defence"));
        stats.health = Stat.read(statsNbt.getCompound("health"));
        stats.mineralsEaten = statsNbt.getInt("mineralsEaten");
        stats.battlesWon = statsNbt.getInt("battlesWon");
        stats.xp = statsNbt.getInt("xp");
        stats.xp_required = statsNbt.getInt("xp_required");
        stats.level = statsNbt.getInt("level");

        return stats;
    }

    public SpectrobeStats copy() {
        return new SpectrobeStats(health, attack, defence, mineralsEaten, battlesWon, xp_required, xp, level);
    }

    //adds 1% of the defeated spectrobes stats to itself.
    public void addStats(SpectrobeStats stats) {
        this.health.add(stats.getHpLevel() / 100);
        this.attack.add(stats.getAtkLevel() / 100);
        this.defence.add(stats.getDefLevel() / 100);
    }

    public void addStats(KrawlProperties stats) {
        this.health.add(stats.getHpOffset());
        this.attack.add(stats.getAtkOffset());
        this.defence.add(stats.getDefOffset());
        this.addXp(stats.getXpWorth());
        battlesWon++;
    }

    public void setStatsOrBase(SpectrobeStats stats) {
        this.health.set(stats.health);
        this.attack.set(stats.attack);
        this.defence.set(stats.defence);
    }
}
