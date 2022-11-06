package com.spectrobes.spectrobesmod.common.world.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GrassSplatterFeature extends Feature<NoneFeatureConfiguration>
{
    public GrassSplatterFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        return place(pContext.config(), pContext.level(), pContext.chunkGenerator(), pContext.random(), pContext.origin());
    }

    @Override
    public boolean place(NoneFeatureConfiguration pConfig, WorldGenLevel worldIn, ChunkGenerator pChunkGenerator, RandomSource rand, BlockPos pos) {
        int i = 0;
        int j = rand.nextInt(8 - 2) + 2;

        for (int k = pos.getX() - j; k <= pos.getX() + j; ++k)
        {
            for (int l = pos.getZ() - j; l <= pos.getZ() + j; ++l)
            {
                int i1 = k - pos.getX();
                int j1 = l - pos.getZ();
                if (i1 * i1 + j1 * j1 <= j * j)
                {
                    for (int k1 = pos.getY() - 2; k1 <= pos.getY() + 2; ++k1)
                    {
                        BlockPos blockpos = new BlockPos(k, k1, l);
                        BlockState blockstate = worldIn.getBlockState(blockpos);
                        BlockState blockstate1 = worldIn.getBlockState(blockpos.above(2));

                        if (blockstate1.isAir())
                        {
                            worldIn.setBlock(blockpos, Blocks.GRASS_BLOCK.defaultBlockState(), 2);
                            if (rand.nextInt(3) == 0)
                            {
                                worldIn.setBlock(blockpos.above(), Blocks.FERN.defaultBlockState(), 2);
                            }

                            ++i;
                            break;
                        }
                    }
                }
            }
        }

        return i > 0;
    }
}