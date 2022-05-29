package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.DongorFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DongorFossilItemModel extends AnimatedGeoModel<DongorFossilItem> {

    @Override
    public ResourceLocation getModelLocation(DongorFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/dongor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DongorFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DongorFossilItem aoiFossilItem) {
        return null;
    }
}
