package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.vilar.EntityVilar;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class VilarModel extends AnimatedGeoModel<EntityVilar> {

    @Override
    public ResourceLocation getModelResource(EntityVilar object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/vilar.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityVilar object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/vilar.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityVilar object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/vilar.json");
    }
}