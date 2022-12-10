package com.spectrobes.spectrobesmod.common.blocks.krawl;

import com.google.common.annotations.VisibleForTesting;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.world.krawl.KrawlSpreader;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.VibrationParticleOption;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.GameEventTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.ClipBlockStateContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SculkSensorBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.level.gameevent.vibrations.VibrationListener;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.Optional;

public class MiniXellesBlockEntity extends BlockEntity implements GameEventListener, IAnimatable {
    private int lastVibrationFrequency;
    private static final int LISTENER_RADIUS = 8;
    private int warningLevel;
    private final BlockPositionSource blockPosSource = new BlockPositionSource(this.worldPosition);
    private final KrawlSpreader krawlSpreader = KrawlSpreader.createLevelSpreader();
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private float receivingDistance;
    private VibrationListener.ReceivingEvent receivingEvent;
    private int travelTimeInTicks;
    private int summon_delay;

    public MiniXellesBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(SpectrobesTileRegistry.MINI_XELLES_TILE.get(), pPos, pBlockState);
//        this.listener = new MiniXellesVibrationListener(this, new BlockPositionSource(this.worldPosition), ((MiniXellesBlock)pBlockState.getBlock()).getListenerRange(), this, (VibrationListener.ReceivingEvent)null, 0.0F, 0);
    }

    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.krawlSpreader.load(pTag);
        this.lastVibrationFrequency = pTag.getInt("last_vibration_frequency");
        if (pTag.contains("warning_level", 99)) {
            this.warningLevel = pTag.getInt("warning_level");
        }
