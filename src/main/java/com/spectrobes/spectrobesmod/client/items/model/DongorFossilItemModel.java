package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.DongorFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DongorFossilItemModel extends AnimatedGeoModel<DongorFossilItem> {

    @Override
    public ResourceLocation getModelResource(DongorFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/dongor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DongorFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DongorFossilItem aoiFossilItem) {
        return null;
    }
}
