package com.spectrobes.spectrobesmod.common.items.machines;

import com.spectrobes.spectrobesmod.client.items.machines.renderer.HealerBlockItemRenderer;
import com.spectrobes.spectrobesmod.common.items.AnimatableBlockItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;

import java.util.function.Consumer;

public class HealerBlockItem extends AnimatableBlockItem {

    public HealerBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private HealerBlockItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null) {
                    this.renderer = new HealerBlockItemRenderer();
                }

                return this.renderer;
            }
        });
    }
}
