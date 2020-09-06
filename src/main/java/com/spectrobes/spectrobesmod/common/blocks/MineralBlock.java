package com.spectrobes.spectrobesmod.common.blocks;

import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineralBlock extends SpectrobesBlock {
    private static Block.Properties props = Block.Properties.create(Material.ROCK)
            .harvestTool(ToolType.PICKAXE)
            .hardnessAndResistance(0.5f)
            .sound(SoundType.STONE)
            .harvestLevel(3);

    public MineralBlock() {
        super(props);

        setRegistryName("mineral_block");
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
