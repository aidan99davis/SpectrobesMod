package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.DanawaFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DanawaFossilModel extends AnimatedGeoModel<DanawaFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(DanawaFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/danawa.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DanawaFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DanawaFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
