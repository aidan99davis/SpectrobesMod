package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.special.XellesTrophyItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class XellesTrophyItemModel extends GeoModel<XellesTrophyItem> {

    @Override
    public ResourceLocation getModelResource(XellesTrophyItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/xelles.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(XellesTrophyItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/xelles_dead.png");
    }

    @Override
    public ResourceLocation getAnimationResource(XellesTrophyItem aoiFossilItem) {
        return null;
    }
}
