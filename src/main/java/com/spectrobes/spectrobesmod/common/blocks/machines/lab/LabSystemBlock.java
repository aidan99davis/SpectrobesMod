package com.spectrobes.spectrobesmod.common.blocks.machines.lab;

import com.mojang.serialization.MapCodec;
import com.spectrobes.spectrobesmod.client.container.LabSystemContainer;
import com.spectrobes.spectrobesmod.common.blocks.api.DirectionalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class LabSystemBlock extends DirectionalBlock {
    public static final MapCodec<LabSystemBlock> CODEC = MapCodec.unit(LabSystemBlock::new);

    public LabSystemBlock() {
        super();
    }

    @Override
    protected MapCodec<? extends LabSystemBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider(
                (containerId, playerInventory, player) -> new LabSystemContainer(containerId, player),
                Component.translatable("container.spectrobesmod.lab_system")
        );
    }

    @Override
    protected InteractionResult useWithoutItem(
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            BlockHitResult hit
    ) {
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(state.getMenuProvider(level, pos));
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LabSystemBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
