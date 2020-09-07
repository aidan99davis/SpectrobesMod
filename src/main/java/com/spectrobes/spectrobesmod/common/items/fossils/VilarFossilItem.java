package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

public class VilarFossilItem extends FossilItem {

    public VilarFossilItem(Properties properties) {
        super(properties,"vilar_fossil_item");
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Vilar.copy(false);
    }
}
