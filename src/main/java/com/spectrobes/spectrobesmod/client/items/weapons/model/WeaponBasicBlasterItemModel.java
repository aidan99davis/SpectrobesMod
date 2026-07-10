package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponBasicBlasterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WeaponBasicBlasterItemModel extends GeoModel<WeaponBasicBlasterItem> {

    @Override
    public ResourceLocation getModelResource(WeaponBasicBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/weapons/weapon_basic_blaster.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WeaponBasicBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/item/blaster_basic.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WeaponBasicBlasterItem object) {
        return null;
    }
}
