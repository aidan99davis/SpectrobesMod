package com.spectrobes.spectrobesmod.common.items;

import com.spectrobes.spectrobesmod.SpectrobesMod;
import com.spectrobes.spectrobesmod.common.items.fossils.KomainuFossilItem;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = SpectrobesMod.MOD_ID, bus = Bus.MOD)
@ObjectHolder(SpectrobesMod.MOD_ID)
public class SpectrobesItems {
    public static final Item mineral_item = null;
    public static final Item komainu_fossil_item = null;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(
                new Item(
                    new Item.Properties()
                    .group(SpectrobesItemGroup.Instance)
                            .food(new Food.Builder()
                                    .hunger(2)
                                    .fastToEat()
                                    .saturation(1)
                                    .build()))
                .setRegistryName("mineral_item"));

        event.getRegistry().register(
                new KomainuFossilItem(
                        new Item.Properties()
                                .group(SpectrobesItemGroup.Instance))
                        .setRegistryName("komainu_fossil_item"));
    }

    public static class SpectrobesItemGroup extends ItemGroup {

        public static final SpectrobesItemGroup Instance = new SpectrobesItemGroup(ItemGroup.GROUPS.length, "spectrobestab");

        public SpectrobesItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return SpectrobesItems.mineral_item.getDefaultInstance();
        }
    }
}
