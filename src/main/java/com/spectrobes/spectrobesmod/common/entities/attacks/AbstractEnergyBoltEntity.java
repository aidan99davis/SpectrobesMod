package com.spectrobes.spectrobesmod.common.entities.attacks;

import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

// TODO: The projectile seems to fly the same speed regardless of what velocity it is shot at.
//      May have to Override assignDirectionalMovement() to make the projectile travel at a constant speed?
//      Idk how the acceleration math goes.
public abstract class AbstractEnergyBoltEntity extends AbstractHurtingProjectile implements IHasNature, GeoAnimatable {
    private int AtkDamage;
    private SpectrobeProperties.Nature Nature;

    public AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    protected int lifetime = 0;
    protected int maxLifetime = 50; // Default (Plz set in constructor of child classes)

    protected AbstractEnergyBoltEntity(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    // = = = MORE SPECTROBY PROJECTILE = = =
    @Override protected boolean shouldBurn() { return false; }

    // TODO: Find / Make a more appropriate particle
    @Override @NotNull protected ParticleOptions getTrailParticle() { return ParticleTypes.GLOW; }

    // = = = GET / SET = = =
    // AtkDamage
    public int getAtkDamage() { return this.AtkDamage; }
    public void setAtkDamage(int pValue) { this.AtkDamage = pValue; }

    // Nature
    @Override public SpectrobeProperties.Nature getNature() { return Nature; } // Mandatory
    public void setNature(SpectrobeProperties.Nature pValue) { this.Nature = pValue; }

    // lifetime
    public int getLifetime() { return this.maxLifetime; }
//    public void setLifetime(int pValue) { this.maxLifetime = pValue; } // Probably shouldn't allow setting the timer as it ticks

    public int getMaxLifetime() { return this.maxLifetime; }
    public void setMaxLifetime(int pValue) { this.maxLifetime = pValue; }

    // = = = TICKS = = =
    @Override
    public void tick() {
        super.tick();
        tickDespawn();
    }

    /** Manages the projectile's lifetime. */
    protected void tickDespawn() {
        ++this.lifetime;
        if (this.lifetime >= this.maxLifetime) {
            this.remove(RemovalReason.DISCARDED);
            this.onDeath();
        }
    }

    // = = = OTHER, MANDATORY METHODS = = =

    @Override public void registerControllers(AnimatableManager.ControllerRegistrar controllers) { }
    @Override public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
    @Override public double getTick(Object object) { return 0; }

    // = = = SYNCED DATA = = =
    @Override protected void defineSynchedData(SynchedEntityData.Builder builder) { }

    // = = = EVENTS = = =
    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult); // <-- Calls both onHitEntity & onHitBlock

        // So everything down here happens after the projectile hits ANYTHING
        this.remove(RemovalReason.DISCARDED);
        this.onDeath();
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        pResult.getEntity().hurt(
                this.level().damageSources().thrown(this, this.getOwner()),
                AtkDamage
        );
    }

    /** Things that happen any time this entity dies. */
    protected void onDeath() {
        // Remove
        // TODO: Don't know if this should go here. Figure out what the individual RemovalReasons are used for.
        // this.remove(RemovalReason.DISCARDED);

        // Spawn death particles
        // TODO: Figure out particles

        // Play death noise
        this.playSound(SoundEvents.FIRE_EXTINGUISH);
    }
}
