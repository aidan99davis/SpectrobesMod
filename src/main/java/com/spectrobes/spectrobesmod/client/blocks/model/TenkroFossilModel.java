package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.TenkroFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class TenkroFossilModel extends GeoModel<TenkroFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(TenkroFossilBlockTileEntity grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/tenkro.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TenkroFossilBlockTileEntity grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TenkroFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
