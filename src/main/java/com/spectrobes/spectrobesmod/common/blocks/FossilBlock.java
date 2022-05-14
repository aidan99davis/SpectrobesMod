package com.spectrobes.spectrobesmod.common.blocks;

import com.spectrobes.spectrobesmod.common.registry.SpectrobesItemsRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;

import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.AbstractBlock.Properties;

public class FossilBlock extends SpectrobesBlock {
    private static Properties props = Properties.of(Material.STONE)
            .harvestTool(ToolType.PICKAXE)
            .strength(1.5f)
            .sound(SoundType.STONE)
            .harvestLevel(2);

    public FossilBlock() {
        super(props);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

        ItemStack fossilItem = SpectrobesItemsRegistry.getRandomFossil();

        ArrayList minerals = new ArrayList();
        minerals.add(fossilItem);

        return  minerals;
    }
}
