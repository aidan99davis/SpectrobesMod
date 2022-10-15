package com.spectrobes.spectrobesmod.common.world.biomes.surfacebuilders;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class SpectrobesConfiguredSurfaceBuilders {

    public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> VOLCANO = register("volcano", new ConfiguredSurfaceBuilder(SpectrobesSurfaceBuilders.VOLCANO, SpectrobesSurfaceBuilders.BASALT_SURFACE));

    private static <C extends ISurfaceBuilderConfig, F extends ConfiguredSurfaceBuilder<C>> F register(String key, F builder)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(SpectrobesInfo.MOD_ID, key), builder);
    }
}
