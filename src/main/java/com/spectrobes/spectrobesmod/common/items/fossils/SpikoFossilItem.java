package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import net.minecraft.entity.EntityType;

public class SpikoFossilItem extends FossilItem {

    public SpikoFossilItem(Properties properties) {
        super(properties,"spiko_fossil_item");
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getSpectrobeInstance() {
        return SpectrobesEntities.ENTITY_SPIKO.get();
    }
}
