package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.client.items.renderer.HarumiFossilItemRenderer;
import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.util.NonNullLazy;

import java.util.function.Consumer;

public class HarumiFossilItem extends FossilBlockItem {

    public HarumiFossilItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions()
        {
            private final NonNullLazy<BlockEntityWithoutLevelRenderer> ister = NonNullLazy.of(() -> new HarumiFossilItemRenderer());

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return ister.get();
            }
        });
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Harumi.copy(false);
    }
}
