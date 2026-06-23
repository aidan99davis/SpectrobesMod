package com.spectrobes.spectrobesmod.client.items.healing.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.tools.healing.SpectrobeAntidoteHealingItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BasicAntidoteItemModel extends GeoModel<SpectrobeAntidoteHealingItem> {

    @Override
    public ResourceLocation getModelResource(SpectrobeAntidoteHealingItem serumItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/items/basic_serum.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpectrobeAntidoteHealingItem serumItem) {
        return switch (serumItem.getTier()) {
            default -> ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/item/basic_antidote.png");
        };
    }

    @Override
    public ResourceLocation getAnimationResource(SpectrobeAntidoteHealingItem serumItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "animations/item/serum.json");
    }
}
