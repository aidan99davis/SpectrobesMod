package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponHomingBlasterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WeaponHomingBlasterItemModel extends GeoModel<WeaponHomingBlasterItem> {

    @Override
    public ResourceLocation getModelResource(WeaponHomingBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/weapons/weapon_homing_blaster.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WeaponHomingBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/item/blaster_homing.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WeaponHomingBlasterItem object) {
        return null;
    }
}
