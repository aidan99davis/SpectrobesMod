package com.spectrobes.spectrobesmod.common.world.teleporters;

import com.spectrobes.spectrobesmod.common.blocks.PlanetaryTeleporterBlock;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class GenshiTeleporter implements ITeleporter {
    public static BlockPos thisPos = BlockPos.ZERO;
    public static boolean thisIsToDaylightDim = true;

    public GenshiTeleporter(BlockPos pos, boolean isToDaylightDim) {
        thisPos = pos;
        thisIsToDaylightDim = isToDaylightDim;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);
        double y = 61;
        if (!thisIsToDaylightDim) {
            y = thisPos.getY();
        }
        BlockPos destPos = new BlockPos(thisPos.getX(), y, thisPos.getZ());
        while ((destWorld.getBlockState(destPos).getMaterial() != Material.AIR && !destWorld.getBlockState(destPos).canBeReplaced(Fluids.WATER)) &&
                (destWorld.getBlockState(destPos.above()).getMaterial() != Material.AIR && !destWorld.getBlockState(destPos.above()).canBeReplaced(Fluids.WATER))) {
            destPos = destPos.above(2);
        }
        if(destWorld.getBlockStates(entity.getBoundingBox().inflate(10, 10, 10)).noneMatch(blockState -> blockState.getBlock() instanceof PlanetaryTeleporterBlock)) {
//            destWorld.setBlock(destPos.above(3), SpectrobesBlocks.planetary_teleporter.get().defaultBlockState(), 1);
        }
        for (BlockPos checkPos : BlockPos.betweenClosed(destPos.below(10).west(10), destPos.above(10).east(10))) {
            if (destWorld.getBlockState(checkPos).getBlock() instanceof AirBlock
                && destWorld.getBlockState(checkPos.above(1)).getBlock() instanceof AirBlock) {
                entity.setPos(destPos.getX(), destPos.above(4).getY(), destPos.getZ());
            }
        }



        return entity;
    }
}
