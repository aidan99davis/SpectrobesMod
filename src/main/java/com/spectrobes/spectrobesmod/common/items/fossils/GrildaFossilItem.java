package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

public class GrildaFossilItem extends FossilItem {

    public GrildaFossilItem(Properties properties) {
        super(properties,"grilda_fossil_item");
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Grilda.copy(false);
    }
}
