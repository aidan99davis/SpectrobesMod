package com.spectrobes.spectrobesmod.client.items.machines.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.machines.CyrusShopBlockItem;
import com.spectrobes.spectrobesmod.common.items.machines.HealerBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CyrusShopBlockItemModel extends GeoModel<CyrusShopBlockItem> {

    @Override
    public ResourceLocation getModelResource(CyrusShopBlockItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/lab_machine_small.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CyrusShopBlockItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/cyrus_shop.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CyrusShopBlockItem aoiFossilItem) {
        return null;
    }
}
