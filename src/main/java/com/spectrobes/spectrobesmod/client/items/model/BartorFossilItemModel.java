package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.BartorFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BartorFossilItemModel extends AnimatedGeoModel<BartorFossilItem> {

    @Override
    public ResourceLocation getModelResource(BartorFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/bartor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BartorFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BartorFossilItem aoiFossilItem) {
        return null;
    }
}
