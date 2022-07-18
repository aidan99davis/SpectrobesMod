package com.spectrobes.spectrobesmod.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import org.apache.commons.lang3.NotImplementedException;

import javax.annotation.Nullable;

public class SpectrobesTileEntityBlock extends Block {

    protected SpectrobesTileEntityBlock(Properties p_i48415_1_) {
        super(p_i48415_1_);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        throw new NotImplementedException();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

//    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
//        builder.add(FACING);
//    }
//
//    @Nullable
//    @Override
//    public BlockState getStateForPlacement(BlockItemUseContext context) {
//        return this.defaultBlockState().setValue(FACING, Direction.UP);
//    }
}
