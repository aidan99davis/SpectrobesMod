package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.MineralBuilder;
import com.spectrobes.spectrobesmod.util.MineralPropertiesBuilder;

public class MineralRegistry {
    public static Mineral MINERAL_POWER_C = null;

    public static void init()
    {
        MINERAL_POWER_C = (new MineralBuilder().withName("mineral_power_c").withMineralProperties(
                new MineralPropertiesBuilder()
                .setAtkOffset(2)
                .setNature(SpectrobeProperties.Nature.OTHER)
                .setXpWorth(10)).build());
    }
}
