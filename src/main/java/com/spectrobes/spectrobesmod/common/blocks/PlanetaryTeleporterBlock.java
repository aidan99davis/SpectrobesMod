package com.spectrobes.spectrobesmod.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class PlanetaryTeleporterBlock extends MultiTextureBlock {

    private static final Properties props = Properties.of().noOcclusion()
            .strength(0f)
            .sound(SoundType.STONE);
    public PlanetaryTeleporterBlock() {
        super(props);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pLevel.isClientSide) {
            if(!pPlayer.isShiftKeyDown())
//                SpectrobesNetwork.sendToServer(new SChangeDimensionPacket());
            return InteractionResult.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

}
