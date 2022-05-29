package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.BasicSwordItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BasicSwordItemModel extends AnimatedGeoModel<BasicSwordItem> {

    @Override
    public ResourceLocation getModelLocation(BasicSwordItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/weapons/basic_sword.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BasicSwordItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/item/basic_sword.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BasicSwordItem aoiFossilItem) {
        return null;
    }
}
