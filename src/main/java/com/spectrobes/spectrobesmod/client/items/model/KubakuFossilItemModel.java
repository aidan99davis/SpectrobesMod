package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.KubakuFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KubakuFossilItemModel extends AnimatedGeoModel<KubakuFossilItem> {

    @Override
    public ResourceLocation getModelResource(KubakuFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/kubaku.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KubakuFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KubakuFossilItem grildaFossilBlock) {
        return null;
    }
}
