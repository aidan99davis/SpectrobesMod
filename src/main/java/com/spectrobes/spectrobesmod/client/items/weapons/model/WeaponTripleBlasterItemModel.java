package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponRayBlasterItem;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponTripleBlasterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WeaponTripleBlasterItemModel extends GeoModel<WeaponTripleBlasterItem> {

    @Override
    public ResourceLocation getModelResource(WeaponTripleBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/weapons/weapon_triple_blaster.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WeaponTripleBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/item/blaster_triple.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WeaponTripleBlasterItem object) {
        return null;
    }
}
