package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.SamukabuFossilBlockTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SamukabuFossilModel extends AnimatedGeoModel<SamukabuFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(SamukabuFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/samukabu.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SamukabuFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SamukabuFossilBlockTileEntity fossil) {
        return null;
    }
}
