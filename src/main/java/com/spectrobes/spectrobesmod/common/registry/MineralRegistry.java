package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.MineralBuilder;
import com.spectrobes.spectrobesmod.util.MineralPropertiesBuilder;

import java.util.ArrayList;
import java.util.List;

public class MineralRegistry {
    public static Mineral MINERAL_POWER_C = null;
    public static Mineral MINERAL_POWER_B = null;
    public static Mineral MINERAL_POWER_A = null;
    public static Mineral MINERAL_POWER_A_PLUS = null;
    public static Mineral MINERAL_DEFENCE_C = null;
    public static Mineral MINERAL_DEFENCE_B = null;
    public static Mineral MINERAL_DEFENCE_A = null;
    public static Mineral MINERAL_DEFENCE_A_PLUS = null;
    public static List<Mineral> ALL_MINERALS = new ArrayList<>();

    public static void init()
    {
        MINERAL_POWER_C = (new MineralBuilder().withName("mineral_item_power_c").withMineralProperties(
                new MineralPropertiesBuilder()
                .setAtkOffset(8)
                .setNature(SpectrobeProperties.Nature.OTHER)
                .setXpWorth(10)).build());
        ALL_MINERALS.add(MINERAL_POWER_C);
        MINERAL_POWER_B = (new MineralBuilder().withName("mineral_item_power_b").withMineralProperties(
                new MineralPropertiesBuilder()
                        .setAtkOffset(16)
                        .setNature(SpectrobeProperties.Nature.OTHER)
                        .setXpWorth(15)).build());
        ALL_MINERALS.add(MINERAL_POWER_B);
        MINERAL_POWER_A = (new MineralBuilder().withName("mineral_item_power_a").withMineralProperties(
                new MineralPropertiesBuilder()
                        .setAtkOffset(24)
                        .setNature(SpectrobeProperties.Nature.OTHER)
                        .setXpWorth(20)).build());
        ALL_MINERALS.add(MINERAL_POWER_A);
        MINERAL_POWER_A_PLUS = (new MineralBuilder().withName("mineral_item_power_a_plus").withMineralProperties(
                new MineralPropertiesBuilder()
                        .setAtkOffset(32)
                        .setNature(SpectrobeProperties.Nature.OTHER)
                        .setXpWorth(30)).build());
        ALL_MINERALS.add(MINERAL_POWER_A_PLUS);
        MINERAL_DEFENCE_C = (new MineralBuilder().withName("mineral_item_defence_c").withMineralProperties(
                new MineralPropertiesBuilder()
                        .setDefOffset(8)
                        .setNature(SpectrobeProperties.Nature.OTHER)
                        .setXpWorth(10)).build());
        ALL_MINERALS.add(MINERAL_DEFENCE_C);
        MINERAL_DEFENCE_B = (new MineralBuilder().withName("mineral_item_defence_b").withMineralProperties(
                new MineralPropertiesBuilder()
                        .setDefOffset(16)
                        .setNature(SpectrobeProperties.Nature.OTHER)
                        .setXpWorth(15)).build());
        ALL_MINERALS.add(MINERAL_DEFENCE_B);
        MINERAL_DEFENCE_A = (new MineralBuilder().withName("mineral_item_defence_a").withMineralProperties(
                new MineralPropertiesBuilder()
                        .setDefOffset(24)
                        .setNature(SpectrobeProperties.Nature.OTHER)
                        .setXpWorth(20)).build());
        ALL_MINERALS.add(MINERAL_DEFENCE_A);
        MINERAL_DEFENCE_A_PLUS = (new MineralBuilder().withName("mineral_item_defence_a_plus").withMineralProperties(
                new MineralPropertiesBuilder()
                        .setDefOffset(32)
                        .setNature(SpectrobeProperties.Nature.OTHER)
                        .setXpWorth(30)).build());
        ALL_MINERALS.add(MINERAL_DEFENCE_A_PLUS);
    }
}