//        if (pTag.contains("listener", 10)) {
//            VibrationListener.codec(this).parse(new Dynamic<>(NbtOps.INSTANCE, pTag.getCompound("listener"))).resultOrPartial(SpectrobesInfo.LOGGER::error).ifPresent((p_222817_) -> {
//                this.listener = p_222817_;
//            });
//        }

    }

    protected void saveAdditional(CompoundTag pTag) {
        this.krawlSpreader.save(pTag);
        super.saveAdditional(pTag);
        pTag.putInt("warning_level", this.warningLevel);
        pTag.putInt("last_vibration_frequency", this.lastVibrationFrequency);
    }

    public boolean isValidVibration(GameEvent pGameEvent, GameEvent.Context pContext) {
        Entity entity = pContext.sourceEntity();
        if (entity != null) {
            if (entity.isSpectator()) {
                return false;
            }

            if (entity.isSteppingCarefully() && pGameEvent.is(GameEventTags.IGNORE_VIBRATIONS_SNEAKING)) {
                if (this.canTriggerAvoidVibration() && entity instanceof ServerPlayer) {
                    ServerPlayer serverplayer = (ServerPlayer)entity;
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
        if (MiniXellesBlock.canActivate(blockstate) || blockstate.getValue(MiniXellesBlock.CAN_SUMMON)) {
            this.trySummon(pLevel, tryGetPlayer(pProjectileOwner != null ? pProjectileOwner : pSourceEntity));
            this.lastVibrationFrequency = MiniXellesBlock.VIBRATION_FREQUENCY_FOR_EVENT.getInt(pGameEvent);
            MiniXellesBlock.activate(pSourceEntity, pLevel, this.worldPosition, blockstate, SculkSensorBlockEntity.getRedstoneStrengthForDistance(pDistance, pListener.getListenerRadius()));
        }

    }

    public void trySummon(ServerLevel pLevel, @Nullable LivingEntity pPlayer) {
        if (pPlayer != null && !(pPlayer instanceof EntityKrawl)) {
            this.shriek(pLevel);
            this.tryRespond(pLevel);
        }
    }

    private void shriek(ServerLevel pLevel) {
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
        if (!this.canRespond(pLevel)) {
            if (!this.trySummonKrawl(pLevel)) {
//                this.playWardenReplySound();
            }
        }
    }

    private boolean trySummonKrawl(ServerLevel pLevel) {
        if(this.summon_delay <= 0) {
            EntityVortex vortex = (EntityVortex) KrawlEntities.ENTITY_VORTEX.get()
                    .spawn(pLevel,
                            null,
                            null,
                            this.worldPosition.relative(Direction.UP, 1),
                            MobSpawnType.MOB_SUMMONED,
                            false,
                            false);
            this.summon_delay = 300;
            return vortex != null;
        }
        return false;
    }

    public void onSignalSchedule() {
        this.setChanged();
    }

    public void setLastVibrationFrequency(int pLastVibrationFrequency) {
        this.lastVibrationFrequency = pLastVibrationFrequency;
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, MiniXellesBlockEntity pSculkCatalyst) {
//        ((MiniXellesBlock)pState.getBlock()).getListener(pLevel).tick(p_154417_)
        pSculkCatalyst.krawlSpreader.updateCursors(pLevel, pPos, pLevel.getRandom(), true);
    }

    public void tick(Level pLevel) {
        if (pLevel instanceof ServerLevel serverlevel) {
            if(this.summon_delay > 0) this.summon_delay--;
            if (this.receivingEvent != null) {
                --this.travelTimeInTicks;
                if (this.travelTimeInTicks <= 0) {
                    this.travelTimeInTicks = 0;
                    this.onSignalReceive(serverlevel, this, new BlockPos(this.receivingEvent.pos()), this.receivingEvent.gameEvent(), this.receivingEvent.getEntity(serverlevel).orElse((Entity)null), this.receivingEvent.getProjectileOwner(serverlevel).orElse((Entity)null), this.receivingDistance);
                    this.receivingEvent = null;
                }
            }
        }

    }

    @VisibleForTesting
    public KrawlSpreader getKrawlSpreader() {
        return this.krawlSpreader;
    }

    public PlayState controller(AnimationEvent event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.xelles.idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::controller));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public PositionSource getListenerSource() {
        return blockPosSource;
    }

    @Override
    public int getListenerRadius() {
        return LISTENER_RADIUS;
    }

    @Override
    public boolean handleGameEvent(ServerLevel pLevel, GameEvent.Message pEventMessage) {
        if (this.isRemoved()) {
                return false;
            } else {
                GameEvent.Context gameevent$context = pEventMessage.context();
                if (pEventMessage.gameEvent() == GameEvent.ENTITY_DIE) {
                    Entity $$4 = gameevent$context.sourceEntity();
                    if ($$4 instanceof LivingEntity) {
                        LivingEntity livingentity = (LivingEntity)$$4;
                        boolean isClient = livingentity.level.isClientSide();
                        if (!livingentity.wasExperienceConsumed()) {
                            int i = livingentity.getExperienceReward();
                            if (livingentity.shouldDropExperience() && i > 0) {
                                this.krawlSpreader.addCursors(new BlockPos(pEventMessage.source().relative(Direction.UP, 0.5D)), i);
                            }

                            livingentity.skipDropExperience();
                            MiniXellesBlock.bloom(pLevel, this.worldPosition, this.getBlockState(), pLevel.getRandom());
                            return true;
                        }
                    }
                }else {
                    GameEvent gameevent = pEventMessage.gameEvent();
                    if (!this.isValidVibration(gameevent, gameevent$context)) {
                        return false;
                    } else {
                        Optional<Vec3> optional = this.blockPosSource.getPosition(pLevel);
                        if (!optional.isPresent()) {
                            return false;
                        } else {
                            Vec3 vec3 = pEventMessage.source();
                            Vec3 vec31 = optional.get();
                            if (!this.shouldListen(pLevel, this, new BlockPos(vec3), gameevent, gameevent$context)) {
                                return false;
                            } else if (isOccluded(pLevel, vec3, vec31)) {
                                return false;
                            } else {
                                this.scheduleSignal(pLevel, gameevent, gameevent$context, vec3, vec31);
                                return true;
                            }
                        }
                    }
                }
            }
        return false;
     }

    private void scheduleSignal(ServerLevel pLevel, GameEvent pEvent, GameEvent.Context pContext, Vec3 pOrigin, Vec3 pDestination) {
        this.receivingDistance = (float)pOrigin.distanceTo(pDestination);
        this.receivingEvent = new VibrationListener.ReceivingEvent(pEvent, this.receivingDistance, pOrigin, pContext.sourceEntity());
        this.travelTimeInTicks = Mth.floor(this.receivingDistance);
        pLevel.sendParticles(new VibrationParticleOption(this.blockPosSource, this.travelTimeInTicks), pOrigin.x, pOrigin.y, pOrigin.z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
        this.onSignalSchedule();
    }

    private static boolean isOccluded(Level pLevel, Vec3 pFrom, Vec3 pTo) {
        Vec3 vec3 = new Vec3((double)Mth.floor(pFrom.x) + 0.5D, (double)Mth.floor(pFrom.y) + 0.5D, (double)Mth.floor(pFrom.z) + 0.5D);
        Vec3 vec31 = new Vec3((double)Mth.floor(pTo.x) + 0.5D, (double)Mth.floor(pTo.y) + 0.5D, (double)Mth.floor(pTo.z) + 0.5D);

        for(Direction direction : Direction.values()) {
            Vec3 vec32 = vec3.relative(direction, (double)1.0E-5F);
            if (pLevel.isBlockInLine(new ClipBlockStateContext(vec32, vec31, (p_223780_) -> {
                return p_223780_.is(BlockTags.OCCLUDES_VIBRATION_SIGNALS);
            })).getType() != HitResult.Type.BLOCK) {
                return false;
            }
        }

        return true;
    }
}
