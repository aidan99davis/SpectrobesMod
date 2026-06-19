package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.MesaFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MesaFossilItemModel extends GeoModel<MesaFossilItem> {

    @Override
    public ResourceLocation getModelResource(MesaFossilItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/mesa.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MesaFossilItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MesaFossilItem aoiFossilItem) {
        return null;
    }
}
