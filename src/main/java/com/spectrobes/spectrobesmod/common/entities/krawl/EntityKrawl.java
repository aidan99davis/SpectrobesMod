package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.entities.attacks.EnergyBoltEntity;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeMasterGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.weapons.ISpectrobeWeapon;
import com.spectrobes.spectrobesmod.common.items.weapons.SpectrobesWeapon;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.DamageUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.Swim;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import static com.spectrobes.spectrobesmod.util.DamageUtils.getTypeBonus;

public abstract class EntityKrawl extends Monster implements IAnimatable, IHasNature {
    public KrawlProperties krawlProperties;
    public AnimationFactory animationControllers = new AnimationFactory(this);
    protected AnimationController moveController = new AnimationController(this, "moveAnimationController", 10F, this::moveController);

    private static final DataParameter<Boolean> IS_ATTACKING =
            EntityDataManager.defineId(EntityKrawl.class,
                    DataSerializers.BOOLEAN);

    protected EntityKrawl(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
        krawlProperties = GetKrawlProperties();
        updateEntityAttributes();
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public abstract KrawlProperties GetKrawlProperties();

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
        if (damageSrc.getDirectEntity() instanceof IHasNature) {
            IHasNature attacker = (IHasNature) damageSrc.getDirectEntity();
            int advantage = Spectrobe.hasTypeAdvantage(attacker, this);

            int atkPower = 0;

            if(damageSrc.getDirectEntity() instanceof EntitySpectrobe)
                atkPower = ((EntitySpectrobe) damageSrc.getDirectEntity()).getSpectrobeData().stats.getAtkLevel();
            else if (damageSrc.getDirectEntity() instanceof EnergyBoltEntity)
                atkPower = ((EnergyBoltEntity) damageSrc.getDirectEntity()).AtkDamage;

            float typeBonus = getTypeBonus(advantage);
            int defPower = GetKrawlProperties().getDefLevel();
            int powerScale = 1;
            float scaledAmount = DamageUtils.getFinalDamageAmount(typeBonus, atkPower, powerScale, defPower);

            super.actuallyHurt(damageSrc, scaledAmount);
        }
        else if(damageSrc.getDirectEntity() instanceof Player) {
            Player playerEntity = (Player) damageSrc.getDirectEntity();
            if(playerEntity.getMainHandItem().getItem() != null
                    && playerEntity.getMainHandItem().getItem() instanceof SpectrobesWeapon) {
                ISpectrobeWeapon weapon = (ISpectrobeWeapon) playerEntity.getMainHandItem().getItem();
                int advantage = Spectrobe.hasTypeAdvantage(weapon, this);
                int atkPower = weapon.GetWeaponStats().AtkDamage;
                float typeBonus = getTypeBonus(advantage);
                int defPower = GetKrawlProperties().getDefLevel();
                int powerScale = 1; //todo this can be used to create secondary attacks for weapons which deal extra damage.

                float scaledAmount = DamageUtils.getFinalDamageAmount(typeBonus, atkPower, powerScale, defPower);
                super.actuallyHurt(damageSrc, scaledAmount);
            }
            else if(playerEntity.getOffhandItem().getItem() != null
                    && playerEntity.getOffhandItem().getItem() instanceof SpectrobesWeapon) {
                ISpectrobeWeapon weapon = (ISpectrobeWeapon) playerEntity.getOffhandItem().getItem();
                int advantage = Spectrobe.hasTypeAdvantage(weapon, this);
                int atkPower = weapon.GetWeaponStats().AtkDamage;
                float typeBonus = getTypeBonus(advantage);
                int defPower = GetKrawlProperties().getDefLevel();
                int powerScale = 1; //todo this can be used to create secondary attacks for weapons which deal extra damage.
                float scaledAmount = DamageUtils.getFinalDamageAmount(typeBonus, atkPower, powerScale, defPower);
                super.actuallyHurt(damageSrc, scaledAmount);
            }
            else {
                super.actuallyHurt(damageSrc, 0);
            }
        }else {
            super.actuallyHurt(damageSrc, damageAmount);
        }
    }


    @Override
    public void aiStep() {
        super.aiStep();

        if(this.isSunBurnTick()) {
            this.setSecondsOnFire(8);
        }
        if((getLastHurtByMobTimestamp() - this.tickCount) > 200) this.setHealth(getHealth() + (getHealth() / 100));
    }

    @Override
    protected void registerGoals()
    {
//        this.goalSelector.addGoal(5, new BreedGoal(this,10)); todo: Make krawl eat mass and duplicate?
//        this.goalSelector.addGoal(7, new SwimGoal(1f));
        this.goalSelector.addGoal(1, new AttackSpectrobeGoal(this, true, true));
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.2d));
        this.goalSelector.addGoal(2, new AttackSpectrobeMasterGoal(this, true, true));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.2D));
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

    public static AttributeSupplier.Builder setCustomAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double)0.5F)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_SPEED, 0.25f)
                .add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    public void updateEntityAttributes() {
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(
                krawlProperties.getHplevel());

        this.setHealth(krawlProperties.getHplevel());

        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(
                krawlProperties.getAtkLevel());

    }

    //Networking
    @Override
    public Packet<?> getAddEntityPacket() {
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
