package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.ZozaFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ZozaFossilItemModel extends GeoModel<ZozaFossilItem> {

    @Override
    public ResourceLocation getModelResource(ZozaFossilItem grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/zoza.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ZozaFossilItem grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ZozaFossilItem grildaFossilBlock) {
        return null;
    }
}
