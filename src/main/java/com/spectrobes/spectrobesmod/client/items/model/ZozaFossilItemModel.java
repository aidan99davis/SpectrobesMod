package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.VilarFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.ZozaFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ZozaFossilItemModel extends AnimatedGeoModel<ZozaFossilItem> {

    @Override
    public ResourceLocation getModelLocation(ZozaFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/zoza.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ZozaFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ZozaFossilItem grildaFossilBlock) {
        return null;
    }
}
