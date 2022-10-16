package com.spectrobes.spectrobesmod.common.world.features;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class SpectrobesConfiguredFeatures {
    public static final ConfiguredFeature<?, ?> VOLCANO_SPRING = register("volcano_spring", Feature.SPRING.configured(SpectrobesFeatures.VOLCANO_SPRING_CONFIG).decorated(Placement.RANGE_VERY_BIASED.configured(new TopSolidRangeConfig(8, 16, 256))).squared().count(75));
    public static final ConfiguredFeature<?, ?> LAVA_LAKE_COMMON = register("lava_lake_common", Feature.LAKE.configured(new BlockStateFeatureConfig(Blocks.LAVA.defaultBlockState())).decorated(Placement.LAVA_LAKE.configured(new ChanceConfig(10))));
    public static final ConfiguredFeature<?, ?> LAVA_RIVER = register("lava_lake_common", Feature.LAKE.configured(new BlockStateFeatureConfig(Blocks.LAVA.defaultBlockState())).decorated(Placement.LAVA_LAKE.configured(new ChanceConfig(10))));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(SpectrobesInfo.MOD_ID, key), feature);
    }
}
