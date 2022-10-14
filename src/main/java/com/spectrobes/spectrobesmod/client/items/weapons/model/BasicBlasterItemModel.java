package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.BasicBlasterItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BasicBlasterItemModel extends AnimatedGeoModel<BasicBlasterItem> {

    @Override
    public ResourceLocation getModelLocation(BasicBlasterItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/weapons/basic_blaster.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BasicBlasterItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/item/blaster_basic.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BasicBlasterItem aoiFossilItem) {
        return null;
    }
}
