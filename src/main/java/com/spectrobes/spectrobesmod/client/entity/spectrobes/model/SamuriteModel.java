package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku.EntitySamurite;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SamuriteModel extends GeoModel<EntitySamurite> {

    @Override
    public ResourceLocation getModelResource(EntitySamurite object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/samurite.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EntitySamurite object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/samurite.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EntitySamurite object)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/spectrobe/samurite.json");
    }
}