package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.kubaku.EntityKubaku;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class KubakuModel extends GeoModel<EntityKubaku> {

    @Override
    public ResourceLocation getModelResource(EntityKubaku object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/kubaku.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityKubaku object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/kubaku.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityKubaku object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/kubaku.json");
    }
}