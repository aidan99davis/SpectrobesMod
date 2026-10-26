package com.spectrobes.spectrobesmod.common.blocks.krawl;

import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.config.KrawlConfig;
import com.spectrobes.spectrobesmod.common.krawl.KrawlInfectionManager;
import com.spectrobes.spectrobesmod.common.save_data.KrawlNest;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.GameEventTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
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
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

public class MiniXellesBlockEntity extends BlockEntity implements GameEventListener.Provider<VibrationSystem.Listener>, VibrationSystem, GeoBlockEntity {
    private static final int LISTENER_RADIUS = 8;

    private static final RawAnimation IDLE_ANIMATION = RawAnimation.begin().thenLoop("animation.xelles.idle");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private final VibrationSystem.User vibrationUser = new MiniXellesVibrationUser();
    private final VibrationSystem.Listener vibrationListener = new VibrationSystem.Listener(this);
    private VibrationSystem.Data vibrationData = new VibrationSystem.Data();

    private int lastVibrationFrequency;
    private int warningLevel;
    private int summonDelay;

    /** The nest this mini xelles guards, or null if it was placed by hand. */
    @Nullable
    private UUID nestId;
    private int tier;
    /** Waves still to be wiped out before this mini xelles dies; -1 until bound. */
    private int wavesRemaining = -1;
    /** Living krawl of the wave currently out. */
    private final Set<UUID> wave = new LinkedHashSet<>();
    private int waveKilled;
    private int waveReturned;
    /** Game time this mini xelles was last triggered by a player. */
    private long lastTriggered;
    /** Game time the current wave's krawl were last found loaded in the world. */
    private long waveLastSeen;

    public MiniXellesBlockEntity(BlockPos pos, BlockState blockState) {
        super(SpectrobesTileRegistry.MINI_XELLES_TILE.get(), pos, blockState);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        this.lastVibrationFrequency = tag.getInt("last_vibration_frequency");
        this.warningLevel = tag.getInt("warning_level");
        this.summonDelay = tag.getInt("summon_delay");
        this.nestId = tag.hasUUID("nest_id") ? tag.getUUID("nest_id") : null;
        this.tier = tag.getInt("tier");
        this.wavesRemaining = tag.contains("waves_remaining") ? tag.getInt("waves_remaining") : -1;
        this.waveKilled = tag.getInt("wave_killed");
        this.waveReturned = tag.getInt("wave_returned");
        this.lastTriggered = tag.getLong("last_triggered");
        this.wave.clear();

        for (Tag uuidTag : tag.getList("wave", Tag.TAG_INT_ARRAY)) {
            this.wave.add(NbtUtils.loadUUID(uuidTag));
        }

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

        tag.putInt("warning_level", this.warningLevel);
        tag.putInt("last_vibration_frequency", this.lastVibrationFrequency);
        tag.putInt("summon_delay", this.summonDelay);
        tag.putInt("tier", this.tier);
        tag.putInt("waves_remaining", this.wavesRemaining);
        tag.putInt("wave_killed", this.waveKilled);
        tag.putInt("wave_returned", this.waveReturned);
        tag.putLong("last_triggered", this.lastTriggered);

        ListTag waveTag = new ListTag();

        for (UUID uuid : this.wave) {
            waveTag.add(NbtUtils.createUUID(uuid));
        }

        tag.put("wave", waveTag);

        if (this.nestId != null) {
            tag.putUUID("nest_id", this.nestId);
        }

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
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        if (this.summonDelay > 0) {
            this.summonDelay--;
        }

        if (serverLevel.getGameTime() % 20 == 0) {
            checkWave(serverLevel);
        }

        if (serverLevel.getGameTime() % 100 == 0) {
            syncWithNest(serverLevel);
            checkWaveReset(serverLevel);
        }

        VibrationSystem.Ticker.tick(level, this.vibrationData, this.vibrationUser);
    }

    // ---------------------------------------------------------------- nest life cycle

    public void bindToNest(@Nullable UUID nestId, int tier) {
        this.nestId = nestId;
        this.tier = tier;

        if (this.wavesRemaining < 0) {
            this.wavesRemaining = wavesPerLife();
        }

        if (this.level != null && this.lastTriggered == 0) {
            this.lastTriggered = this.level.getGameTime();
        }

        this.setChanged();
    }

    @Nullable
    public UUID getNestId() {
        return this.nestId;
    }

    public int getTier() {
        return this.tier;
    }

    public boolean isDead() {
        return MiniXellesBlock.isDead(this.getBlockState());
    }

    public int getWavesRemaining() {
        return this.wavesRemaining;
    }

    private int wavesPerLife() {
        return KrawlConfig.MINI_XELLES_WAVES_PER_LIFE.get();
    }

