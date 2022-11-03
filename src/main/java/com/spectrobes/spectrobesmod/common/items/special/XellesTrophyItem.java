package com.spectrobes.spectrobesmod.common.items.special;

import com.spectrobes.spectrobesmod.client.items.renderer.XellesTrophyItemRenderer;
import com.spectrobes.spectrobesmod.common.items.AnimatableBlockItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.util.NonNullLazy;

import java.util.function.Consumer;

public class XellesTrophyItem extends AnimatableBlockItem {

    public XellesTrophyItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions()
        {
            private final NonNullLazy<BlockEntityWithoutLevelRenderer> ister = NonNullLazy.of(() -> new XellesTrophyItemRenderer());

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return ister.get();
            }
        });
    }

}
