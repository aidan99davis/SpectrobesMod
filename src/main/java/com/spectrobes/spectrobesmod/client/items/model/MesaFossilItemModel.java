package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.MesaFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MesaFossilItemModel extends AnimatedGeoModel<MesaFossilItem> {

    @Override
    public ResourceLocation getModelResource(MesaFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/mesa.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MesaFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MesaFossilItem aoiFossilItem) {
        return null;
    }
}
