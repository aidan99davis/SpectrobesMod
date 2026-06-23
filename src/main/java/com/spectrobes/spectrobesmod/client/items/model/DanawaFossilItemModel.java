package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.DanawaFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DanawaFossilItemModel extends GeoModel<DanawaFossilItem> {

    @Override
    public ResourceLocation getModelResource(DanawaFossilItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/danawa.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DanawaFossilItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DanawaFossilItem aoiFossilItem) {
        return null;
    }
}
