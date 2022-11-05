package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku.EntitySamurite;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SamuriteModel extends AnimatedGeoModel<EntitySamurite> {

    @Override
    public ResourceLocation getModelResource(EntitySamurite object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/samurite.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntitySamurite object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/samurite.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntitySamurite object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/samurite.json");
    }
}