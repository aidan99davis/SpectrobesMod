package com.spectrobes.spectrobesmod.common.world.dimensions;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class SpectrobesDimensions {
    public static final RegistryKey<World> GENSHI_DIMENSION = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(SpectrobesInfo.MOD_ID, "genshi_dimension"));
}
