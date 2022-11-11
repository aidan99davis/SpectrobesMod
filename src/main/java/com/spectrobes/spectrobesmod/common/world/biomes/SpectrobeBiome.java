package com.spectrobes.spectrobesmod.common.world.biomes;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;

public abstract class SpectrobeBiome {

    abstract void configureBiome(Biome.BiomeBuilder builder);
    abstract void configureGeneration(BiomeGenerationSettings.Builder builder);
    abstract void configureMobSpawns(MobSpawnSettings.Builder builder);
    abstract String getBiomeName();

    protected void configureDefaultMobSpawns(MobSpawnSettings.Builder builder)
    {
//        builder.setPlayerCanSpawn();
    }

    public final Biome build()
    {
        Biome.BiomeBuilder biomeBuilder = new Biome.BiomeBuilder();

        // Configure the biome generation
        BiomeGenerationSettings.Builder biomeGenBuilder = new BiomeGenerationSettings.Builder();
        this.configureGeneration(biomeGenBuilder);
        biomeBuilder.generationSettings(biomeGenBuilder.build());

        // Configure mob spawning
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        this.configureDefaultMobSpawns(mobSpawnBuilder);
        this.configureMobSpawns(mobSpawnBuilder);
        biomeBuilder.mobSpawnSettings(mobSpawnBuilder.build());

        // Configure and build the biome
        this.configureBiome(biomeBuilder);
        return biomeBuilder.build()/*.setRegistryName(SpectrobesInfo.MOD_ID, getBiomeName())*/;
    }
}
