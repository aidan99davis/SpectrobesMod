package com.spectrobes.spectrobesmod.common.blocks.krawl;

import com.spectrobes.spectrobesmod.common.blocks.SpectrobesTileEntityBlock;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMaps;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

import static com.spectrobes.spectrobesmod.common.blocks.krawl.MiniXellesBlockEntity.tryGetPlayer;

public class MiniXellesBlock extends SpectrobesTileEntityBlock {
    private static final Properties props = Properties.of(Material.STONE).noOcclusion()
            .strength(0f)
            .sound(SoundType.ROOTS);
    public static final BooleanProperty CAN_SUMMON = BlockStateProperties.CAN_SUMMON;
    public static final BooleanProperty SUMMONING = BlockStateProperties.SHRIEKING;
    public static final int PULSE_TICKS = 8;
    public static final BooleanProperty PULSE = BlockStateProperties.BLOOM;
    public static final int ACTIVE_TICKS = 40;
    public static final int COOLDOWN_TICKS = 1;
    public static final EnumProperty<SculkSensorPhase> PHASE = BlockStateProperties.SCULK_SENSOR_PHASE;
    public static final IntegerProperty POWER = BlockStateProperties.POWER;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    private final int listenerRange;
    public static final Object2IntMap<GameEvent> VIBRATION_FREQUENCY_FOR_EVENT = Object2IntMaps.unmodifiable(Util.make(new Object2IntOpenHashMap<>(), (p_238254_) -> {
        p_238254_.put(GameEvent.STEP, 1);
        p_238254_.put(GameEvent.FLAP, 2);
        p_238254_.put(GameEvent.SWIM, 3);
        p_238254_.put(GameEvent.ELYTRA_GLIDE, 4);
        p_238254_.put(GameEvent.HIT_GROUND, 5);
        p_238254_.put(GameEvent.TELEPORT, 5);
        p_238254_.put(GameEvent.SPLASH, 6);
        p_238254_.put(GameEvent.ENTITY_SHAKE, 6);
        p_238254_.put(GameEvent.BLOCK_CHANGE, 6);
        p_238254_.put(GameEvent.NOTE_BLOCK_PLAY, 6);
        p_238254_.put(GameEvent.PROJECTILE_SHOOT, 7);
        p_238254_.put(GameEvent.DRINK, 7);
        p_238254_.put(GameEvent.PRIME_FUSE, 7);
        p_238254_.put(GameEvent.PROJECTILE_LAND, 8);
        p_238254_.put(GameEvent.EAT, 8);
        p_238254_.put(GameEvent.ENTITY_INTERACT, 8);
        p_238254_.put(GameEvent.ENTITY_DAMAGE, 8);
        p_238254_.put(GameEvent.EQUIP, 9);
        p_238254_.put(GameEvent.SHEAR, 9);
        p_238254_.put(GameEvent.ENTITY_ROAR, 9);
        p_238254_.put(GameEvent.BLOCK_CLOSE, 10);
        p_238254_.put(GameEvent.BLOCK_DEACTIVATE, 10);
        p_238254_.put(GameEvent.BLOCK_DETACH, 10);
        p_238254_.put(GameEvent.DISPENSE_FAIL, 10);
        p_238254_.put(GameEvent.BLOCK_OPEN, 11);
        p_238254_.put(GameEvent.BLOCK_ACTIVATE, 11);
        p_238254_.put(GameEvent.BLOCK_ATTACH, 11);
        p_238254_.put(GameEvent.ENTITY_PLACE, 12);
        p_238254_.put(GameEvent.BLOCK_PLACE, 12);
        p_238254_.put(GameEvent.FLUID_PLACE, 12);
        p_238254_.put(GameEvent.ENTITY_DIE, 13);
        p_238254_.put(GameEvent.BLOCK_DESTROY, 13);
        p_238254_.put(GameEvent.FLUID_PICKUP, 13);
        p_238254_.put(GameEvent.ITEM_INTERACT_FINISH, 14);
        p_238254_.put(GameEvent.CONTAINER_CLOSE, 14);
        p_238254_.put(GameEvent.PISTON_CONTRACT, 14);
        p_238254_.put(GameEvent.PISTON_EXTEND, 15);
        p_238254_.put(GameEvent.CONTAINER_OPEN, 15);
        p_238254_.put(GameEvent.ITEM_INTERACT_START, 15);
        p_238254_.put(GameEvent.EXPLODE, 15);
        p_238254_.put(GameEvent.LIGHTNING_STRIKE, 15);
        p_238254_.put(GameEvent.INSTRUMENT_PLAY, 15);
    }));

    public MiniXellesBlock() {
        this(props, 5);
    }

