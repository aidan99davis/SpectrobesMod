package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.block.Block;

public class DanawaFossilItem extends FossilBlockItem {

    public DanawaFossilItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Danawa.copy(false);
    }
}
