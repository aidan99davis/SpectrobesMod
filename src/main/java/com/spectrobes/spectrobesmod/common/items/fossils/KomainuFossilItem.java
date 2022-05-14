package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.block.Block;

import net.minecraft.item.Item.Properties;

public class KomainuFossilItem extends FossilBlockItem {

    public KomainuFossilItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Komainu.copy(false);
    }
}
