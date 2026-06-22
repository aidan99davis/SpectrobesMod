package com.spectrobes.spectrobesmod.common.items;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesArmourRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesFossilsRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesToolsRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SpectrobesItemGroups {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SpectrobesInfo.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SPECTROBES_BLOCKS_TAB =
            CREATIVE_MODE_TABS.register("blocks", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.spectrobestab.blocks"))
                    .icon(() -> new ItemStack(SpectrobesBlocks.fossil_block.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(SpectrobesBlocks.fossil_block.get());
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SPECTROBES_FOSSILS_TAB =
            CREATIVE_MODE_TABS.register("fossils", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.spectrobestab.fossils"))
                    .icon(() -> new ItemStack(SpectrobesFossilsRegistry.komainu_fossil_item.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(SpectrobesFossilsRegistry.komainu_fossil_item.get());
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SPECTROBES_WEAPONS_TAB =
            CREATIVE_MODE_TABS.register("weapons", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.spectrobestab.weapons"))
                    .icon(() -> new ItemStack(SpectrobesToolsRegistry.basic_sword_item.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(SpectrobesToolsRegistry.basic_sword_item.get());
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SPECTROBES_MINERALS_TAB =
            CREATIVE_MODE_TABS.register("minerals", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.spectrobestab.minerals"))
                    .icon(() -> new ItemStack(SpectrobesMineralsRegistry.mineral_item_power_c.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(SpectrobesMineralsRegistry.mineral_item_power_c.get());
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SPECTROBES_TOOLS_TAB =
            CREATIVE_MODE_TABS.register("tools", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.spectrobestab.tools"))
                    .icon(() -> new ItemStack(SpectrobesToolsRegistry.prizmod_item.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(SpectrobesToolsRegistry.prizmod_item.get());
                    })
                    .build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SPECTROBES_ARMOUR_TAB =
            CREATIVE_MODE_TABS.register("armour", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.spectrobestab.armour"))
                    .icon(() -> new ItemStack(SpectrobesArmourRegistry.BASIC_CHEST.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(SpectrobesArmourRegistry.BASIC_CHEST.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}