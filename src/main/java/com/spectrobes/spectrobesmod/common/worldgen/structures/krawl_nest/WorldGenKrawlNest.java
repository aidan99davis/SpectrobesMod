package com.spectrobes.spectrobesmod.common.worldgen.structures.krawl_nest;

import com.mojang.serialization.Codec;
import com.spectrobes.spectrobesmod.common.blocks.SpectrobesBlock;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
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

    public boolean placeSmallGen(ISeedReader worldIn, Random rand, BlockPos pos) {
        generateNestRoom(worldIn, rand, pos);
        return false;
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator p_230362_3_, Random rand, BlockPos position, NoFeatureConfig p_230362_6_) {
        generateNestRoom(world, rand, position);
        return false;
    }

    public void generateNestRoom(ISeedReader world, Random rand, BlockPos position) {
        generateSphere(world, rand, position, 25, 10, KRAWL_NEST_BLOCK, KRAWL_NEST_BLOCK_2);
        generateSphere(world, rand, position, 22, 7, Blocks.AIR.defaultBlockState());

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
}
