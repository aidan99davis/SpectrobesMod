package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku.EntitySamukabu;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SamukabuModel extends AnimatedGeoModel<EntitySamukabu> {

    @Override
    public ResourceLocation getModelResource(EntitySamukabu object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/samukabu.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntitySamukabu object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/samukabu.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntitySamukabu object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/samukabu.json");
    }
}