package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.HealerBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class HealerBlockModel extends GeoModel<HealerBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(HealerBlockTileEntity grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/healingpod.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HealerBlockTileEntity grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/healing_pod.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HealerBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
