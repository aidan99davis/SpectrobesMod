package com.spectrobes.spectrobesmod.common.registry.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.items.healing.renderer.SerumItemRenderer;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItemGroups;
import com.spectrobes.spectrobesmod.common.items.tools.PrizmodItem;
import com.spectrobes.spectrobesmod.common.items.tools.healing.SpectrobeSerumHealingItem;
import com.spectrobes.spectrobesmod.common.items.weapons.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SpectrobesToolsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpectrobesInfo.MOD_ID);

    public static final RegistryObject<PrizmodItem> prizmod_item =
            ITEMS.register("prizmod_item",
                    () -> new PrizmodItem(new Item.Properties()
                            .tab(SpectrobesItemGroups.SpectrobesToolsItemGroup.Instance)));

    public static final RegistryObject<SpectrobesRangedWeapon> basic_blaster_item =
            ITEMS.register("basic_blaster_item",
                    () -> new BasicBlasterItem(new Item.Properties()
                            .tab(SpectrobesItemGroups.SpectrobesWeaponsItemGroup.Instance)));

    public static final RegistryObject<SpectrobesWeapon> basic_sword_item =
            ITEMS.register("basic_sword_item",
                    () -> new BasicSwordItem(new Item.Properties()
                            .tab(SpectrobesItemGroups.SpectrobesWeaponsItemGroup.Instance)));

    public static final RegistryObject<SpectrobesWeapon> basic_glove_item =
            ITEMS.register("basic_glove_item",
                    () -> new BasicGloveItem(new Item.Properties()
                            .tab(SpectrobesItemGroups.SpectrobesWeaponsItemGroup.Instance)));

    public static final RegistryObject<SpectrobeSerumHealingItem> basic_serum =
            ITEMS.register("basic_serum",
                    () -> new SpectrobeSerumHealingItem(50, 500, 1, new Item.Properties()
                            .tab(SpectrobesItemGroups.SpectrobesToolsItemGroup.Instance)));

}
