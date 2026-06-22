package com.spectrobes.spectrobesmod.common.registry.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.items.machines.renderer.CyrusShopBlockItemRenderer;
import com.spectrobes.spectrobesmod.client.items.machines.renderer.HealerBlockItemRenderer;
import com.spectrobes.spectrobesmod.common.items.machines.CyrusShopBlockItem;
import com.spectrobes.spectrobesmod.common.items.machines.HealerBlockItem;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SpectrobesMachinesRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, SpectrobesInfo.MOD_ID);

    public static final Supplier<BlockItem> healer_block_item =
            ITEMS.register("healer_block_item",
                    () -> new HealerBlockItem(SpectrobesBlocks.healer_block.get(),
                            new Item.Properties()));

    public static final Supplier<BlockItem> cyrus_shop_block_item =
            ITEMS.register("cyrus_shop_block_item",
                    () -> new CyrusShopBlockItem(SpectrobesBlocks.cyrus_shop_block.get(),
                            new Item.Properties()));
}
