package com.spectrobes.spectrobesmod.client.items.healing.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.tools.healing.SpectrobeSerumHealingItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BasicSerumItemModel extends AnimatedGeoModel<SpectrobeSerumHealingItem> {

    @Override
    public ResourceLocation getModelResource(SpectrobeSerumHealingItem serumItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/items/basic_serum.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpectrobeSerumHealingItem serumItem) {
        return switch (serumItem.getTier()) {
            default -> new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/item/basic_serum.png");
        };
    }

    @Override
    public ResourceLocation getAnimationResource(SpectrobeSerumHealingItem serumItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/item/serum.json");
    }
}
