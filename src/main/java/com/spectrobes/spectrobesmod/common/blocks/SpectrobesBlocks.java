package com.spectrobes.spectrobesmod.common.blocks;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Bus.MOD)
@ObjectHolder(SpectrobesInfo.MOD_ID)
public class SpectrobesBlocks {

    public static final Block mineral_block = null;
    public static final Block fossil_block = null;

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new MineralBlock());
        event.getRegistry().register(new FossilBlock());
    }

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new BlockItem(mineral_block,
                new Item.Properties().group(SpectrobesItems.SpectrobesItemGroup.Instance))
                .setRegistryName("mineral_block"));

        event.getRegistry().register(new BlockItem(fossil_block,
                new Item.Properties().group(SpectrobesItems.SpectrobesItemGroup.Instance))
                .setRegistryName("fossil_block"));

    }
}
