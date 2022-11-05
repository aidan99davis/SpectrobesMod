package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.segu.EntitySegulos;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SegulosModel extends AnimatedGeoModel<EntitySegulos> {

    @Override
    public ResourceLocation getModelResource(EntitySegulos object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/segulos.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntitySegulos object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/segulos_0.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntitySegulos object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/segulos.json");
    }
}