package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.MasettoFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MasettoFossilItemModel extends AnimatedGeoModel<MasettoFossilItem> {

    @Override
    public ResourceLocation getModelResource(MasettoFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/masetto.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MasettoFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MasettoFossilItem aoiFossilItem) {
        return null;
    }
}
