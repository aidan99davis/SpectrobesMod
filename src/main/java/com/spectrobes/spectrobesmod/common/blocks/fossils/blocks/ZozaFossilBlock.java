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
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ZozaFossilBlock extends DirectionalBlock {

    public static final MapCodec<ZozaFossilBlock> CODEC = MapCodec.unit(ZozaFossilBlock::new);

    public ZozaFossilBlock() {
        super();
    }

    @Override
    protected MapCodec<? extends ZozaFossilBlock> codec() {
        return CODEC;
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return List.of(new ItemStack(SpectrobesFossilsRegistry.zoza_fossil_item.get()));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return SpectrobesTileRegistry.ZOZA_FOSSIL_TILE.get().create(pPos, pState);
    }
}