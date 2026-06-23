package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.NaguFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class NaguFossilItemModel extends GeoModel<NaguFossilItem> {

    @Override
    public ResourceLocation getModelResource(NaguFossilItem grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/nagu.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NaguFossilItem grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NaguFossilItem grildaFossilBlock) {
        return null;
    }
}
