package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.MossariFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.NaguFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MossariFossilItemModel extends AnimatedGeoModel<MossariFossilItem> {

    @Override
    public ResourceLocation getModelLocation(MossariFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/mossari.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MossariFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MossariFossilItem grildaFossilBlock) {
        return null;
    }
}
