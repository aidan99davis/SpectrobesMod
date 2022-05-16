package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.block.Block;

import net.minecraft.item.Item.Properties;

public class KubakuFossilItem extends FossilBlockItem {

    public KubakuFossilItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Kubaku.copy(false);
    }
}
