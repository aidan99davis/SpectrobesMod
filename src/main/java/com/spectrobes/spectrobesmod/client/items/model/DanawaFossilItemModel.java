package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.DanawaFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DanawaFossilItemModel extends AnimatedGeoModel<DanawaFossilItem> {

    @Override
    public ResourceLocation getModelResource(DanawaFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/danawa.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DanawaFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DanawaFossilItem aoiFossilItem) {
        return null;
    }
}
