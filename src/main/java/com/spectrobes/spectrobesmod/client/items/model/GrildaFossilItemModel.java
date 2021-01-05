package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.GrildaFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrildaFossilItemModel extends AnimatedGeoModel<GrildaFossilItem> {

    @Override
    public ResourceLocation getModelLocation(GrildaFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/grilda.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GrildaFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GrildaFossilItem grildaFossilBlock) {
        return null;
    }
}
