//package com.spectrobes.spectrobesmod.common.world.biomes;
//
//import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
//import com.spectrobes.spectrobesmod.common.world.biomes.surfacebuilders.SpectrobesConfiguredSurfaceBuilders;
//import net.minecraft.core.particles.ParticleTypes;
//import net.minecraft.util.Mth;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.biome.BiomeGenerationSettings;
//
//public class GenshiLavaRiverBiome extends SpectrobeBiome {
//
//    @Override
//    void configureBiome(Biome.BiomeBuilder builder) {
//        builder.precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.NETHER).depth(4.5F).scale(0.0F).temperature(0.95F).downfall(0.3F);
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
//        MobSpawnInfo.Builder mobspawninfo$builder = (new MobSpawnInfo.Builder()).addSpawn(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(EntityType.SQUID, 2, 1, 4)).addSpawn(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(EntityType.SALMON, 5, 1, 5));
//        DefaultBiomeFeatures.commonSpawns(mobspawninfo$builder);
//        builder.surfaceBuilder(SpectrobesConfiguredSurfaceBuilders.VOLCANO);
//        builder.addStructureStart(StructureFeatures.MINESHAFT);
//        DefaultBiomeFeatures.addDefaultCarvers(builder);
//        builder.addFeature(GenerationStage.Decoration.LAKES, Features.LAKE_LAVA);
//        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
//        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
//        DefaultBiomeFeatures.addDefaultOres(builder);
//
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
//        return "genshi_lava_river_biome_2";
//    }
//}
