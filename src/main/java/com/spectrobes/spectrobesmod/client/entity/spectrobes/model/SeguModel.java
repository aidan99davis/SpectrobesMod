package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.segu.EntitySegu;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SeguModel extends AnimatedGeoModel<EntitySegu> {

    @Override
    public ResourceLocation getModelResource(EntitySegu object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/segu.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntitySegu object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/segu.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntitySegu object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/segu.json");
    }
}