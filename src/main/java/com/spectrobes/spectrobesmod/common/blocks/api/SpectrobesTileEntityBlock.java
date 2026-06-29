package com.spectrobes.spectrobesmod.common.blocks.api;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class SpectrobesTileEntityBlock extends BaseEntityBlock {
    private static final Properties props = Properties.of().noOcclusion()
            .strength(0f)
            .sound(SoundType.STONE);

    public SpectrobesTileEntityBlock() { super(props); }

    protected SpectrobesTileEntityBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
