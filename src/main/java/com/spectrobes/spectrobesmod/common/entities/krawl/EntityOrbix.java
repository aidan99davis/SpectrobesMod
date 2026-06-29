package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeMasterGoal;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.data.KrawlRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public class EntityOrbix extends EntityBossKrawl {
    private static final int DESPAWN_AFTER_UNATTACKED_TICKS = 20 * 180; // 3 minutes
    private static final String TAG_LAST_HURT_TICKS = "LAST_HURT_TICKS";

    private static final RawAnimation IDLE_ANIMATION =
            RawAnimation.begin().thenLoop("animation.orbix.idle");

    private static final RawAnimation ATTACK_ANIMATION =
            RawAnimation.begin()
                    .thenPlay("animation.orbix.attack")
                    .thenLoop("animation.orbix.idle");

    private static final EntityDataAccessor<Integer> LAST_HURT_TICKS =
            SynchedEntityData.defineId(EntityOrbix.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> IS_ATTACKING =
            SynchedEntityData.defineId(EntityOrbix.class, EntityDataSerializers.BOOLEAN);

    public EntityOrbix(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.setPersistenceRequired();
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide()) {
            this.entityData.set(LAST_HURT_TICKS, this.entityData.get(LAST_HURT_TICKS) + 1);
        }
        int lastHurtTicks = Math.min(
                this.entityData.get(LAST_HURT_TICKS) + 1,
                DESPAWN_AFTER_UNATTACKED_TICKS
        );

        this.entityData.set(LAST_HURT_TICKS, lastHurtTicks);

        if (lastHurtTicks >= DESPAWN_AFTER_UNATTACKED_TICKS) {
            this.discard();
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new AttackSpectrobeMasterGoal(this, true, true));
        this.goalSelector.addGoal(0, new AttackSpectrobeGoal(this, true, true));
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
    public void setIsAttacking(boolean attacking) {
        this.entityData.set(IS_ATTACKING, attacking);
        super.setIsAttacking(attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }

    @Override
    public BossEvent.BossBarColor getBossNameColour() {
        return BossEvent.BossBarColor.GREEN;
    }

    @Override
    public KrawlProperties GetKrawlProperties() {
        return KrawlRegistry.Orbix_Properties.copy();
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationControllers;
    }

    @Override
    public PlayState moveController(AnimationState<EntityKrawl> animationState) {
        return animationState.setAndContinue(this.isAttacking() ? ATTACK_ANIMATION : IDLE_ANIMATION);
    }

    @Override
    public double getTick(Object object) {
        return this.tickCount;
    }
}