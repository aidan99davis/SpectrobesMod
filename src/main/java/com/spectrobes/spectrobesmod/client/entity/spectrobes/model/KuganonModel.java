package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.kubaku.EntityKuganon;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.model.AnimatedGeoModel;

public class KuganonModel extends AnimatedGeoModel<EntityKuganon> {

    @Override
    public ResourceLocation getModelLocation(EntityKuganon object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/kuganon.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(EntityKuganon object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/kuganon.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(EntityKuganon object)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/kuganon.json");
    }
}