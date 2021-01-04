package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.NaguFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.SamukabuFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SamukabuFossilItemModel extends AnimatedGeoModel<SamukabuFossilItem> {

    @Override
    public ResourceLocation getModelLocation(SamukabuFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/samukabu.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SamukabuFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SamukabuFossilItem grildaFossilBlock) {
        return null;
    }
}
