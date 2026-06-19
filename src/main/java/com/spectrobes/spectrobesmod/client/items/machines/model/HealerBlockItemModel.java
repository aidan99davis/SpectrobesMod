package com.spectrobes.spectrobesmod.client.items.machines.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.machines.HealerBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HealerBlockItemModel extends GeoModel<HealerBlockItem> {

    @Override
    public ResourceLocation getModelResource(HealerBlockItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/healingpod.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HealerBlockItem aoiFossilItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/healing_pod.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HealerBlockItem aoiFossilItem) {
        return null;
    }
}
