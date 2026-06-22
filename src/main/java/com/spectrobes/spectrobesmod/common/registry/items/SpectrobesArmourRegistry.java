package com.spectrobes.spectrobesmod.common.registry.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.armour.BasicNppArmourItem;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesItemGroupsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SpectrobesArmourRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, SpectrobesInfo.MOD_ID);

    //Armour
    public static final Supplier<BasicNppArmourItem> BASIC_HEAD = ITEMS.register("basic_npp_helmet",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.OTHER, 10, ArmorMaterials.DIAMOND.value(), EquipmentSlot.HEAD,
                    new Item.Properties()));
    public static final Supplier<BasicNppArmourItem> BASIC_CHEST = ITEMS.register("basic_npp_chestplate",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.OTHER, 20, ArmorMaterials.DIAMOND.value(), EquipmentSlot.CHEST,
                    new Item.Properties()));
    public static final Supplier<BasicNppArmourItem> BASIC_LEGGINGS = ITEMS.register("basic_npp_leggings",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.OTHER, 10, ArmorMaterials.DIAMOND.value(), EquipmentSlot.LEGS,
                    new Item.Properties()));
    public static final Supplier<BasicNppArmourItem> BASIC_BOOTS = ITEMS.register("basic_npp_boots",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.OTHER, 10, ArmorMaterials.DIAMOND.value(), EquipmentSlot.FEET,
                    new Item.Properties()));

    public static final Supplier<BasicNppArmourItem> BASIC_CORONA_HEAD = ITEMS.register("basic_corona_helmet",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.CORONA, 10, ArmorMaterials.DIAMOND.value(), EquipmentSlot.HEAD,
                    new Item.Properties()));
    public static final Supplier<BasicNppArmourItem> BASIC_CORONA_CHEST = ITEMS.register("basic_corona_chestplate",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.CORONA, 20, ArmorMaterials.DIAMOND.value(), EquipmentSlot.CHEST,
                    new Item.Properties()));
    public static final Supplier<BasicNppArmourItem> BASIC_CORONA_LEGGINGS = ITEMS.register("basic_corona_leggings",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.CORONA, 10, ArmorMaterials.DIAMOND.value(), EquipmentSlot.LEGS,
                    new Item.Properties()));
    public static final Supplier<BasicNppArmourItem> BASIC_CORONA_BOOTS = ITEMS.register("basic_corona_boots",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.CORONA, 10, ArmorMaterials.DIAMOND.value(), EquipmentSlot.FEET,
                    new Item.Properties()));

    public static final Supplier<BasicNppArmourItem> BASIC_AURORA_HEAD = ITEMS.register("basic_aurora_helmet",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.AURORA, 10, ArmorMaterials.DIAMOND.value(), EquipmentSlot.HEAD,
                    new Item.Properties()));
    public static final Supplier<BasicNppArmourItem> BASIC_AURORA_CHEST = ITEMS.register("basic_aurora_chestplate",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.AURORA, 20, ArmorMaterials.DIAMOND.value(), EquipmentSlot.CHEST,
                    new Item.Properties()));
    public static final Supplier<BasicNppArmourItem> BASIC_AURORA_LEGGINGS = ITEMS.register("basic_aurora_leggings",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.AURORA, 10, ArmorMaterials.DIAMOND.value(), EquipmentSlot.LEGS,
                    new Item.Properties()));
    public static final Supplier<BasicNppArmourItem> BASIC_AURORA_BOOTS = ITEMS.register("basic_aurora_boots",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.AURORA, 20, ArmorMaterials.DIAMOND.value(), EquipmentSlot.FEET,
                    new Item.Properties()));

    public static final Supplier<BasicNppArmourItem> BASIC_FLASH_HEAD = ITEMS.register("basic_flash_helmet",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.FLASH, 10, ArmorMaterials.DIAMOND.value(), EquipmentSlot.HEAD,
                    new Item.Properties()));
    public static final Supplier<BasicNppArmourItem> BASIC_FLASH_CHEST = ITEMS.register("basic_flash_chestplate",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.FLASH, 20, ArmorMaterials.DIAMOND.value(), EquipmentSlot.CHEST,
                    new Item.Properties()));
    public static final Supplier<BasicNppArmourItem> BASIC_FLASH_LEGGINGS = ITEMS.register("basic_flash_leggings",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.FLASH, 10, ArmorMaterials.DIAMOND.value(), EquipmentSlot.LEGS,
                    new Item.Properties()));
    public static final Supplier<BasicNppArmourItem> BASIC_FLASH_BOOTS = ITEMS.register("basic_flash_boots",
            () -> new BasicNppArmourItem(SpectrobeProperties.Nature.FLASH, 10, ArmorMaterials.DIAMOND.value(), EquipmentSlot.FEET,
                    new Item.Properties()));

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SPECTROBES_ARMOUR_TAB =
            SpectrobesItemGroupsRegistry.CREATIVE_MODE_TABS.register("armour", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.spectrobestab.armour"))
                    .icon(() -> new ItemStack(SpectrobesArmourRegistry.BASIC_CHEST.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(SpectrobesArmourRegistry.BASIC_HEAD.get());
                        output.accept(SpectrobesArmourRegistry.BASIC_CHEST.get());
                        output.accept(SpectrobesArmourRegistry.BASIC_LEGGINGS.get());
                        output.accept(SpectrobesArmourRegistry.BASIC_BOOTS.get());

                        output.accept(SpectrobesArmourRegistry.BASIC_CORONA_HEAD.get());
                        output.accept(SpectrobesArmourRegistry.BASIC_CORONA_CHEST.get());
                        output.accept(SpectrobesArmourRegistry.BASIC_CORONA_LEGGINGS.get());
                        output.accept(SpectrobesArmourRegistry.BASIC_CORONA_BOOTS.get());

                        output.accept(SpectrobesArmourRegistry.BASIC_AURORA_HEAD.get());
                        output.accept(SpectrobesArmourRegistry.BASIC_AURORA_CHEST.get());
                        output.accept(SpectrobesArmourRegistry.BASIC_AURORA_LEGGINGS.get());
                        output.accept(SpectrobesArmourRegistry.BASIC_AURORA_BOOTS.get());

                        output.accept(SpectrobesArmourRegistry.BASIC_FLASH_HEAD.get());
                        output.accept(SpectrobesArmourRegistry.BASIC_FLASH_CHEST.get());
                        output.accept(SpectrobesArmourRegistry.BASIC_FLASH_LEGGINGS.get());
                        output.accept(SpectrobesArmourRegistry.BASIC_FLASH_BOOTS.get());
                    })
                    .build());

}
