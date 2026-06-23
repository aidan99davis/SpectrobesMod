package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.KubakuFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class KubakuFossilItemModel extends GeoModel<KubakuFossilItem> {

    @Override
    public ResourceLocation getModelResource(KubakuFossilItem grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/kubaku.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KubakuFossilItem grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KubakuFossilItem grildaFossilBlock) {
        return null;
    }
}
