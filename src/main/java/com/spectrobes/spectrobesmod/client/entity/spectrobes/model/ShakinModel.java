package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.shakin.EntityShakin;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class ShakinModel extends AnimatedGeoModel<EntityShakin> {

    @Override
    public ResourceLocation getModelLocation(EntityShakin object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/shakin.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityShakin object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/shakin.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityShakin object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/shakin.json");
    }
}