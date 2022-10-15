package com.spectrobes.spectrobesmod.common.worldgen;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WorldGenUtils {

    public static void generateSphere(IWorld world, Random rand, BlockPos position, int size, int height, BlockState fill) {
        int i2 = size;
        int ySize = rand.nextInt(2);
        int j = i2 + rand.nextInt(2);
        int k = height + ySize;
        int l = i2 + rand.nextInt(2);
        float f = (j + k + l) * 0.333F;
        for (BlockPos blockpos : BlockPos.betweenClosedStream(position.offset(-j, -k, -l), position.offset(j, k, l)).map(BlockPos::immutable).collect(Collectors.toSet())) {
            if (blockpos.distSqr(position) <= f * f && !world.getBlockState(blockpos).isAir()) {
                world.setBlock(blockpos, fill, 3);
            }
        }
    }

    public static void generateSphere(IWorld world, Random rand, BlockPos position, int size, int height, BlockState fill, BlockState fill2) {
        int i2 = size;
        int ySize = rand.nextInt(2);
        int j = i2 + rand.nextInt(2);
        int k = height + ySize;
        int l = i2 + rand.nextInt(2);
        float f = (j + k + l) * 0.333F;
        for (BlockPos blockpos : BlockPos.betweenClosedStream(position.offset(-j, -k, -l), position.offset(j, k, l)).map(BlockPos::immutable).collect(Collectors.toSet())) {
            if (blockpos.distSqr(position) <= f * f) {
                world.setBlock(blockpos, rand.nextInt(3) == 0 ? fill2 : fill, 2);
            }
        }
    }

    public static void generateSphereRespectAir(IWorld world, Random rand, BlockPos position, int size, int height, BlockState fill, BlockState fill2) {
        int i2 = size;
        int ySize = rand.nextInt(2);
        int j = i2 + rand.nextInt(2);
        int k = height + ySize;
        int l = i2 + rand.nextInt(2);
        float f = (j + k + l) * 0.333F;
        for (BlockPos blockpos : BlockPos.betweenClosedStream(position.offset(-j, -k, -l), position.offset(j, k, l)).map(BlockPos::immutable).collect(Collectors.toSet())) {
            if (blockpos.distSqr(position) <= f * f * MathHelper.clamp(rand.nextFloat(), 0.75F, 1.0F)
                    && !world.getBlockState(blockpos).isAir()) {
                world.setBlock(blockpos, rand.nextInt(3) == 0 ? fill2 : fill, 2);
            }
        }
    }

    public static void generateCircle(IWorld world, BlockPos position, int radius, int height, BlockState block) {
        List<BlockPos> affectedBlocks = new ArrayList<>(radius * radius * 2 * 4);
        final int maxDis = radius * radius;
        // check 3-layer circle around this entity (disc, not sphere)
        int dY = 1;
        // map the blockstates
        for (int i = -radius; i <= radius; i++) {
            for (int j = dY; j <= dY; j++) {
                for (int k = -radius; k <= radius; k++) {
                    BlockPos currentPos = position.offset(i, j, k);
                    if (position.distSqr(currentPos) <= maxDis) {
                        world.setBlock(new BlockPos(currentPos.getX(), currentPos.getY(), currentPos.getZ()), block, 11);
                    }
                }
            }
        }

        //        int centre = radius / 2, squareDistance;
//        int x1, y1, z1;
//
//        for(x1 = centre - radius; x1 < centre + radius; x1++){
////            for(y1 = centre - radius; y1 < centre + radius; y1++){
//                for(z1 = centre - radius; z1 < centre + radius; z1++) {
//                    squareDistance = (x1 -centre) * (x1 -centre) + (z1-centre) * (z1-centre);
//                    if(squareDistance <= (radius) * (radius))
//                        world.setBlock(new BlockPos(position.getX() + x1, position.getY(), position.getZ() + z1), block, 11);
//                }
////            }
//        }
    }

    public static void generateCircleRespectSky(IWorld world, Random rand, BlockPos position, int size, int height, Direction direction, BlockState block1, BlockState block2) {
        int radius = size + 2;
        {
            for (float i = 0; i < radius; i += 0.5) {
                for (float j = 0; j < 2 * Math.PI * i; j += 0.5) {
                    int x = (int) Math.floor(MathHelper.sin(j) * i);
                    int z = (int) Math.floor(MathHelper.cos(j) * i);
                    if (direction == Direction.WEST || direction == Direction.EAST) {
                        if (!world.canSeeSky(position.offset(0, x, z))) {
                            world.setBlock(position.offset(0, x, z), rand.nextInt(3) == 0 ? block1 : block2, 3);
                        }

                    } else {
                        if (!world.canSeeSky(position.offset(x, z, 0))) {
                            world.setBlock(position.offset(x, z, 0), rand.nextInt(3) == 0 ? block1 : block2, 3);
                        }
                    }
                }
            }
        }
        radius -= 2;
        {
            for (float i = 0; i < radius; i += 0.5) {
                for (float j = 0; j < 2 * Math.PI * i; j += 0.5) {
                    int x = (int) Math.floor(MathHelper.sin(j) * i * MathHelper.clamp(rand.nextFloat(), 0.5F, 1.0F));
                    int z = (int) Math.floor(MathHelper.cos(j) * i * MathHelper.clamp(rand.nextFloat(), 0.5F, 1.0F));
                    if (direction == Direction.WEST || direction == Direction.EAST) {
                        world.setBlock(position.offset(0, x, z), Blocks.AIR.defaultBlockState(), 3);
                    } else {
                        world.setBlock(position.offset(x, z, 0), Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
        }
    }
}
