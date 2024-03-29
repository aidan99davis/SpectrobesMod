package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.AoiFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AoiFossilModel extends AnimatedGeoModel<AoiFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(AoiFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/aoi.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AoiFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AoiFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
