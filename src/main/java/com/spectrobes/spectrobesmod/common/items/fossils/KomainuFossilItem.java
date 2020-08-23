package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

public class KomainuFossilItem extends FossilItem {

    public KomainuFossilItem(Properties properties) {
        super(properties,"komainu_fossil_item");
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Komainu.copy();
    }
}
