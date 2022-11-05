package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.KomainuFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KomainuFossilItemModel extends AnimatedGeoModel<KomainuFossilItem> {

    @Override
    public ResourceLocation getModelResource(KomainuFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/komainu.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KomainuFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KomainuFossilItem grildaFossilBlock) {
        return null;
    }
}
