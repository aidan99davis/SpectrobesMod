package com.spectrobes.spectrobesmod.common.blocks;

import com.spectrobes.spectrobesmod.client.container.HealerContainer;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class HealerBlock extends SpectrobesBlock {
    private static Properties props = Properties.of(Material.WOOD).noOcclusion()
            .harvestTool(ToolType.PICKAXE)
            .strength(0f)
            .sound(SoundType.STONE)
            .harvestLevel(0);
    public HealerBlock() {
        super(props);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (context.getEntity() instanceof PlayerEntity == false) {
            System.out.print("not player!\n");
            return VoxelShapes.block();
        } else {
            System.out.print("player!\n");
            return VoxelShapes.empty();
        }
    }

    @Override
    public ActionResultType use(BlockState pState, World pLevel, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockRayTraceResult pHit) {
        if (pLevel.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            NetworkHooks.openGui((ServerPlayerEntity) pPlayer, new SimpleNamedContainerProvider(
                            (id, player, stack) -> new HealerContainer(id, pPlayer),
                            new StringTextComponent(""))
//                        buf -> buf.writeItemStack(stack)
            );
            pPlayer.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return ActionResultType.CONSUME;
        }
    }

    public INamedContainerProvider getMenuProvider(BlockState pState, World pLevel, BlockPos pPos) {
        return new SimpleNamedContainerProvider((containerId, playerInventory, playerEntity) -> {
            return new HealerContainer(containerId, playerEntity);
        }, new StringTextComponent("Healer"));
    }
}