    /** Minimum level of the krawl this mini xelles summons: deeper chambers send stronger krawl. */
    private int defenderLevel() {
        return 3 + this.tier * 6;
    }

    /** Krawl in each wave: deeper chambers send more. */
    private int waveSize() {
        return Math.min(6, 2 + this.tier);
    }

    public void revive() {
        if (this.level == null) {
            return;
        }

        this.level.setBlock(this.worldPosition, this.getBlockState().setValue(MiniXellesBlock.DEAD, false), Block.UPDATE_ALL);
        this.wavesRemaining = wavesPerLife();
        clearWave();
        this.lastTriggered = this.level.getGameTime();
        this.summonDelay = 100;
        this.setChanged();
    }

    // ---------------------------------------------------------------- waves

    public boolean isWaveOut() {
        return !this.wave.isEmpty();
    }

    private void clearWave() {
        this.wave.clear();
        this.waveKilled = 0;
        this.waveReturned = 0;
    }

    /** A krawl from the current wave was killed. */
    public void onWaveKrawlKilled(ServerLevel level, UUID krawlId) {
        if (this.wave.remove(krawlId)) {
            this.waveKilled++;
            this.setChanged();
            finishWaveIfDone(level);
        }
    }

    /** A krawl from the current wave found no enemies left and came back. */
    public void onWaveKrawlReturned(ServerLevel level, UUID krawlId) {
        if (this.wave.remove(krawlId)) {
            this.waveReturned++;
            this.setChanged();
            finishWaveIfDone(level);
        }
    }

    /**
     * Once every krawl of the wave is back or dead: a wave that was wiped out costs this mini xelles one wave
     * (and its life on the last one); a wave that came back just resets.
     */
    private void finishWaveIfDone(ServerLevel level) {
        if (!this.wave.isEmpty()) {
            return;
        }

        boolean wipedOut = this.waveReturned == 0 && this.waveKilled > 0;
        clearWave();

        if (!wipedOut || isDead()) {
            return;
        }

        if (this.wavesRemaining < 0) {
            this.wavesRemaining = wavesPerLife();
        }

        this.wavesRemaining--;
        this.setChanged();

        if (this.wavesRemaining <= 0) {
            die(level);
        } else {
            level.playSound(null, this.worldPosition, SoundEvents.SCULK_SHRIEKER_BREAK, SoundSource.HOSTILE, 1.0F, 1.2F);
        }
    }

    /**
     * Wave krawl that stay unloaded or vanish without dying (for example /kill with no death, or a despawn by
     * another mod) would hold the wave open forever; after a minute without seeing any of them the wave resets.
     */
    private void checkWave(ServerLevel level) {
        if (this.wave.isEmpty()) {
            return;
        }

        long now = level.getGameTime();

        for (UUID uuid : this.wave) {
            if (level.getEntity(uuid) instanceof EntityKrawl krawl && krawl.isAlive()) {
                this.waveLastSeen = now;
                return;
            }
        }

        if (this.waveLastSeen == 0) {
            this.waveLastSeen = now;
        } else if (now - this.waveLastSeen >= 1200) {
            clearWave();
            this.setChanged();
        }
    }

    /** A living mini xelles left alone for a full day regrows the waves it lost. */
    private void checkWaveReset(ServerLevel level) {
        if (isDead() || isWaveOut() || this.wavesRemaining < 0 || this.wavesRemaining >= wavesPerLife()) {
            return;
        }

        if (level.getGameTime() - this.lastTriggered >= KrawlConfig.MINI_XELLES_WAVE_RESET_TICKS.get()) {
            this.wavesRemaining = wavesPerLife();
            this.setChanged();
        }
    }

    /** Kills this mini xelles outright (its nest's Xelles was defeated). */
    public void kill(ServerLevel level) {
        if (!isDead()) {
            die(level);
        }
    }

    private void die(ServerLevel level) {
        level.setBlock(this.worldPosition, this.getBlockState()
                .setValue(MiniXellesBlock.DEAD, true)
                .setValue(MiniXellesBlock.SUMMONING, false), Block.UPDATE_ALL);
        clearWave();
        this.setChanged();
        level.playSound(null, this.worldPosition, SoundEvents.SCULK_SHRIEKER_BREAK, SoundSource.HOSTILE, 2.0F, 0.6F);
        level.sendParticles(net.minecraft.core.particles.ParticleTypes.SCULK_SOUL,
                this.worldPosition.getX() + 0.5D, this.worldPosition.getY() + 0.5D, this.worldPosition.getZ() + 0.5D,
                20, 0.4D, 0.4D, 0.4D, 0.05D);
        KrawlInfectionManager.onMiniXellesDefeated(level, this.nestId, this.worldPosition);
    }

