package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.HarumiFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HarumiFossilModel extends GeoModel<HarumiFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(HarumiFossilBlockTileEntity fossil) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/harumi.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HarumiFossilBlockTileEntity fossil) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HarumiFossilBlockTileEntity fossil) {
        return null;
    }
}
