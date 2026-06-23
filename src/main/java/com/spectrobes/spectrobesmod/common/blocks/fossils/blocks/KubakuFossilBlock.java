package com.spectrobes.spectrobesmod.common.blocks.fossils.blocks;

import com.mojang.serialization.MapCodec;
import com.spectrobes.spectrobesmod.common.blocks.DirectionalBlock;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesFossilsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class KubakuFossilBlock extends DirectionalBlock {

    public static final MapCodec<KubakuFossilBlock> CODEC = MapCodec.unit(KubakuFossilBlock::new);

    public KubakuFossilBlock() {
        super();
    }

    @Override
    protected MapCodec<? extends KubakuFossilBlock> codec() {
        return CODEC;
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return List.of(new ItemStack(SpectrobesFossilsRegistry.kubaku_fossil_item.get()));
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return SpectrobesTileRegistry.KUBAKU_FOSSIL_TILE.get().create(pPos, pState);
    }
}