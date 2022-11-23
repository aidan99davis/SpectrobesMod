package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.XellesTrophyBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class XellesTrophyBlockModel extends AnimatedGeoModel<XellesTrophyBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(XellesTrophyBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/xelles.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(XellesTrophyBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/xelles_dead.png");
    }

    @Override
    public ResourceLocation getAnimationResource(XellesTrophyBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
