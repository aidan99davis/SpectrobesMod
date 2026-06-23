package com.spectrobes.spectrobesmod.common.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class PlanetaryTeleporterBlock extends MultiTextureBlock {

    public static final MapCodec<PlanetaryTeleporterBlock> CODEC = MapCodec.unit(PlanetaryTeleporterBlock::new);

    private static final Properties props = Properties.of()
            .noOcclusion()
            .strength(0F)
            .sound(SoundType.STONE);

    public PlanetaryTeleporterBlock() {
        super(props);
    }

    @Override
    protected MapCodec<? extends PlanetaryTeleporterBlock> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            if (!pPlayer.isShiftKeyDown()) {
                // SpectrobesNetwork.sendToServer(new SChangeDimensionPacket());
            }

            return InteractionResult.SUCCESS;
        }

        return super.useWithoutItem(pState, pLevel, pPos, pPlayer, pHit);
    }
}