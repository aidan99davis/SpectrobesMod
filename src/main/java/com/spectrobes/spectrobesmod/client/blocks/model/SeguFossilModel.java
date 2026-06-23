package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.SeguFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SeguFossilModel extends GeoModel<SeguFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(SeguFossilBlockTileEntity fossil) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/segu.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SeguFossilBlockTileEntity fossil) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SeguFossilBlockTileEntity fossil) {
        return null;
    }
}
