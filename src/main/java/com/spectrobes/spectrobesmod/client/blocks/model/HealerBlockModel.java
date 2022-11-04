package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.HealerBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HealerBlockModel extends AnimatedGeoModel<HealerBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(HealerBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/healingpod.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HealerBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/healing_pod.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HealerBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
