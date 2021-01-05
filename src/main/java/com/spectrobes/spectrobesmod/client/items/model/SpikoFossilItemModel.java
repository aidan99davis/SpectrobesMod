package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.ShakinFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.SpikoFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpikoFossilItemModel extends AnimatedGeoModel<SpikoFossilItem> {

    @Override
    public ResourceLocation getModelLocation(SpikoFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/spiko.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SpikoFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpikoFossilItem grildaFossilBlock) {
        return null;
    }
}
