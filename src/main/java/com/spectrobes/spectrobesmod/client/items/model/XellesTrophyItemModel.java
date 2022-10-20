package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.machines.HealerBlockItem;
import com.spectrobes.spectrobesmod.common.items.special.XellesTrophyItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class XellesTrophyItemModel extends AnimatedGeoModel<XellesTrophyItem> {

    @Override
    public ResourceLocation getModelLocation(XellesTrophyItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/xelles.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(XellesTrophyItem aoiFossilItem) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/xelles_dead.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(XellesTrophyItem aoiFossilItem) {
        return null;
    }
}
