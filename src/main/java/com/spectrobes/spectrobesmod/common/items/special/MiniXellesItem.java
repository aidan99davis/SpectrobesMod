package com.spectrobes.spectrobesmod.common.items.special;

import com.spectrobes.spectrobesmod.client.items.renderer.MiniXellesItemRenderer;
import com.spectrobes.spectrobesmod.common.items.AnimatableBlockItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;

import java.util.function.Consumer;

public class MiniXellesItem extends AnimatableBlockItem {

    public MiniXellesItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private MiniXellesItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null) {
                    this.renderer = new MiniXellesItemRenderer();
                }

                return this.renderer;
            }
        });
    }
}
