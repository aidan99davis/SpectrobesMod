package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.special.MiniXellesItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MiniXellesItemModel extends AnimatedGeoModel<MiniXellesItem> {

    @Override
    public ResourceLocation getModelResource(MiniXellesItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/xelles.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MiniXellesItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/krawl/xelles.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MiniXellesItem aoiFossilItem) {
        return null;
    }
}
