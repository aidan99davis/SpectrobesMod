package com.spectrobes.spectrobesmod.common.blocks.krawl;

import com.google.common.annotations.VisibleForTesting;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.world.krawl.KrawlSpreader;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.GameEventTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.BlockPositionSource;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.gameevent.PositionSource;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class MiniXellesBlockEntity extends BlockEntity implements GameEventListener.Provider<VibrationSystem.Listener>, VibrationSystem, GeoBlockEntity {
    private static final int LISTENER_RADIUS = 8;
    private static final int SUMMON_DELAY_TICKS = 300;

    private static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("animation.xelles.idle");

    private final KrawlSpreader krawlSpreader = KrawlSpreader.createLevelSpreader();
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private final VibrationSystem.User vibrationUser = new MiniXellesVibrationUser();
    private final VibrationSystem.Listener vibrationListener = new VibrationSystem.Listener(this);
    private VibrationSystem.Data vibrationData = new VibrationSystem.Data();

    private int lastVibrationFrequency;
    private int warningLevel;
    private int summonDelay;

    public MiniXellesBlockEntity(BlockPos pos, BlockState blockState) {
        super(SpectrobesTileRegistry.MINI_XELLES_TILE.get(), pos, blockState);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        this.krawlSpreader.load(tag);
        this.lastVibrationFrequency = tag.getInt("last_vibration_frequency");
        this.warningLevel = tag.getInt("warning_level");
        this.summonDelay = tag.getInt("summon_delay");

        if (tag.contains(VibrationSystem.Data.NBT_TAG_KEY, Tag.TAG_COMPOUND)) {
            VibrationSystem.Data.CODEC
                    .parse(registries.createSerializationContext(NbtOps.INSTANCE), tag.get(VibrationSystem.Data.NBT_TAG_KEY))
                    .result()
                    .ifPresent(data -> this.vibrationData = data);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        this.krawlSpreader.save(tag);
        tag.putInt("warning_level", this.warningLevel);
        tag.putInt("last_vibration_frequency", this.lastVibrationFrequency);
        tag.putInt("summon_delay", this.summonDelay);

        VibrationSystem.Data.CODEC
                .encodeStart(registries.createSerializationContext(NbtOps.INSTANCE), this.vibrationData)
                .result()
                .ifPresent(vibrationTag -> tag.put(VibrationSystem.Data.NBT_TAG_KEY, vibrationTag));
    }

    @Override
    public VibrationSystem.Data getVibrationData() {
        return this.vibrationData;
    }

    @Override
    public VibrationSystem.User getVibrationUser() {
        return this.vibrationUser;
    }

    @Override
    public VibrationSystem.Listener getListener() {
        return this.vibrationListener;
    }

    public int getLastVibrationFrequency() {
        return this.lastVibrationFrequency;
    }

    public void setLastVibrationFrequency(int lastVibrationFrequency) {
        this.lastVibrationFrequency = lastVibrationFrequency;
    }

    public boolean canTriggerAvoidVibration() {
        return true;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, MiniXellesBlockEntity blockEntity) {
        blockEntity.tick(level);
    }

    public void tick(Level level) {
        if (!(level instanceof ServerLevel)) {
            return;
        }

        if (this.summonDelay > 0) {
            this.summonDelay--;
        }

        VibrationSystem.Ticker.tick(level, this.vibrationData, this.vibrationUser);
        this.krawlSpreader.updateCursors(level, this.worldPosition, level.getRandom(), true);
    }

    private boolean canReceiveVibration(ServerLevel level, BlockPos sourcePos, Holder<GameEvent> gameEvent, @Nullable GameEvent.Context context) {
        if (this.isRemoved()) {
            return false;
        }

        BlockState blockState = this.getBlockState();

        if (blockState.getValue(MiniXellesBlock.SUMMONING)) {
            return false;
        }

        if (sourcePos.equals(this.getBlockPos()) && (gameEvent.is(GameEvent.BLOCK_DESTROY) || gameEvent.is(GameEvent.BLOCK_PLACE))) {
            return false;
        }

        if (!MiniXellesBlock.canActivate(blockState)) {
            return false;
        }

        return this.isValidVibration(gameEvent, context);
    }

    private boolean isValidVibration(Holder<GameEvent> gameEvent, @Nullable GameEvent.Context context) {
        if (context == null) {
            return true;
        }

        Entity entity = context.sourceEntity();

        if (entity != null) {
            if (entity.isSpectator()) {
                return false;
            }

            if (entity.isSteppingCarefully() && gameEvent.is(GameEventTags.IGNORE_VIBRATIONS_SNEAKING)) {
                return false;
            }

            if (entity.dampensVibrations()) {
                return false;
            }
        }

        BlockState affectedState = context.affectedState();
        return affectedState == null || !affectedState.is(BlockTags.DAMPENS_VIBRATIONS);
    }

    private void onReceiveVibration(ServerLevel level, BlockPos sourcePos, Holder<GameEvent> gameEvent, @Nullable Entity sourceEntity, @Nullable Entity projectileOwner, float distance) {
        if (gameEvent.is(GameEvent.ENTITY_DIE)) {
            if (this.handleEntityDeath(level, sourcePos, sourceEntity)) {
                return;
            }
        }

        BlockState blockState = this.getBlockState();

        this.trySummon(level, sourceEntity, projectileOwner);
        this.lastVibrationFrequency = VibrationSystem.getGameEventFrequency(gameEvent);

        MiniXellesBlock.activate(
                sourceEntity,
                level,
                this.worldPosition,
                blockState,
                VibrationSystem.getRedstoneStrengthForDistance(distance, LISTENER_RADIUS)
        );
    }

    private boolean handleEntityDeath(ServerLevel level, BlockPos sourcePos, @Nullable Entity sourceEntity) {
        if (!(sourceEntity instanceof LivingEntity livingEntity)) {
            return false;
        }

        if (livingEntity.wasExperienceConsumed()) {
            return false;
        }

        int experience = livingEntity.getExperienceReward(level, livingEntity.getLastHurtByMob());

        if (livingEntity.shouldDropExperience() && experience > 0) {
            this.krawlSpreader.addCursors(sourcePos.above(), experience);
        }

        livingEntity.skipDropExperience();
        MiniXellesBlock.bloom(level, this.worldPosition, this.getBlockState(), level.getRandom());
        return true;
    }

    public void trySummon(ServerLevel level, @Nullable Entity sourceEntity, @Nullable Entity projectileOwner) {
        if (sourceEntity instanceof EntityKrawl) {
            return;
        }

        ServerPlayer player = tryGetPlayer(projectileOwner != null ? projectileOwner : sourceEntity);

        if (player != null) {
            this.shriek(level);
            this.trySummonKrawl(level);
        }
    }

    private void shriek(ServerLevel level) {
        BlockPos pos = this.getBlockPos();
        BlockState blockState = this.getBlockState();

        level.setBlock(pos, blockState.setValue(MiniXellesBlock.SUMMONING, true), 2);
        level.scheduleTick(pos, blockState.getBlock(), 90);
        level.levelEvent(3007, pos, 0);
    }

    private boolean canRespond(ServerLevel level) {
        return this.getBlockState().getValue(MiniXellesBlock.CAN_SUMMON) && level.getDifficulty() != Difficulty.PEACEFUL;
    }

    public void tryRespond(ServerLevel level) {
        if (this.canRespond(level)) {
            this.trySummonKrawl(level);
        }
    }

    private boolean trySummonKrawl(ServerLevel level) {
        if (this.summonDelay > 0) {
            return false;
        }

        EntityVortex vortex = KrawlEntities.ENTITY_VORTEX.get().spawn(
                level,
                null,
                null,
                this.worldPosition.relative(Direction.UP, 1),
                MobSpawnType.MOB_SUMMONED,
                false,
                false
        );

        this.summonDelay = SUMMON_DELAY_TICKS;
        return vortex != null;
    }

    @Nullable
    public static ServerPlayer tryGetPlayer(@Nullable Entity entity) {
        if (entity instanceof ServerPlayer serverPlayer) {
            return serverPlayer;
        }

        if (entity != null) {
            Entity controllingPassenger = entity.getControllingPassenger();

            if (controllingPassenger instanceof ServerPlayer serverPlayer) {
                return serverPlayer;
            }
        }

        if (entity instanceof Projectile projectile && projectile.getOwner() instanceof ServerPlayer serverPlayer) {
            return serverPlayer;
        }

        if (entity instanceof ItemEntity itemEntity && itemEntity.getOwner() instanceof ServerPlayer serverPlayer) {
            return serverPlayer;
        }

        if (entity instanceof EntitySpectrobe spectrobe && spectrobe.getOwner() instanceof ServerPlayer serverPlayer) {
            return serverPlayer;
        }

        return null;
    }

    @VisibleForTesting
    public KrawlSpreader getKrawlSpreader() {
        return this.krawlSpreader;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, state -> {
            state.setAnimation(IDLE_ANIMATION);
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    private class MiniXellesVibrationUser implements VibrationSystem.User {
        private final PositionSource positionSource = new BlockPositionSource(MiniXellesBlockEntity.this.worldPosition);

        @Override
        public int getListenerRadius() {
            return LISTENER_RADIUS;
        }

        @Override
        public PositionSource getPositionSource() {
            return this.positionSource;
        }

        @Override
        public TagKey<GameEvent> getListenableEvents() {
            return GameEventTags.VIBRATIONS;
        }

        @Override
        public boolean canTriggerAvoidVibration() {
            return true;
        }

        @Override
        public boolean requiresAdjacentChunksToBeTicking() {
            return true;
        }

        @Override
        public boolean canReceiveVibration(ServerLevel level, BlockPos sourcePos, Holder<GameEvent> gameEvent, GameEvent.Context context) {
            return MiniXellesBlockEntity.this.canReceiveVibration(level, sourcePos, gameEvent, context);
        }

        @Override
        public void onReceiveVibration(ServerLevel level, BlockPos sourcePos, Holder<GameEvent> gameEvent, @Nullable Entity sourceEntity, @Nullable Entity projectileOwner, float distance) {
            MiniXellesBlockEntity.this.onReceiveVibration(level, sourcePos, gameEvent, sourceEntity, projectileOwner, distance);
        }

        @Override
        public void onDataChanged() {
            MiniXellesBlockEntity.this.setChanged();
        }
    }
}