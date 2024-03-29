//package com.spectrobes.spectrobesmod.common.world.features;
//
//import com.google.common.collect.ImmutableSet;
//import com.spectrobes.spectrobesmod.SpectrobesInfo;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.levelgen.feature.Feature;
//import net.minecraft.world.level.material.Fluids;
//import net.minecraftforge.registries.ForgeRegistries;
//
//public class SpectrobesFeatures {
//
//    public static final LiquidsConfig VOLCANO_SPRING_CONFIG = new LiquidsConfig(Fluids.LAVA.defaultFluidState(), true, 4, 1, ImmutableSet.of(Blocks.BASALT, Blocks.MAGMA_BLOCK, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE));
//
//    public static final Feature<NoFeatureConfig> GRASS_SPLATTER = register("grass_splatter", new GrassSplatterFeature(NoFeatureConfig.CODEC.stable()));
//
//    private static <C extends IFeatureConfig, F extends Feature<C>> F register(String key, F value)
//    {
//        value.setRegistryName(new ResourceLocation(SpectrobesInfo.MOD_ID, key));
//        ForgeRegistries.FEATURES.register(value);
//        return value;
//    }
//}
