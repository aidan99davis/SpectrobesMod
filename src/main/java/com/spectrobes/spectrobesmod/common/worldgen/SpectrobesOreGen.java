package com.spectrobes.spectrobesmod.common.worldgen;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpectrobesOreGen {

    static Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;
    private static final ArrayList<ConfiguredFeature<?, ?>> overworldOres = new ArrayList<>();
    private static final ArrayList<ConfiguredFeature<?, ?>> netherOres = new ArrayList<>();
    private static final ArrayList<ConfiguredFeature<?, ?>> endOres = new ArrayList<>();

    public static void registerOres(){
        //Overworld Ore Register
        overworldOres.add(register("mineral_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, SpectrobesBlocks.mineral_block.get().getDefaultState(), 3))
                .range(180).square()
                .func_242731_b(16)));

        overworldOres.add(register("fossil_ore", Feature.ORE.withConfiguration(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, SpectrobesBlocks.fossil_block.get().getDefaultState(), 2)) //Vein Size
                .range(180).square() //max spawn height
                .func_242731_b(16))); //amount of veins

    }

    @SubscribeEvent
    public void gen(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        if(event.getCategory().equals(Biome.Category.NETHER)){
            for(ConfiguredFeature<?, ?> ore : netherOres){
                if (ore != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        } else if(event.getCategory().equals(Biome.Category.THEEND)){
            for(ConfiguredFeature<?, ?> ore : endOres){
                if (ore != null) generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        } else {
            for(ConfiguredFeature<?, ?> ore : overworldOres){
                if (ore != null) {
                    generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
                }
            }
        }

    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(registry, SpectrobesInfo.MOD_ID + ":" + name, configuredFeature);
    }

}
