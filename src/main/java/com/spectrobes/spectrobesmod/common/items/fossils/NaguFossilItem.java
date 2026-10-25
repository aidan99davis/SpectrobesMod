package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.client.items.renderer.NaguFossilItemRenderer;
import com.spectrobes.spectrobesmod.common.registry.data.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;

import java.util.function.Consumer;

public class NaguFossilItem extends FossilBlockItem {

    public NaguFossilItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private NaguFossilItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null) {
                    this.renderer = new NaguFossilItemRenderer();
                }

                return this.renderer;
            }
        });
    }

    @Override
    public Spectrobe getSpectrobeInstance() {
        return SpectrobeRegistry.Nagu.copy(false);
    }
}
