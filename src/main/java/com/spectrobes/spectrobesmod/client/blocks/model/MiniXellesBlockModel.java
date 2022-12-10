package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.XellesTrophyBlockTileEntity;
import com.spectrobes.spectrobesmod.common.blocks.krawl.MiniXellesBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MiniXellesBlockModel extends AnimatedGeoModel<MiniXellesBlockEntity> {

    @Override
    public ResourceLocation getModelResource(MiniXellesBlockEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/xelles.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MiniXellesBlockEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/krawl/xelles.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MiniXellesBlockEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/krawl/xelles.json");
    }
}
