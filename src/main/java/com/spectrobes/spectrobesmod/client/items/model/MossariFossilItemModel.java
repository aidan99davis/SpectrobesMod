package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.MossariFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MossariFossilItemModel extends AnimatedGeoModel<MossariFossilItem> {

    @Override
    public ResourceLocation getModelResource(MossariFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/mossari.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MossariFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MossariFossilItem grildaFossilBlock) {
        return null;
    }
}
