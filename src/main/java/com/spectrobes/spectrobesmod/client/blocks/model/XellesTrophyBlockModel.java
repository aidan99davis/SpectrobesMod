package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.HealerBlockTileEntity;
import com.spectrobes.spectrobesmod.common.blocks.tile.XellesTrophyBlockTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class XellesTrophyBlockModel extends AnimatedGeoModel<XellesTrophyBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(XellesTrophyBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/xelles.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(XellesTrophyBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/xelles_dead.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(XellesTrophyBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
