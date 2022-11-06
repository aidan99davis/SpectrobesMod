//package com.spectrobes.spectrobesmod.common.world.biomes;
//
//import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
//import com.spectrobes.spectrobesmod.common.world.biomes.surfacebuilders.SpectrobesConfiguredSurfaceBuilders;
//import com.spectrobes.spectrobesmod.common.world.features.SpectrobesConfiguredFeatures;
//import net.minecraft.core.particles.ParticleTypes;
//import net.minecraft.util.Mth;
//import net.minecraft.world.level.biome.Biome;
//
//public class GenshiVolcanoBiome extends SpectrobeBiome {
//
//    @Override
//    void configureBiome(Biome.BiomeBuilder builder) {
//        builder.precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.NONE).depth(4.5F).scale(0.0F).temperature(0.95F).downfall(0.3F);
//        builder.specialEffects((new BiomeAmbience.Builder())
//                .fogColor(0x7F7F7F)
//                .skyColor(Mth.color(235, 192, 52)) //201, 164, 42 <- darker yellow
//                .grassColorOverride(0x4A703B)
//                .waterColor(4159204)
//                .waterFogColor(329011)
//                .foliageColorOverride(0x547D42)
//                .ambientParticle(new ParticleEffectAmbience(ParticleTypes.WHITE_ASH, 0.059046667F))
//                .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
//                .build());
//    }
//
//    @Override
//    void configureGeneration(BiomeGenerationSettings.Builder builder) {
//        builder.surfaceBuilder(SpectrobesConfiguredSurfaceBuilders.VOLCANO);
//
//        // Structures
//        DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);
//        builder.addStructureStart(StructureFeatures.RUINED_PORTAL_MOUNTAIN);
//
//        // Underground
//        DefaultBiomeFeatures.addDefaultCarvers(builder);
//
//        builder.addFeature(GenerationStage.Decoration.LAKES, SpectrobesConfiguredFeatures.LAVA_LAKE_COMMON);
//
//        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
//        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
//        DefaultBiomeFeatures.addDefaultOres(builder);
//
//        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.DISK_GRAVEL);
//
//        // Other Features
//        DefaultBiomeFeatures.addDefaultSprings(builder);
//
//        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, SpectrobesConfiguredFeatures.VOLCANO_SPRING);
//
//        DefaultBiomeFeatures.addExtraEmeralds(builder);
//        DefaultBiomeFeatures.addInfestedStone(builder);
//        DefaultBiomeFeatures.addSurfaceFreezing(builder);
//    }
//
//    @Override
//    void configureMobSpawns(MobSpawnInfo.Builder builder) {
//        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4));
//        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 95, 4, 4));
//        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
//        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 100, 4, 4));
//        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.CREEPER, 100, 4, 4));
//        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
//        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
//        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));
//        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(KrawlEntities.ENTITY_VORTEX.get(), 95, 4, 4));
//    }
//
//    @Override
//    String getBiomeName() {
//        return "genshi_volcano_biome";
//    }
//}
