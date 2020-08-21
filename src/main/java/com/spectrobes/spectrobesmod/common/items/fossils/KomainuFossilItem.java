package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.client.entity.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.EntitySpectrobe;
import net.minecraft.entity.EntityType;

public class KomainuFossilItem extends FossilItem {

    public KomainuFossilItem(Properties properties) {
        super(properties,"komainu_fossil_item");
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getSpectrobeInstance() {
        return SpectrobesEntities.ENTITY_KOMAINU.get();
    }
}
