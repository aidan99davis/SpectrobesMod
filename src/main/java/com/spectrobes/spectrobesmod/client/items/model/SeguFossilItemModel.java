package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.SeguFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SeguFossilItemModel extends AnimatedGeoModel<SeguFossilItem> {

    @Override
    public ResourceLocation getModelResource(SeguFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/segu.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SeguFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SeguFossilItem grildaFossilBlock) {
        return null;
    }
}
