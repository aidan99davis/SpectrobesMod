package com.spectrobes.spectrobesmod.common.worldgen;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SpectrobesOreGen {

    @SubscribeEvent
    public static void generateOre(FMLLoadCompleteEvent event) {
        for(Biome biome : ForgeRegistries.BIOMES) {
            ConfiguredPlacement mineralOreConfig = Placement.RANGE.configure(new OreFeatureConfig(20, 23, 2, 128));
            ConfiguredPlacement fossilOreConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 20,  4, 128));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                            SpectrobesBlocks.fossil_block.get().getDefaultState(), 4))
                            .withPlacement(fossilOreConfig));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                            SpectrobesBlocks.mineral_block.get().getDefaultState(), 4))
                            .withPlacement(mineralOreConfig));
        }
    }
}
