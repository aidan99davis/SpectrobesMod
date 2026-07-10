package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponShatterBlasterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WeaponShatterBlasterItemModel extends GeoModel<WeaponShatterBlasterItem> {

    @Override
    public ResourceLocation getModelResource(WeaponShatterBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/weapons/weapon_shatter_blaster.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WeaponShatterBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/item/blaster_shatter.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WeaponShatterBlasterItem object) {
        return null;
    }
}
