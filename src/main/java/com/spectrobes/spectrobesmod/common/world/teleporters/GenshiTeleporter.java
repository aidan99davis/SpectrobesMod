package com.spectrobes.spectrobesmod.common.world.teleporters;

import com.spectrobes.spectrobesmod.common.blocks.FossilBlock;
import com.spectrobes.spectrobesmod.common.blocks.PlanetaryTeleporterBlock;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
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
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);
        double y = 61;
        if (!thisIsToDaylightDim) {
            y = thisPos.getY();
        }
        BlockPos destPos = new BlockPos(thisPos.getX(), y, thisPos.getZ());
        int tries = 0;
        while ((destWorld.getBlockState(destPos).getMaterial() != Material.AIR && !destWorld.getBlockState(destPos).canBeReplaced(Fluids.WATER)) &&
                (destWorld.getBlockState(destPos.above()).getMaterial() != Material.AIR && !destWorld.getBlockState(destPos.above()).canBeReplaced(Fluids.WATER)) &&
                tries < 25) {
            destPos = destPos.above(2);
            tries++;
        }
        entity.setPos(destPos.getX(), destPos.getY(), destPos.getZ());
        if (thisIsToDaylightDim) {
            for (BlockPos checkPos : BlockPos.betweenClosed(destPos.below(10).west(10), destPos.above(10).east(10))) {
                if (destWorld.getBlockState(checkPos).getBlock() instanceof FossilBlock) {
                    break;
                }
            }
        }
        if(destWorld.getBlockStates(entity.getBoundingBox().inflate(10, 10, 10)).noneMatch(blockState -> blockState.getBlock() instanceof PlanetaryTeleporterBlock)) {
            destWorld.setBlock(destPos, SpectrobesBlocks.planetary_teleporter.get().defaultBlockState(), 1);
        }
        return entity;
    }
}
