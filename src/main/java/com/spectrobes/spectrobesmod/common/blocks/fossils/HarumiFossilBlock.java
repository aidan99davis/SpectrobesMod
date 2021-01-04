package com.spectrobes.spectrobesmod.common.blocks.fossils;

import com.spectrobes.spectrobesmod.common.registry.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesItemsRegistry;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class HarumiFossilBlock extends DirectionalBlock {
    private static Properties props = Properties.create(Material.WOOD).notSolid()
            .harvestTool(ToolType.PICKAXE)
            .hardnessAndResistance(0f)
            .sound(SoundType.STONE)
            .harvestLevel(0);

    public HarumiFossilBlock() {
        super(props);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        ItemStack stack = new ItemStack(SpectrobesItemsRegistry.harumi_fossil_item.get());
        List<ItemStack> stackList = new ArrayList<>();
        stackList.add(stack);

        return stackList;
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return SpectrobesTileRegistry.HARUMI_FOSSIL_TILE.get().create();
    }

    @Override
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.getDefaultState().with(FACING, context.getNearestLookingDirection().getOpposite());
    }
}
