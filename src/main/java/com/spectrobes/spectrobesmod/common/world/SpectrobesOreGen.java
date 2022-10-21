package com.spectrobes.spectrobesmod.common.world;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
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
        overworldOres.add(register("mineral_ore", Feature.ORE.configured(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.NATURAL_STONE, SpectrobesBlocks.mineral_block.get().defaultBlockState(), 5))
                .range(180).squared()
                .count(128)));

        overworldOres.add(register("fossil_ore", Feature.ORE.configured(new OreFeatureConfig(
                OreFeatureConfig.FillerBlockType.NATURAL_STONE, SpectrobesBlocks.fossil_block.get().defaultBlockState(), 3)) //Vein Size
                .range(180).squared()
                .count(128)));
        
        overworldOres.add(register("metalium_ore", Feature.ORE.configured(new OreFeatureConfig(
        		OreFeatureConfig.FillerBlockType.NATURAL_STONE, SpectrobesBlocks.metalium_ore.get().defaultBlockState(), 4))
                .range(16)
                .squared()).count(2));
        
        overworldOres.add(register("titanium_ore", Feature.ORE.configured(new OreFeatureConfig(
        		OreFeatureConfig.FillerBlockType.NATURAL_STONE, SpectrobesBlocks.titanium_ore.get().defaultBlockState(), 4))
                .range(16)
                .squared()).count(2));
        
        overworldOres.add(register("marble_ore", Feature.ORE.configured(new OreFeatureConfig(
        		OreFeatureConfig.FillerBlockType.NATURAL_STONE, SpectrobesBlocks.marble_ore.get().defaultBlockState(), 4))
                .range(16)
                .squared()).count(2));

    }

    @SubscribeEvent
    public void gen(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        if(event.getCategory().equals(Biome.Category.NETHER)){
            for(ConfiguredFeature<?, ?> ore : netherOres){
                if (ore != null) generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        } else if(event.getCategory().equals(Biome.Category.THEEND)){
            for(ConfiguredFeature<?, ?> ore : endOres){
                if (ore != null) generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
            }
        } else {
            for(ConfiguredFeature<?, ?> ore : overworldOres){
                if (ore != null) {
                    generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
                }
            }
        }
    }

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(registry, SpectrobesInfo.MOD_ID + ":" + name, configuredFeature);
    }

}
