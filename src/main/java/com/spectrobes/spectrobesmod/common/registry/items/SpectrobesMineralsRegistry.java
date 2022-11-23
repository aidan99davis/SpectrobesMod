package com.spectrobes.spectrobesmod.common.registry.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
import com.spectrobes.spectrobesmod.common.items.minerals.chroma.ChromaMineralItem;
import com.spectrobes.spectrobesmod.common.registry.MineralRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.spectrobes.spectrobesmod.common.registry.MineralRegistry.*;

public class SpectrobesMineralsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpectrobesInfo.MOD_ID);
    public static final HashMap<Mineral.MineralRarity, List<Item>> all_minerals = new HashMap<>();

    public static final RegistryObject<Item> mineral_item_power_c =
            ITEMS.register("mineral_item_power_c",
                    () -> new MineralItem(MINERAL_POWER_C));

    public static final RegistryObject<Item> mineral_item_power_b =
            ITEMS.register("mineral_item_power_b",
                    () -> new MineralItem(MINERAL_POWER_B));

    public static final RegistryObject<Item> mineral_item_power_a =
            ITEMS.register("mineral_item_power_a",
                    () -> new MineralItem(MINERAL_POWER_A));

    public static final RegistryObject<Item> mineral_item_power_a_plus =
            ITEMS.register("mineral_item_power_a_plus",
                    () -> new MineralItem(MINERAL_POWER_A_PLUS));
    public static final RegistryObject<Item> mineral_item_defence_c =
            ITEMS.register("mineral_item_defence_c",
                    () -> new MineralItem(MINERAL_DEFENCE_C));

    public static final RegistryObject<Item> mineral_item_defence_b =
            ITEMS.register("mineral_item_defence_b",
                    () -> new MineralItem(MINERAL_DEFENCE_B));

    public static final RegistryObject<Item> mineral_item_defence_a =
            ITEMS.register("mineral_item_defence_a",
                    () -> new MineralItem(MINERAL_DEFENCE_A));

    public static final RegistryObject<Item> mineral_item_defence_a_plus =
            ITEMS.register("mineral_item_defence_a_plus",
                    () -> new MineralItem(MINERAL_DEFENCE_A_PLUS));

    public static final RegistryObject<Item> mineral_item_health_c =
            ITEMS.register("mineral_item_health_c",
                    () -> new MineralItem(MINERAL_HEALTH_C));

    public static final RegistryObject<Item> mineral_item_health_b =
            ITEMS.register("mineral_item_health_b",
                    () -> new MineralItem(MINERAL_HEALTH_B));

    public static final RegistryObject<Item> mineral_item_health_a =
            ITEMS.register("mineral_item_health_a",
                    () -> new MineralItem(MINERAL_HEALTH_A));

    public static final RegistryObject<Item> mineral_item_health_a_plus =
            ITEMS.register("mineral_item_health_a_plus",
                    () -> new MineralItem(MINERAL_HEALTH_A_PLUS));

    public static final RegistryObject<Item> mineral_item_agate =
            ITEMS.register("mineral_item_agate",
                    () -> new MineralItem(MINERAL_AGATE));

    public static final RegistryObject<Item> mineral_item_amber =
            ITEMS.register("mineral_item_amber",
                    () -> new MineralItem(MINERAL_AMBER));

    public static final RegistryObject<Item> mineral_item_azurite =
            ITEMS.register("mineral_item_azurite",
                    () -> new MineralItem(MINERAL_AZURITE));

    public static final RegistryObject<Item> mineral_item_citrine =
            ITEMS.register("mineral_item_citrine",
                    () -> new MineralItem(MINERAL_CITRINE));

    public static final RegistryObject<Item> mineral_item_cobalt =
            ITEMS.register("mineral_item_cobalt",
                    () -> new MineralItem(MINERAL_COBALT));

    public static final RegistryObject<Item> mineral_item_coral =
            ITEMS.register("mineral_item_coral",
                    () -> new MineralItem(MINERAL_CORAL));

    public static final RegistryObject<Item> mineral_item_diamond =
            ITEMS.register("mineral_item_diamond",
                    () -> new MineralItem(MINERAL_DIAMOND));

    public static final RegistryObject<Item> mineral_item_emerald =
            ITEMS.register("mineral_item_emerald",
                    () -> new MineralItem(MINERAL_EMERALD));

    public static final RegistryObject<Item> mineral_item_fluorite =
            ITEMS.register("mineral_item_fluorite",
                    () -> new MineralItem(MINERAL_FLUORITE));

    public static final RegistryObject<Item> mineral_item_garnet =
            ITEMS.register("mineral_item_garnet",
                    () -> new MineralItem(MINERAL_GARNET));

    public static final RegistryObject<Item> mineral_item_gold =
            ITEMS.register("mineral_item_gold",
                    () -> new MineralItem(MINERAL_GOLD));

    public static final RegistryObject<Item> mineral_item_graphite =
            ITEMS.register("mineral_item_graphite",
                    () -> new MineralItem(MINERAL_GRAPHITE));

    public static final RegistryObject<Item> mineral_item_jade =
            ITEMS.register("mineral_item_jade",
                    () -> new MineralItem(MINERAL_JADE));

    public static final RegistryObject<Item> mineral_item_lazuli =
            ITEMS.register("mineral_item_lazuli",
                    () -> new MineralItem(MINERAL_LAZULI));

    public static final RegistryObject<Item> mineral_item_onyx =
            ITEMS.register("mineral_item_onyx",
                    () -> new MineralItem(MINERAL_ONYX));

    public static final RegistryObject<Item> mineral_item_opal =
            ITEMS.register("mineral_item_opal",
                    () -> new MineralItem(MINERAL_OPAL));

    public static final RegistryObject<Item> mineral_item_pearl =
            ITEMS.register("mineral_item_pearl",
                    () -> new MineralItem(MINERAL_PEARL));

    public static final RegistryObject<Item> mineral_item_platinum =
            ITEMS.register("mineral_item_platinum",
                    () -> new MineralItem(MINERAL_PLATINUM));

    public static final RegistryObject<Item> mineral_item_quartz =
            ITEMS.register("mineral_item_quartz",
                    () -> new MineralItem(MINERAL_QUARTZ));

    public static final RegistryObject<Item> mineral_item_ruby =
            ITEMS.register("mineral_item_ruby",
                    () -> new MineralItem(MINERAL_RUBY));

    public static final RegistryObject<Item> mineral_item_sapphire =
            ITEMS.register("mineral_item_sapphire",
                    () -> new MineralItem(MINERAL_SAPPHIRE));

    public static final RegistryObject<Item> mineral_item_spinal =
            ITEMS.register("mineral_item_spinal",
                    () -> new MineralItem(MINERAL_SPINAL));

    public static final RegistryObject<Item> mineral_item_sylvite =
            ITEMS.register("mineral_item_sylvite",
                    () -> new MineralItem(MINERAL_SYLVITE));

    public static final RegistryObject<Item> mineral_item_topaz =
            ITEMS.register("mineral_item_topaz",
                    () -> new MineralItem(MINERAL_TOPAZ));

    public static final RegistryObject<Item> mineral_item_zircon =
            ITEMS.register("mineral_item_zircon",
                    () -> new MineralItem(MINERAL_ZIRCON));

    public static final RegistryObject<Item> chroma_mineral_item_zero =
            ITEMS.register("chroma_mineral_item_zero",
                    () -> new ChromaMineralItem(0));

    public static final RegistryObject<Item> chroma_mineral_item_one =
            ITEMS.register("chroma_mineral_item_one",
                    () -> new ChromaMineralItem(1));

    public static final RegistryObject<Item> chroma_mineral_item_two =
            ITEMS.register("chroma_mineral_item_two",
                    () -> new ChromaMineralItem(2));

    public static void init() {
        MineralRegistry.init();

        all_minerals.put(Mineral.MineralRarity.Common, new ArrayList<>());
        all_minerals.put(Mineral.MineralRarity.Uncommon, new ArrayList<>());
        all_minerals.put(Mineral.MineralRarity.Rare, new ArrayList<>());
    }

    public static ItemStack getMineralByRegistryName(String name) {
        List<Item> allMinerals = new ArrayList<>();

        if(name.contains("chroma")) {
            if(name.endsWith("zero")) {
                return chroma_mineral_item_zero.get().getDefaultInstance();
            } else if(name.endsWith("one")) {
                return chroma_mineral_item_one.get().getDefaultInstance();
            } else if(name.endsWith("two")) {
                return chroma_mineral_item_two.get().getDefaultInstance();
            }
        }

        all_minerals.values().forEach(allMinerals::addAll);

        return allMinerals.stream().filter(item ->
                ((MineralItem)item.asItem()).mineral.name.equals(name))
                .collect(Collectors.toList())
                .stream()
                .findFirst()
                .get()
                .getDefaultInstance();
    }

    public static ItemStack getRandomMineral(Mineral.MineralRarity rarity) {
        Random random = new Random();
        int index = random.nextInt(all_minerals.get(rarity).size());
        return new ItemStack(all_minerals.get(rarity).get(index));
    }
}
