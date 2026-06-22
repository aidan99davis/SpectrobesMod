package com.spectrobes.spectrobesmod.common.items.machines;

import com.spectrobes.spectrobesmod.client.items.machines.renderer.HealerBlockItemRenderer;
import com.spectrobes.spectrobesmod.common.items.AnimatableBlockItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class HealerBlockItem extends AnimatableBlockItem {

    public HealerBlockItem(Block block, Properties properties) {
        super(block, properties);
    }
}
