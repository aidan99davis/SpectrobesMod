package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.AoiFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.GrildaFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AoiFossilItemModel extends AnimatedGeoModel<AoiFossilItem> {

    @Override
    public ResourceLocation getModelLocation(AoiFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/aoi.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AoiFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AoiFossilItem aoiFossilItem) {
        return null;
    }
}
