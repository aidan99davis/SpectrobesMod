package com.spectrobes.spectrobesmod.common.blocks.krawl;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.Dynamic;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.world.krawl.KrawlSpreader;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.GameEventTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.monster.warden.WardenSpawnTracker;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SculkSensorBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.level.gameevent.vibrations.VibrationListener;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.OptionalInt;

public class MiniXellesBlockEntityOld extends BlockEntity implements VibrationListener.VibrationListenerConfig, IAnimatable {
    private VibrationListener listener;
    private int lastVibrationFrequency;
    private static final int LISTENER_RADIUS = 8;
    private static final int WARNING_SOUND_RADIUS = 10;
    private static final int KRAWL_SPAWN_ATTEMPTS = 20;
    private static final int KRAWL_SPAWN_RANGE_XZ = 5;
    private static final int KRAWL_SPAWN_RANGE_Y = 6;
    private static final int SUMMONING_TICKS = 90;
    private int warningLevel;
    private final BlockPositionSource blockPosSource = new BlockPositionSource(this.worldPosition);
    private final KrawlSpreader krawlSpreader = KrawlSpreader.createLevelSpreader();
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public MiniXellesBlockEntityOld(BlockPos pPos, BlockState pBlockState) {
        super(SpectrobesTileRegistry.MINI_XELLES_TILE.get(), pPos, pBlockState);
        this.listener = new MiniXellesVibrationListener(this, new BlockPositionSource(this.worldPosition), ((MiniXellesBlock)pBlockState.getBlock()).getListenerRange(), this, (VibrationListener.ReceivingEvent)null, 0.0F, 0);
    }


    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.krawlSpreader.load(pTag);
        this.lastVibrationFrequency = pTag.getInt("last_vibration_frequency");
        if (pTag.contains("warning_level", 99)) {
            this.warningLevel = pTag.getInt("warning_level");
        }
        if (pTag.contains("listener", 10)) {
            VibrationListener.codec(this).parse(new Dynamic<>(NbtOps.INSTANCE, pTag.getCompound("listener"))).resultOrPartial(SpectrobesInfo.LOGGER::error).ifPresent((p_222817_) -> {
                this.listener = p_222817_;
            });
        }

    }

    protected void saveAdditional(CompoundTag pTag) {
        this.krawlSpreader.save(pTag);
        super.saveAdditional(pTag);
        pTag.putInt("warning_level", this.warningLevel);
        pTag.putInt("last_vibration_frequency", this.lastVibrationFrequency);
        VibrationListener.codec(this).encodeStart(NbtOps.INSTANCE, this.listener).resultOrPartial(SpectrobesInfo.LOGGER::error).ifPresent((p_222820_) -> {
            pTag.put("listener", p_222820_);
        });
    }

    public VibrationListener getListener() {
        return this.listener;
    }

    @Override
    public boolean isValidVibration(GameEvent pGameEvent, GameEvent.Context pContext) {
        Entity entity = pContext.sourceEntity();
        if (entity != null) {
            if (entity.isSpectator()) {
                return false;
            }

            if (entity.isSteppingCarefully() && pGameEvent.is(GameEventTags.IGNORE_VIBRATIONS_SNEAKING)) {
                if (this.canTriggerAvoidVibration() && entity instanceof ServerPlayer) {
                    ServerPlayer serverplayer = (ServerPlayer)entity;
                    CriteriaTriggers.AVOID_VIBRATION.trigger(serverplayer);
                }

                return false;
            }

            if (entity.dampensVibrations()) {
                return false;
            }
        }

        if (pContext.affectedState() != null) {
            return !pContext.affectedState().is(BlockTags.DAMPENS_VIBRATIONS);
        } else {
            return true;
        }
    }


    public TagKey<GameEvent> getListenableEvents() {
        return null;
    }

    public int getLastVibrationFrequency() {
        return this.lastVibrationFrequency;
    }

    public boolean canTriggerAvoidVibration() {
        return true;
    }

    public boolean shouldListen(ServerLevel pLevel, GameEventListener pListener, BlockPos pPos, GameEvent pGameEvent, @Nullable GameEvent.Context pContext) {
        return !this.isRemoved() && !this.getBlockState().getValue(MiniXellesBlock.SUMMONING) && (!pPos.equals(this.getBlockPos()) || pGameEvent != GameEvent.BLOCK_DESTROY && pGameEvent != GameEvent.BLOCK_PLACE) && MiniXellesBlock.canActivate(this.getBlockState());
    }

    @Nullable
    public static ServerPlayer tryGetPlayer(@Nullable Entity pEntity) {
        if (pEntity instanceof ServerPlayer serverplayer1) {
            return serverplayer1;
        } else {
            if (pEntity != null) {
                Entity $$6 = pEntity.getControllingPassenger();
                if ($$6 instanceof ServerPlayer) {
                    ServerPlayer serverplayer = (ServerPlayer)$$6;
                    return serverplayer;
                }
            }

            if (pEntity instanceof Projectile projectile) {
                Entity entity1 = projectile.getOwner();
                if (entity1 instanceof ServerPlayer serverplayer3) {
                    return serverplayer3;
                }
            }

            if (pEntity instanceof ItemEntity itementity) {
                Entity entity2 = itementity.getThrowingEntity();
                if (entity2 instanceof ServerPlayer serverplayer2) {
                    return serverplayer2;
                }
            }

            if (pEntity instanceof EntitySpectrobe spectrobe) {
                Entity entity2 = spectrobe.getOwner();
                if (entity2 != null && entity2 instanceof ServerPlayer serverplayer2) {
                    return serverplayer2;
                }
            }

            return null;
        }
    }

    public void onSignalReceive(ServerLevel pLevel, GameEventListener pListener, BlockPos pSourcePos, GameEvent pGameEvent, @Nullable Entity pSourceEntity, @Nullable Entity pProjectileOwner, float pDistance) {
        BlockState blockstate = this.getBlockState();
        if (MiniXellesBlock.canActivate(blockstate)) {
            this.trySummon(pLevel, tryGetPlayer(pProjectileOwner != null ? pProjectileOwner : pSourceEntity));
            this.lastVibrationFrequency = MiniXellesBlock.VIBRATION_FREQUENCY_FOR_EVENT.getInt(pGameEvent);
            MiniXellesBlock.activate(pSourceEntity, pLevel, this.worldPosition, blockstate, SculkSensorBlockEntity.getRedstoneStrengthForDistance(pDistance, pListener.getListenerRadius()));
        }

    }

    public void trySummon(ServerLevel pLevel, @Nullable ServerPlayer pPlayer) {
        if (pPlayer != null) {
            BlockState blockstate = this.getBlockState();
            if (!blockstate.getValue(MiniXellesBlock.SUMMONING)) {
                this.warningLevel = 0;
                if (!this.canRespond(pLevel) || this.tryToWarn(pLevel, pPlayer)) {
                    this.shriek(pLevel, pPlayer);
                }
            }
        }
    }

    private boolean tryToWarn(ServerLevel pLevel, ServerPlayer pPlayer) {
        OptionalInt optionalint = WardenSpawnTracker.tryWarn(pLevel, this.getBlockPos(), pPlayer);
        optionalint.ifPresent((p_222838_) -> {
            this.warningLevel = p_222838_;
        });
        return optionalint.isPresent();
    }

    private void shriek(ServerLevel pLevel, @Nullable Entity pSourceEntity) {
        BlockPos blockpos = this.getBlockPos();
        BlockState blockstate = this.getBlockState();
        pLevel.setBlock(blockpos, blockstate.setValue(MiniXellesBlock.SUMMONING, Boolean.valueOf(true)), 2);
        pLevel.scheduleTick(blockpos, blockstate.getBlock(), 90);
        pLevel.levelEvent(3007, blockpos, 0);
    }

    private boolean canRespond(ServerLevel pLevel) {
        return this.getBlockState().getValue(MiniXellesBlock.CAN_SUMMON) && pLevel.getDifficulty() != Difficulty.PEACEFUL;
    }

    public void tryRespond(ServerLevel pLevel) {
        if (this.canRespond(pLevel) && this.warningLevel > 0) {
            if (!this.trySummonKrawl(pLevel)) {
//                this.playWardenReplySound();
            }

            Warden.applyDarknessAround(pLevel, Vec3.atCenterOf(this.getBlockPos()), (Entity)null, 40);
        }

    }

    private boolean trySummonKrawl(ServerLevel pLevel) {
        return this.warningLevel < 4 ? false : SpawnUtil.trySpawnMob(KrawlEntities.ENTITY_VORTEX.get(), MobSpawnType.TRIGGERED, pLevel, this.getBlockPos(), 20, 5, 6, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER).isPresent();
    }

    public void onSignalSchedule() {
        this.setChanged();
    }

    public void setLastVibrationFrequency(int pLastVibrationFrequency) {
        this.lastVibrationFrequency = pLastVibrationFrequency;
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, MiniXellesBlockEntityOld pSculkCatalyst) {
//        ((MiniXellesBlock)pState.getBlock()).getListener(pLevel).tick(p_154417_)
        pSculkCatalyst.krawlSpreader.updateCursors(pLevel, pPos, pLevel.getRandom(), true);
    }

    @VisibleForTesting
    public KrawlSpreader getKrawlSpreader() {
        return this.krawlSpreader;
    }

    @Override
    public void registerControllers(AnimationData data) {
        //TODO: ANIMATE MINI XELLES BLOCK
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public class MiniXellesVibrationListener extends VibrationListener {
        private MiniXellesBlockEntityOld blockEntity;
        public MiniXellesVibrationListener(MiniXellesBlockEntityOld entity, PositionSource pListenerSource, int pListenerRange, VibrationListenerConfig pConfig, @org.jetbrains.annotations.Nullable VibrationListener.ReceivingEvent pReceivingEvent, float pReceivingDistance, int pTravelTimeInTicks) {
            super(pListenerSource, pListenerRange, pConfig, pReceivingEvent, pReceivingDistance, pTravelTimeInTicks);
            this.blockEntity = entity;
        }

        @Override
        public boolean handleEventsImmediately() {
            return true;
        }

        @Override
        public boolean handleGameEvent(ServerLevel pLevel, GameEvent.Message pEventMessage) {
            if (blockEntity.isRemoved()) {
                return false;
            } else {
                GameEvent.Context gameevent$context = pEventMessage.context();
                if (pEventMessage.gameEvent() == GameEvent.ENTITY_DIE) {
                    Entity $$4 = gameevent$context.sourceEntity();
                    if ($$4 instanceof LivingEntity) {
                        LivingEntity livingentity = (LivingEntity)$$4;
                        if (!livingentity.wasExperienceConsumed()) {
                            int i = livingentity.getExperienceReward();
                            if (livingentity.shouldDropExperience() && i > 0) {
                                blockEntity.krawlSpreader.addCursors(new BlockPos(pEventMessage.source().relative(Direction.UP, 0.5D)), i);
                            }

                            livingentity.skipDropExperience();
                            MiniXellesBlock.bloom(pLevel, blockEntity.worldPosition, blockEntity.getBlockState(), pLevel.getRandom());
                            return true;
                        }

                    }
                }
            }
            return super.handleGameEvent(pLevel, pEventMessage);
        }

        private class MiniXellesVibrationListenerConfig implements VibrationListenerConfig {

            @Override
            public boolean isValidVibration(GameEvent pGameEvent, GameEvent.Context pContext) {
                Entity entity = pContext.sourceEntity();
                if (entity != null) {
                    if (entity.isSpectator()) {
                        return false;
                    }

                    if (entity.isSteppingCarefully() && pGameEvent.is(GameEventTags.IGNORE_VIBRATIONS_SNEAKING)) {
                        if (this.canTriggerAvoidVibration() && entity instanceof ServerPlayer) {
                            ServerPlayer serverplayer = (ServerPlayer)entity;
                            CriteriaTriggers.AVOID_VIBRATION.trigger(serverplayer);
                        }

                        return false;
                    }

                    if (entity.dampensVibrations()) {
                        return false;
                    }
                }

                if (pContext.affectedState() != null) {
                    return !pContext.affectedState().is(BlockTags.DAMPENS_VIBRATIONS);
                } else {
                    return true;
                }
            }

            @Override
            public boolean shouldListen(ServerLevel pLevel, GameEventListener pListener, BlockPos pPos, GameEvent pGameEvent, GameEvent.Context pContext) {
                return false;
            }

            @Override
            public void onSignalReceive(ServerLevel pLevel, GameEventListener pListener, BlockPos pSourcePos, GameEvent pGameEvent, @org.jetbrains.annotations.Nullable Entity pSourceEntity, @org.jetbrains.annotations.Nullable Entity pProjectileOwner, float pDistance) {

            }
        }

    }
}
