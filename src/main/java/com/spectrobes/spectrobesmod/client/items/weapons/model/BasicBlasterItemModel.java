package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.BasicBlasterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BasicBlasterItemModel extends AnimatedGeoModel<BasicBlasterItem> {

    @Override
    public ResourceLocation getModelResource(BasicBlasterItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/weapons/basic_blaster.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BasicBlasterItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/item/blaster_basic.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BasicBlasterItem aoiFossilItem) {
        return null;
    }
}