    public MiniXellesBlock(Properties pProperties, int pListenerRange) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(PHASE, SculkSensorPhase.INACTIVE)
                .setValue(POWER, Integer.valueOf(0))
                .setValue(PULSE, Boolean.valueOf(false))
                .setValue(SUMMONING, false)
                .setValue(CAN_SUMMON, false));
        this.listenerRange = pListenerRange;
    }

    public int getListenerRange() {
        return this.listenerRange;
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(SUMMONING)) {
            pLevel.setBlock(pPos, pState.setValue(SUMMONING, Boolean.valueOf(false)), 3);
            pLevel.getBlockEntity(pPos, SpectrobesTileRegistry.MINI_XELLES_TILE.get()).ifPresent((xelles) -> {
                xelles.tryRespond(pLevel);
            });
        }
        if (pState.getValue(PULSE)) {
            pLevel.setBlock(pPos, pState.setValue(PULSE, Boolean.valueOf(false)), 3);
        }
        if (getPhase(pState) != SculkSensorPhase.ACTIVE) {
            if (getPhase(pState) == SculkSensorPhase.COOLDOWN) {
                pLevel.setBlock(pPos, pState.setValue(PHASE, SculkSensorPhase.INACTIVE), 3);
            }

        } else {
            deactivate(pLevel, pPos, pState);
        }
        super.tick(pState, pLevel, pPos, pRandom);
    }

    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pLevel.isClientSide() && canActivate(pState) && !(pEntity instanceof EntityKrawl)) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            ServerPlayer serverplayer = tryGetPlayer(pEntity);
            if (serverplayer != null) {
                pLevel.getBlockEntity(pPos, SpectrobesTileRegistry.MINI_XELLES_TILE.get()).ifPresent((p_222163_) -> {
                    p_222163_.trySummon((ServerLevel) pLevel, serverplayer);
                });
            }
            if (blockentity instanceof MiniXellesBlockEntity) {
                MiniXellesBlockEntity sculksensorblockentity = (MiniXellesBlockEntity)blockentity;
                sculksensorblockentity.setLastVibrationFrequency(VIBRATION_FREQUENCY_FOR_EVENT.get(GameEvent.STEP));
            }

            activate(pEntity, pLevel, pPos, pState, 15);
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pLevel.isClientSide() && !pState.is(pOldState.getBlock())) {
            if (pState.getValue(POWER) > 0 && !pLevel.getBlockTicks().hasScheduledTick(pPos, this)) {
                pLevel.setBlock(pPos, pState.setValue(POWER, Integer.valueOf(0)), 18);
            }

            pLevel.scheduleTick(new BlockPos(pPos), pState.getBlock(), 1);
        }
    }

    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pLevel instanceof ServerLevel serverlevel) {
            if (pState.getValue(SUMMONING) && !pState.is(pNewState.getBlock())) {
                serverlevel.getBlockEntity(pPos, SpectrobesTileRegistry.MINI_XELLES_TILE.get()).ifPresent((xelles) -> {
                    xelles.tryRespond(serverlevel);
                });
            }
        }

        if (!pState.is(pNewState.getBlock())) {
            if (getPhase(pState) == SculkSensorPhase.ACTIVE) {
                updateNeighbours(pLevel, pPos);
            }

            super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
        }
    }

    private static void updateNeighbours(Level pLevel, BlockPos pPos) {
        pLevel.updateNeighborsAt(pPos, SpectrobesBlocks.mini_xelles_block.get());
        pLevel.updateNeighborsAt(pPos.relative(Direction.UP.getOpposite()), SpectrobesBlocks.mini_xelles_block.get());
    }

    @Nullable
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new MiniXellesBlockEntity(pPos, pState);
    }

    @Nullable
    public <T extends BlockEntity> GameEventListener getListener(ServerLevel pLevel, T pBlockEntity) {
        return pBlockEntity instanceof MiniXellesBlockEntity ? ((MiniXellesBlockEntity)pBlockEntity) : null;
//        return pBlockEntity instanceof SculkCatalystBlockEntity ? (SculkCatalystBlockEntity)pBlockEntity : null;
//        return pBlockEntity instanceof SculkSensorBlockEntity ? ((SculkSensorBlockEntity)pBlockEntity).getListener() : null;
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? createTickerHelper(pBlockEntityType, SpectrobesTileRegistry.MINI_XELLES_TILE.get(), (p_154417_, p_154418_, p_154419_, p_154420_) -> {
            p_154420_.tick(p_154417_);
        }) : createTickerHelper(pBlockEntityType, SpectrobesTileRegistry.MINI_XELLES_TILE.get(), MiniXellesBlockEntity::serverTick);
    }

    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     * @deprecated call via {@link net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#getRenderShape}
     * whenever possible. Implementing/overriding is fine.
     */
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     * @deprecated call via {@link net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#isSignalSource}
     * whenever possible. Implementing/overriding is fine.
     */
    public boolean isSignalSource(BlockState pState) {
        return true;
    }

    /**
     * @deprecated call via {@link net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#getSignal}
     * whenever possible. Implementing/overriding is fine.
     */
    public int getSignal(BlockState pState, BlockGetter pLevel, BlockPos pPos, Direction pDirection) {
        return pState.getValue(POWER);
    }

    public static SculkSensorPhase getPhase(BlockState pState) {
        return pState.getValue(PHASE);
    }

    public static boolean canActivate(BlockState pState) {
        return getPhase(pState) == SculkSensorPhase.INACTIVE;
    }

    public static void deactivate(Level pLevel, BlockPos pPos, BlockState pState) {
        pLevel.setBlock(pPos, pState.setValue(PHASE, SculkSensorPhase.COOLDOWN).setValue(POWER, Integer.valueOf(0)), 3);
        pLevel.scheduleTick(pPos, pState.getBlock(), 1);

        updateNeighbours(pLevel, pPos);
    }

    public static void activate(@Nullable Entity pEntity, Level pLevel, BlockPos pPos, BlockState pState, int pPower) {
        pLevel.setBlock(pPos, pState.setValue(PHASE, SculkSensorPhase.ACTIVE).setValue(POWER, Integer.valueOf(pPower)), 3);
        pLevel.scheduleTick(pPos, pState.getBlock(), 40);
        pLevel.getBlockEntity(pPos, SpectrobesTileRegistry.MINI_XELLES_TILE.get()).ifPresent((p_222163_) -> {
            if(pEntity instanceof LivingEntity) {
                p_222163_.trySummon((ServerLevel) pLevel, (LivingEntity) pEntity);
            }
        });
        updateNeighbours(pLevel, pPos);
        pLevel.gameEvent(pEntity, GameEvent.SCULK_SENSOR_TENDRILS_CLICKING, pPos);
    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles).
     */
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (getPhase(pState) == SculkSensorPhase.ACTIVE) {
            Direction direction = Direction.getRandom(pRandom);
            if (direction != Direction.UP && direction != Direction.DOWN) {
                double d0 = (double)pPos.getX() + 0.5D + (direction.getStepX() == 0 ? 0.5D - pRandom.nextDouble() : (double)direction.getStepX() * 0.6D);
                double d1 = (double)pPos.getY() + 0.25D;
                double d2 = (double)pPos.getZ() + 0.5D + (direction.getStepZ() == 0 ? 0.5D - pRandom.nextDouble() : (double)direction.getStepZ() * 0.6D);
                double d3 = (double)pRandom.nextFloat() * 0.04D;
                pLevel.addParticle(DustColorTransitionOptions.SCULK_TO_REDSTONE, d0, d1, d2, 0.0D, d3, 0.0D);
            }
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(PHASE, POWER, PULSE, SUMMONING, CAN_SUMMON);
    }

    /**
     * @deprecated call via {@link
     * net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#hasAnalogOutputSignal} whenever possible.
     * Implementing/overriding is fine.
     */
    public boolean hasAnalogOutputSignal(BlockState pState) {
        return true;
    }

    /**
     * @deprecated call via {@link
     * net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase#getAnalogOutputSignal} whenever possible.
     * Implementing/overriding is fine.
     */
    public int getAnalogOutputSignal(BlockState pState, Level pLevel, BlockPos pPos) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof MiniXellesBlockEntity sculksensorblockentity) {
            return getPhase(pState) == SculkSensorPhase.ACTIVE ? sculksensorblockentity.getLastVibrationFrequency() : 0;
        } else {
            return 0;
        }
    }

    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return false;
    }

    public boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    /**
     * Perform side-effects from block dropping, such as creating silverfish
     */
    public void spawnAfterBreak(BlockState pState, ServerLevel pLevel, BlockPos pPos, ItemStack pStack, boolean pDropExperience) {
        super.spawnAfterBreak(pState, pLevel, pPos, pStack, pDropExperience);
    }

    public static void bloom(ServerLevel pLevel, BlockPos pPos, BlockState pState, RandomSource pSource) {
        pLevel.setBlock(pPos, pState.setValue(PULSE, Boolean.valueOf(true)), 3);
        pLevel.scheduleTick(pPos, pState.getBlock(), 8);
        pLevel.sendParticles(ParticleTypes.SCULK_SOUL, (double)pPos.getX() + 0.5D, (double)pPos.getY() + 1.15D, (double)pPos.getZ() + 0.5D, 2, 0.2D, 0.0D, 0.2D, 0.0D);
        pLevel.playSound((Player)null, pPos, SoundEvents.SCULK_CATALYST_BLOOM, SoundSource.BLOCKS, 2.0F, 0.6F + pSource.nextFloat() * 0.4F);
    }
}
