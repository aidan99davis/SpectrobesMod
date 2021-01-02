package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.segu.EntitySegu;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.segu.EntitySegulos;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SegulosModel extends AnimatedGeoModel<EntitySegulos> {

    @Override
    public ResourceLocation getModelLocation(EntitySegulos object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/segulos.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntitySegulos object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/segulos_0.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntitySegulos object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/segulos.json");
    }
}