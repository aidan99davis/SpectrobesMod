package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.SpikoFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpikoFossilModel extends AnimatedGeoModel<SpikoFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(SpikoFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/spiko.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpikoFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpikoFossilBlockTileEntity fossil) {
        return null;
    }
}
