package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeMasterGoal;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.KrawlRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class EntityOtorso extends EntityBossKrawl {

    private static final DataParameter<Integer> LAST_HURT_TICKS =
            EntityDataManager.defineId(EntityOtorso.class,
                    DataSerializers.INT);

    private static final DataParameter<Boolean> IS_ATTACKING =
            EntityDataManager.defineId(EntityOtorso.class,
                    DataSerializers.BOOLEAN);

    public EntityOtorso(EntityType<? extends MonsterEntity> type, World worldIn) {
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
    public void addAdditionalSaveData(CompoundNBT pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("LAST_HURT_TICKS", entityData.get(LAST_HURT_TICKS));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT pCompound) {
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
    public BossInfo.Color getBossNameColour() {
        return BossInfo.Color.RED;
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
        if(event.getAnimatable().getDeltaMovement() != Vector3d.ZERO) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.otorso.walk", true));
            return PlayState.CONTINUE;
        } else if(((EntityOtorso)event.getAnimatable()).isAttacking()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.otorso.attack", true));
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
                        entityOrbix.getDisplayName().getContents() == "Orbix")) {
                    orbix = (EntityOrbix) KrawlEntities.ENTITY_ORBIX.get()
                            .spawn((ServerWorld) mob.level,
                                    null,
                                    null,
                                    mob.blockPosition(),
                                    SpawnReason.MOB_SUMMONED,
                                    false,
                                    false);
                    orbix.setDeltaMovement(0, 0.5, 0);

                }
                List<? extends EntityOrbix> existingOrbux = mob.level.getEntities(KrawlEntities.ENTITY_ORBUX.get(), mob.getBoundingBox().inflate(40, 40, 40), entity -> true);
                if(existingOrbux.size() == 0
                        || !existingOrbux.stream().anyMatch(entityOrbux ->
                        entityOrbux.getDisplayName().getContents() == "Orbux")) {
                    orbux = (EntityOrbux) KrawlEntities.ENTITY_ORBUX.get()
                            .spawn((ServerWorld) mob.level,
                                    null,
                                    null,
                                    mob.blockPosition(),
                                    SpawnReason.MOB_SUMMONED,
                                    false,
                                    false);
                    orbux.setDeltaMovement(0, 0.5, 0);
                }
            }
        }
    }

}
