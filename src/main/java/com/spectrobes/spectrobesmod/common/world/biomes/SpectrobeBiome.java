package com.spectrobes.spectrobesmod.common.world.biomes;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;

public abstract class SpectrobeBiome {

    abstract void configureBiome(Biome.Builder builder);
    abstract void configureGeneration(BiomeGenerationSettings.Builder builder);
    abstract void configureMobSpawns(MobSpawnInfo.Builder builder);
    abstract String getBiomeName();

    protected void configureDefaultMobSpawns(MobSpawnInfo.Builder builder)
    {
        builder.setPlayerCanSpawn();
    }

    public final Biome build()
    {
        Biome.Builder biomeBuilder = new Biome.Builder();

        // Configure the biome generation
        BiomeGenerationSettings.Builder biomeGenBuilder = new BiomeGenerationSettings.Builder();
        this.configureGeneration(biomeGenBuilder);
        biomeBuilder.generationSettings(biomeGenBuilder.build());

        // Configure mob spawning
        MobSpawnInfo.Builder mobSpawnBuilder = new MobSpawnInfo.Builder();
        this.configureDefaultMobSpawns(mobSpawnBuilder);
        this.configureMobSpawns(mobSpawnBuilder);
        biomeBuilder.mobSpawnSettings(mobSpawnBuilder.build());

        // Configure and build the biome
        this.configureBiome(biomeBuilder);
        return biomeBuilder.build().setRegistryName(SpectrobesInfo.MOD_ID, getBiomeName());
    }
}
