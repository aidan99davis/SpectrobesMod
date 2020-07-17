package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeStats;
import net.minecraft.nbt.CompoundNBT;

public class SpectrobeUtils {
    public static Spectrobe readFromNbt(CompoundNBT nbtData) {
        Spectrobe s = new Spectrobe();
        s.MasterUUID = nbtData.getUniqueId("MasterUUID");
        s.name = nbtData.get("name").getString();
        s.SpectrobeUUID = nbtData.getUniqueId("UUID");
        s.properties = nbtToProperties((CompoundNBT) nbtData.get("SpectrobeProperties"));

        SpectrobeStats stats = new SpectrobeStats();
        stats.read((CompoundNBT) nbtData.get("SpectrobeStats"));
        s.stats = stats;

        return s;
    }

    private static SpectrobeProperties nbtToProperties(CompoundNBT properties) {

        return new SpectrobeProperties(
                SpectrobeProperties.Nature.valueOf(properties.get("nature").getString()),
                SpectrobeProperties.Stage.valueOf(properties.get("stage").getString()));
    }

    private static SpectrobeStats nbtToStats(CompoundNBT properties) {
        int statsNbtAtk = properties.getInt("atk");
        int statsNbtDef = properties.getInt("def");
        int statsNbtHp = properties.getInt("hp");
        int statsNbtXp = properties.getInt("xp");
        int statsNbtXp_Required = properties.getInt("xp_required");
        int statsNbt_Level = properties.getInt("level");
        return new SpectrobeStats(statsNbtHp, statsNbtAtk, statsNbtDef, statsNbtXp_Required, statsNbtXp, statsNbt_Level);
    }
}
