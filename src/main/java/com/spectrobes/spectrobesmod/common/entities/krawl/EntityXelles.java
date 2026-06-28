package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AbsorbKrawlGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeMasterGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.HealKrawlGoal;
import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import com.spectrobes.spectrobesmod.common.save_data.KrawlNest;
import com.spectrobes.spectrobesmod.common.save_data.SpectrobesWorldSaveData;
import com.spectrobes.spectrobesmod.util.KrawlPropertiesBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.Animation;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

import java.util.List;

import static com.spectrobes.spectrobesmod.common.entities.krawl.EntitySpawningSpore.BOSS_SPORE;

public class EntityXelles extends EntityBossKrawl {

    private static final RawAnimation DEATH_ANIMATION =
            RawAnimation.begin().then("animation.xelles.death", Animation.LoopType.PLAY_ONCE);

    private static final RawAnimation SPAWNING_ANIMATION =
            RawAnimation.begin().then("animation.xelles.spawning", Animation.LoopType.LOOP);

    private static final RawAnimation HURT_ANIMATION =
            RawAnimation.begin().then("animation.xelles.hurt", Animation.LoopType.PLAY_ONCE);

    private static final RawAnimation IDLE_ANIMATION =
            RawAnimation.begin().then("animation.xelles.idle", Animation.LoopType.LOOP);

