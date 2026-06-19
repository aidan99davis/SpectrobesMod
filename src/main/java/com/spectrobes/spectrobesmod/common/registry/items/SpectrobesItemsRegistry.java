package com.spectrobes.spectrobesmod.common.registry.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItemGroups;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SpectrobesItemsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, SpectrobesInfo.MOD_ID);

    public static final Supplier<Item> metalium =
    		ITEMS.register("metalium",
    				() -> new Item(new Item.Properties().tab(SpectrobesItemGroups.SpectrobesMineralItemGroup.Instance)));
    
    public static final Supplier<Item> titanium =
    		ITEMS.register("titanium",
    				() -> new Item(new Item.Properties().tab(SpectrobesItemGroups.SpectrobesMineralItemGroup.Instance)));
    
    public static final Supplier<Item> marble =
    		ITEMS.register("marble",
    				() -> new Item(new Item.Properties().tab(SpectrobesItemGroups.SpectrobesMineralItemGroup.Instance)));

}
