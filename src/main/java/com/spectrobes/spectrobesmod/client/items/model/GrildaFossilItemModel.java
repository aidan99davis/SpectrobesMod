package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.GrildaFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrildaFossilItemModel extends AnimatedGeoModel<GrildaFossilItem> {

    @Override
    public ResourceLocation getModelResource(GrildaFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/grilda.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GrildaFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GrildaFossilItem grildaFossilBlock) {
        return null;
    }
}
