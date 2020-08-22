package com.spectrobes.spectrobesmod.common.worldgen;

import com.spectrobes.spectrobesmod.common.blocks.SpectrobesBlock;
import com.spectrobes.spectrobesmod.common.blocks.SpectrobesBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class SpectrobesOreGen {
    public static void generateOre() {
        for(Biome biome : ForgeRegistries.BIOMES) {
            ConfiguredPlacement mineralOreConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 23, 2, 128));
            ConfiguredPlacement fossilOreConfig = Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 20,  4, 128));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                            SpectrobesBlocks.fossil_block.getDefaultState(), 4))
                            .withPlacement(fossilOreConfig));
            biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                            SpectrobesBlocks.mineral_block.getDefaultState(), 4))
                            .withPlacement(mineralOreConfig));
        }
    }
}
