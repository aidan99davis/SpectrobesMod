package com.spectrobes.spectrobesmod.common.blocks;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;

public abstract class DirectionalBlock extends BaseEntityBlock {
    private static final Properties props = Properties.of(Material.WOOD).noOcclusion()
            .strength(0f)
            .sound(SoundType.STONE);

    public static final DirectionProperty FACING = net.minecraft.world.level.block.DirectionalBlock.FACING;

    public DirectionalBlock() { super(props); }

    protected DirectionalBlock(Properties pProperties) {
        super(pProperties);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
    }
}