    private static final EntityDataAccessor<Integer> STAGE =
            SynchedEntityData.defineId(EntityXelles.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> AGE_IN_TICKS =
            SynchedEntityData.defineId(EntityXelles.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> LAST_HURT_TICKS =
            SynchedEntityData.defineId(EntityXelles.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> LAST_SPAWNED_HEALING_SPORES_TICKS =
            SynchedEntityData.defineId(EntityXelles.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> LAST_SPAWNED_SUMMONING_SPORES_TICKS =
            SynchedEntityData.defineId(EntityXelles.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS =
            SynchedEntityData.defineId(EntityXelles.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> IS_SPAWNING_SPORES =
            SynchedEntityData.defineId(EntityXelles.class, EntityDataSerializers.BOOLEAN);

    public EntityXelles(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        setPersistenceRequired();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AttackSpectrobeMasterGoal(this, true, true));
        this.goalSelector.addGoal(0, new AttackSpectrobeGoal(this, true, true));
        this.goalSelector.addGoal(1, new HealKrawlGoal(this));
        this.goalSelector.addGoal(1, new XellesSpawnKrawlGroupGoal(this));
        this.goalSelector.addGoal(1, new XellesSpawnKrawlBossGoal(this));
        this.goalSelector.addGoal(1, new AbsorbKrawlGoal(this));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            MobSpawnType reason,
            @Nullable SpawnGroupData spawnData
    ) {
        if (reason == MobSpawnType.COMMAND && level instanceof ServerLevel serverLevel) {
            SpectrobesWorldSaveData worldData = SpectrobesWorldSaveData.getWorldData(serverLevel);
            worldData.addNest(new KrawlNest(blockPosition(), level().dimension().location().toString(), getUUID()));
        }

        spawnMiniXelles(level);

        return super.finalizeSpawn(level, difficulty, reason, spawnData);
    }

    private void spawnMiniXelles(ServerLevelAccessor level) {
        int xellesToCreate = getRandom().nextIntBetweenInclusive(2, 4);

        for (Direction direction : Direction.allShuffled(getRandom())) {
            if (xellesToCreate <= 0) {
                return;
            }

            if (direction == Direction.UP || direction == Direction.DOWN) {
                continue;
            }

            BlockPos initialPosition = blockPosition()
                    .relative(direction, getRandom().nextIntBetweenInclusive(6, 10))
                    .immutable();

            BlockPos placementPosition = findMiniXellesPlacement(level, initialPosition);

            if (placementPosition == null) {
                continue;
            }

            level.setBlock(
                    placementPosition,
                    SpectrobesBlocks.mini_xelles_block.get().defaultBlockState(),
                    3
            );

            xellesToCreate--;
        }
    }

    @Nullable
    private BlockPos findMiniXellesPlacement(ServerLevelAccessor level, BlockPos initialPosition) {
        for (int yOffset = 6; yOffset >= -6; yOffset--) {
            BlockPos potentialPosition = initialPosition.offset(0, yOffset, 0);

            if (canPlaceMiniXellesAt(level, potentialPosition)) {
                return potentialPosition.immutable();
            }
        }

        return null;
    }

    private boolean canPlaceMiniXellesAt(ServerLevelAccessor level, BlockPos position) {
        BlockPos below = position.below();

        return level.getBlockState(position).isAir()
                && level.getBlockState(position.above()).isAir()
                && level.getBlockState(below).isFaceSturdy(level, below, Direction.UP);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(AGE_IN_TICKS, 0);
        builder.define(STAGE, 1);
        builder.define(LAST_SPAWNED_SUMMONING_SPORES_TICKS, 0);
        builder.define(LAST_SPAWNED_HEALING_SPORES_TICKS, 0);
        builder.define(LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS, 24000);
        builder.define(LAST_HURT_TICKS, 1000);
        builder.define(IS_SPAWNING_SPORES, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("AGE_IN_TICKS", entityData.get(AGE_IN_TICKS));
        compound.putInt("STAGE", entityData.get(STAGE));
        compound.putInt("LAST_SPAWNED_SUMMONING_SPORES_TICKS", entityData.get(LAST_SPAWNED_SUMMONING_SPORES_TICKS));
        compound.putInt("LAST_SPAWNED_HEALING_SPORES_TICKS", entityData.get(LAST_SPAWNED_HEALING_SPORES_TICKS));
        compound.putInt("LAST_SPAWNED_BOSS_SPORES_TICKS", entityData.get(LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS));
        compound.putInt("LAST_HURT_TICKS", entityData.get(LAST_HURT_TICKS));
        compound.putBoolean("IS_SPAWNING_SPORES", entityData.get(IS_SPAWNING_SPORES));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        entityData.set(AGE_IN_TICKS, compound.getInt("AGE_IN_TICKS"));
        entityData.set(STAGE, compound.getInt("STAGE"));
        refreshBossBarName();
        entityData.set(LAST_SPAWNED_SUMMONING_SPORES_TICKS, compound.getInt("LAST_SPAWNED_SUMMONING_SPORES_TICKS"));
        entityData.set(LAST_SPAWNED_HEALING_SPORES_TICKS, compound.getInt("LAST_SPAWNED_HEALING_SPORES_TICKS"));
        entityData.set(LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS, compound.getInt("LAST_SPAWNED_BOSS_SPORES_TICKS"));
        entityData.set(LAST_HURT_TICKS, compound.getInt("LAST_HURT_TICKS"));
        entityData.set(IS_SPAWNING_SPORES, compound.getBoolean("IS_SPAWNING_SPORES"));
    }

    @Override
    protected void actuallyHurt(DamageSource damageSource, float damageAmount) {
        if (!damageSource.is(DamageTypes.CRAMMING)) {
            AABB bounds = getBoundingBox().inflate(40.0D, 40.0D, 40.0D);
            List<EntityOtorso> otorso = level().getEntities(
                    KrawlEntities.ENTITY_OTORSO.get(),
                    bounds,
                    entity -> true
            );

            if (otorso.isEmpty()) {
                super.actuallyHurt(damageSource, damageAmount);
            }

            entityData.set(LAST_HURT_TICKS, 0);
        }
    }

    @Override
    public Vec3 getDeltaMovement() {
        return Vec3.ZERO;
    }

    @Override
    public void tick() {
        super.tick();

        entityData.set(AGE_IN_TICKS, entityData.get(AGE_IN_TICKS) + 1);
        entityData.set(LAST_HURT_TICKS, entityData.get(LAST_HURT_TICKS) + 1);
        entityData.set(LAST_SPAWNED_SUMMONING_SPORES_TICKS, entityData.get(LAST_SPAWNED_SUMMONING_SPORES_TICKS) + 1);
        entityData.set(LAST_SPAWNED_HEALING_SPORES_TICKS, entityData.get(LAST_SPAWNED_HEALING_SPORES_TICKS) + 1);
        entityData.set(LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS, entityData.get(LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS) + 1);

        if (level().isClientSide()) {
            return;
        }

        SpectrobesWorldSaveData worldData = SpectrobesWorldSaveData.getWorldData((ServerLevel) level());
        KrawlNest nest = worldData.getNest(blockPosition());

        if (nest == null) {
            return;
        }

        setStage(nest.stage);

        if (getStage() == 1 && (nest.vortex_absorbed > 5 || getAge() >= 5)) {
            nest.stage = 2;
            setStage(2);
            worldData.setDirty();
        }

        if (getAge() >= 3 && nest.stage == 2) {
            nest.stage = 3;
            setStage(3);
            worldData.setDirty();
        }

        if (getAge() >= 3) {
            AABB bounds = getBoundingBox().inflate(40.0D, 40.0D, 40.0D);
            List<EntityVortex> nearbyVortex = level().getEntities(
                    KrawlEntities.ENTITY_VORTEX.get(),
                    bounds,
                    entityVortex -> true
            );

            nearbyVortex.forEach(entityVortex -> entityVortex.remove(RemovalReason.DISCARDED));
        }
    }

    private void setStage(int stage) {
        int oldStage = entityData.get(STAGE);

        if (oldStage == stage) {
            return;
        }

        entityData.set(STAGE, stage);
        refreshBossBarName();
    }

    private void refreshBossBarName() {
        if (!level().isClientSide()) {
            updateBossBarName(getDisplayName());
        }
    }

    @Override
    public Component getName() {
        return Component.literal(super.getName().getString() + " - Stage: " + getStage());
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);

        if (!level().isClientSide()) {
            SpectrobesWorldSaveData saveData = SpectrobesWorldSaveData.getWorldData((ServerLevel) level());
            KrawlNest nest = saveData.getNest(blockPosition());

            if (nest != null) {
                nest.setDead();
                saveData.setDirty();
            }
        }

        if (getStage() == 3) {
            int mineralCount = getRandom().nextInt(3) + 3;

            for (int i = 0; i < mineralCount; i++) {
                ItemStack mineralStack = SpectrobesMineralsRegistry.getRandomMineral(Mineral.MineralRarity.Rare);

                ItemEntity minerals = new ItemEntity(
                        level(),
                        this.getX() + 0.5D,
                        this.getY() + 1.0D,
                        this.getZ() + 0.5D,
                        mineralStack
                );

                minerals.setDefaultPickUpDelay();
                level().addFreshEntity(minerals);
            }

            ItemEntity trophy = new ItemEntity(
                    level(),
                    this.getX() + 0.5D,
                    this.getY() + 1.0D,
                    this.getZ() + 0.5D,
                    SpectrobesBlocks.xelles_trophy.get().asItem().getDefaultInstance()
            );

            trophy.setDefaultPickUpDelay();
            level().addFreshEntity(trophy);
        }
    }

    public boolean isSpawningSpores() {
        return entityData.get(IS_SPAWNING_SPORES);
    }

    public void setIsSpawningSpores(boolean isSpawningSpores) {
        entityData.set(IS_SPAWNING_SPORES, isSpawningSpores);
    }

    public int lastHurtTicksAgo() {
        return entityData.get(LAST_HURT_TICKS);
    }

    public int getAge() {
        return entityData.get(AGE_IN_TICKS) / 24000;
    }

    public int getStage() {
        return entityData.get(STAGE);
    }

    public boolean canSpawnHealSpores() {
        return entityData.get(LAST_SPAWNED_HEALING_SPORES_TICKS) >= 600;
    }

    public boolean canSpawnBossSpore() {
        AABB searchBox = getBoundingBox().inflate(50.0D, 50.0D, 50.0D);
        List<EntityOrbix> nearbyBosses = level().getEntities(
                KrawlEntities.ENTITY_ORBIX.get(),
                searchBox,
                entityOrbix -> true
        );

        boolean hasSpawnedBoss = !nearbyBosses.isEmpty();

        return getStage() > 1
                && entityData.get(LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS) >= 24000
                && !hasSpawnedBoss;
    }

    private boolean canSummonWave() {
        return entityData.get(LAST_SPAWNED_SUMMONING_SPORES_TICKS) >= 1200;
    }

    private int waveSize() {
        return switch (getStage()) {
            case 2 -> 2;
            case 3 -> 4;
            default -> 0;
        };
    }

    public void spawnHealingSpores(List<EntityKrawl> targets) {
        entityData.set(LAST_SPAWNED_HEALING_SPORES_TICKS, 0);
        setIsSpawningSpores(true);

        if (!level().isClientSide()) {
            for (EntityKrawl krawl : targets) {
                EntityHealingSpore spore = KrawlEntities.ENTITY_HEALING_SPORES.get()
                        .spawn(
                                (ServerLevel) level(),
                                null,
                                null,
                                blockPosition(),
                                MobSpawnType.MOB_SUMMONED,
                                false,
                                false
                        );

                if (spore != null) {
                    spore.setTarget(krawl);
                    spore.setDeltaMovement(0.0D, 0.5D, 0.0D);
                }
            }
        }

        setIsSpawningSpores(false);
    }

    @Override
    public BossEvent.BossBarColor getBossNameColour() {
        return BossEvent.BossBarColor.PURPLE;
    }

    @Override
    public KrawlProperties GetKrawlProperties() {
        return new KrawlPropertiesBuilder()
                .withGuraWorth(2000)
                .withXpWorth(1000)
                .withAtkLevel(0)
                .withDefLevel(0)
                .withHpLevel(800)
                .withAtkOffset(10)
                .withDefOffset(10)
                .withHpOffset(10)
                .withLevel(30)
                .build();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animationControllers;
    }

    @Override
    public PlayState moveController(AnimationState<EntityKrawl> animationState) {
        EntityKrawl animatable = animationState.getAnimatable();

        if (animatable.isDeadOrDying()) {
            animationState.getController().setAnimation(DEATH_ANIMATION);
            return PlayState.CONTINUE;
        }

        EntityXelles xelles = (EntityXelles) animatable;

        if (xelles.isSpawningSpores()) {
            animationState.getController().setAnimation(SPAWNING_ANIMATION);
            return PlayState.CONTINUE;
        }

        if (xelles.lastHurtTicksAgo() == 0) {
            animationState.getController().forceAnimationReset();
            animationState.getController().setAnimation(HURT_ANIMATION);
            return PlayState.CONTINUE;
        }

        animationState.getController().setAnimation(IDLE_ANIMATION);
        return PlayState.CONTINUE;
    }

    private static class XellesSpawnKrawlGroupGoal extends Goal {

        private final EntityXelles mob;

        public XellesSpawnKrawlGroupGoal(EntityXelles xelles) {
            this.mob = xelles;
        }

        @Override
        public boolean canUse() {
            return mob.getTarget() != null && mob.canSummonWave();
        }

        @Override
        public void start() {
            super.start();

            int numToSpawn = mob.waveSize();
            mob.setIsSpawningSpores(true);

            if (!mob.level().isClientSide()) {
                for (int i = 0; i < numToSpawn; i++) {
                    EntitySpawningSpore spore = KrawlEntities.ENTITY_SPAWNING_SPORE.get()
                            .spawn(
                                    (ServerLevel) mob.level(),
                                    null,
                                    null,
                                    mob.blockPosition(),
                                    MobSpawnType.MOB_SUMMONED,
                                    false,
                                    false
                            );

                    if (spore != null) {
                        spore.setDeltaMovement(0.0D, 0.5D, 0.0D);
                    }
                }

                mob.entityData.set(LAST_SPAWNED_SUMMONING_SPORES_TICKS, 0);
            }

            mob.setIsSpawningSpores(false);
        }
    }

    private static class XellesSpawnKrawlBossGoal extends Goal {

        private final EntityXelles mob;

        public XellesSpawnKrawlBossGoal(EntityXelles xelles) {
            this.mob = xelles;
        }

        @Override
        public boolean canUse() {
            return mob.getTarget() != null && mob.canSpawnBossSpore();
        }

        @Override
        public void start() {
            super.start();

            mob.setIsSpawningSpores(true);

            if (!mob.level().isClientSide()) {
                EntitySpawningSpore spore = KrawlEntities.ENTITY_SPAWNING_SPORE.get()
                        .spawn(
                                (ServerLevel) mob.level(),
                                null,
                                null,
                                mob.blockPosition(),
                                MobSpawnType.MOB_SUMMONED,
                                false,
                                false
                        );

                if (spore != null) {
                    spore.getEntityData().set(BOSS_SPORE, true);
                    spore.setDeltaMovement(0.0D, 0.5D, 0.0D);
                }

                mob.entityData.set(LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS, 0);
            }

            mob.setIsSpawningSpores(false);
        }
    }
}