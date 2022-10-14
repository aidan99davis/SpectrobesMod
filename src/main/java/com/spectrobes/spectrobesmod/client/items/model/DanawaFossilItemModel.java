package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.DanawaFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.DongorFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DanawaFossilItemModel extends AnimatedGeoModel<DanawaFossilItem> {

    @Override
    public ResourceLocation getModelLocation(DanawaFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/danawa.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DanawaFossilItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DanawaFossilItem aoiFossilItem) {
        return null;
    }
}
