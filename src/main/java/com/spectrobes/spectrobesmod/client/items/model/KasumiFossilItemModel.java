package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.KasumiFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KasumiFossilItemModel extends AnimatedGeoModel<KasumiFossilItem> {

    @Override
    public ResourceLocation getModelResource(KasumiFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/kasumi.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KasumiFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KasumiFossilItem aoiFossilItem) {
        return null;
    }
}
