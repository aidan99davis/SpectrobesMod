package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

public class SpikoFossilItem extends FossilItem {

    public SpikoFossilItem(Properties properties) {
        super(properties,"spiko_fossil_item");
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Spiko.copy();
    }
}
