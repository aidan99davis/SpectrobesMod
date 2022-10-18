package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.entities.krawl.goals.MoveToTargetGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.Random;

public class EntityHealingSpore extends MonsterEntity implements IAnimatable, IFlyingAnimal {
    private static final DataParameter<Integer> AGE_IN_TICKS =
            EntityDataManager.defineId(EntityHealingSpore.class,
                    DataSerializers.INT);

    public AnimationFactory animationControllers = new AnimationFactory(this);
    protected AnimationController moveController = new AnimationController(this, "moveAnimationController", 10F, this::moveController);

    public EntityHealingSpore(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.navigation.setCanFloat(true);
        this.moveControl = new FlyingMovementController(this, 10, false);
        this.setPathfindingMalus(PathNodeType.OPEN, 0.0F);
    }

    @Override
    public void tick() {
        super.tick();
        entityData.set(AGE_IN_TICKS, entityData.get(AGE_IN_TICKS) + 1);

        if(entityData.get(AGE_IN_TICKS) > 300) {
            this.remove();
        }
    }

    @Override
    public void setTarget(@Nullable LivingEntity pLivingEntity) {
        super.setTarget(pLivingEntity);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(AGE_IN_TICKS, 0);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean doHurtTarget(Entity toAttack) {
        if(toAttack instanceof EntityKrawl) {
            float amountToHeal = (((EntityKrawl) toAttack).getMaxHealth() / 100) * getHealPercent();
            ((EntityKrawl) toAttack).heal(amountToHeal);
            this.remove();
            return false;
        }
        return false;
    }

    @Override
    protected void actuallyHurt(DamageSource damageSrc, float damageAmount) {
        super.actuallyHurt(damageSrc, 0);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this,0.3f , true));
        this.goalSelector.addGoal(0, new MoveToTargetGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MonsterEntity.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 0)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FLYING_SPEED, 0.5);
    }

    public int getHealPercent() {
        return new Random().nextInt(20) + 1;
    }

    //Networking
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    //Animation
    public <ENTITY extends EntityKrawl> PlayState moveController(AnimationEvent<ENTITY> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.xelles_spore.idle", true));
        return PlayState.CONTINUE;
    }
    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::moveController));
    }
}
