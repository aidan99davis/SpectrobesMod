package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponBasicBlasterItem;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponDoubleBlasterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WeaponDoubleBlasterItemModel extends GeoModel<WeaponDoubleBlasterItem> {

    @Override
    public ResourceLocation getModelResource(WeaponDoubleBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/weapons/weapon_double_blaster.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WeaponDoubleBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/item/blaster_double.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WeaponDoubleBlasterItem object) {
        return null;
    }
}
