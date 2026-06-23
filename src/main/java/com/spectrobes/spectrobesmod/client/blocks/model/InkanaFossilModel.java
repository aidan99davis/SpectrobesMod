package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.InkanaFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class InkanaFossilModel extends GeoModel<InkanaFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(InkanaFossilBlockTileEntity grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/inkana.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(InkanaFossilBlockTileEntity grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(InkanaFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
