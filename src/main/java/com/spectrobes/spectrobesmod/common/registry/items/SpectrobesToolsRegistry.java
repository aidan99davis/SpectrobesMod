package com.spectrobes.spectrobesmod.common.registry.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItemGroups;
import com.spectrobes.spectrobesmod.common.items.tools.PrizmodItem;
import com.spectrobes.spectrobesmod.common.items.tools.healing.SpectrobeAntidoteHealingItem;
import com.spectrobes.spectrobesmod.common.items.tools.healing.SpectrobeSerumHealingItem;
import com.spectrobes.spectrobesmod.common.items.weapons.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SpectrobesToolsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, SpectrobesInfo.MOD_ID);

    public static final Supplier<PrizmodItem> prizmod_item =
            ITEMS.register("prizmod_item",
                    () -> new PrizmodItem(new Item.Properties()
                            .tab(SpectrobesItemGroups.SpectrobesToolsItemGroup.Instance)));

    public static final Supplier<SpectrobesRangedWeapon> basic_blaster_item =
            ITEMS.register("basic_blaster_item",
                    () -> new BasicBlasterItem(new Item.Properties()
                            .tab(SpectrobesItemGroups.SpectrobesWeaponsItemGroup.Instance)));

    public static final Supplier<SpectrobesWeapon> basic_sword_item =
            ITEMS.register("basic_sword_item",
                    () -> new BasicSwordItem(new Item.Properties()
                            .tab(SpectrobesItemGroups.SpectrobesWeaponsItemGroup.Instance)));

    public static final Supplier<SpectrobesWeapon> basic_glove_item =
            ITEMS.register("basic_glove_item",
                    () -> new BasicGloveItem(new Item.Properties()
                            .tab(SpectrobesItemGroups.SpectrobesWeaponsItemGroup.Instance)));

    public static final Supplier<SpectrobeSerumHealingItem> basic_serum =
            ITEMS.register("basic_serum",
                    () -> new SpectrobeSerumHealingItem(50, 500, 1, new Item.Properties()
                            .tab(SpectrobesItemGroups.SpectrobesToolsItemGroup.Instance)));

    public static final Supplier<SpectrobeAntidoteHealingItem> basic_antidote =
            ITEMS.register("basic_antidote",
                    () -> new SpectrobeAntidoteHealingItem(50, 2000, 1, new Item.Properties()
                            .tab(SpectrobesItemGroups.SpectrobesToolsItemGroup.Instance)));

}
