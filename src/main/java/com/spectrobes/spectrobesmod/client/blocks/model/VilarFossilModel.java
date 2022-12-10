package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.VilarFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class VilarFossilModel extends AnimatedGeoModel<VilarFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(VilarFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/vilar.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(VilarFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(VilarFossilBlockTileEntity fossil) {
        return null;
    }
}
