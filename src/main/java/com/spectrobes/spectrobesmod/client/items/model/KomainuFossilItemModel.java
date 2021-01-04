package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.HarumiFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.KomainuFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KomainuFossilItemModel extends AnimatedGeoModel<KomainuFossilItem> {

    @Override
    public ResourceLocation getModelLocation(KomainuFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/komainu.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(KomainuFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(KomainuFossilItem grildaFossilBlock) {
        return null;
    }
}
