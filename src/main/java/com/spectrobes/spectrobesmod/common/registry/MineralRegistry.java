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
    public static Mineral MINERAL_HEALTH_C = null;
    public static Mineral MINERAL_HEALTH_B = null;
    public static Mineral MINERAL_HEALTH_A = null;
    public static Mineral MINERAL_HEALTH_A_PLUS = null;
    public static Mineral MINERAL_AGATE = null;
    public static Mineral MINERAL_AMBER = null;
    public static Mineral MINERAL_AZURITE = null;
    public static Mineral MINERAL_CITRINE = null;
    public static Mineral MINERAL_COBALT = null;
    public static Mineral MINERAL_CORAL = null;
    public static Mineral MINERAL_DIAMOND= null;
    public static Mineral MINERAL_EMERALD = null;
    public static Mineral MINERAL_FLUORITE = null;
    public static Mineral MINERAL_GARNET = null;
    public static Mineral MINERAL_GOLD = null;
    public static Mineral MINERAL_GRAPHITE = null;
    public static Mineral MINERAL_JADE = null;
    public static Mineral MINERAL_LAZULI = null;
    public static Mineral MINERAL_OPAL = null;
    public static Mineral MINERAL_ONYX = null;
    public static Mineral MINERAL_PEARL = null;
    public static Mineral MINERAL_PLATINUM = null;
    public static Mineral MINERAL_QUARTZ = null;
    public static Mineral MINERAL_RUBY = null;
    public static Mineral MINERAL_SAPPHIRE = null;
    public static Mineral MINERAL_SPINAL = null;
    public static Mineral MINERAL_SYLVITE = null;
    public static Mineral MINERAL_TOPAZ = null;
    public static Mineral MINERAL_ZIRCON = null;
    public static List<Mineral> ALL_MINERALS = new ArrayList<>();

    public static void init()
    {
        // Attack Minerals

        MINERAL_POWER_C = (new MineralBuilder()
                .withName("mineral_item_power_c")
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                    .setAtkOffset(8)
                    .setNature(SpectrobeProperties.Nature.OTHER)
                    .setXpWorth(10)
                    .setGuraWorth(5)).build());
        ALL_MINERALS.add(MINERAL_POWER_C);
        MINERAL_POWER_B = (new MineralBuilder()
                .withName("mineral_item_power_b")
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setAtkOffset(16)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(15)
                            .setGuraWorth(15)).build());
        ALL_MINERALS.add(MINERAL_POWER_B);
        MINERAL_POWER_A = (new MineralBuilder()
                .withName("mineral_item_power_a")
                .withRarity(Mineral.MineralRarity.Uncommon)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setAtkOffset(24)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(40)).build());
        ALL_MINERALS.add(MINERAL_POWER_A);
        MINERAL_POWER_A_PLUS = (new MineralBuilder()
                .withName("mineral_item_power_a_plus")
                .withRarity(Mineral.MineralRarity.Rare)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setAtkOffset(32)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(30)
                            .setGuraWorth(80)).build());
        ALL_MINERALS.add(MINERAL_POWER_A_PLUS);

        // Defence Minerals

        MINERAL_DEFENCE_C = (new MineralBuilder()
                .withName("mineral_item_defence_c")
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setDefOffset(8)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(10)
                            .setGuraWorth(5)).build());
        ALL_MINERALS.add(MINERAL_DEFENCE_C);
        MINERAL_DEFENCE_B = (new MineralBuilder()
                .withName("mineral_item_defence_b")
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setDefOffset(16)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(15)
                            .setGuraWorth(12))
                            .build());
        ALL_MINERALS.add(MINERAL_DEFENCE_B);
        MINERAL_DEFENCE_A = (new MineralBuilder()
                .withName("mineral_item_defence_a")
                .withRarity(Mineral.MineralRarity.Uncommon)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setDefOffset(24)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(30)).build());
        ALL_MINERALS.add(MINERAL_DEFENCE_A);
        MINERAL_DEFENCE_A_PLUS = (new MineralBuilder()
                .withName("mineral_item_defence_a_plus")
                .withRarity(Mineral.MineralRarity.Rare)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setDefOffset(32)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(30)
                            .setGuraWorth(60)).build());
        ALL_MINERALS.add(MINERAL_DEFENCE_A_PLUS);

        //Health Minerals

        MINERAL_HEALTH_C = (new MineralBuilder()
                .withName("mineral_item_health_c")
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(8)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(10)
                            .setGuraWorth(10)).build());
        ALL_MINERALS.add(MINERAL_HEALTH_C);
        MINERAL_HEALTH_B = (new MineralBuilder()
                .withName("mineral_item_health_b")
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(16)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(15)
                            .setGuraWorth(20)).build());
        ALL_MINERALS.add(MINERAL_HEALTH_B);
        MINERAL_HEALTH_A = (new MineralBuilder()
                .withName("mineral_item_health_a")
                .withRarity(Mineral.MineralRarity.Uncommon)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(24)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(50)).build());
        ALL_MINERALS.add(MINERAL_HEALTH_A);
        MINERAL_HEALTH_A_PLUS = (new MineralBuilder()
                .withName("mineral_item_health_a_plus")
                .withRarity(Mineral.MineralRarity.Rare)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(32)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(30)
                            .setGuraWorth(100)).build());
        ALL_MINERALS.add(MINERAL_HEALTH_A_PLUS);

        //Misc

        MINERAL_AGATE = (new MineralBuilder()
                .withName("mineral_item_agate")
                .withRarity(Mineral.MineralRarity.Uncommon)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setAtkOffset(8)
                            .setDefOffset(8)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(15)
                            .setGuraWorth(400)).build());
        ALL_MINERALS.add(MINERAL_AGATE);

        MINERAL_AMBER = (new MineralBuilder()
                .withName("mineral_item_amber")
                .withRarity(Mineral.MineralRarity.Uncommon)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setAtkOffset(16)
                            .setHpOffset(16)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(15)
                            .setGuraWorth(500)).build());
        ALL_MINERALS.add(MINERAL_AMBER);

        MINERAL_AZURITE = (new MineralBuilder()
                .withName("mineral_item_azurite")
                .withRarity(Mineral.MineralRarity.Uncommon)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setDefOffset(40)
                            .setHpOffset(-16)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(120)).build());
        ALL_MINERALS.add(MINERAL_AZURITE);

        MINERAL_CITRINE = (new MineralBuilder()
                .withName("mineral_item_citrine")
                .withRarity(Mineral.MineralRarity.Uncommon)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(40)
                            .setAtkOffset(-16)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(135)).build());
        ALL_MINERALS.add(MINERAL_CITRINE);

        MINERAL_COBALT = (new MineralBuilder()
                .withName("mineral_item_cobalt")
                .withRarity(Mineral.MineralRarity.Uncommon)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(-16)
                            .setAtkOffset(40)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(110)).build());
        ALL_MINERALS.add(MINERAL_COBALT);

        MINERAL_CORAL = (new MineralBuilder()
                .withName("mineral_item_coral")
                .withRarity(Mineral.MineralRarity.Uncommon)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(16)
                            .setDefOffset(16)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(500)).build());
        ALL_MINERALS.add(MINERAL_CORAL);

        MINERAL_DIAMOND = (new MineralBuilder()
                .withName("mineral_item_diamond")
                .withRarity(Mineral.MineralRarity.Rare)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(30)
                            .setGuraWorth(75000)).build());
        ALL_MINERALS.add(MINERAL_DIAMOND);

        MINERAL_EMERALD = (new MineralBuilder()
                .withName("mineral_item_emerald")
                .withRarity(Mineral.MineralRarity.Rare)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(36)
                            .setAtkOffset(36)
                            .setDefOffset(36)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(40)
                            .setGuraWorth(20000)).build());
        ALL_MINERALS.add(MINERAL_EMERALD);

        MINERAL_FLUORITE = (new MineralBuilder()
                .withName("mineral_item_fluorite")
                .withRarity(Mineral.MineralRarity.Uncommon)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(8)
                            .setDefOffset(8)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(300)).build());
        ALL_MINERALS.add(MINERAL_FLUORITE);

        MINERAL_GARNET = (new MineralBuilder()
                .withName("mineral_item_garnet")
                .withRarity(Mineral.MineralRarity.Uncommon)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setAtkOffset(-8)
                            .setDefOffset(30)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(25)
                            .setGuraWorth(40)).build());
        ALL_MINERALS.add(MINERAL_GARNET);

        MINERAL_GOLD = (new MineralBuilder()
                .withName("mineral_item_gold")
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(-15)
                            .setAtkOffset(-15)
                            .setDefOffset(-15)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(100000)).build());
        ALL_MINERALS.add(MINERAL_GOLD);

        MINERAL_GRAPHITE = (new MineralBuilder()
                .withName("mineral_item_graphite")
                .withRarity(Mineral.MineralRarity.Uncommon)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setAtkOffset(30)
                            .setDefOffset(-8)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(50)).build());
        ALL_MINERALS.add(MINERAL_GRAPHITE);

        MINERAL_JADE = (new MineralBuilder()
                .withName("mineral_item_jade")
                .withRarity(Mineral.MineralRarity.Uncommon)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(8)
                            .setAtkOffset(8)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(300)).build());
        ALL_MINERALS.add(MINERAL_JADE);

        MINERAL_LAZULI = (new MineralBuilder()
                .withName("mineral_item_lazuli")
                .withRarity(Mineral.MineralRarity.Uncommon)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setDefOffset(16)
                            .setAtkOffset(16)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(600)).build());
        ALL_MINERALS.add(MINERAL_LAZULI);

        MINERAL_ONYX = (new MineralBuilder()
                .withName("mineral_item_onyx")
                .withRarity(Mineral.MineralRarity.Uncommon)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setDefOffset(-16)
                            .setAtkOffset(40)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(100)).build());
        ALL_MINERALS.add(MINERAL_ONYX);

        MINERAL_OPAL = (new MineralBuilder()
                .withName("mineral_item_opal")
                .withRarity(Mineral.MineralRarity.Rare)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setAtkOffset(-8)
                            .setHpOffset(30)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(90)).build());
        ALL_MINERALS.add(MINERAL_OPAL);

        MINERAL_PEARL = (new MineralBuilder()
                .withName("mineral_item_pearl")
                .withRarity(Mineral.MineralRarity.Rare)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setAtkOffset(12)
                            .setHpOffset(12)
                            .setDefOffset(12)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(10000)).build());
        ALL_MINERALS.add(MINERAL_PEARL);

        MINERAL_PLATINUM = (new MineralBuilder()
                .withName("mineral_item_platinum")
                .withRarity(Mineral.MineralRarity.Rare)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setAtkOffset(5)
                            .setHpOffset(5)
                            .setDefOffset(5)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(25)
                            .setGuraWorth(3500)).build());
        ALL_MINERALS.add(MINERAL_PLATINUM);

        MINERAL_QUARTZ = (new MineralBuilder()
                .withName("mineral_item_quartz")
                .withRarity(Mineral.MineralRarity.Rare)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(40)
                            .setDefOffset(-16)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(25)
                            .setGuraWorth(150)).build());
        ALL_MINERALS.add(MINERAL_QUARTZ);

        MINERAL_RUBY = (new MineralBuilder()
                .withName("mineral_item_ruby")
                .withRarity(Mineral.MineralRarity.Rare)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(50)
                            .setAtkOffset(50)
                            .setDefOffset(50)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(40)
                            .setGuraWorth(25000)).build());
        ALL_MINERALS.add(MINERAL_RUBY);

        MINERAL_SAPPHIRE = (new MineralBuilder()
                .withName("mineral_item_sapphire")
                .withRarity(Mineral.MineralRarity.Rare)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(24)
                            .setAtkOffset(24)
                            .setDefOffset(24)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(20)
                            .setGuraWorth(15000)).build());
        ALL_MINERALS.add(MINERAL_SAPPHIRE);

        MINERAL_SPINAL = (new MineralBuilder()
                .withName("mineral_item_spinal")
                .withRarity(Mineral.MineralRarity.Rare)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(30)
                            .setAtkOffset(0)
                            .setDefOffset(-8)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(40)
                            .setGuraWorth(85)).build());
        ALL_MINERALS.add(MINERAL_SPINAL);

        MINERAL_SYLVITE = (new MineralBuilder()
                .withName("mineral_item_sylvite")
                .withRarity(Mineral.MineralRarity.Rare)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(-8)
                            .setAtkOffset(0)
                            .setDefOffset(30)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(40)
                            .setGuraWorth(70)).build());
        ALL_MINERALS.add(MINERAL_SYLVITE);

        MINERAL_TOPAZ = (new MineralBuilder()
                .withName("mineral_item_topaz")
                .withRarity(Mineral.MineralRarity.Rare)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(0)
                            .setAtkOffset(-16)
                            .setDefOffset(40)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(40)
                            .setGuraWorth(120)).build());
        ALL_MINERALS.add(MINERAL_TOPAZ);

        MINERAL_ZIRCON = (new MineralBuilder()
                .withName("mineral_item_zircon")
                .withRarity(Mineral.MineralRarity.Rare)
                .withMineralProperties(
                    new MineralPropertiesBuilder()
                            .setHpOffset(-8)
                            .setAtkOffset(30)
                            .setDefOffset(0)
                            .setNature(SpectrobeProperties.Nature.OTHER)
                            .setXpWorth(40)
                            .setGuraWorth(75)).build());
        ALL_MINERALS.add(MINERAL_ZIRCON);
    }
}
