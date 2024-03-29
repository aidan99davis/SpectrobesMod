package com.spectrobes.spectrobesmod.common.blocks;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;

public abstract class SpectrobesTileEntityBlock extends BaseEntityBlock {
    private static final Properties props = Properties.of(Material.WOOD).noOcclusion()
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
