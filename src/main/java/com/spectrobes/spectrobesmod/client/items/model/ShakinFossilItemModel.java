package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.SeguFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.ShakinFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShakinFossilItemModel extends AnimatedGeoModel<ShakinFossilItem> {

    @Override
    public ResourceLocation getModelLocation(ShakinFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/shakin.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ShakinFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ShakinFossilItem grildaFossilBlock) {
        return null;
    }
}
