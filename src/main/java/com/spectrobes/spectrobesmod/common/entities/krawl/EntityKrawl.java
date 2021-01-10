package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.entities.goals.AttackSpectrobeGoal;
import com.spectrobes.spectrobesmod.common.entities.goals.AttackSpectrobeMasterGoal;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
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

import java.util.Random;

public abstract class EntityKrawl extends MonsterEntity implements IAnimatable, IHasNature {

    public KrawlProperties krawlProperties;
    public AnimationFactory animationControllers = new AnimationFactory(this);
    protected AnimationController moveController = new AnimationController(this, "moveAnimationController", 10F, this::moveController);

    private static final DataParameter<Boolean> IS_ATTACKING =
            EntityDataManager.createKey(EntityKrawl.class,
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
        return dataManager.get(IS_ATTACKING);
    }
    public void setIsAttacking(boolean attacking) {
        dataManager.set(IS_ATTACKING, attacking);
    }

    @Override
    public boolean attackEntityAsMob(Entity toAttack) {
        setIsAttacking(true);
        return super.attackEntityAsMob(toAttack);
    }

    @Override
    public void setAggroed(boolean hasAggro) {
        if(!hasAggro){
            setIsAttacking(false);
        }
        super.setAggroed(hasAggro);
    }

    @Override
    protected void damageEntity(DamageSource damageSrc, float damageAmount) {
        if(damageSrc.getImmediateSource() instanceof IHasNature) {
            IHasNature attacker = (IHasNature)damageSrc.getImmediateSource();
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
            super.damageEntity(damageSrc, scaledAmount);
        } else {
            super.damageEntity(damageSrc, damageAmount);
        }
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if(this.isInDaylight()) {
            this.setFire(8);
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

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.registerAttributes();
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(IS_ATTACKING, false);
    }

    //Networking
    @Override
    public IPacket<?> createSpawnPacket() {
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
