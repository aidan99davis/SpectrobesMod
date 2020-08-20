package com.spectrobes.spectrobesmod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

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
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn,pos,state,player);
    }
}
