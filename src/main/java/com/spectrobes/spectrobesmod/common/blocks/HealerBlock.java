package com.spectrobes.spectrobesmod.common.blocks;

import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class HealerBlock extends SpectrobesTileEntityBlock {
    protected static final VoxelShape SHAPE = Block.box(0D, 0.0D, 0D, 16.0D, 32.0D, 16.0D);
    protected static final VoxelShape AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);

    private static final Properties props = Properties.of(Material.WOOD).noOcclusion()
            .harvestTool(ToolType.PICKAXE)
            .strength(0f)
            .sound(SoundType.STONE)
            .harvestLevel(0);
    public HealerBlock() {
        super(props);
    }

    @Override
    public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
        return AABB;
    }

    @Override
    public void entityInside(BlockState pState, World pLevel, BlockPos pPos, Entity pEntity) {
        if(!pLevel.isClientSide && pEntity instanceof PlayerEntity) {
            PlayerSpectrobeMaster capability = pEntity.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                    .orElseThrow(IllegalStateException::new);
            capability.getCurrentTeamUuids().forEach((integer, uuid) -> {

                if(uuid != null) {
                    Spectrobe spectrobe = capability.getSpectrobeByUuid(uuid);
                    spectrobe.setCurrentHealth(spectrobe.stats.getHpLevel());
                }
            });

            SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(capability), (ServerPlayerEntity) pEntity);
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return SpectrobesTileRegistry.HEALER_TILE.get().create();
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

}
