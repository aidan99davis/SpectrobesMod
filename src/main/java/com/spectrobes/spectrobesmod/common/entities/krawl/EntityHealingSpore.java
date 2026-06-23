package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.entities.krawl.goals.MoveToTargetGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EntityHealingSpore extends Monster implements GeoEntity, FlyingAnimal {
    private static final String TAG_AGE_IN_TICKS = "AGE_IN_TICKS";

    private static final int MAX_AGE_TICKS = 300;

    private static final RawAnimation IDLE_ANIMATION =
            RawAnimation.begin().thenLoop("animation.xelles_spore.idle");

    private static final EntityDataAccessor<Integer> AGE_IN_TICKS =
            SynchedEntityData.defineId(EntityHealingSpore.class, EntityDataSerializers.INT);

    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    public EntityHealingSpore(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);

        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.setPathfindingMalus(PathType.OPEN, 0.0F);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation navigation = new FlyingPathNavigation(this, level);
        navigation.setCanFloat(true);
        return navigation;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(AGE_IN_TICKS, 0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MoveToTargetGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.3D, true));
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            return;
        }

        int ageInTicks = this.getAgeInTicks() + 1;
        this.setAgeInTicks(ageInTicks);

        if (ageInTicks > MAX_AGE_TICKS) {
            this.discard();
        }
    }

    public int getAgeInTicks() {
        return this.entityData.get(AGE_IN_TICKS);
    }

    private void setAgeInTicks(int ageInTicks) {
        this.entityData.set(AGE_IN_TICKS, ageInTicks);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt(TAG_AGE_IN_TICKS, this.getAgeInTicks());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setAgeInTicks(compound.getInt(TAG_AGE_IN_TICKS));
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (!(target instanceof EntityKrawl krawl)) {
            return false;
        }

        float amountToHeal = krawl.getMaxHealth() * (this.getHealPercent() / 100.0F);
        krawl.heal(amountToHeal);

        if (!this.level().isClientSide()) {
            this.discard();
        }

        return true;
    }

    @Override
    public boolean hurt(DamageSource damageSource, float damageAmount) {
        // Healing spores are intentionally damage-immune.
        return false;
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.FLYING_SPEED, 0.5D)
                .add(Attributes.ATTACK_DAMAGE, 0.0D);
    }

    public int getHealPercent() {
        return this.random.nextInt(20) + 1;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "move_controller", 10, this::moveController));
    }

    private PlayState moveController(AnimationState<EntityHealingSpore> animationState) {
        return animationState.setAndContinue(IDLE_ANIMATION);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationCache;
    }

    @Override
    public double getTick(Object object) {
        return this.tickCount;
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }
}