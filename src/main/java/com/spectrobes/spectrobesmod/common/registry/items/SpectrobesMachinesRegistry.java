package com.spectrobes.spectrobesmod.common.registry.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItemGroups;
import com.spectrobes.spectrobesmod.common.items.machines.CyrusShopBlockItem;
import com.spectrobes.spectrobesmod.common.items.machines.HealerBlockItem;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SpectrobesMachinesRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpectrobesInfo.MOD_ID);

    public static final RegistryObject<BlockItem> healer_block_item =
            ITEMS.register("healer_block_item",
                    () -> new HealerBlockItem(SpectrobesBlocks.healer_block.get(),
                            new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

    public static final RegistryObject<BlockItem> cyrus_shop_block_item =
            ITEMS.register("cyrus_shop_block_item",
                    () -> new CyrusShopBlockItem(SpectrobesBlocks.cyrus_shop_block.get(),
                            new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

}
