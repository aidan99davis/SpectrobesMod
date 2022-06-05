package com.spectrobes.spectrobesmod.common.worldgen.structures.krawl_nest;

import com.mojang.serialization.Codec;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.stream.Collectors;

public class WorldGenKrawlNest extends Feature<NoFeatureConfig> {
    private static final BlockState KRAWL_NEST_BLOCK = SpectrobesBlocks.mineral_block.get().defaultBlockState();
    private static final BlockState KRAWL_NEST_BLOCK_2 = SpectrobesBlocks.fossil_block.get().defaultBlockState();

    public WorldGenKrawlNest(Codec<NoFeatureConfig> pCodec) {
        super(pCodec);
    }

    public void placeSmallGen(ISeedReader worldIn, Random rand, BlockPos pos) {
        generateNestRoom(worldIn, rand, pos);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator p_230362_3_, Random rand, BlockPos position, NoFeatureConfig p_230362_6_) {
        generateNestRoom(world, rand, position);
        return false;
    }

    public void generateNestRoom(ISeedReader world, Random rand, BlockPos position) {
        generateSphere(world, rand, position, 25, 10, KRAWL_NEST_BLOCK, KRAWL_NEST_BLOCK_2);
        generateSphere(world, rand, position, 22, 7, Blocks.AIR.defaultBlockState());
        Direction direction = Direction.getRandom(rand);
        generateEntrance(world, rand, position.relative(direction, 17).below(4), 4, 4, direction);

    }

    public void generateSphere(IWorld world, Random rand, BlockPos position, int size, int height, BlockState fill) {
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

    public void generateSphere(IWorld world, Random rand, BlockPos position, int size, int height, BlockState fill, BlockState fill2) {
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

    private void generateEntrance(IWorld world, Random rand, BlockPos position, int size, int height, Direction direction) {
        BlockPos up = position.above();
        while (up.getY() < world.getHeight(Heightmap.Type.WORLD_SURFACE, up.getX(), up.getZ())) {
            generateCircleRespectSky(world, rand, up, size, height, direction);
            up = up.above().offset(direction.getNormal());
        }
        generateSphereRespectAir(world, rand, up, size + 4, height + 2, KRAWL_NEST_BLOCK, KRAWL_NEST_BLOCK_2);
        generateSphere(world, rand, up.above(), size + 1, height, Blocks.AIR.defaultBlockState());
    }

    public void generateSphereRespectAir(IWorld world, Random rand, BlockPos position, int size, int height, BlockState fill, BlockState fill2) {
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

    private void generateCircleRespectSky(IWorld world, Random rand, BlockPos position, int size, int height, Direction direction) {
        int radius = size + 2;
        {
            for (float i = 0; i < radius; i += 0.5) {
                for (float j = 0; j < 2 * Math.PI * i; j += 0.5) {
                    int x = (int) Math.floor(MathHelper.sin(j) * i);
                    int z = (int) Math.floor(MathHelper.cos(j) * i);
                    if (direction == Direction.WEST || direction == Direction.EAST) {
                        if (!world.canSeeSky(position.offset(0, x, z))) {
                            world.setBlock(position.offset(0, x, z), rand.nextInt(3) == 0 ? KRAWL_NEST_BLOCK : KRAWL_NEST_BLOCK_2, 3);
                        }

                    } else {
                        if (!world.canSeeSky(position.offset(x, z, 0))) {
                            world.setBlock(position.offset(x, z, 0), rand.nextInt(3) == 0 ? KRAWL_NEST_BLOCK : KRAWL_NEST_BLOCK_2, 3);
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
