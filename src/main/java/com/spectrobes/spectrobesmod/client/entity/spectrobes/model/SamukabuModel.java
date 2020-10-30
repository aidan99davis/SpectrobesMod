package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku.EntitySamukabu;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class SamukabuModel extends AnimatedGeoModel<EntitySamukabu> {

    @Override
    public ResourceLocation getModelLocation(EntitySamukabu object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/samukabu.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySamukabu object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/samukabu.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntitySamukabu object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/samukabu.json");
    }
}