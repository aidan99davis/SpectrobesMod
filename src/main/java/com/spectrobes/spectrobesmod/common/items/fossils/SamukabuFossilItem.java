package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

public class SamukabuFossilItem extends FossilItem {

    public SamukabuFossilItem(Properties properties) {
        super(properties,"samukabu_fossil_item");
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Samukabu.copy();
    }
}
