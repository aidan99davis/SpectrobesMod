package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.block.Block;

public class NaguFossilItem extends FossilBlockItem {

    public NaguFossilItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Nagu.copy(false);
    }
}
