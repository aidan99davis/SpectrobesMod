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

public class FossilBlock extends SpectrobesBlock {
    private static Properties props = Properties.create(Material.ROCK)
            .harvestTool(ToolType.PICKAXE)
            .hardnessAndResistance(1.5f)
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
