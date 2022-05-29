package com.spectrobes.spectrobesmod.common.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.items.renderer.HealerBlockItemRenderer;
import com.spectrobes.spectrobesmod.client.items.weapons.renderer.BasicSwordItemRenderer;
import com.spectrobes.spectrobesmod.common.items.machines.HealerBlockItem;
import com.spectrobes.spectrobesmod.common.items.weapons.BasicSwordItem;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
import com.spectrobes.spectrobesmod.common.items.minerals.SpecialMineralItem;
import com.spectrobes.spectrobesmod.common.items.minerals.chroma.ChromaMineralItem;
import com.spectrobes.spectrobesmod.common.items.tools.PrizmodItem;
import com.spectrobes.spectrobesmod.common.registry.MineralRegistry;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesItemsRegistry;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.spectrobes.spectrobesmod.common.registry.SpectrobesItemsRegistry.ITEMS;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Bus.MOD)
@ObjectHolder(SpectrobesInfo.MOD_ID)
public class SpectrobesItems {
    //Minerals
    public static final Item mineral_item_power_c = null;
    public static final Item mineral_item_power_b = null;
    public static final Item mineral_item_power_a = null;
    public static final Item mineral_item_power_a_plus = null;
    public static final Item mineral_item_defence_c = null;
    public static final Item mineral_item_defence_b = null;
    public static final Item mineral_item_defence_a = null;
    public static final Item mineral_item_defence_a_plus = null;
    public static final Item mineral_item_health_c = null;
    public static final Item mineral_item_health_b = null;
    public static final Item mineral_item_health_a = null;
    public static final Item mineral_item_health_a_plus = null;
    public static final Item mineral_item_agate = null;
    public static final Item mineral_item_amber = null;
    public static final Item mineral_item_azurite = null;
    public static final Item mineral_item_citrine = null;
    public static final Item mineral_item_cobalt = null;
    public static final Item mineral_item_coral = null;
    public static final Item mineral_item_diamond = null;
    public static final Item mineral_item_emerald = null;
    public static final Item mineral_item_fluorite = null;
    public static final Item mineral_item_garnet = null;
    public static final Item mineral_item_gold = null;
    public static final Item mineral_item_graphite = null;
    public static final Item mineral_item_jade = null;
    public static final Item mineral_item_lazuli = null;
    public static final Item mineral_item_onyx = null;
    public static final Item mineral_item_opal = null;
    public static final Item mineral_item_pearl = null;
    public static final Item mineral_item_platinum = null;
    public static final Item mineral_item_quartz = null;
    public static final Item mineral_item_ruby = null;
    public static final Item mineral_item_sapphire = null;
    public static final Item mineral_item_spinal = null;
    public static final Item mineral_item_sylvite = null;
    public static final Item mineral_item_topaz = null;
    public static final Item mineral_item_zircon = null;
    public static final Item chroma_mineral_item_zero = null;
    public static final Item chroma_mineral_item_one = null;
    public static final Item chroma_mineral_item_two = null;

    //Tools
    public static final Item prizmod_item = null;

    //Weapons
    public static final Item basic_sword = null;

    private static final HashMap<Mineral.MineralRarity, List<Item>> all_minerals = new HashMap<>();

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        registerMinerals(event);

        registerBlockItems(event);

        registerTools(event);
    }

    private static void registerWeapons(RegistryEvent.Register<Item> event) {
    }

    private static void registerTools(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(
                new PrizmodItem(
                        new Item.Properties()
                                .tab(SpectrobesToolsItemGroup.Instance)));
    }

    private static void registerBlockItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new BlockItem(SpectrobesBlocks.mineral_block.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("mineral_block"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.fossil_block.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("fossil_block"));
    }

    private static void registerMinerals(RegistryEvent.Register<Item> event) {
        MineralRegistry.init();

        all_minerals.put(Mineral.MineralRarity.Common, new ArrayList<>());
        all_minerals.put(Mineral.MineralRarity.Uncommon, new ArrayList<>());
        all_minerals.put(Mineral.MineralRarity.Rare, new ArrayList<>());

        for(Mineral m : MineralRegistry.ALL_MINERALS) {
            registerMineral(event, m);
        }

        SpecialMineralItem specialMineralItem;

        specialMineralItem = new ChromaMineralItem(
                new Item.Properties()
                        .tab(SpectrobesMineralItemGroup.Instance),
                "chroma_mineral_item_zero",
                            0);

        event.getRegistry().register(specialMineralItem);

        specialMineralItem = new ChromaMineralItem(
                new Item.Properties()
                        .tab(SpectrobesMineralItemGroup.Instance),
                "chroma_mineral_item_one",
                1);

        event.getRegistry().register(specialMineralItem);

        specialMineralItem = new ChromaMineralItem(
                new Item.Properties()
                        .tab(SpectrobesMineralItemGroup.Instance),
                "chroma_mineral_item_two",
                2);

        event.getRegistry().register(specialMineralItem);
    }

    static void registerMineral(final RegistryEvent.Register<Item> event, Mineral mineral) {
        Item newItem = new MineralItem(
                new Item.Properties()
                        .tab(SpectrobesMineralItemGroup.Instance),
                mineral.name,
                mineral.properties.copy());
        event.getRegistry().register(newItem);
        List<Item> list = all_minerals.get(mineral.rarity);
        list.add(newItem);
        all_minerals.put(mineral.rarity, list);
    }

    public static ItemStack getRandomMineral(Mineral.MineralRarity rarity) {
        Random random = new Random();
        int index = random.nextInt(all_minerals.get(rarity).size());
        return new ItemStack(all_minerals.get(rarity).get(index));
    }

    //Creative Tabs

    public static class SpectrobesBlocksItemGroup extends ItemGroup {

        public static final SpectrobesBlocksItemGroup Instance = new SpectrobesBlocksItemGroup(ItemGroup.TABS.length, "spectrobestab.blocks");

        public SpectrobesBlocksItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesBlocks.fossil_block.get().asItem().getDefaultInstance();
        }
    }

    public static class SpectrobesFossilsItemGroup extends ItemGroup {

        public static final SpectrobesFossilsItemGroup Instance = new SpectrobesFossilsItemGroup(ItemGroup.TABS.length, "spectrobestab.fossils");

        public SpectrobesFossilsItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesItemsRegistry.komainu_fossil_item.get().getDefaultInstance();
        }
    }

    public static class SpectrobesWeaponsItemGroup extends ItemGroup {

        public static final SpectrobesWeaponsItemGroup Instance = new SpectrobesWeaponsItemGroup(ItemGroup.TABS.length, "spectrobestab.weapons");

        public SpectrobesWeaponsItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesItemsRegistry.basic_sword_item.get().getDefaultInstance();
        }
    }

    public static class SpectrobesMineralItemGroup extends ItemGroup {

        public static final SpectrobesMineralItemGroup Instance = new SpectrobesMineralItemGroup(ItemGroup.TABS.length, "spectrobestab.minerals");

        public SpectrobesMineralItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesItems.mineral_item_power_c.getDefaultInstance();
        }
    }
    public static class SpectrobesToolsItemGroup extends ItemGroup {

        public static final SpectrobesToolsItemGroup Instance = new SpectrobesToolsItemGroup(ItemGroup.TABS.length, "spectrobestab.tools");

        public SpectrobesToolsItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesItems.prizmod_item.getDefaultInstance();
        }
    }

}
