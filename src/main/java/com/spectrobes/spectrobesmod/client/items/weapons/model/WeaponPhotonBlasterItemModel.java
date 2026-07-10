package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponPhotonBlasterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WeaponPhotonBlasterItemModel extends GeoModel<WeaponPhotonBlasterItem> {

    @Override
    public ResourceLocation getModelResource(WeaponPhotonBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/weapons/weapon_photon_blaster.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WeaponPhotonBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/item/blaster_photon.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WeaponPhotonBlasterItem object) {
        return null;
    }
}
