package com.spectrobes.spectrobesmod.common.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.items.fossils.KomainuFossilItem;
import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
import com.spectrobes.spectrobesmod.common.registry.MineralRegistry;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

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
    public static final Item mineral_item_amber = null;
    public static final Item mineral_item_azurite = null;
    public static final Item mineral_item_citrine = null;
    public static final Item mineral_item_cobalt = null;
    public static final Item mineral_item_coral = null;
    public static final Item mineral_item_diamond = null;
    public static final Item mineral_item_emerald = null;
    public static final Item mineral_item_fluorite = null;
    public static final Item mineral_item_garnet = null;
    public static final Item mineral_item_gold = null;
    public static final Item mineral_item_graphite = null;
    public static final Item mineral_item_jade = null;
    public static final Item mineral_item_lazuli = null;
    public static final Item mineral_item_onyx = null;
    public static final Item mineral_item_opal = null;
    public static final Item mineral_item_pearl = null;
    public static final Item mineral_item_platinum = null;
    public static final Item mineral_item_quartz = null;
    public static final Item mineral_item_ruby = null;
    public static final Item mineral_item_sapphire = null;
    public static final Item mineral_item_spinal = null;
    public static final Item mineral_item_sylvite = null;
    public static final Item mineral_item_topaz = null;
    public static final Item mineral_item_zircon = null;

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
                                .group(SpectrobesFossilsItemGroup.Instance)));
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

    public static class SpectrobesBlocksItemGroup extends ItemGroup {

        public static final SpectrobesBlocksItemGroup Instance = new SpectrobesBlocksItemGroup(ItemGroup.GROUPS.length, "spectrobestab.blocks");

        public SpectrobesBlocksItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return SpectrobesBlocks.fossil_block.asItem().getDefaultInstance();
        }
    }

    public static class SpectrobesFossilsItemGroup extends ItemGroup {

        public static final SpectrobesFossilsItemGroup Instance = new SpectrobesFossilsItemGroup(ItemGroup.GROUPS.length, "spectrobestab.fossils");

        public SpectrobesFossilsItemGroup(int index, String label) {
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
