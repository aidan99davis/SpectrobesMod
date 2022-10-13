package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.BasicGloveItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BasicGloveItemModel extends AnimatedGeoModel<BasicGloveItem> {

    @Override
    public ResourceLocation getModelLocation(BasicGloveItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/weapons/basic_glove.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BasicGloveItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/item/glove_basic.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BasicGloveItem aoiFossilItem) {
        return null;
    }
}
