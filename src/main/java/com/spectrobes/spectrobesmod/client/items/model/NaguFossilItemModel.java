package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.KubakuFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.NaguFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NaguFossilItemModel extends AnimatedGeoModel<NaguFossilItem> {

    @Override
    public ResourceLocation getModelLocation(NaguFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/nagu.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NaguFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NaguFossilItem grildaFossilBlock) {
        return null;
    }
}
