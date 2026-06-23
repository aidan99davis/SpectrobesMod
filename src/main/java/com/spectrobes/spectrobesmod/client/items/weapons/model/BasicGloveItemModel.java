package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.BasicGloveItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BasicGloveItemModel extends GeoModel<BasicGloveItem> {

    @Override
    public ResourceLocation getModelResource(BasicGloveItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/weapons/basic_glove.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BasicGloveItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/item/glove_basic.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BasicGloveItem aoiFossilItem) {
        return null;
    }
}
