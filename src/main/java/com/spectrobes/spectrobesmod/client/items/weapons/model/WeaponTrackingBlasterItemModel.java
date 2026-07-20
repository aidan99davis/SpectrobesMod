package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponTrackingBlasterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WeaponTrackingBlasterItemModel extends GeoModel<WeaponTrackingBlasterItem> {

    @Override
    public ResourceLocation getModelResource(WeaponTrackingBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/weapons/weapon_tracking_blaster.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WeaponTrackingBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/item/blaster_tracking.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WeaponTrackingBlasterItem object) {
        return null;
    }
}
