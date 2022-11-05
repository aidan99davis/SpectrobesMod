package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.ZozaFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ZozaFossilItemModel extends AnimatedGeoModel<ZozaFossilItem> {

    @Override
    public ResourceLocation getModelResource(ZozaFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/zoza.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ZozaFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ZozaFossilItem grildaFossilBlock) {
        return null;
    }
}
