package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.block.Block;

public class KasumiFossilItem extends FossilBlockItem {

    public KasumiFossilItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Kasumi.copy(false);
    }
}
