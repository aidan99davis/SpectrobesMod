package com.spectrobes.spectrobesmod.common.blocks;

import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class HealerBlock extends SpectrobesTileEntityBlock {
    protected static final VoxelShape SHAPE = Block.box(0D, 0.0D, 0D, 16.0D, 32.0D, 16.0D);
    protected static final VoxelShape AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    public HealerBlock() {
        super();
    }

    public static VoxelShape getSHAPE() {
        return SHAPE;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return AABB;
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if(!pLevel.isClientSide && pEntity instanceof Player) {
            PlayerSpectrobeMaster capability = pEntity.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                    .orElseThrow(IllegalStateException::new);
            capability.setCurrentHealth(capability.getMaxHealth());
            capability.getCurrentTeamUuids().forEach((integer, uuid) -> {

                if(uuid != null) {
                    Spectrobe spectrobe = capability.getSpectrobeByUuid(uuid);
                    spectrobe.setCurrentHealth(spectrobe.stats.getHpLevel());
                }
            });

            SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(capability), (ServerPlayer) pEntity);
        }
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return SpectrobesTileRegistry.HEALER_TILE.get().create(pPos, pState);
    }
}
