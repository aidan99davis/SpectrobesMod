package com.spectrobes.spectrobesmod.common.blocks.tile;

import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FossilBlockTileEntity extends BlockEntity {
    public FossilBlockTileEntity(BlockPos pos, BlockState state) {
        super(SpectrobesTileRegistry.FOSSIL_TILE.get(), pos, state);
    }
}
