package com.spectrobes.spectrobesmod.common.blocks.fossils.blocks;

import com.spectrobes.spectrobesmod.common.blocks.DirectionalBlock;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesFossilsRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesItemsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class KasumiFossilBlock extends DirectionalBlock {

    public KasumiFossilBlock() {
        super();
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        ItemStack stack = new ItemStack(SpectrobesFossilsRegistry.kasumi_fossil_item.get());
        List<ItemStack> stackList = new ArrayList<>();
        stackList.add(stack);

        return stackList;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return SpectrobesTileRegistry.KASUMI_FOSSIL_TILE.get().create(pPos, pState);
    }
}
