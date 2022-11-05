package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.ShakinFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShakinFossilItemModel extends AnimatedGeoModel<ShakinFossilItem> {

    @Override
    public ResourceLocation getModelResource(ShakinFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/shakin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShakinFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShakinFossilItem grildaFossilBlock) {
        return null;
    }
}
