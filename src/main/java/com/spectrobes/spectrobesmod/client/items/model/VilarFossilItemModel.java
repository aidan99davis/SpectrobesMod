package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.SpikoFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.VilarFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class VilarFossilItemModel extends AnimatedGeoModel<VilarFossilItem> {

    @Override
    public ResourceLocation getModelLocation(VilarFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/vilar.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(VilarFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(VilarFossilItem grildaFossilBlock) {
        return null;
    }
}
