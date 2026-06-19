package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.shakin.EntityShakin;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ShakinModel extends GeoModel<EntityShakin> {

    @Override
    public ResourceLocation getModelResource(EntityShakin object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/shakin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityShakin object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/shakin_0.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityShakin object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/shakin.json");
    }
}