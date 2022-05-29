package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.GejioFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GejioFossilItemModel extends AnimatedGeoModel<GejioFossilItem> {

    @Override
    public ResourceLocation getModelLocation(GejioFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/gejio.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GejioFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GejioFossilItem aoiFossilItem) {
        return null;
    }
}
