package com.spectrobes.spectrobesmod.common.blocks;

import com.mojang.serialization.MapCodec;
import com.spectrobes.spectrobesmod.common.blocks.api.SpectrobesTileEntityBlock;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class XellesTrophyBlock extends SpectrobesTileEntityBlock {

    public static final MapCodec<XellesTrophyBlock> CODEC = MapCodec.unit(XellesTrophyBlock::new);

    private static final Properties PROPS = Properties.of()
            .noOcclusion()
            .strength(0.0F)
            .sound(SoundType.STONE);

    public XellesTrophyBlock() {
        this(PROPS);
    }

    private XellesTrophyBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends XellesTrophyBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return SpectrobesTileRegistry.XELLES_TROPHY_TILE.get().create(pos, state);
    }
}