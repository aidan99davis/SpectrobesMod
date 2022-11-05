package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.TenkroFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TenkroFossilItemModel extends AnimatedGeoModel<TenkroFossilItem> {

    @Override
    public ResourceLocation getModelResource(TenkroFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/tenkro.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TenkroFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TenkroFossilItem aoiFossilItem) {
        return null;
    }
}
