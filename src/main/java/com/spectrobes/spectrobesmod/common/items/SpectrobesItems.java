package com.spectrobes.spectrobesmod.common.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesItemsRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Bus.MOD)
public class SpectrobesItems {

    private static void registerBlockItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new BlockItem(SpectrobesBlocks.mineral_block.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("mineral_block"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.fossil_block.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("fossil_block"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.metalium_ore.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("metalium_ore"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.titanium_ore.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("titanium_ore"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.marble_ore.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("marble_ore"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.metalium_block_horizontal.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("metalium_block_horizontal"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.metalium_block_vertical.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("metalium_block_vertical"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.titanium_block.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("titanium_block"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.titanium_slab.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("titanium_slab"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.titanium_stairs.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("titanium_stairs"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.minergy_lamp.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("minergy_lamp"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.marble_block.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("marble_block"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.krawl_nest.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("krawl_nest"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.krawl_stone.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("krawl_stone"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.planetary_teleporter.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("planetary_teleporter"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.krawl_vine.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("krawl_vine"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.krawl_mycelium.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("krawl_mycelium"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.krawl_mud.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("krawl_mud"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.snag_log.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("snag_log"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.snag_planks.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("snag_planks"));

        event.getRegistry().register(new BlockItem(SpectrobesBlocks.krawlshroom.get(),
                new Item.Properties().tab(SpectrobesItems.SpectrobesBlocksItemGroup.Instance))
                .setRegistryName("krawlshroom"));
    }

    //Creative Tabs

    public static class SpectrobesBlocksItemGroup extends CreativeModeTab {

        public static final SpectrobesBlocksItemGroup Instance = new SpectrobesBlocksItemGroup(CreativeModeTab.TABS.length, "spectrobestab.blocks");

        public SpectrobesBlocksItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesBlocks.fossil_block.get().asItem().getDefaultInstance();
        }
    }

    public static class SpectrobesFossilsItemGroup extends CreativeModeTab {

        public static final SpectrobesFossilsItemGroup Instance = new SpectrobesFossilsItemGroup(CreativeModeTab.TABS.length, "spectrobestab.fossils");

        public SpectrobesFossilsItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesItemsRegistry.komainu_fossil_item.get().getDefaultInstance();
        }
    }

    public static class SpectrobesWeaponsItemGroup extends CreativeModeTab {

        public static final SpectrobesWeaponsItemGroup Instance = new SpectrobesWeaponsItemGroup(CreativeModeTab.TABS.length, "spectrobestab.weapons");

        public SpectrobesWeaponsItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesItemsRegistry.basic_sword_item.get().getDefaultInstance();
        }
    }

    public static class SpectrobesMineralItemGroup extends CreativeModeTab {

        public static final SpectrobesMineralItemGroup Instance = new SpectrobesMineralItemGroup(CreativeModeTab.TABS.length, "spectrobestab.minerals");

        public SpectrobesMineralItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesItems.mineral_item_power_c.getDefaultInstance();
        }
    }
    public static class SpectrobesToolsItemGroup extends CreativeModeTab {

        public static final SpectrobesToolsItemGroup Instance = new SpectrobesToolsItemGroup(CreativeModeTab.TABS.length, "spectrobestab.tools");

        public SpectrobesToolsItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesItems.prizmod_item.getDefaultInstance();
        }
    }
    public static class SpectrobesArmourItemGroup extends CreativeModeTab {

        public static final SpectrobesArmourItemGroup Instance = new SpectrobesArmourItemGroup(CreativeModeTab.TABS.length, "spectrobestab.armour");

        public SpectrobesArmourItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesItemsRegistry.BASIC_CHEST.get().getDefaultInstance();
        }
    }

}
