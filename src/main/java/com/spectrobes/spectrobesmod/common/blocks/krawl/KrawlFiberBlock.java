package com.spectrobes.spectrobesmod.common.blocks.krawl;

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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Collection;

public class KrawlFiberBlock extends MultifaceBlock implements KrawlBehaviour {
    private final MultifaceSpreader veinSpreader = new MultifaceSpreader(new KrawlFiberBlock.SculkVeinSpreaderConfig(MultifaceSpreader.DEFAULT_SPREAD_ORDER));
    private final MultifaceSpreader sameSpaceSpreader = new MultifaceSpreader(new KrawlFiberBlock.SculkVeinSpreaderConfig(MultifaceSpreader.SpreadType.SAME_POSITION));

    public KrawlFiberBlock(Properties pProperties) {
        super(pProperties);
    }

    public MultifaceSpreader getSpreader() {
        return this.veinSpreader;
    }

    public MultifaceSpreader getSameSpaceSpreader() {
        return this.sameSpaceSpreader;
    }

    public static boolean regrow(LevelAccessor pLevel, BlockPos pPos, BlockState pState, Collection<Direction> pDirections) {
        boolean flag = false;
        BlockState blockstate = SpectrobesBlocks.krawl_fiber.get().defaultBlockState();

        for(Direction direction : pDirections) {
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

    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return true;
    }

    public void onDischarged(LevelAccessor pLevel, BlockState pState, BlockPos pPos, RandomSource pRandom) {
        if (pState.is(this)) {
            for(Direction direction : DIRECTIONS) {
                BooleanProperty booleanproperty = getFaceProperty(direction);
                if (pState.getValue(booleanproperty) && pLevel.getBlockState(pPos.relative(direction)).getBlock() instanceof SpreadingKrawlNestBlock) {
                    pState = pState.setValue(booleanproperty, Boolean.valueOf(false));
                }
            }

            pLevel.setBlock(pPos, pState, 3);
            KrawlBehaviour.super.onDischarged(pLevel, pState, pPos, pRandom);
        }
    }

    public int attemptUseCharge(KrawlSpreader.ChargeCursor pCursor, LevelAccessor pLevel, BlockPos pPos, RandomSource pRandom, KrawlSpreader pSpreader, boolean p_222374_) {
        if (p_222374_ && this.attemptPlaceSculk(pSpreader, pLevel, pCursor.getPos(), pRandom)) {
            return pCursor.getCharge() - 1;
        } else {
            return pRandom.nextInt(pSpreader.chargeDecayRate()) == 0 ? Mth.floor((float)pCursor.getCharge() * 0.5F) : pCursor.getCharge();
        }
    }

    private boolean attemptPlaceSculk(KrawlSpreader pSpreader, LevelAccessor pLevel, BlockPos pPos, RandomSource pRandom) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        TagKey<Block> tagkey = pSpreader.replaceableBlocks();

        for(Direction direction : Direction.allShuffled(pRandom)) {
            if (hasFace(blockstate, direction)) {
                BlockPos blockpos = pPos.relative(direction);
                BlockPos blockposupper = pPos.relative(direction).above(1);
                BlockState blockstate1 = pLevel.getBlockState(blockpos);
                BlockState blockstate1upper = pLevel.getBlockState(blockposupper);
                if (blockstate1.is(tagkey)) {
                    //decide what krawl nest material to make
                    BlockState blockstate2;
                    if(blockstate1.is(BlockTags.STONE_ORE_REPLACEABLES)) {
                        blockstate2 = SpectrobesBlocks.krawl_stone.get().defaultBlockState();
                    } else if(blockstate1.is(BlockTags.DIRT)) {
                        blockstate2 = SpectrobesBlocks.krawl_mud.get().defaultBlockState();
                    } else if(blockstate1.is(BlockTags.LOGS)) {
                        blockstate2 = SpectrobesBlocks.krawl_mycelium.get().defaultBlockState();
                    } else if(blockstate1.is(BlockTags.CAVE_VINES)) {
                        blockstate2 = SpectrobesBlocks.krawl_vine.get().defaultBlockState();
                    } /*else if(blockstate1.getFluidState().is(FluidTags.WATER)) {
                    TODO: Add krawl goo liquid
//                        blockstate2 = SpectrobesFluids.krawl_goo.get().defaultBlockState();
                    }*/ else {
                        blockstate2 = SpectrobesBlocks.krawl_nest.get().defaultBlockState();
                    }


                    pLevel.setBlock(blockpos, blockstate2, 3);
                    Block.pushEntitiesUp(blockstate1, blockstate2, pLevel, blockpos);
                    pLevel.playSound((Player)null, blockpos, SoundEvents.SCULK_BLOCK_SPREAD, SoundSource.BLOCKS, 1.0F, 1.0F);
                    this.veinSpreader.spreadAll(blockstate2, pLevel, blockpos, pSpreader.isWorldGeneration());
                    Direction direction1 = direction.getOpposite();

                    for(Direction direction2 : DIRECTIONS) {
                        if (direction2 != direction1) {
                            BlockPos blockpos1 = blockpos.relative(direction2);
                            BlockState blockstate3 = pLevel.getBlockState(blockpos1);
                            if (blockstate3.is(this)) {
                                this.onDischarged(pLevel, blockstate3, blockpos1, pRandom);
                            }
                        }
                    }

                    return true;
                } else if(blockstate1upper.is(SpectrobesBlocks.krawl_fiber.get())
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
            for(Direction direction : DIRECTIONS) {
                if (hasFace(pState, direction) && pLevel.getBlockState(pPos.relative(direction)).is(BlockTags.SCULK_REPLACEABLE)) {
                    return true;
                }
            }

            return false;
        }
    }

    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return !pUseContext.getItemInHand().is(SpectrobesBlockItemsRegistry.krawl_fiber.get()) || super.canBeReplaced(pState, pUseContext);
    }

    /**
     * @deprecated call via {@link
     * net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#getPistonPushReaction} whenever possible.
     * Implementing/overriding is fine.
     */
    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.DESTROY;
    }

