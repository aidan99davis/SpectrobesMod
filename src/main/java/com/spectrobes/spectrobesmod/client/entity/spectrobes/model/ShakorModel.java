package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.shakin.EntityShakor;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ShakorModel extends GeoModel<EntityShakor> {

    @Override
    public ResourceLocation getModelResource(EntityShakor object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/shakor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntityShakor object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/shakor_0.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntityShakor object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/shakor.json");
    }
}