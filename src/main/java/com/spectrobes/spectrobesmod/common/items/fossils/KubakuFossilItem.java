package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

public class KubakuFossilItem extends FossilItem {

    public KubakuFossilItem(Properties properties) {
        super(properties,"kubaku_fossil_item");
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Kubaku.copy(false);
    }
}