    class SculkVeinSpreaderConfig extends MultifaceSpreader.DefaultSpreaderConfig {
        private final MultifaceSpreader.SpreadType[] spreadTypes;

        public SculkVeinSpreaderConfig(MultifaceSpreader.SpreadType... pSpreadTypes) {
            super(KrawlFiberBlock.this);
            this.spreadTypes = pSpreadTypes;
        }

        public boolean stateCanBeReplaced(BlockGetter pLevel, BlockPos p_222406_, BlockPos p_222407_, Direction p_222408_, BlockState p_222409_) {
            BlockState blockstate = pLevel.getBlockState(p_222407_.relative(p_222408_));
            if (!blockstate.is(SpectrobesBlocks.krawl_fiber.get())
                    && !blockstate.is(SpectrobesBlocks.mini_xelles_block.get())
                    && !blockstate.is(SpectrobesBlocks.krawl_mud.get())
                    && !blockstate.is(SpectrobesBlocks.krawl_mycelium.get())
                    && !blockstate.is(SpectrobesBlocks.krawl_nest.get())
                    && !blockstate.is(SpectrobesBlocks.krawl_stone.get())
                    && !blockstate.is(SpectrobesBlocks.krawl_vine.get())
                    && !blockstate.is(Blocks.MOVING_PISTON)) {
                if (p_222406_.distManhattan(p_222407_) == 2) {
                    BlockPos blockpos = p_222406_.relative(p_222408_.getOpposite());
                    if (pLevel.getBlockState(blockpos).isFaceSturdy(pLevel, blockpos, p_222408_)) {
                        return false;
                    }
                }

                FluidState fluidstate = p_222409_.getFluidState();
                if (!fluidstate.isEmpty() && !fluidstate.is(Fluids.WATER)) {
                    return true;
                } else {
                    Material material = p_222409_.getMaterial();
                    if (material == Material.FIRE) {
                        return false;
                    } else {
                        return material.isReplaceable() || super.stateCanBeReplaced(pLevel, p_222406_, p_222407_, p_222408_, p_222409_);
                    }
                }
            } else {
                return false;
            }
        }

        public MultifaceSpreader.SpreadType[] getSpreadTypes() {
            return this.spreadTypes;
        }

        public boolean isOtherBlockValidAsSource(BlockState pOtherBlock) {
            return !pOtherBlock.is(SpectrobesBlocks.krawl_fiber.get());
        }
    }
}
