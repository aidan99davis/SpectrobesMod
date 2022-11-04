package com.spectrobes.spectrobesmod.common.blocks.fossils;

import com.spectrobes.spectrobesmod.common.blocks.DirectionalBlock;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesItemsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.ArrayList;
import java.util.List;

public class KubakuFossilBlock extends DirectionalBlock {

    public KubakuFossilBlock() {
        super();
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        ItemStack stack = new ItemStack(SpectrobesItemsRegistry.kubaku_fossil_item.get());
        List<ItemStack> stackList = new ArrayList<>();
        stackList.add(stack);

        return stackList;
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return SpectrobesTileRegistry.KUBAKU_FOSSIL_TILE.get().create(pPos, pState);
    }
}
