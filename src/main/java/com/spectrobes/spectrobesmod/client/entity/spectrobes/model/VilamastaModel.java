package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.vilar.EntityVilamasta;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class VilamastaModel extends GeoModel<EntityVilamasta> {

    @Override
    public ResourceLocation getModelResource(EntityVilamasta object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/vilamasta.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityVilamasta object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/vilamasta_0.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityVilamasta object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/vilamasta.json");
    }
}