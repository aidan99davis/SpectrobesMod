package com.spectrobes.spectrobesmod.common.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SixWayBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IWorld;

public class MultiTextureBlock extends Block
{
	public static final BooleanProperty NORTH = SixWayBlock.NORTH;
	public static final BooleanProperty EAST = SixWayBlock.EAST;
	public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;
	public static final BooleanProperty WEST = SixWayBlock.WEST;
	public static final BooleanProperty UP = SixWayBlock.UP;
	public static final BooleanProperty DOWN = SixWayBlock.DOWN;
	
	public MultiTextureBlock(AbstractBlock.Properties p_i49982_1_)
	{
		super(p_i49982_1_);
		this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, false)
				.setValue(EAST, false)
				.setValue(SOUTH, false)
				.setValue(WEST, false)
				.setValue(UP, false)
				.setValue(DOWN, false));
	}
	
	public BlockState getStateForPlacement(BlockItemUseContext pContext) 
	{
		return getUpdatedBlockState(this.defaultBlockState(), pContext.getLevel(), pContext.getClickedPos());
	}
	
	/**
	* Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
	* For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
	* returns its solidified counterpart.
	* Note that this method should ideally consider only the specific face passed in.
	*/
	public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, IWorld pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) 
	{
		return getUpdatedBlockState(pState, pLevel, pCurrentPos);
	}
	
	protected BlockState getUpdatedBlockState (BlockState pState, IWorld pLevel, BlockPos pCurrentPos)
	{
		return pState.setValue(NORTH, pLevel.getBlockState(pCurrentPos.north()).is(this))
				.setValue(EAST, pLevel.getBlockState(pCurrentPos.east()).is(this))
				.setValue(SOUTH, pLevel.getBlockState(pCurrentPos.south()).is(this))
				.setValue(WEST, pLevel.getBlockState(pCurrentPos.west()).is(this))
				.setValue(UP, pLevel.getBlockState(pCurrentPos.above()).is(this))
				.setValue(DOWN, pLevel.getBlockState(pCurrentPos.below()).is(this));
	}
	
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) 
	{
		pBuilder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST);
	}
}
