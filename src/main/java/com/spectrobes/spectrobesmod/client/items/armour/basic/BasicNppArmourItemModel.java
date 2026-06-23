package com.spectrobes.spectrobesmod.client.items.armour.basic;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.armour.BasicNppArmourItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BasicNppArmourItemModel extends GeoModel<BasicNppArmourItem> {
    @Override
    public ResourceLocation getModelResource(BasicNppArmourItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(
                SpectrobesInfo.MOD_ID,
                "geo/armour/basic.geo.json"
        );
    }

    @Override
    public ResourceLocation getTextureResource(BasicNppArmourItem animatable) {
        switch(animatable.getNature()) {
            case AURORA:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/armour/basic_aurora.png");
            case CORONA:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/armour/basic_corona.png");
            case FLASH:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/armour/basic_flash.png");
            default:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/armour/basic.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(BasicNppArmourItem animatable) {
        return ResourceLocation.fromNamespaceAndPath(
                SpectrobesInfo.MOD_ID,
                "animations/armor/basic_npp_armour.animation.json"
        );
    }
}