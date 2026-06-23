package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.SamukabuFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SamukabuFossilModel extends GeoModel<SamukabuFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(SamukabuFossilBlockTileEntity fossil) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/samukabu.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SamukabuFossilBlockTileEntity fossil) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SamukabuFossilBlockTileEntity fossil) {
        return null;
    }
}
