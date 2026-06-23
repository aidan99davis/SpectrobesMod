package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.BartorFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BartorFossilItemModel extends GeoModel<BartorFossilItem> {

    @Override
    public ResourceLocation getModelResource(BartorFossilItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/bartor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BartorFossilItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BartorFossilItem aoiFossilItem) {
        return null;
    }
}
