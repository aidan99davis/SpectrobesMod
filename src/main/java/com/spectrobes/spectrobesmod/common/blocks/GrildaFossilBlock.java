package com.spectrobes.spectrobesmod.common.blocks;

import com.spectrobes.spectrobesmod.common.blocks.tile.SpectrobesTileRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class GrildaFossilBlock extends DirectionalBlock {
    private static Properties props = Properties.create(Material.ROCK).notSolid()
            .harvestTool(ToolType.PICKAXE)
            .hardnessAndResistance(0.5f)
            .sound(SoundType.STONE)
            .harvestLevel(1);

    public GrildaFossilBlock() {
        super(props);
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return SpectrobesTileRegistry.GRILDA_FOSSIL_TILE.get().create();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.getDefaultState().with(FACING, context.getNearestLookingDirection().getOpposite());
    }
}
