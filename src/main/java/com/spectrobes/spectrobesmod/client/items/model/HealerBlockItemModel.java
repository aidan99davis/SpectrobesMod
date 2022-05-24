package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.AoiFossilItem;
import com.spectrobes.spectrobesmod.common.items.machines.HealerBlockItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HealerBlockItemModel extends AnimatedGeoModel<HealerBlockItem> {

    @Override
    public ResourceLocation getModelLocation(HealerBlockItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/healingpod.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HealerBlockItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/healing_pod.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HealerBlockItem aoiFossilItem) {
        return null;
    }
}
