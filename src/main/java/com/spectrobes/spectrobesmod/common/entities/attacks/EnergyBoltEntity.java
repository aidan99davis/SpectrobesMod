package com.spectrobes.spectrobesmod.common.entities.attacks;

import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EnergyBoltEntity extends ThrowableProjectile implements IHasNature, GeoAnimatable {
    public int AtkDamage;
    public SpectrobeProperties.Nature Nature;

    public AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int life = 0;

    public EnergyBoltEntity(EntityType<? extends ThrowableProjectile> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    public void tick() {
        super.tick();
        tickDespawn();
    }

    protected void tickDespawn() {
        ++this.life;
        if (this.life >= 120) {
            this.remove(RemovalReason.DISCARDED);
        }

    }

    @Override
    public SpectrobeProperties.Nature getNature() {
        return Nature;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        result.getEntity().hurt(
                this.level().damageSources().thrown(this, this.getOwner()),
                AtkDamage
        );
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object object) {
        return 0;
    }
}
