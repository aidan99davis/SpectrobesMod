package com.spectrobes.spectrobesmod.client.items.healing.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.tools.healing.SpectrobeSerumHealingItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BasicSerumItemModel extends AnimatedGeoModel<SpectrobeSerumHealingItem> {

    @Override
    public ResourceLocation getModelLocation(SpectrobeSerumHealingItem serumItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/items/basic_serum.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SpectrobeSerumHealingItem serumItem) {
        switch (serumItem.getTier()) {
            case 1:
                return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/item/basic_serum.png");
            default:
                return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/item/basic_serum.png");

        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpectrobeSerumHealingItem serumItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/item/serum.json");
    }
}
