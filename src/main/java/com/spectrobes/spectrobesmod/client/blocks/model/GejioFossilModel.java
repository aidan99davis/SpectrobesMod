package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.GejioFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GejioFossilModel extends GeoModel<GejioFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(GejioFossilBlockTileEntity grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/gejio.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GejioFossilBlockTileEntity grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GejioFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
