package com.spectrobes.spectrobesmod.common.blocks;

import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.AbstractBlock;

public class MineralBlock extends SpectrobesBlock {
    private static AbstractBlock.Properties props = AbstractBlock.Properties.of(Material.STONE)
            .harvestTool(ToolType.PICKAXE)
            .strength(1.5f)
            .sound(SoundType.STONE)
            .harvestLevel(2);

    public MineralBlock() {
        super(props);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        Random random = new Random();
        int minerals_dropped = random.nextInt(3);
        ItemStack mineralItem = SpectrobesItems.getRandomMineral();
        mineralItem.grow(minerals_dropped + 1);

        ArrayList minerals = new ArrayList();
        minerals.add(mineralItem);

        return  minerals;
    }
}
