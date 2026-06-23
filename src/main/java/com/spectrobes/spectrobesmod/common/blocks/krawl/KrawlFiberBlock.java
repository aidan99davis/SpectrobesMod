package com.spectrobes.spectrobesmod.common.blocks.krawl;

import com.mojang.serialization.MapCodec;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesBlockItemsRegistry;
import com.spectrobes.spectrobesmod.common.world.krawl.KrawlBehaviour;
import com.spectrobes.spectrobesmod.common.world.krawl.KrawlSpreader;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Collection;

public class KrawlFiberBlock extends MultifaceBlock implements KrawlBehaviour {

    public static final MapCodec<KrawlFiberBlock> CODEC = BlockBehaviour.simpleCodec(KrawlFiberBlock::new);

    private final MultifaceSpreader veinSpreader = new MultifaceSpreader(new KrawlFiberBlock.SculkVeinSpreaderConfig(MultifaceSpreader.DEFAULT_SPREAD_ORDER));
    private final MultifaceSpreader sameSpaceSpreader = new MultifaceSpreader(new KrawlFiberBlock.SculkVeinSpreaderConfig(MultifaceSpreader.SpreadType.SAME_POSITION));

    public KrawlFiberBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected MapCodec<? extends KrawlFiberBlock> codec() {
        return CODEC;
    }

    @Override
    public MultifaceSpreader getSpreader() {
        return this.veinSpreader;
    }

    public MultifaceSpreader getSameSpaceSpreader() {
        return this.sameSpaceSpreader;
    }

    public static boolean regrow(LevelAccessor pLevel, BlockPos pPos, BlockState pState, Collection<Direction> pDirections) {
        boolean flag = false;
        BlockState blockstate = SpectrobesBlocks.krawl_fiber.get().defaultBlockState();

        for (Direction direction : pDirections) {
            BlockPos blockpos = pPos.relative(direction);
            if (canAttachTo(pLevel, direction, blockpos, pLevel.getBlockState(blockpos))) {
                blockstate = blockstate.setValue(getFaceProperty(direction), Boolean.TRUE);
                flag = true;
            }
        }

        if (!flag) {
            return false;
        } else {
            pLevel.setBlock(pPos, blockstate, 3);
            return true;
        }
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return true;
    }

    @Override
    public void onDischarged(LevelAccessor pLevel, BlockState pState, BlockPos pPos, RandomSource pRandom) {
        if (pState.is(this)) {
            for (Direction direction : DIRECTIONS) {
                BooleanProperty booleanproperty = getFaceProperty(direction);
                if (pState.getValue(booleanproperty) && pLevel.getBlockState(pPos.relative(direction)).getBlock() instanceof SpreadingKrawlNestBlock) {
                    pState = pState.setValue(booleanproperty, Boolean.FALSE);
                }
            }

            pLevel.setBlock(pPos, pState, 3);
            KrawlBehaviour.super.onDischarged(pLevel, pState, pPos, pRandom);
        }
    }

    @Override
    public int attemptUseCharge(KrawlSpreader.ChargeCursor pCursor, LevelAccessor pLevel, BlockPos pPos, RandomSource pRandom, KrawlSpreader pSpreader, boolean pCanPlaceSculk) {
        if (pCanPlaceSculk && this.attemptPlaceSculk(pSpreader, pLevel, pCursor.getPos(), pRandom)) {
            return pCursor.getCharge() - 1;
        } else {
            return pRandom.nextInt(pSpreader.chargeDecayRate()) == 0 ? Mth.floor((float) pCursor.getCharge() * 0.5F) : pCursor.getCharge();
        }
    }

    private boolean attemptPlaceSculk(KrawlSpreader pSpreader, LevelAccessor pLevel, BlockPos pPos, RandomSource pRandom) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        TagKey<Block> tagkey = pSpreader.replaceableBlocks();

