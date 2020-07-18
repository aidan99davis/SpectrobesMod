package com.spectrobes.spectrobesmod.common.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class FossilBlock extends SpectrobesBlock {
    private static Properties props = Properties.create(Material.ROCK)
            .harvestTool(ToolType.PICKAXE)
            .hardnessAndResistance(1.5f)
            .sound(SoundType.STONE)
            .harvestLevel(4);

    public FossilBlock() {
        super(props);

        setRegistryName("fossil_block");
    }



    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn,pos,state,player);
    }
}
