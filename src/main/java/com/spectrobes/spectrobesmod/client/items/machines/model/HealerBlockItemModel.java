package com.spectrobes.spectrobesmod.client.items.machines.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.machines.HealerBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HealerBlockItemModel extends AnimatedGeoModel<HealerBlockItem> {

    @Override
    public ResourceLocation getModelResource(HealerBlockItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/healingpod.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HealerBlockItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/healing_pod.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HealerBlockItem aoiFossilItem) {
        return null;
    }
}
