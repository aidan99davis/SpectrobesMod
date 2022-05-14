package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.entities.goals.AttackSpectrobeGoal;
import com.spectrobes.spectrobesmod.common.entities.goals.AttackSpectrobeMasterGoal;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.PostConstruct;
import java.util.Random;

public abstract class EntityKrawl extends MonsterEntity implements IAnimatable, IHasNature {

    public KrawlProperties krawlProperties;
    public AnimationFactory animationControllers = new AnimationFactory(this);
    protected AnimationController moveController = new AnimationController(this, "moveAnimationController", 10F, this::moveController);

    private static final DataParameter<Boolean> IS_ATTACKING =
            EntityDataManager.defineId(EntityKrawl.class,
                    DataSerializers.BOOLEAN);

    protected EntityKrawl(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        krawlProperties = GetKrawlProperties();
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    protected abstract KrawlProperties GetKrawlProperties();

    public boolean IsAttacking() {
        return entityData.get(IS_ATTACKING);
    }
    public void setIsAttacking(boolean attacking) {
        entityData.set(IS_ATTACKING, attacking);
    }

    @Override
    public boolean doHurtTarget(Entity toAttack) {
        setIsAttacking(true);
        return super.doHurtTarget(toAttack);
    }

    @Override
    public void setAggressive(boolean hasAggro) {
        if(!hasAggro){
            setIsAttacking(false);
        }
        super.setAggressive(hasAggro);
    }

    @Override
    protected void actuallyHurt(DamageSource damageSrc, float damageAmount) {
        if(damageSrc.getDirectEntity() instanceof IHasNature) {
            IHasNature attacker = (IHasNature)damageSrc.getDirectEntity();
            int advantage = Spectrobe.hasTypeAdvantage(attacker, this);
            float scaledAmount;

            switch (advantage) {
                case -1:
                    scaledAmount = damageAmount * 0.75f;
                    break;
                case 1:
                    scaledAmount = damageAmount * 1.25f;
                    break;
                default:
                    scaledAmount = damageAmount;
                    break;
            }
            super.actuallyHurt(damageSrc, scaledAmount);
        } else {
            super.actuallyHurt(damageSrc, damageAmount);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if(this.isSunBurnTick()) {
            this.setSecondsOnFire(8);
        }
    }

    @Override
    protected void registerGoals()
    {
//        this.goalSelector.addGoal(5, new BreedGoal(this,10)); todo: Make krawl eat mass and duplicate?
        this.goalSelector.addGoal(1, new AttackSpectrobeGoal(this, true, true));
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.2d));
        this.goalSelector.addGoal(2, new AttackSpectrobeMasterGoal(this, true, true));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 10.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.2D));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this,0.3f , true));
    }

    public boolean isVortex() {
        return false;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(IS_ATTACKING, false);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MonsterEntity.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double)0.5F)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_SPEED, 0.25f)
                .add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    //Networking
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    //Animation
    public abstract <ENTITY extends EntityKrawl> PlayState moveController(AnimationEvent<ENTITY> entityAnimationTestEvent);

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::moveController));
    }

    @Override
    public SpectrobeProperties.Nature getNature() {
        return krawlProperties.getNature();
    }
}
