package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.vilar.EntityVilamasta;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class VilamastaModel extends AnimatedGeoModel<EntityVilamasta> {

    @Override
    public ResourceLocation getModelLocation(EntityVilamasta object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/vilamasta.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityVilamasta object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/vilamasta.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityVilamasta object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/vilamasta.json");
    }
}