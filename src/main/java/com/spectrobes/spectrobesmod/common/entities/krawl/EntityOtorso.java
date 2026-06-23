package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.KrawlRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

import java.util.List;

public class EntityOtorso extends EntityBossKrawl {
    private static final String TAG_LAST_HURT_TICKS = "LAST_HURT_TICKS";

    private static final RawAnimation WALK_ANIMATION =
            RawAnimation.begin().thenLoop("animation.otorso.walk");

    private static final RawAnimation ATTACK_ANIMATION =
            RawAnimation.begin().thenLoop("animation.otorso.attack");

    private static final EntityDataAccessor<Integer> LAST_HURT_TICKS =
            SynchedEntityData.defineId(EntityOtorso.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> IS_ATTACKING =
            SynchedEntityData.defineId(EntityOtorso.class, EntityDataSerializers.BOOLEAN);

    public EntityOtorso(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.setPersistenceRequired();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new OtorsoSpawnKrawlBossGroupGoal(this));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(LAST_HURT_TICKS, 1000);
        builder.define(IS_ATTACKING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt(TAG_LAST_HURT_TICKS, this.entityData.get(LAST_HURT_TICKS));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(LAST_HURT_TICKS, compound.getInt(TAG_LAST_HURT_TICKS));
    }

    @Override
    protected void actuallyHurt(DamageSource damageSource, float damageAmount) {
        if (damageSource.is(DamageTypes.CRAMMING)) {
            return;
        }

        super.actuallyHurt(damageSource, damageAmount);
        this.entityData.set(LAST_HURT_TICKS, 0);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide()) {
            this.entityData.set(LAST_HURT_TICKS, this.entityData.get(LAST_HURT_TICKS) + 1);
        }
    }

    public boolean isAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }

    @Override
    public void setIsAttacking(boolean attacking) {
        this.entityData.set(IS_ATTACKING, attacking);
        super.setIsAttacking(attacking);
    }

    public int lastHurtTicksAgo() {
        return this.entityData.get(LAST_HURT_TICKS);
    }

    private boolean canSpawnAid() {
        List<EntityOrbix> existingOrbix = this.level().getEntities(
                KrawlEntities.ENTITY_ORBIX.get(),
                this.getBoundingBox().inflate(40.0D),
                entity -> true
        );

        List<EntityOrbux> existingOrbux = this.level().getEntities(
                KrawlEntities.ENTITY_ORBUX.get(),
                this.getBoundingBox().inflate(40.0D),
                entity -> true
        );

        return existingOrbix.isEmpty() || existingOrbux.isEmpty();
    }

    @Override
    public BossEvent.BossBarColor getBossNameColour() {
        return BossEvent.BossBarColor.RED;
    }

    @Override
    public KrawlProperties GetKrawlProperties() {
        return KrawlRegistry.Otorso_Properties.copy();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationControllers;
    }

    @Override
    public PlayState moveController(AnimationState<EntityKrawl> animationState) {
        if (this.getDeltaMovement().lengthSqr() > 1.0E-6D) {
            return animationState.setAndContinue(WALK_ANIMATION);
        }

        if (this.isAttacking()) {
            return animationState.setAndContinue(ATTACK_ANIMATION);
        }

        return PlayState.STOP;
    }

    @Override
    public double getTick(Object object) {
        return this.tickCount;
    }

    private static class OtorsoSpawnKrawlBossGroupGoal extends Goal {
        private final EntityOtorso mob;

        private OtorsoSpawnKrawlBossGroupGoal(EntityOtorso mob) {
            this.mob = mob;
        }

        @Override
        public boolean canUse() {
            return !this.mob.level().isClientSide()
                    && this.mob.getTarget() != null
                    && this.mob.canSpawnAid();
        }

        @Override
        public void start() {
            if (!(this.mob.level() instanceof ServerLevel serverLevel)) {
                return;
            }

            List<EntityOrbix> existingOrbix = serverLevel.getEntities(
                    KrawlEntities.ENTITY_ORBIX.get(),
                    this.mob.getBoundingBox().inflate(40.0D),
                    entity -> true
            );

            if (existingOrbix.isEmpty()) {
                EntityOrbix orbix = KrawlEntities.ENTITY_ORBIX.get().spawn(
                        serverLevel,
                        this.mob.blockPosition(),
                        MobSpawnType.MOB_SUMMONED
                );

                if (orbix != null) {
                    orbix.setDeltaMovement(0.0D, 0.5D, 0.0D);
                }
            }

            List<EntityOrbux> existingOrbux = serverLevel.getEntities(
                    KrawlEntities.ENTITY_ORBUX.get(),
                    this.mob.getBoundingBox().inflate(40.0D),
                    entity -> true
            );

            if (existingOrbux.isEmpty()) {
                EntityOrbux orbux = KrawlEntities.ENTITY_ORBUX.get().spawn(
                        serverLevel,
                        this.mob.blockPosition(),
                        MobSpawnType.MOB_SUMMONED
                );

                if (orbux != null) {
                    orbux.setDeltaMovement(0.0D, 0.5D, 0.0D);
                }
            }
        }
    }
}