        for (Direction direction : Direction.allShuffled(pRandom)) {
            if (hasFace(blockstate, direction)) {
                BlockPos blockpos = pPos.relative(direction);
                BlockPos blockposupper = blockpos.above();
                BlockState blockstate1 = pLevel.getBlockState(blockpos);

                if (blockstate1.is(tagkey)) {
                    BlockState blockstate2;

                    if (blockstate1.is(BlockTags.STONE_ORE_REPLACEABLES)) {
                        blockstate2 = SpectrobesBlocks.krawl_stone.get().defaultBlockState();
                    } else if (blockstate1.is(BlockTags.DIRT)) {
                        blockstate2 = SpectrobesBlocks.krawl_mud.get().defaultBlockState();
                    } else if (blockstate1.is(BlockTags.LOGS)) {
                        blockstate2 = SpectrobesBlocks.krawl_mycelium.get().defaultBlockState();
                    } else if (blockstate1.is(BlockTags.CAVE_VINES)) {
                        blockstate2 = SpectrobesBlocks.krawl_vine.get().defaultBlockState();
                    } else {
                        blockstate2 = SpectrobesBlocks.krawl_nest.get().defaultBlockState();
                    }

                    pLevel.setBlock(blockpos, blockstate2, 3);
                    Block.pushEntitiesUp(blockstate1, blockstate2, pLevel, blockpos);
                    pLevel.playSound(null, blockpos, SoundEvents.SCULK_BLOCK_SPREAD, SoundSource.BLOCKS, 1.0F, 1.0F);
                    this.veinSpreader.spreadAll(blockstate2, pLevel, blockpos, pSpreader.isWorldGeneration());

                    Direction direction1 = direction.getOpposite();

                    for (Direction direction2 : DIRECTIONS) {
                        if (direction2 != direction1) {
                            BlockPos blockpos1 = blockpos.relative(direction2);
                            BlockState blockstate3 = pLevel.getBlockState(blockpos1);
                            if (blockstate3.is(this)) {
                                this.onDischarged(pLevel, blockstate3, blockpos1, pRandom);
                            }
                        }
                    }

                    return true;
                } else if (pLevel.getBlockState(blockposupper).is(SpectrobesBlocks.krawl_fiber.get())
                        && !pLevel.getBlockStates(AABB.ofSize(
                        new Vec3(blockposupper.getX(), blockposupper.getY(), blockposupper.getZ()),
                        7, 7, 7)).anyMatch(blockState -> blockState.is(SpectrobesBlocks.mini_xelles_block.get()))) {
                    pLevel.setBlock(blockposupper, SpectrobesBlocks.mini_xelles_block.get().defaultBlockState(), 3);
                }
            }
        }

        return false;
    }

    public static boolean hasSubstrateAccess(LevelAccessor pLevel, BlockState pState, BlockPos pPos) {
        if (!pState.is(SpectrobesBlocks.krawl_fiber.get())) {
            return false;
        } else {
            for (Direction direction : DIRECTIONS) {
                if (hasFace(pState, direction) && pLevel.getBlockState(pPos.relative(direction)).is(BlockTags.SCULK_REPLACEABLE)) {
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    protected boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return !pUseContext.getItemInHand().is(SpectrobesBlockItemsRegistry.krawl_fiber.get()) || super.canBeReplaced(pState, pUseContext);
    }

    @Override
    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.DESTROY;
    }

    private class SculkVeinSpreaderConfig extends MultifaceSpreader.DefaultSpreaderConfig {
        private final MultifaceSpreader.SpreadType[] spreadTypes;

        public SculkVeinSpreaderConfig(MultifaceSpreader.SpreadType... pSpreadTypes) {
            super(KrawlFiberBlock.this);
            this.spreadTypes = pSpreadTypes;
        }

        @Override
        protected boolean stateCanBeReplaced(BlockGetter pLevel, BlockPos pSourcePos, BlockPos pTargetPos, Direction pDirection, BlockState pTargetState) {
            BlockState adjacentState = pLevel.getBlockState(pTargetPos.relative(pDirection));

            if (adjacentState.is(SpectrobesBlocks.krawl_fiber.get())
                    || adjacentState.is(SpectrobesBlocks.mini_xelles_block.get())
                    || adjacentState.is(SpectrobesBlocks.krawl_mud.get())
                    || adjacentState.is(SpectrobesBlocks.krawl_mycelium.get())
                    || adjacentState.is(SpectrobesBlocks.krawl_nest.get())
                    || adjacentState.is(SpectrobesBlocks.krawl_stone.get())
                    || adjacentState.is(SpectrobesBlocks.krawl_vine.get())
                    || adjacentState.is(Blocks.MOVING_PISTON)) {
                return false;
            }

            if (pSourcePos.distManhattan(pTargetPos) == 2) {
                BlockPos blockpos = pSourcePos.relative(pDirection.getOpposite());
                if (pLevel.getBlockState(blockpos).isFaceSturdy(pLevel, blockpos, pDirection)) {
                    return false;
                }
            }

            FluidState fluidstate = pTargetState.getFluidState();
            if (!fluidstate.isEmpty() && !fluidstate.is(Fluids.WATER)) {
                return true;
            }

            if (pTargetState.getBlock() instanceof BaseFireBlock) {
                return false;
            }

            return pTargetState.canBeReplaced() || super.stateCanBeReplaced(pLevel, pSourcePos, pTargetPos, pDirection, pTargetState);
        }

        @Override
        public MultifaceSpreader.SpreadType[] getSpreadTypes() {
            return this.spreadTypes;
        }

        @Override
        public boolean isOtherBlockValidAsSource(BlockState pOtherBlock) {
            return !pOtherBlock.is(SpectrobesBlocks.krawl_fiber.get());
        }
    }
}