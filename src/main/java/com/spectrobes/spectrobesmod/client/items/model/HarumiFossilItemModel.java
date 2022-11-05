package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.HarumiFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HarumiFossilItemModel extends AnimatedGeoModel<HarumiFossilItem> {

    @Override
    public ResourceLocation getModelResource(HarumiFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/harumi.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HarumiFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HarumiFossilItem grildaFossilBlock) {
        return null;
    }
}
