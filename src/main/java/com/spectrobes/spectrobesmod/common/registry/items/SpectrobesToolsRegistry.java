package com.spectrobes.spectrobesmod.common.registry.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
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
                    () -> new PrizmodItem(new Item.Properties()));

    public static final Supplier<SpectrobesRangedWeapon> basic_blaster_item =
            ITEMS.register("basic_blaster_item",
                    () -> new BasicBlasterItem(new Item.Properties()));

    public static final Supplier<SpectrobesWeapon> basic_sword_item =
            ITEMS.register("basic_sword_item",
                    () -> new BasicSwordItem(new Item.Properties()));

    public static final Supplier<SpectrobesWeapon> basic_glove_item =
            ITEMS.register("basic_glove_item",
                    () -> new BasicGloveItem(new Item.Properties()));

    public static final Supplier<SpectrobeSerumHealingItem> basic_serum =
            ITEMS.register("basic_serum",
                    () -> new SpectrobeSerumHealingItem(50, 500, 1,
                            new Item.Properties()));

    public static final Supplier<SpectrobeAntidoteHealingItem> basic_antidote =
            ITEMS.register("basic_antidote",
                    () -> new SpectrobeAntidoteHealingItem(50, 2000, 1,
                            new Item.Properties()));

    // = = = TESTING = = =
    public static final Supplier<AbstractSpectrobesRangedWeapon> WEAPON_BASIC_BLASTER_ITEM =
            ITEMS.register("weapon_basic_blaster_item",
                    () -> new WeaponBasicBlasterItem(new Item.Properties()));
    public static final Supplier<AbstractSpectrobesRangedWeapon> WEAPON_DOUBLE_BLASTER_ITEM =
            ITEMS.register("weapon_double_blaster_item",
                    () -> new WeaponDoubleBlasterItem(new Item.Properties()));
    public static final Supplier<AbstractSpectrobesRangedWeapon> WEAPON_RAY_BLASTER_ITEM =
            ITEMS.register("weapon_ray_blaster_item",
                    () -> new WeaponRayBlasterItem(new Item.Properties()));
    public static final Supplier<AbstractSpectrobesRangedWeapon> WEAPON_TRIPLE_BLASTER_ITEM =
            ITEMS.register("weapon_triple_blaster_item",
                    () -> new WeaponTripleBlasterItem(new Item.Properties()));
    public static final Supplier<AbstractSpectrobesRangedWeapon> WEAPON_HOMING_BLASTER_ITEM =
            ITEMS.register("weapon_homing_blaster_item",
                    () -> new WeaponHomingBlasterItem(new Item.Properties()));
    public static final Supplier<AbstractSpectrobesRangedWeapon> WEAPON_PHOTON_BLASTER_ITEM =
            ITEMS.register("weapon_photon_blaster_item",
                    () -> new WeaponPhotonBlasterItem(new Item.Properties()));
    public static final Supplier<AbstractSpectrobesRangedWeapon> WEAPON_SHATTER_BLASTER_ITEM =
            ITEMS.register("weapon_shatter_blaster_item",
                    () -> new WeaponShatterBlasterItem(new Item.Properties()));
    public static final Supplier<AbstractSpectrobesRangedWeapon> WEAPON_TRACKING_BLASTER_ITEM =
            ITEMS.register("weapon_tracking_blaster_item",
                    () -> new WeaponTrackingBlasterItem(new Item.Properties()));
    public static final Supplier<AbstractSpectrobesRangedWeapon> WEAPON_HYPER_BLASTER_ITEM =
            ITEMS.register("weapon_hyper_blaster_item",
                    () -> new WeaponHyperBlasterItem(new Item.Properties()));
    public static final Supplier<AbstractSpectrobesRangedWeapon> WEAPON_RUPTURE_BLASTER_ITEM =
            ITEMS.register("weapon_rupture_blaster_item",
                    () -> new WeaponRuptureBlasterItem(new Item.Properties()));

    // = = = NON-CANNON (...THO MAYYY CONTAIN A COUPLE CANNONS) = = =
    public static final Supplier<AbstractSpectrobesRangedWeapon> WEAPON_BASIC_BLASTER_C_ITEM =
            ITEMS.register("weapon_basic_blaster_c_item",
                    () -> new WeaponBasicBlasterCItem(new Item.Properties()));
}