    /** Keeps the block in step with its nest record (e.g. it revived or was killed while this chunk was unloaded). */
    private void syncWithNest(ServerLevel level) {
        KrawlNest.MiniXellesRecord record = KrawlInfectionManager.getMiniXellesRecord(level, this.nestId, this.worldPosition);

        if (record == null) {
            return;
        }

        if (!record.dead && isDead()) {
            revive();
        } else if (record.dead && !isDead()) {
            die(level);
        }
    }

    private boolean canReceiveVibration(ServerLevel level, BlockPos sourcePos, Holder<GameEvent> gameEvent, @Nullable GameEvent.Context context) {
        if (this.isRemoved()) {
            return false;
        }

        BlockState blockState = this.getBlockState();

        if (blockState.getValue(MiniXellesBlock.SUMMONING) || MiniXellesBlock.isDead(blockState)) {
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
        if (!(sourceEntity instanceof LivingEntity) || sourceEntity instanceof EntityKrawl) {
            return false;
        }

        MiniXellesBlock.bloom(level, this.worldPosition, this.getBlockState(), level.getRandom());
        return true;
    }

    public void trySummon(ServerLevel level, @Nullable Entity sourceEntity, @Nullable Entity projectileOwner) {
        if (sourceEntity instanceof EntityKrawl) {
            return;
        }

        ServerPlayer player = tryGetPlayer(projectileOwner != null ? projectileOwner : sourceEntity);

        if (player != null && !isDead()) {
            this.lastTriggered = level.getGameTime();
            this.setChanged();
            this.shriek(level);
            this.trySummonKrawl(level, player);
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
        return trySummonKrawl(level, null);
    }

    private boolean trySummonKrawl(ServerLevel level, @Nullable ServerPlayer target) {
        if (isDead()) {
            return false;
        }

        boolean validTarget = target != null && !target.isCreative() && !target.isSpectator();

        if (isWaveOut()) {
            // The wave already out turns on whoever woke the mini xelles again.
            if (validTarget) {
                for (UUID uuid : this.wave) {
                    if (level.getEntity(uuid) instanceof EntityKrawl krawl && krawl.getTarget() == null) {
                        krawl.setTarget(target);
                    }
                }
            }

            return false;
        }

        if (this.summonDelay > 0) {
            return false;
        }

        int krawlLevel = Math.max(1, defenderLevel());

        if (target != null) {
            PlayerSpectrobeMaster master = target.getCapability(SpectrobeMaster.INSTANCE);

            if (master != null && master.getLevel() > krawlLevel) {
                krawlLevel = master.getLevel();
            }
        }

        RandomSource random = level.getRandom();
        int count = waveSize();

        for (int i = 0; i < count; i++) {
            EntityType<? extends EntityKrawl> type = KrawlEntities.getByLevel(krawlLevel, level);
            EntityKrawl krawl = type.create(level);

            if (krawl == null) {
                continue;
            }

            BlockPos spawnPos = findSpawnPos(level, type, random);
            krawl.moveTo(spawnPos.getX() + 0.5D, spawnPos.getY(), spawnPos.getZ() + 0.5D, random.nextFloat() * 360.0F, 0.0F);
            krawl.setGuarding(this.worldPosition);

            if (validTarget) {
                krawl.setTarget(target);
            }

            if (level.addFreshEntity(krawl)) {
                this.wave.add(krawl.getUUID());
                level.sendParticles(net.minecraft.core.particles.ParticleTypes.SCULK_SOUL,
                        krawl.getX(), krawl.getY() + 0.5D, krawl.getZ(), 6, 0.3D, 0.3D, 0.3D, 0.02D);
            }
        }

        this.summonDelay = KrawlConfig.MINI_XELLES_SUMMON_COOLDOWN.get();
        this.waveKilled = 0;
        this.waveReturned = 0;
        this.waveLastSeen = level.getGameTime();
        this.setChanged();
        return isWaveOut();
    }

    /** A free spot with a floor near the mini xelles, falling back to the block above it. */
    private BlockPos findSpawnPos(ServerLevel level, EntityType<?> type, RandomSource random) {
        for (int attempt = 0; attempt < 16; attempt++) {
            BlockPos candidate = this.worldPosition.offset(random.nextInt(7) - 3, random.nextInt(3) - 1, random.nextInt(7) - 3);

            if (candidate.equals(this.worldPosition)) {
                continue;
            }

            BlockPos below = candidate.below();

            if (level.getBlockState(below).isFaceSturdy(level, below, Direction.UP)
                    && level.noCollision(type.getSpawnAABB(candidate.getX() + 0.5D, candidate.getY(), candidate.getZ() + 0.5D))) {
                return candidate;
            }
        }

        return this.worldPosition.above();
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