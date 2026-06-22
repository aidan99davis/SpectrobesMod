package com.spectrobes.spectrobesmod.common.blocks.machines.blocks;

import com.mojang.serialization.MapCodec;
import com.spectrobes.spectrobesmod.common.blocks.DirectionalBlock;
import com.spectrobes.spectrobesmod.common.blocks.machines.entity.CyrusShopBlockEntity;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SOpenCyrusShopPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class CyrusShopBlock extends DirectionalBlock {

    public static final MapCodec<CyrusShopBlock> CODEC = MapCodec.unit(CyrusShopBlock::new);

    public CyrusShopBlock() {
        super();
    }

    @Override
    protected MapCodec<? extends CyrusShopBlock> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHit) {
        if (!pLevel.isClientSide && pPlayer instanceof ServerPlayer serverPlayer) {
            SpectrobesNetwork.sendToClient(new SOpenCyrusShopPacket(), serverPlayer);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CyrusShopBlockEntity(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}