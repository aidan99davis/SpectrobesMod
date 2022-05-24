package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.AoiFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.BartorFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BartorFossilItemModel extends AnimatedGeoModel<BartorFossilItem> {

    @Override
    public ResourceLocation getModelLocation(BartorFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/bartor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BartorFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BartorFossilItem aoiFossilItem) {
        return null;
    }
}
