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
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import static com.spectrobes.spectrobesmod.util.DamageUtils.getTypeBonus;

public abstract class EntityKrawl extends Monster implements GeoAnimatable, IHasNature {
    public KrawlProperties krawlProperties;
    public AnimatableInstanceCache animationControllers = GeckoLibUtil.createInstanceCache(this);
    protected AnimationController<EntityKrawl> moveController = new AnimationController<>(this, "moveAnimationController", 10, this::moveController);

    private static final EntityDataAccessor<Boolean> IS_ATTACKING =
            SynchedEntityData.defineId(EntityKrawl.class,
                    EntityDataSerializers.BOOLEAN);

    protected EntityKrawl(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
        krawlProperties = GetKrawlProperties();
        updateEntityAttributes();
    }

    public abstract KrawlProperties GetKrawlProperties();

    public boolean isAttacking() {
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
        if (damageSrc.getDirectEntity() instanceof IHasNature attacker) {
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
        else if(damageSrc.getDirectEntity() instanceof Player playerEntity) {
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
            this.setRemainingFireTicks(8);
        }
        if (this.tickCount - getLastHurtByMobTimestamp() > 2000) this.setHealth(getHealth() + (getHealth() / 100));
    }

    @Override
    protected void registerGoals()
    {
//        this.goalSelector.addGoal(5, new BreedGoal(this,10)); todo: Make krawl eat mass and duplicate?
        this.goalSelector.addGoal(0, new FloatGoal(this));
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
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_ATTACKING, false);
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
//    @Override
//    public Packet<?> getAddEntityPacket() {
//        return NetworkHooks.getEntitySpawningPacket(this);
//    }

    //Animation
    public abstract PlayState moveController(AnimationState<EntityKrawl> event);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animationControllers;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::moveController));
    }

    @Override
    public SpectrobeProperties.Nature getNature() {
        return krawlProperties.getNature();
    }

    public void setGlowing(boolean glowing) {
        this.setGlowingTag(glowing);
//        this.setSharedFlag(6, glowing);
    }

    @Override
    public double getTick(Object object) {
        return this.tickCount;
    }
}