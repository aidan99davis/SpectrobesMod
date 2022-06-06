package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.InkanaFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class InkanaFossilItemModel extends AnimatedGeoModel<InkanaFossilItem> {

    @Override
    public ResourceLocation getModelLocation(InkanaFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/inkana.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(InkanaFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(InkanaFossilItem aoiFossilItem) {
        return null;
    }
}
