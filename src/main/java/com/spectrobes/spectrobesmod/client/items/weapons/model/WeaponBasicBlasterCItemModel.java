package com.spectrobes.spectrobesmod.client.items.weapons.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponBasicBlasterCItem;
import com.spectrobes.spectrobesmod.common.items.weapons.WeaponBasicBlasterItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class WeaponBasicBlasterCItemModel extends GeoModel<WeaponBasicBlasterCItem> {

    @Override
    public ResourceLocation getModelResource(WeaponBasicBlasterCItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/weapons/weapon_basic_blaster_c.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WeaponBasicBlasterCItem object) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/item/blaster_basic_c.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WeaponBasicBlasterCItem object) {
        return null;
    }
}
