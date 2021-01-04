package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.GrildaFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.HarumiFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HarumiFossilItemModel extends AnimatedGeoModel<HarumiFossilItem> {

    @Override
    public ResourceLocation getModelLocation(HarumiFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/harumi.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HarumiFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HarumiFossilItem grildaFossilBlock) {
        return null;
    }
}
