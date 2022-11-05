package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.AoiFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AoiFossilItemModel extends AnimatedGeoModel<AoiFossilItem> {

    @Override
    public ResourceLocation getModelResource(AoiFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/aoi.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AoiFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AoiFossilItem aoiFossilItem) {
        return null;
    }
}
