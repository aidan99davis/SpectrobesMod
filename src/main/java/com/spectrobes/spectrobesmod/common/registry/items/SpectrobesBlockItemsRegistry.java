package com.spectrobes.spectrobesmod.common.registry.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItemGroups;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SpectrobesBlockItemsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpectrobesInfo.MOD_ID);

    public static final RegistryObject<Item> mineral_block = ITEMS.register("mineral_block",
            () -> new BlockItem(SpectrobesBlocks.mineral_block.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> fossil_block = ITEMS.register("fossil_block",
            () -> new BlockItem(SpectrobesBlocks.fossil_block.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> metalium_ore = ITEMS.register("metalium_ore",
            () -> new BlockItem(SpectrobesBlocks.metalium_ore.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> titanium_ore = ITEMS.register("titanium_ore",
            () -> new BlockItem(SpectrobesBlocks.titanium_ore.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> marble_ore = ITEMS.register("marble_ore",
            () -> new BlockItem(SpectrobesBlocks.marble_ore.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> metalium_block_horizontal = ITEMS.register("metalium_block_horizontal",
            () -> new BlockItem(SpectrobesBlocks.metalium_block_horizontal.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> metalium_block_vertical = ITEMS.register("metalium_block_vertical",
            () -> new BlockItem(SpectrobesBlocks.metalium_block_vertical.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> titanium_block = ITEMS.register("titanium_block",
            () -> new BlockItem(SpectrobesBlocks.titanium_block.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> titanium_slab = ITEMS.register("titanium_slab",
            () -> new BlockItem(SpectrobesBlocks.titanium_slab.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> titanium_stairs = ITEMS.register("titanium_stairs",
            () -> new BlockItem(SpectrobesBlocks.titanium_stairs.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> minergy_lamp = ITEMS.register("minergy_lamp",
            () -> new BlockItem(SpectrobesBlocks.minergy_lamp.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> marble_block = ITEMS.register("marble_block",
            () -> new BlockItem(SpectrobesBlocks.marble_block.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> krawl_nest = ITEMS.register("krawl_nest",
            () -> new BlockItem(SpectrobesBlocks.krawl_nest.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> krawl_stone = ITEMS.register("krawl_stone",
            () -> new BlockItem(SpectrobesBlocks.krawl_stone.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> planetary_teleporter = ITEMS.register("planetary_teleporter",
            () -> new BlockItem(SpectrobesBlocks.planetary_teleporter.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> krawl_vine = ITEMS.register("krawl_vine",
            () -> new BlockItem(SpectrobesBlocks.krawl_vine.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> krawl_mycelium = ITEMS.register("krawl_mycelium",
            () -> new BlockItem(SpectrobesBlocks.krawl_mycelium.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> krawl_mud = ITEMS.register("krawl_mud",
            () -> new BlockItem(SpectrobesBlocks.krawl_mud.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> snag_log = ITEMS.register("snag_log",
            () -> new BlockItem(SpectrobesBlocks.snag_log.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> snag_planks = ITEMS.register("snag_planks",
            () -> new BlockItem(SpectrobesBlocks.snag_planks.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<Item> krawlshroom = ITEMS.register("krawlshroom",
            () -> new BlockItem(SpectrobesBlocks.krawlshroom.get(),
                    new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));
}
