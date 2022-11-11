//package com.spectrobes.spectrobesmod.common.world.biomes.surfacebuilders;
//
//import com.spectrobes.spectrobesmod.SpectrobesInfo;
//import net.minecraft.core.Registry;
//import net.minecraft.resources.ResourceLocation;
//
//public class SpectrobesConfiguredSurfaceBuilders {
//
//    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> VOLCANO = register("volcano", new ConfiguredSurfaceBuilder(SpectrobesSurfaceBuilders.VOLCANO, SpectrobesSurfaceBuilders.BASALT_SURFACE));
//
//    private static <C extends ISurfaceBuilderConfig, F extends ConfiguredSurfaceBuilder<C>> F register(String key, F builder)
//    {
//        return Registry.register(WorldGenRegistry.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(SpectrobesInfo.MOD_ID, key), builder);
//    }
//}
