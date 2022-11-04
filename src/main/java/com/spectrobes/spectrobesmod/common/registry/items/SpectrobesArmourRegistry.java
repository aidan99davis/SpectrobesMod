package com.spectrobes.spectrobesmod.common.registry.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.items.armour.BasicNppArmourItem;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SpectrobesArmourRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpectrobesInfo.MOD_ID);

    //Armour
    public static final RegistryObject<BasicNppArmourItem> BASIC_HEAD = ITEMS.register("basic_npp_helmet",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.OTHER, 10, ArmorMaterials.DIAMOND, EquipmentSlot.HEAD,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));
    public static final RegistryObject<BasicNppArmourItem> BASIC_CHEST = ITEMS.register("basic_npp_chestplate",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.OTHER, 20, ArmorMaterials.DIAMOND, EquipmentSlot.CHEST,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));
    public static final RegistryObject<BasicNppArmourItem> BASIC_LEGGINGS = ITEMS.register("basic_npp_leggings",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.OTHER, 10, ArmorMaterials.DIAMOND, EquipmentSlot.LEGS,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));
    public static final RegistryObject<BasicNppArmourItem> BASIC_BOOTS = ITEMS.register("basic_npp_boots",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.OTHER, 10, ArmorMaterials.DIAMOND, EquipmentSlot.FEET,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));

    public static final RegistryObject<BasicNppArmourItem> BASIC_CORONA_HEAD = ITEMS.register("basic_corona_helmet",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.CORONA, 10, ArmorMaterials.DIAMOND, EquipmentSlot.HEAD,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));
    public static final RegistryObject<BasicNppArmourItem> BASIC_CORONA_CHEST = ITEMS.register("basic_corona_chestplate",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.CORONA, 20, ArmorMaterials.DIAMOND, EquipmentSlot.CHEST,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));
    public static final RegistryObject<BasicNppArmourItem> BASIC_CORONA_LEGGINGS = ITEMS.register("basic_corona_leggings",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.CORONA, 10, ArmorMaterials.DIAMOND, EquipmentSlot.LEGS,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));
    public static final RegistryObject<BasicNppArmourItem> BASIC_CORONA_BOOTS = ITEMS.register("basic_corona_boots",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.CORONA, 10, ArmorMaterials.DIAMOND, EquipmentSlot.FEET,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));

    public static final RegistryObject<BasicNppArmourItem> BASIC_AURORA_HEAD = ITEMS.register("basic_aurora_helmet",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.AURORA, 10, ArmorMaterials.DIAMOND, EquipmentSlot.HEAD,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));
    public static final RegistryObject<BasicNppArmourItem> BASIC_AURORA_CHEST = ITEMS.register("basic_aurora_chestplate",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.AURORA, 20, ArmorMaterials.DIAMOND, EquipmentSlot.CHEST,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));
    public static final RegistryObject<BasicNppArmourItem> BASIC_AURORA_LEGGINGS = ITEMS.register("basic_aurora_leggings",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.AURORA, 10, ArmorMaterials.DIAMOND, EquipmentSlot.LEGS,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));
    public static final RegistryObject<BasicNppArmourItem> BASIC_AURORA_BOOTS = ITEMS.register("basic_aurora_boots",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.AURORA, 20, ArmorMaterials.DIAMOND, EquipmentSlot.FEET,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));

    public static final RegistryObject<BasicNppArmourItem> BASIC_FLASH_HEAD = ITEMS.register("basic_flash_helmet",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.FLASH, 10, ArmorMaterials.DIAMOND, EquipmentSlot.HEAD,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));
    public static final RegistryObject<BasicNppArmourItem> BASIC_FLASH_CHEST = ITEMS.register("basic_flash_chestplate",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.FLASH, 20, ArmorMaterials.DIAMOND, EquipmentSlot.CHEST,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));
    public static final RegistryObject<BasicNppArmourItem> BASIC_FLASH_LEGGINGS = ITEMS.register("basic_flash_leggings",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.FLASH, 10, ArmorMaterials.DIAMOND, EquipmentSlot.LEGS,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));
    public static final RegistryObject<BasicNppArmourItem> BASIC_FLASH_BOOTS = ITEMS.register("basic_flash_boots",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.FLASH, 10, ArmorMaterials.DIAMOND, EquipmentSlot.FEET,
                    new Item.Properties().tab(SpectrobesItems.SpectrobesArmourItemGroup.Instance)));

}
