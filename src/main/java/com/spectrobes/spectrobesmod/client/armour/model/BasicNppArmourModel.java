package com.spectrobes.spectrobesmod.client.armour.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.armour.BasicNppArmourItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BasicNppArmourModel extends AnimatedGeoModel<BasicNppArmourItem> {
    @Override
    public ResourceLocation getModelLocation(BasicNppArmourItem object) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/armour/basic.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BasicNppArmourItem object) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/armour/basic.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BasicNppArmourItem animatable) {
        return null;
    }
}
