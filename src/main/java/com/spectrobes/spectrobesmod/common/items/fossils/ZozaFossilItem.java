package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

public class ZozaFossilItem extends FossilItem {

    public ZozaFossilItem(Properties properties) {
        super(properties,"zoza_fossil_item");
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Zoza.copy(false);
    }
}
