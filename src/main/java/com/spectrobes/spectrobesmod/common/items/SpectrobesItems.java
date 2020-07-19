package com.spectrobes.spectrobesmod.common.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.SpectrobesMod;
import com.spectrobes.spectrobesmod.common.items.fossils.KomainuFossilItem;
import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
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
    //Minerals
    public static final Item mineral_item_power_c = null;
    public static final Item mineral_item_power_b = null;
    public static final Item mineral_item_power_a = null;
    public static final Item mineral_item_power_a_plus = null;
    public static final Item mineral_item_defence_c = null;
    public static final Item mineral_item_defence_b = null;
    public static final Item mineral_item_defence_a = null;
    public static final Item mineral_item_defence_a_plus = null;
    public static final Item mineral_item_health_c = null;
    public static final Item mineral_item_health_b = null;
    public static final Item mineral_item_health_a = null;
    public static final Item mineral_item_health_a_plus = null;
    public static final Item mineral_item_agate = null;

    //Fossils
    public static final Item komainu_fossil_item = null;


    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        MineralRegistry.init();

        for(Mineral m : MineralRegistry.ALL_MINERALS) {
            registerMineral(event, m);
        }

        event.getRegistry().register(
                new KomainuFossilItem(
                        new Item.Properties()
                                .group(SpectrobesItemGroup.Instance)));
    }

    static void registerMineral(final RegistryEvent.Register<Item> event, Mineral mineral) {
        event.getRegistry().register(
                new MineralItem(
                        new Item.Properties()
                                .group(SpectrobesMineralItemGroup.Instance),
                        mineral.name,
                        mineral.properties.copy()));
    }

    //Creative Tabs

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
