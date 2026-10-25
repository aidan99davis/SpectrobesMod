package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.client.items.renderer.KomainuFossilItemRenderer;
import com.spectrobes.spectrobesmod.common.registry.data.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;

import java.util.function.Consumer;

public class KomainuFossilItem extends FossilBlockItem {

    public KomainuFossilItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private KomainuFossilItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null) {
                    this.renderer = new KomainuFossilItemRenderer();
                }

                return this.renderer;
            }
        });
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Komainu.copy(false);
    }
}
