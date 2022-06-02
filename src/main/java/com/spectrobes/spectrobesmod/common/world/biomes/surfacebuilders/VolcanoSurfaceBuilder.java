package com.spectrobes.spectrobesmod.common.world.biomes.surfacebuilders;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class VolcanoSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
    public VolcanoSurfaceBuilder(Codec<SurfaceBuilderConfig> deserializer)
    {
        super(deserializer);
    }

    @Override
    public void apply(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config)
    {
        if (noise > 2.7F)
        {
            SurfaceBuilder.DEFAULT.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SpectrobesSurfaceBuilders.MAGMA_SURFACE);
        }
        else
        {
            SpectrobesSurfaceBuilders.DEEP_TOP_LAYER.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SpectrobesSurfaceBuilders.BASALT_SURFACE);
        }
    }
}
