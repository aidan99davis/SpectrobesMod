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

public class MossariFossilBlock extends DirectionalBlock {

    public static final MapCodec<MossariFossilBlock> CODEC = MapCodec.unit(MossariFossilBlock::new);

    public MossariFossilBlock() {
        super();
    }

    @Override
    protected MapCodec<? extends MossariFossilBlock> codec() {
        return CODEC;
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return List.of(new ItemStack(SpectrobesFossilsRegistry.mossari_fossil_item.get()));
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return SpectrobesTileRegistry.MOSSARI_FOSSIL_TILE.get().create(pPos, pState);
    }
}