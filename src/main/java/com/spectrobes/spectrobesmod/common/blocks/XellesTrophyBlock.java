package com.spectrobes.spectrobesmod.common.blocks;

import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class XellesTrophyBlock extends SpectrobesTileEntityBlock {
    private static final Properties props = Properties.of(Material.WOOD).noOcclusion()
            .strength(0f)
            .sound(SoundType.STONE);
    public XellesTrophyBlock() {
        super(props);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return SpectrobesTileRegistry.XELLES_TROPHY_TILE.get().create(pPos, pState);
    }
}
