package com.spectrobes.spectrobesmod.common.registry.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItemGroups;
import com.spectrobes.spectrobesmod.common.items.special.XellesTrophyItem;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SpectrobesItemsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpectrobesInfo.MOD_ID);

    public static final RegistryObject<Item> metalium = 
    		ITEMS.register("metalium",
    				() -> new Item(new Item.Properties().tab(SpectrobesItemGroups.SpectrobesMineralItemGroup.Instance)));
    
    public static final RegistryObject<Item> titanium = 
    		ITEMS.register("titanium",
    				() -> new Item(new Item.Properties().tab(SpectrobesItemGroups.SpectrobesMineralItemGroup.Instance)));
    
    public static final RegistryObject<Item> marble = 
    		ITEMS.register("marble",
    				() -> new Item(new Item.Properties().tab(SpectrobesItemGroups.SpectrobesMineralItemGroup.Instance)));


    @SuppressWarnings("unused")
    public static final RegistryObject<BlockItem> xelles_trophy_item =
            ITEMS.register("xelles_trophy_item",
                    () -> new XellesTrophyItem(SpectrobesBlocks.xelles_trophy.get(),
                            new Item.Properties().tab(SpectrobesItemGroups.SpectrobesBlocksItemGroup.Instance)));

}
