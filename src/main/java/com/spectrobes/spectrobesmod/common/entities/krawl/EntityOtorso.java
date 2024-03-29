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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class EntityOtorso extends EntityBossKrawl {

    private static final EntityDataAccessor<Integer> LAST_HURT_TICKS =
            SynchedEntityData.defineId(EntityOtorso.class,
                    EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> IS_ATTACKING =
            SynchedEntityData.defineId(EntityOtorso.class,
                    EntityDataSerializers.BOOLEAN);

    public EntityOtorso(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
        setPersistenceRequired();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new OtorsoSpawnKrawlBossGroupGoal(this));
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
            super.actuallyHurt(damageSrc, damageAmount);
            entityData.set(LAST_HURT_TICKS, 0);
        }
    }

    @Override
    public void tick() {
        super.tick();
        entityData.set(LAST_HURT_TICKS, entityData.get(LAST_HURT_TICKS) + 1);
    }

    public boolean isAttacking() {
        return entityData.get(IS_ATTACKING);
    }

    @Override
    public void setIsAttacking(boolean attacking) {
        entityData.set(IS_ATTACKING, attacking);
        super.setIsAttacking(attacking);
    }

    public int lastHurtTicksAgo() {return entityData.get(LAST_HURT_TICKS); }

    private boolean canSpawnAid() {
        List<? extends EntityOrbix> existingOrbix = level.getEntities(KrawlEntities.ENTITY_ORBIX.get(), getBoundingBox().inflate(40, 40, 40), entity -> true);
        List<? extends EntityOrbux> existingOrbux = level.getEntities(KrawlEntities.ENTITY_ORBUX.get(), getBoundingBox().inflate(40, 40, 40), entity -> true);
        return existingOrbux.size() == 0 || existingOrbix.size() == 0;
    }

    @Override
    public BossEvent.BossBarColor getBossNameColour() {
        return BossEvent.BossBarColor.RED;
    }

    @Override
    public KrawlProperties GetKrawlProperties() {
        return KrawlRegistry.Otorso_Properties;
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntityKrawl> PlayState moveController(AnimationEvent<ENTITY> event) {
        if(event.getAnimatable().getDeltaMovement() != Vec3.ZERO) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.otorso.walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        } else if(((EntityOtorso)event.getAnimatable()).isAttacking()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.otorso.attack", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    private static class OtorsoSpawnKrawlBossGroupGoal extends Goal {
        EntityOtorso mob;

        public OtorsoSpawnKrawlBossGroupGoal(EntityOtorso xelles) {
            mob = xelles;
        }

        @Override
        public boolean canUse() {
            return mob.getTarget() != null && mob.canSpawnAid();
        }

        @Override
        public void start() {
            super.start();

            if(!mob.level.isClientSide()) {
                EntityOrbix orbix;
                EntityOrbux orbux;
                List<? extends EntityOrbix> existingOrbix = mob.level.getEntities(KrawlEntities.ENTITY_ORBIX.get(), mob.getBoundingBox().inflate(40, 40, 40), entity -> true);
                if(existingOrbix.size() == 0
                    || !existingOrbix.stream().anyMatch(entityOrbix ->
                        entityOrbix.getDisplayName().toString() == "Orbix")) {
                    orbix = (EntityOrbix) KrawlEntities.ENTITY_ORBIX.get()
                            .spawn((ServerLevel) mob.level,
                                    null,
                                    null,
                                    mob.blockPosition(),
                                    MobSpawnType.MOB_SUMMONED,
                                    false,
                                    false);
                    orbix.setDeltaMovement(0, 0.5, 0);

                }
                List<? extends EntityOrbix> existingOrbux = mob.level.getEntities(KrawlEntities.ENTITY_ORBUX.get(), mob.getBoundingBox().inflate(40, 40, 40), entity -> true);
                if(existingOrbux.size() == 0
                        || !existingOrbux.stream().anyMatch(entityOrbux ->
                        entityOrbux.getDisplayName().toString() == "Orbux")) {
                    orbux = (EntityOrbux) KrawlEntities.ENTITY_ORBUX.get()
                            .spawn((ServerLevel) mob.level,
                                    null,
                                    null,
                                    mob.blockPosition(),
                                    MobSpawnType.MOB_SUMMONED,
                                    false,
                                    false);
                    orbux.setDeltaMovement(0, 0.5, 0);
                }
            }
        }
    }

}
