package com.spectrobes.spectrobesmod.common.blocks.fossils;

import com.spectrobes.spectrobesmod.common.blocks.DirectionalBlock;
import com.spectrobes.spectrobesmod.common.blocks.tile.AoiFossilBlockTileEntity;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesItemsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.ArrayList;
import java.util.List;

public class AoiFossilBlock extends DirectionalBlock {

    public AoiFossilBlock() {
        super();
    }

    @Override
    public List<ItemStack> getDrops(BlockState pState, LootContext.Builder pBuilder) {
        ItemStack stack = new ItemStack(SpectrobesItemsRegistry.aoi_fossil_item.get());
        List<ItemStack> stackList = new ArrayList<>();
        stackList.add(stack);

        return stackList;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AoiFossilBlockTileEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState)
    {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

}
