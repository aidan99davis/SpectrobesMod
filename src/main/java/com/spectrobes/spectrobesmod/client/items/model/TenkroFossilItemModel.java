package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.TenkroFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TenkroFossilItemModel extends AnimatedGeoModel<TenkroFossilItem> {

    @Override
    public ResourceLocation getModelLocation(TenkroFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/tenkro.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TenkroFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TenkroFossilItem aoiFossilItem) {
        return null;
    }
}
