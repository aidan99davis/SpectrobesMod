package com.spectrobes.spectrobesmod.common.registry.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.armour.BasicNppArmourItem;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SpectrobesArmourRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, SpectrobesInfo.MOD_ID);

    //Armour
    public static final Supplier<BasicNppArmourItem> BASIC_HEAD = registerArmour(
            "basic_npp_helmet", SpectrobeProperties.Nature.OTHER, 10, ArmorItem.Type.HELMET);
    public static final Supplier<BasicNppArmourItem> BASIC_CHEST = registerArmour(
            "basic_npp_chestplate", SpectrobeProperties.Nature.OTHER, 20, ArmorItem.Type.CHESTPLATE);
    public static final Supplier<BasicNppArmourItem> BASIC_LEGGINGS = registerArmour(
            "basic_npp_leggings", SpectrobeProperties.Nature.OTHER, 10, ArmorItem.Type.LEGGINGS);
    public static final Supplier<BasicNppArmourItem> BASIC_BOOTS = registerArmour(
            "basic_npp_boots", SpectrobeProperties.Nature.OTHER, 10, ArmorItem.Type.BOOTS);

    public static final Supplier<BasicNppArmourItem> BASIC_CORONA_HEAD = registerArmour(
            "basic_corona_helmet", SpectrobeProperties.Nature.CORONA, 10, ArmorItem.Type.HELMET);
    public static final Supplier<BasicNppArmourItem> BASIC_CORONA_CHEST = registerArmour(
            "basic_corona_chestplate", SpectrobeProperties.Nature.CORONA, 20, ArmorItem.Type.CHESTPLATE);
    public static final Supplier<BasicNppArmourItem> BASIC_CORONA_LEGGINGS = registerArmour(
            "basic_corona_leggings", SpectrobeProperties.Nature.CORONA, 10, ArmorItem.Type.LEGGINGS);
    public static final Supplier<BasicNppArmourItem> BASIC_CORONA_BOOTS = registerArmour(
            "basic_corona_boots", SpectrobeProperties.Nature.CORONA, 10, ArmorItem.Type.BOOTS);

    public static final Supplier<BasicNppArmourItem> BASIC_AURORA_HEAD = registerArmour(
            "basic_aurora_helmet", SpectrobeProperties.Nature.AURORA, 10, ArmorItem.Type.HELMET);
    public static final Supplier<BasicNppArmourItem> BASIC_AURORA_CHEST = registerArmour(
            "basic_aurora_chestplate", SpectrobeProperties.Nature.AURORA, 20, ArmorItem.Type.CHESTPLATE);
    public static final Supplier<BasicNppArmourItem> BASIC_AURORA_LEGGINGS = registerArmour(
            "basic_aurora_leggings", SpectrobeProperties.Nature.AURORA, 10, ArmorItem.Type.LEGGINGS);
    public static final Supplier<BasicNppArmourItem> BASIC_AURORA_BOOTS = registerArmour(
            "basic_aurora_boots", SpectrobeProperties.Nature.AURORA, 20, ArmorItem.Type.BOOTS);

    public static final Supplier<BasicNppArmourItem> BASIC_FLASH_HEAD = registerArmour(
            "basic_flash_helmet", SpectrobeProperties.Nature.FLASH, 10, ArmorItem.Type.HELMET);
    public static final Supplier<BasicNppArmourItem> BASIC_FLASH_CHEST = registerArmour(
            "basic_flash_chestplate", SpectrobeProperties.Nature.FLASH, 20, ArmorItem.Type.CHESTPLATE);
    public static final Supplier<BasicNppArmourItem> BASIC_FLASH_LEGGINGS = registerArmour(
            "basic_flash_leggings", SpectrobeProperties.Nature.FLASH, 10, ArmorItem.Type.LEGGINGS);
    public static final Supplier<BasicNppArmourItem> BASIC_FLASH_BOOTS = registerArmour(
            "basic_flash_boots", SpectrobeProperties.Nature.FLASH, 10, ArmorItem.Type.BOOTS);

    private static Supplier<BasicNppArmourItem> registerArmour(
            String name,
            SpectrobeProperties.Nature nature,
            int healthBonus,
            ArmorItem.Type type
    ) {
        return ITEMS.register(name, () -> new BasicNppArmourItem(
                nature,
                healthBonus,
                ArmorMaterials.DIAMOND,
                type,
                new Item.Properties().durability(type.getDurability(33))
        ));
    }
}
