package com.spectrobes.spectrobesmod.common.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.SpectrobesMod;
import com.spectrobes.spectrobesmod.common.items.fossils.KomainuFossilItem;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralProperties;
import com.spectrobes.spectrobesmod.common.registry.MineralRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.MineralPropertiesBuilder;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Bus.MOD)
@ObjectHolder(SpectrobesInfo.MOD_ID)
public class SpectrobesItems {
    public static final Item mineral_item_power_c = null;
    public static final Item mineral_item_power_b = null;
    public static final Item mineral_item_power_a = null;
    public static final Item komainu_fossil_item = null;
    public static final Item[] ALL_MINERALS = new Item[] {
            mineral_item_power_c,
            mineral_item_power_b,
            mineral_item_power_a
    };

    static void registerMineral(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(
                new MineralItem(
                        new Item.Properties()
                                .group(SpectrobesMineralItemGroup.Instance),
                        MineralRegistry.MINERAL_POWER_C.name,
                        MineralRegistry.MINERAL_POWER_C.properties.copy()));
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        MineralRegistry.init();

        registerMineral(event);
        event.getRegistry().register(
                new MineralItem(
                        new Item.Properties()
                                .group(SpectrobesMineralItemGroup.Instance),
                        MineralRegistry.MINERAL_POWER_B.name,
                        MineralRegistry.MINERAL_POWER_B.properties.copy()));
        event.getRegistry().register(
                new MineralItem(
                        new Item.Properties()
                                .group(SpectrobesMineralItemGroup.Instance),
                        MineralRegistry.MINERAL_POWER_A.name,
                        MineralRegistry.MINERAL_POWER_A.properties.copy()));

        event.getRegistry().register(
                new KomainuFossilItem(
                        new Item.Properties()
                                .group(SpectrobesItemGroup.Instance)));
    }

    public static class SpectrobesItemGroup extends ItemGroup {

        public static final SpectrobesItemGroup Instance = new SpectrobesItemGroup(ItemGroup.GROUPS.length, "spectrobestab");

        public SpectrobesItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return SpectrobesItems.komainu_fossil_item.getDefaultInstance();
        }
    }

    public static class SpectrobesMineralItemGroup extends ItemGroup {

        public static final SpectrobesMineralItemGroup Instance = new SpectrobesMineralItemGroup(ItemGroup.GROUPS.length, "spectrobestab.minerals");

        public SpectrobesMineralItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return SpectrobesItems.mineral_item_power_c.getDefaultInstance();
        }
    }
}
