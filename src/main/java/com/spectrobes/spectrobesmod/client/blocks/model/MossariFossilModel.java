package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.MossariFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MossariFossilModel extends AnimatedGeoModel<MossariFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(MossariFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/mossari.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MossariFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MossariFossilBlockTileEntity fossil) {
        return null;
    }
}
