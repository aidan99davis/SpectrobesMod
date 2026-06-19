package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.kubaku.EntityKuganon;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class KuganonModel extends GeoModel<EntityKuganon> {

    @Override
    public ResourceLocation getModelResource(EntityKuganon object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/kuganon.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityKuganon object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/kuganon.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityKuganon object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/kuganon.json");
    }
}