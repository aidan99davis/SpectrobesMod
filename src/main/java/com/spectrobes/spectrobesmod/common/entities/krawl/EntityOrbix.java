package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeMasterGoal;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.KrawlRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.ParametersAreNonnullByDefault;

public class EntityOrbix extends EntityBossKrawl {

    private static final EntityDataAccessor<Integer> LAST_HURT_TICKS =
            SynchedEntityData.defineId(EntityOrbix.class,
                    EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> IS_ATTACKING =
            SynchedEntityData.defineId(EntityOrbix.class,
                    EntityDataSerializers.BOOLEAN);

    public EntityOrbix(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
        setPersistenceRequired();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new AttackSpectrobeMasterGoal(this, true, true));
        this.goalSelector.addGoal(0, new AttackSpectrobeGoal(this, true, true));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(LAST_HURT_TICKS, 1000);
        entityData.define(IS_ATTACKING, false);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("LAST_HURT_TICKS", entityData.get(LAST_HURT_TICKS));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        entityData.set(LAST_HURT_TICKS, pCompound.getInt("LAST_HURT_TICKS"));
    }

    @Override
    protected void actuallyHurt(DamageSource damageSrc, float damageAmount) {
        if(damageSrc != DamageSource.CRAMMING) {
            SpectrobesInfo.LOGGER.debug(damageSrc);
            super.actuallyHurt(damageSrc, damageAmount);
            entityData.set(LAST_HURT_TICKS, 0);
        }
    }

    @Override
    public void setIsAttacking(boolean attacking) {
        entityData.set(IS_ATTACKING, attacking);
        super.setIsAttacking(attacking);
    }

    public boolean isAttacking() {return entityData.get(IS_ATTACKING); }

    @Override
    public BossEvent.BossBarColor getBossNameColour() {
        return BossEvent.BossBarColor.GREEN;
    }

    @Override
    public KrawlProperties GetKrawlProperties() {
        return KrawlRegistry.Orbix_Properties.copy();
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntityKrawl> PlayState moveController(AnimationEvent<ENTITY> event) {
        if(this.isAttacking()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.orbix.idle", ILoopType.EDefaultLoopTypes.LOOP).addAnimation("animation.orbix.attack"));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.orbix.idle", ILoopType.EDefaultLoopTypes.LOOP));
        }
        return PlayState.CONTINUE;
    }
}
