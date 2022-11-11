package com.spectrobes.spectrobesmod.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import javax.annotation.Nullable;

public class MultiTextureBlock extends SpectrobesBlock
{
	public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
	public static final BooleanProperty EAST = BlockStateProperties.EAST;
	public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
	public static final BooleanProperty WEST = BlockStateProperties.WEST;
	public static final BooleanProperty UP = BlockStateProperties.UP;
	public static final BooleanProperty DOWN = BlockStateProperties.DOWN;
	
	public MultiTextureBlock(BlockBehaviour.Properties p_i49982_1_)
	{
		super(p_i49982_1_);
		this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, false)
				.setValue(EAST, false)
				.setValue(SOUTH, false)
				.setValue(WEST, false)
				.setValue(UP, false)
				.setValue(DOWN, false));
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(UP);
		builder.add(DOWN);
		builder.add(NORTH);
		builder.add(EAST);
		builder.add(SOUTH);
		builder.add(WEST);
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		switch(context.getNearestLookingDirection().getOpposite()) {
			case UP:
				return this.defaultBlockState().setValue(UP, true);
			case DOWN:
				return this.defaultBlockState().setValue(DOWN, true);
			case NORTH:
				return this.defaultBlockState().setValue(NORTH, true);
			case EAST:
				return this.defaultBlockState().setValue(EAST, true);
			case SOUTH:
				return this.defaultBlockState().setValue(SOUTH, true);
			case WEST:
				return this.defaultBlockState().setValue(WEST, true);
			default: return this.defaultBlockState().setValue(UP, true);
		}
	}
	
	/**
	* Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
	* For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
	* returns its solidified counterpart.
	* Note that this method should ideally consider only the specific face passed in.
	*/
	public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, Level pLevel, BlockPos pCurrentPos, BlockPos pFacingPos)
	{
		return getUpdatedBlockState(pState, pLevel, pCurrentPos);
	}
	
	protected BlockState getUpdatedBlockState (BlockState pState, Level pLevel, BlockPos pCurrentPos)
	{
		return pState.setValue(NORTH, pLevel.getBlockState(pCurrentPos.north()).is(this))
				.setValue(EAST, pLevel.getBlockState(pCurrentPos.east()).is(this))
				.setValue(SOUTH, pLevel.getBlockState(pCurrentPos.south()).is(this))
				.setValue(WEST, pLevel.getBlockState(pCurrentPos.west()).is(this))
				.setValue(UP, pLevel.getBlockState(pCurrentPos.above()).is(this))
				.setValue(DOWN, pLevel.getBlockState(pCurrentPos.below()).is(this));
	}
}
