package com.spectrobes.spectrobesmod.common.items.machines;

import com.spectrobes.spectrobesmod.client.items.machines.renderer.CyrusShopBlockItemRenderer;
import com.spectrobes.spectrobesmod.common.items.AnimatableBlockItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.util.NonNullLazy;

import java.util.function.Consumer;

public class CyrusShopBlockItem extends AnimatableBlockItem {

    public CyrusShopBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions()
        {
            private final NonNullLazy<BlockEntityWithoutLevelRenderer> ister = NonNullLazy.of(CyrusShopBlockItemRenderer::new);

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return ister.get();
            }
        });
    }
}
