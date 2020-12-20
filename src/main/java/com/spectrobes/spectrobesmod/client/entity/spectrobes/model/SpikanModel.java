package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko.EntitySpikan;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpikanModel extends AnimatedGeoModel<EntitySpikan> {

    @Override
    public ResourceLocation getModelLocation(EntitySpikan object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/spikan.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySpikan object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/spikan.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntitySpikan object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/spikan.json");
    }
}