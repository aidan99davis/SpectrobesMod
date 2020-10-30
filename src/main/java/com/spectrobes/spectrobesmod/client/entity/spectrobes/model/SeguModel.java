package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.segu.EntitySegu;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class SeguModel extends AnimatedGeoModel<EntitySegu> {

    @Override
    public ResourceLocation getModelLocation(EntitySegu object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/segu.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySegu object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/segu.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntitySegu object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/segu.json");
    }
}