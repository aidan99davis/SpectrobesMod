package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

public class NaguFossilItem extends FossilItem {

    public NaguFossilItem(Properties properties) {
        super(properties,"nagu_fossil_item");
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Nagu.copy(false);
    }
}
