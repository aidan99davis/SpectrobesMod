package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.MineralBuilder;
import com.spectrobes.spectrobesmod.util.MineralPropertiesBuilder;

public class MineralRegistry {
    public static Mineral MINERAL_POWER_C = null;
    public static Mineral MINERAL_POWER_B = null;
    public static Mineral MINERAL_POWER_A = null;

    public static void init()
    {
        MINERAL_POWER_C = (new MineralBuilder().withName("mineral_item_power_c").withMineralProperties(
                new MineralPropertiesBuilder()
                .setAtkOffset(8)
                .setNature(SpectrobeProperties.Nature.OTHER)
                .setXpWorth(10)).build());
        MINERAL_POWER_B = (new MineralBuilder().withName("mineral_item_power_b").withMineralProperties(
                new MineralPropertiesBuilder()
                        .setAtkOffset(16)
                        .setNature(SpectrobeProperties.Nature.OTHER)
                        .setXpWorth(15)).build());
        MINERAL_POWER_A = (new MineralBuilder().withName("mineral_item_power_a").withMineralProperties(
                new MineralPropertiesBuilder()
                        .setAtkOffset(24)
                        .setNature(SpectrobeProperties.Nature.OTHER)
                        .setXpWorth(20)).build());
    }
}
