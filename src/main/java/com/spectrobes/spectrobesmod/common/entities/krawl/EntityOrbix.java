package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AbsorbKrawlGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeMasterGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.HealKrawlGoal;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.KrawlRegistry;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.save_data.SpectrobesWorldSaveData;
import com.spectrobes.spectrobesmod.util.KrawlPropertiesBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
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

public class EntityOrbix extends EntityBossKrawl {

    private static final DataParameter<Integer> LAST_HURT_TICKS =
            EntityDataManager.defineId(EntityOrbix.class,
                    DataSerializers.INT);

    private static final DataParameter<Boolean> IS_ATTACKING =
            EntityDataManager.defineId(EntityOrbix.class,
                    DataSerializers.BOOLEAN);

    public EntityOrbix(EntityType<? extends MonsterEntity> type, World worldIn) {
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
    public BossInfo.Color getBossNameColour() {
        return BossInfo.Color.GREEN;
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
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.orbix.idle", true).addAnimation("animation.orbix.attack"));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.orbix.idle", true));
        }
        return PlayState.CONTINUE;
    }
}
