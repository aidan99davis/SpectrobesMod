package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.client.items.renderer.AoiFossilItemRenderer;
import com.spectrobes.spectrobesmod.common.registry.data.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;

import java.util.function.Consumer;

public class AoiFossilItem extends FossilBlockItem {

    public AoiFossilItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private AoiFossilItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null) {
                    this.renderer = new AoiFossilItemRenderer();
                }

                return this.renderer;
            }
        });
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Aoi.copy(false);
    }
}
