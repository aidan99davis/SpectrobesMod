package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.vilar.EntityVilamasta;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class VilamastaModel extends AnimatedGeoModel<EntityVilamasta> {

    @Override
    public ResourceLocation getModelResource(EntityVilamasta object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/vilamasta.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityVilamasta object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/vilamasta.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityVilamasta object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/vilamasta.json");
    }
}