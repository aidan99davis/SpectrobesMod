package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.shakin.EntityShakor;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class ShakorModel extends AnimatedGeoModel<EntityShakor> {

    @Override
    public ResourceLocation getModelLocation(EntityShakor object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/shakor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityShakor object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/shakor.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityShakor object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/shakor.json");
    }
}