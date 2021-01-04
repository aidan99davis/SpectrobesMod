package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SpectrobesItemsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpectrobesInfo.MOD_ID);

    public static final RegistryObject<BlockItem> grilda_fossil_item =
            ITEMS.register("grilda_fossil_item",
                    () -> new BlockItem(SpectrobesBlocks.grilda_fossil.get(),
                            new Item.Properties()
                                    .group(SpectrobesItems.SpectrobesFossilsItemGroup.Instance)));
}
