package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.ShakinFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ShakinFossilItemModel extends GeoModel<ShakinFossilItem> {

    @Override
    public ResourceLocation getModelResource(ShakinFossilItem grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/shakin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShakinFossilItem grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShakinFossilItem grildaFossilBlock) {
        return null;
    }
}
