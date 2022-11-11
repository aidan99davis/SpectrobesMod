package com.spectrobes.spectrobesmod.common.items;

import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.registry.items.*;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class SpectrobesItemGroups {
    public static class SpectrobesBlocksItemGroup extends CreativeModeTab {

        public static final SpectrobesBlocksItemGroup Instance = new SpectrobesBlocksItemGroup(CreativeModeTab.TABS.length, "spectrobestab.blocks");

        public SpectrobesBlocksItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesBlocks.fossil_block.get().asItem().getDefaultInstance();
        }
    }

    public static class SpectrobesFossilsItemGroup extends CreativeModeTab {

        public static final SpectrobesFossilsItemGroup Instance = new SpectrobesFossilsItemGroup(CreativeModeTab.TABS.length, "spectrobestab.fossils");

        public SpectrobesFossilsItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesFossilsRegistry.komainu_fossil_item.get().getDefaultInstance();
        }
    }

    public static class SpectrobesWeaponsItemGroup extends CreativeModeTab {

        public static final SpectrobesWeaponsItemGroup Instance = new SpectrobesWeaponsItemGroup(CreativeModeTab.TABS.length, "spectrobestab.weapons");

        public SpectrobesWeaponsItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesToolsRegistry.basic_sword_item.get().getDefaultInstance();
        }
    }

    public static class SpectrobesMineralItemGroup extends CreativeModeTab {

        public static final SpectrobesMineralItemGroup Instance = new SpectrobesMineralItemGroup(CreativeModeTab.TABS.length, "spectrobestab.minerals");

        public SpectrobesMineralItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesMineralsRegistry.mineral_item_power_c.get().getDefaultInstance();
        }
    }
    public static class SpectrobesToolsItemGroup extends CreativeModeTab {

        public static final SpectrobesToolsItemGroup Instance = new SpectrobesToolsItemGroup(CreativeModeTab.TABS.length, "spectrobestab.tools");

        public SpectrobesToolsItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesToolsRegistry.prizmod_item.get().getDefaultInstance();
        }
    }
    public static class SpectrobesArmourItemGroup extends CreativeModeTab {

        public static final SpectrobesArmourItemGroup Instance = new SpectrobesArmourItemGroup(CreativeModeTab.TABS.length, "spectrobestab.armour");

        public SpectrobesArmourItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return SpectrobesArmourRegistry.BASIC_CHEST.get().getDefaultInstance();
        }
    }

}
