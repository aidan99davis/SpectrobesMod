package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.InkanaFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class InkanaFossilItemModel extends AnimatedGeoModel<InkanaFossilItem> {

    @Override
    public ResourceLocation getModelResource(InkanaFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/inkana.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(InkanaFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(InkanaFossilItem aoiFossilItem) {
        return null;
    }
}
