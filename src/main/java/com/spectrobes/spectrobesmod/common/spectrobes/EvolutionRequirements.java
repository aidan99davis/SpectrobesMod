package com.spectrobes.spectrobesmod.common.spectrobes;

import net.minecraft.nbt.CompoundTag;

public class EvolutionRequirements {
    private int level_required;
    private int minerals_eaten;
    private int battles_fought;

    public EvolutionRequirements() {}

    public EvolutionRequirements(
            int level_required,
            int minerals_eaten,
            int battles_fought) {
        this.level_required = level_required;
        this.minerals_eaten = minerals_eaten;
        this.battles_fought = battles_fought;
    }

    public boolean canEvolve(Spectrobe spectrobe) {
        if(spectrobe.stats.getLevel() < level_required) {
            return false;
        }
        if(spectrobe.stats.getMineralsEaten() < minerals_eaten) {
            return false;
        }
        if(spectrobe.stats.getBattlesWon() < battles_fought) {
            return false;
        }

        return true;
    }

    public CompoundTag write() {
        CompoundTag statsNbt = new CompoundTag();

        statsNbt.putInt("level_required", level_required);
        statsNbt.putInt("minerals_eaten", minerals_eaten);
        statsNbt.putInt("battles_fought", battles_fought);

        return statsNbt;
    }

    public static EvolutionRequirements read(CompoundTag statsNbt) {
        int level_required = statsNbt.getInt("level_required");
        int minerals_eaten = statsNbt.getInt("minerals_eaten");
        int battles_fought = statsNbt.getInt("battles_fought");
        EvolutionRequirements stats = new EvolutionRequirements(
                level_required,
                minerals_eaten,
                battles_fought
        );

        return stats;
    }

    public EvolutionRequirements copy() {
        return new EvolutionRequirements(level_required, minerals_eaten, battles_fought);
    }

    public int getMineralsRequired() {
        return minerals_eaten;
    }

    public int getBattlesRequired() {
        return battles_fought;
    }

    public int getLevelRequired() {
        return level_required;
    }
}
