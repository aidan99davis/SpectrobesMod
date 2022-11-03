package com.spectrobes.spectrobesmod.common.entities.attacks;

import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EnergyBoltEntity extends ThrowableProjectile implements IHasNature, IAnimatable {
    public int AtkDamage;
    public SpectrobeProperties.Nature Nature;

    public AnimationFactory factory = new AnimationFactory(this);
    private int life = 0;

    public EnergyBoltEntity(EntityType<? extends ThrowableProjectile> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected float getGravity() {
        return 0;
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
    public void registerControllers(AnimationData data) {
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        pResult.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), AtkDamage);
    }


    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
