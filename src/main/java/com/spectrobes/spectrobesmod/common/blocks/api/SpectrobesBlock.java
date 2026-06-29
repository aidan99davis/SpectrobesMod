package com.spectrobes.spectrobesmod.common.blocks.api;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;

public class SpectrobesBlock extends Block {

    public static final MapCodec<SpectrobesBlock> CODEC = simpleCodec(SpectrobesBlock::new);

    public SpectrobesBlock(Properties blockProps) {
        super(blockProps);
    }

    @Override
    protected MapCodec<? extends SpectrobesBlock> codec() {
        return CODEC;
    }
}