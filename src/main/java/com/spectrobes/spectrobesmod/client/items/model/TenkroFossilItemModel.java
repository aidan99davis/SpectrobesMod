package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.TenkroFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TenkroFossilItemModel extends GeoModel<TenkroFossilItem> {

    @Override
    public ResourceLocation getModelResource(TenkroFossilItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/tenkro.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TenkroFossilItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TenkroFossilItem aoiFossilItem) {
        return null;
    }
}
