package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.kubaku.EntityKubaku;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class KubakuModel extends AnimatedGeoModel<EntityKubaku> {

    @Override
    public ResourceLocation getModelLocation(EntityKubaku object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/kubaku.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityKubaku object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/kubaku.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityKubaku object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/kubaku.json");
    }
}