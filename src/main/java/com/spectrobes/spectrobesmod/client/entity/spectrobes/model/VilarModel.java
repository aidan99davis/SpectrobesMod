package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.vilar.EntityVilar;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class VilarModel extends AnimatedGeoModel<EntityVilar> {

    @Override
    public ResourceLocation getModelLocation(EntityVilar object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/vilar.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityVilar object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/vilar.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityVilar object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/vilar.json");
    }
}