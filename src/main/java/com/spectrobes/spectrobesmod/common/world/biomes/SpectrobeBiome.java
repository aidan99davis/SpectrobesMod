package com.spectrobes.spectrobesmod.common.world.biomes;

import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public abstract class SpectrobeBiome {

    protected abstract void configureBiome(Biome.BiomeBuilder builder);

    protected abstract void configureGeneration(BiomeGenerationSettings.Builder builder);

    protected abstract void configureMobSpawns(MobSpawnSettings.Builder builder);

    protected abstract String getBiomeName();

    protected void configureDefaultMobSpawns(MobSpawnSettings.Builder builder) {
        // builder.setPlayerCanSpawn();
    }

    public final Biome build(
            HolderGetter<PlacedFeature> placedFeatures,
            HolderGetter<ConfiguredWorldCarver<?>> configuredWorldCarvers
    ) {
        Biome.BiomeBuilder biomeBuilder = new Biome.BiomeBuilder();

        BiomeGenerationSettings.Builder biomeGenBuilder =
                new BiomeGenerationSettings.Builder(placedFeatures, configuredWorldCarvers);
        this.configureGeneration(biomeGenBuilder);
        biomeBuilder.generationSettings(biomeGenBuilder.build());

        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        this.configureDefaultMobSpawns(mobSpawnBuilder);
        this.configureMobSpawns(mobSpawnBuilder);
        biomeBuilder.mobSpawnSettings(mobSpawnBuilder.build());

        this.configureBiome(biomeBuilder);

        return biomeBuilder.build();
    }
}