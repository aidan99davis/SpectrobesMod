package com.spectrobes.spectrobesmod.common.blocks.krawl;

import com.mojang.serialization.MapCodec;
import com.spectrobes.spectrobesmod.common.blocks.api.SpectrobesTileEntityBlock;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.SculkSensorPhase;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

import static com.spectrobes.spectrobesmod.common.blocks.krawl.MiniXellesBlockEntity.tryGetPlayer;

public class MiniXellesBlock extends SpectrobesTileEntityBlock {

    public static final MapCodec<MiniXellesBlock> CODEC = MapCodec.unit(MiniXellesBlock::new);

    private static final BlockBehaviour.Properties props = BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BLACK)
            .noOcclusion()
            .strength(0.0F)
            .sound(SoundType.ROOTS);

    public static final BooleanProperty CAN_SUMMON = BlockStateProperties.CAN_SUMMON;
    public static final BooleanProperty SUMMONING = BlockStateProperties.SHRIEKING;
    public static final int PULSE_TICKS = 8;
    public static final BooleanProperty PULSE = BlockStateProperties.BLOOM;
    public static final int ACTIVE_TICKS = 40;
    public static final int COOLDOWN_TICKS = 1;
    public static final EnumProperty<SculkSensorPhase> PHASE = BlockStateProperties.SCULK_SENSOR_PHASE;
    public static final IntegerProperty POWER = BlockStateProperties.POWER;

    protected static final VoxelShape SHAPE = Block.box(
            0.0D,
            0.0D,
            0.0D,
            16.0D,
            8.0D,
            16.0D
    );

    private final int listenerRange;

    public MiniXellesBlock() {
        this(props, 5);
    }

    private MiniXellesBlock(BlockBehaviour.Properties properties) {
        this(properties, 5);
    }

    private MiniXellesBlock(BlockBehaviour.Properties properties, int listenerRange) {
        super(properties);

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(PHASE, SculkSensorPhase.INACTIVE)
                .setValue(POWER, 0)
                .setValue(PULSE, false)
                .setValue(SUMMONING, false)
                .setValue(CAN_SUMMON, false));

        this.listenerRange = listenerRange;
    }

    @Override
    public MapCodec<? extends MiniXellesBlock> codec() {
        return CODEC;
    }

    public int getListenerRange() {
        return this.listenerRange;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(SUMMONING)) {
            level.setBlock(pos, state.setValue(SUMMONING, false), Block.UPDATE_ALL);

            level.getBlockEntity(pos, SpectrobesTileRegistry.MINI_XELLES_TILE.get()).ifPresent(xelles -> {
                xelles.tryRespond(level);
            });
        }

        if (state.getValue(PULSE)) {
            level.setBlock(pos, state.setValue(PULSE, false), Block.UPDATE_ALL);
        }

        if (getPhase(state) != SculkSensorPhase.ACTIVE) {
            if (getPhase(state) == SculkSensorPhase.COOLDOWN) {
                level.setBlock(pos, state.setValue(PHASE, SculkSensorPhase.INACTIVE), Block.UPDATE_ALL);
            }
        } else {
            deactivate(level, pos, state);
        }

        super.tick(state, level, pos, random);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide() && canActivate(state) && !(entity instanceof EntityKrawl)) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            ServerPlayer serverPlayer = tryGetPlayer(entity);

            if (serverPlayer != null && level instanceof ServerLevel serverLevel) {
                level.getBlockEntity(pos, SpectrobesTileRegistry.MINI_XELLES_TILE.get()).ifPresent(xelles -> {
                    xelles.trySummon(serverLevel, serverPlayer, serverPlayer);
                });
            }

            if (blockEntity instanceof MiniXellesBlockEntity miniXellesBlockEntity) {
                miniXellesBlockEntity.setLastVibrationFrequency(
                        VibrationSystem.getGameEventFrequency(GameEvent.STEP)
                );
            }

            activate(entity, level, pos, state, 15);
        }

        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide() && !state.is(oldState.getBlock())) {
            if (state.getValue(POWER) > 0 && !level.getBlockTicks().hasScheduledTick(pos, this)) {
                level.setBlock(pos, state.setValue(POWER, 0), Block.UPDATE_CLIENTS | Block.UPDATE_KNOWN_SHAPE);
            }

            level.scheduleTick(pos, state.getBlock(), COOLDOWN_TICKS);
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (level instanceof ServerLevel serverLevel) {
            if (state.getValue(SUMMONING) && !state.is(newState.getBlock())) {
                serverLevel.getBlockEntity(pos, SpectrobesTileRegistry.MINI_XELLES_TILE.get()).ifPresent(xelles -> {
                    xelles.tryRespond(serverLevel);
                });
            }
        }

        if (!state.is(newState.getBlock())) {
            if (getPhase(state) == SculkSensorPhase.ACTIVE) {
                updateNeighbours(level, pos);
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    private static void updateNeighbours(Level level, BlockPos pos) {
        level.updateNeighborsAt(pos, SpectrobesBlocks.mini_xelles_block.get());
        level.updateNeighborsAt(pos.relative(Direction.DOWN), SpectrobesBlocks.mini_xelles_block.get());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MiniXellesBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> GameEventListener getListener(ServerLevel level, T blockEntity) {
        return blockEntity instanceof MiniXellesBlockEntity miniXellesBlockEntity ? miniXellesBlockEntity.getListener() : null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level,
            BlockState state,
            BlockEntityType<T> blockEntityType
    ) {
        if (level.isClientSide()) {
            return createTickerHelper(
                    blockEntityType,
                    SpectrobesTileRegistry.MINI_XELLES_TILE.get(),
                    (tickerLevel, tickerPos, tickerState, blockEntity) -> blockEntity.tick(tickerLevel)
            );
        }

        return createTickerHelper(
                blockEntityType,
                SpectrobesTileRegistry.MINI_XELLES_TILE.get(),
                MiniXellesBlockEntity::serverTick
        );
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.getValue(POWER);
    }

    public static SculkSensorPhase getPhase(BlockState state) {
        return state.getValue(PHASE);
    }

    public static boolean canActivate(BlockState state) {
        return getPhase(state) == SculkSensorPhase.INACTIVE;
    }

    public static void deactivate(Level level, BlockPos pos, BlockState state) {
        level.setBlock(
                pos,
                state.setValue(PHASE, SculkSensorPhase.COOLDOWN)
                        .setValue(POWER, 0),
                Block.UPDATE_ALL
        );

        level.scheduleTick(pos, state.getBlock(), COOLDOWN_TICKS);

        updateNeighbours(level, pos);
    }

    public static void activate(@Nullable Entity entity, Level level, BlockPos pos, BlockState state, int power) {
        level.setBlock(
                pos,
                state.setValue(PHASE, SculkSensorPhase.ACTIVE)
                        .setValue(POWER, power),
                Block.UPDATE_ALL
        );

        level.scheduleTick(pos, state.getBlock(), ACTIVE_TICKS);

        if (level instanceof ServerLevel serverLevel && entity instanceof LivingEntity livingEntity) {
            level.getBlockEntity(pos, SpectrobesTileRegistry.MINI_XELLES_TILE.get()).ifPresent(xelles -> {
                xelles.trySummon(serverLevel, livingEntity, livingEntity);
            });
        }

        updateNeighbours(level, pos);

        level.gameEvent(entity, GameEvent.SCULK_SENSOR_TENDRILS_CLICKING, pos);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (getPhase(state) == SculkSensorPhase.ACTIVE) {
            Direction direction = Direction.getRandom(random);

            if (direction != Direction.UP && direction != Direction.DOWN) {
                double x = (double) pos.getX() + 0.5D
                        + (direction.getStepX() == 0 ? 0.5D - random.nextDouble() : (double) direction.getStepX() * 0.6D);
                double y = (double) pos.getY() + 0.25D;
                double z = (double) pos.getZ() + 0.5D
                        + (direction.getStepZ() == 0 ? 0.5D - random.nextDouble() : (double) direction.getStepZ() * 0.6D);
                double ySpeed = (double) random.nextFloat() * 0.04D;

                level.addParticle(
                        DustColorTransitionOptions.SCULK_TO_REDSTONE,
                        x,
                        y,
                        z,
                        0.0D,
                        ySpeed,
                        0.0D
                );
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PHASE, POWER, PULSE, SUMMONING, CAN_SUMMON);
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        BlockEntity blockEntity = level.getBlockEntity(pos);

        if (blockEntity instanceof MiniXellesBlockEntity miniXellesBlockEntity) {
            return getPhase(state) == SculkSensorPhase.ACTIVE
                    ? miniXellesBlockEntity.getLastVibrationFrequency()
                    : 0;
        }

        return 0;
    }

    @Override
    public boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public void spawnAfterBreak(
            BlockState state,
            ServerLevel level,
            BlockPos pos,
            ItemStack stack,
            boolean dropExperience
    ) {
        super.spawnAfterBreak(state, level, pos, stack, dropExperience);
    }

    public static void bloom(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        level.setBlock(pos, state.setValue(PULSE, true), Block.UPDATE_ALL);
        level.scheduleTick(pos, state.getBlock(), PULSE_TICKS);

        level.sendParticles(
                ParticleTypes.SCULK_SOUL,
                (double) pos.getX() + 0.5D,
                (double) pos.getY() + 1.15D,
                (double) pos.getZ() + 0.5D,
                2,
                0.2D,
                0.0D,
                0.2D,
                0.0D
        );

        level.playSound(
                (Player) null,
                pos,
                SoundEvents.SCULK_CATALYST_BLOOM,
                SoundSource.BLOCKS,
                2.0F,
                0.6F + random.nextFloat() * 0.4F
        );
    }
}