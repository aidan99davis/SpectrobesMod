package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

public class HarumiFossilItem extends FossilItem {

    public HarumiFossilItem(Properties properties) {
        super(properties,"harumi_fossil_item");
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Harumi.copy(false);
    }
}
