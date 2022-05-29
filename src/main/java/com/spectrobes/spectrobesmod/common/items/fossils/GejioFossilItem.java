package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.block.Block;

public class GejioFossilItem extends FossilBlockItem {

    public GejioFossilItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Gejio.copy(false);
    }
}
