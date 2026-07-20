package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponHyperBlasterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WeaponHyperBlasterItemModel extends GeoModel<WeaponHyperBlasterItem> {

    @Override
    public ResourceLocation getModelResource(WeaponHyperBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/weapons/weapon_hyper_blaster.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WeaponHyperBlasterItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/item/blaster_hyper.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WeaponHyperBlasterItem object) {
        return null;
    }
}
