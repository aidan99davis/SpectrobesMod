package com.spectrobes.spectrobesmod.common.world.biomes.surfacebuilders;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class SpectrobesSurfaceBuilders {
    public static final SurfaceBuilderConfig BASALT_SURFACE = new SurfaceBuilderConfig(Blocks.BASALT.defaultBlockState(), Blocks.BASALT.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
    public static final SurfaceBuilderConfig MAGMA_SURFACE = new SurfaceBuilderConfig(Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.BASALT.defaultBlockState());

    public static final SurfaceBuilder<SurfaceBuilderConfig> VOLCANO = register("volcano", new VolcanoSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));
    public static final SurfaceBuilder<SurfaceBuilderConfig> DEEP_TOP_LAYER = register("deep_top_layer", new DeepTopLayerSurfaceBuilder(SurfaceBuilderConfig.CODEC.stable()));

    private static <C extends ISurfaceBuilderConfig, F extends SurfaceBuilder<C>> F register(String key, F builder)
    {
        builder.setRegistryName(new ResourceLocation(SpectrobesInfo.MOD_ID, key));
        ForgeRegistries.SURFACE_BUILDERS.register(builder);
        return builder;
    }
}
