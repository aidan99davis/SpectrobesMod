package com.spectrobes.spectrobesmod.common.blocks.fossils.blocks;

import com.mojang.serialization.MapCodec;
import com.spectrobes.spectrobesmod.common.blocks.api.DirectionalBlock;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesFossilsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BartorFossilBlock extends DirectionalBlock {

    public static final MapCodec<BartorFossilBlock> CODEC = MapCodec.unit(BartorFossilBlock::new);

    public BartorFossilBlock() {
        super();
    }

    @Override
    protected MapCodec<? extends BartorFossilBlock> codec() {
        return CODEC;
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return List.of(new ItemStack(SpectrobesFossilsRegistry.bartor_fossil_item.get()));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return SpectrobesTileRegistry.BARTOR_FOSSIL_TILE.get().create(pPos, pState);
    }
}