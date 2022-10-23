package com.spectrobes.spectrobesmod.common.blocks;

import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SChangeDimensionPacket;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class PlanetaryTeleporterBlock extends MultiTextureBlock {

    private static final Properties props = Properties.of(Material.WOOD).noOcclusion()
            .harvestTool(ToolType.PICKAXE)
            .strength(0f)
            .sound(SoundType.STONE)
            .harvestLevel(0);
    public PlanetaryTeleporterBlock() {
        super(props);
    }

    @Override
    public void tick(BlockState pState, ServerWorld pLevel, BlockPos pPos, Random pRand) {
        super.tick(pState, pLevel, pPos, pRand);
    }

    @Override
    public ActionResultType use(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockRayTraceResult pHit) {
        if(pLevel.isClientSide) {
            if(!pPlayer.isShiftKeyDown())
                SpectrobesNetwork.sendToServer(new SChangeDimensionPacket());
            return ActionResultType.SUCCESS;
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

}
