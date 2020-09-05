package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.entities.goals.AttackSpectrobeGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib.animation.controller.EntityAnimationController;
import software.bernie.geckolib.entity.IAnimatedEntity;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;

import java.util.Random;

public abstract class EntityKrawl extends MonsterEntity implements IAnimatedEntity, IHasNature {

    public KrawlProperties krawlProperties;
    public EntityAnimationManager animationControllers = new EntityAnimationManager();
    protected EntityAnimationController moveController = new EntityAnimationController(this, "moveAnimationController", 10F, this::moveController);

    private static final DataParameter<Boolean> IS_ATTACKING =
            EntityDataManager.createKey(EntityKrawl.class,
                    DataSerializers.BOOLEAN);

    protected EntityKrawl(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        registerAnimationControllers();
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
    public void damageEntity(DamageSource source, float amount) {
        if(source.getImmediateSource() instanceof EntitySpectrobe){
            super.damageEntity(source,amount);
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
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.2d));
        this.goalSelector.addGoal(2, new AttackSpectrobeGoal(this, true, true));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 10.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this,0.3f , true));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);
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
    public abstract <ENTITY extends EntityKrawl> boolean moveController(AnimationTestEvent<ENTITY> entityAnimationTestEvent);

    @Override
    public EntityAnimationManager getAnimationManager() {
        return animationControllers;
    }

    public void registerAnimationControllers()
    {
        if(world.isRemote)
        {
            this.animationControllers.addAnimationController(moveController);
        }
    }

    @Override
    public void onDeath(DamageSource source) {
        Random random = new Random();

        int mineralCount = random.nextInt(3);

        ItemStack mineralStack =SpectrobesItems.getRandomMineral();
        mineralStack.grow(mineralCount);

        ItemEntity lvt_10_1_ = new ItemEntity(world,
                this.getPosX() + 0.5D,
                (this.getPosY() + 1),
                this.getPosZ() + 0.5D, mineralStack);
        lvt_10_1_.setDefaultPickupDelay();
        world.addEntity(lvt_10_1_);
        super.onDeath(source);
    }

    @Override
    public SpectrobeProperties.Nature getNature() {
        return krawlProperties.getNature();
    }
}
