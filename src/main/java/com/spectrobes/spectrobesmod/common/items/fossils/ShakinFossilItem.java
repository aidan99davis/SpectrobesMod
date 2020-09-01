package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

public class ShakinFossilItem extends FossilItem {

    public ShakinFossilItem(Properties properties) {
        super(properties,"shakin_fossil_item");
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Shakin.copy(false);
    }
}
