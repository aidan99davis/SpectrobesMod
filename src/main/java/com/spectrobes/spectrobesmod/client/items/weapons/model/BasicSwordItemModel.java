package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.BasicSwordItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BasicSwordItemModel extends AnimatedGeoModel<BasicSwordItem> {

    @Override
    public ResourceLocation getModelResource(BasicSwordItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/weapons/basic_sword.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BasicSwordItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/item/basic_sword.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BasicSwordItem aoiFossilItem) {
        return null;
    }
}
