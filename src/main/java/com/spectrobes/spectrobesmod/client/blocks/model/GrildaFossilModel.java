package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.GrildaFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrildaFossilModel extends AnimatedGeoModel<GrildaFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(GrildaFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/grilda.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GrildaFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GrildaFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
