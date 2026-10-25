package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponDoubleBlasterItem;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponRayBlasterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WeaponRayBlasterItemModel extends GeoModel<WeaponRayBlasterItem> {

    @Override
    public ResourceLocation getModelResource(WeaponRayBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/weapons/weapon_ray_blaster.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WeaponRayBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/item/blaster_ray.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WeaponRayBlasterItem object) {
        return null;
    }
}
