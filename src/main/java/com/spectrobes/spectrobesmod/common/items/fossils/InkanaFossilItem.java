package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.client.items.renderer.InkanaFossilItemRenderer;
import com.spectrobes.spectrobesmod.common.registry.data.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;

import java.util.function.Consumer;

public class InkanaFossilItem extends FossilBlockItem {

    public InkanaFossilItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private InkanaFossilItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null) {
                    this.renderer = new InkanaFossilItemRenderer();
                }

                return this.renderer;
            }
        });
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Inkana.copy(false);
    }
}
