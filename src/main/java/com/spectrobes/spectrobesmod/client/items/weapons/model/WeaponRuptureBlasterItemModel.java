package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponRuptureBlasterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WeaponRuptureBlasterItemModel extends GeoModel<WeaponRuptureBlasterItem> {

    @Override
    public ResourceLocation getModelResource(WeaponRuptureBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/weapons/weapon_rupture_blaster.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WeaponRuptureBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/item/blaster_rupture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WeaponRuptureBlasterItem object) {
        return null;
    }
}
