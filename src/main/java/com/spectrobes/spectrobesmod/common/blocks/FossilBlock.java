package com.spectrobes.spectrobesmod.common.blocks;

import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.common.ToolType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class FossilBlock extends SpectrobesBlock {
    private static Properties props = Properties.create(Material.ROCK)
            .harvestTool(ToolType.PICKAXE)
            .hardnessAndResistance(0.5f)
            .sound(SoundType.STONE)
            .harvestLevel(2);

    public FossilBlock() {
        super(props);

//        setRegistryName("fossil_block");
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {

        ItemStack fossilItem = SpectrobesItems.getRandomFossil();

        ArrayList minerals = new ArrayList();
        minerals.add(fossilItem);

        return  minerals;
    }
}
