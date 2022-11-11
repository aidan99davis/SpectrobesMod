package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko.EntitySpiko;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpikoModel extends AnimatedGeoModel<EntitySpiko> {

    @Override
    public ResourceLocation getModelResource(EntitySpiko object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/spiko.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntitySpiko object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/spiko.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntitySpiko object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/spiko.json");
    }
}