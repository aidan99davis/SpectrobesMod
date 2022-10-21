package com.spectrobes.spectrobesmod.common.entities.attacks;

import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EnergyBoltEntity extends ThrowableEntity implements IHasNature, IAnimatable {
    public int AtkDamage;
    public SpectrobeProperties.Nature Nature;

    public AnimationFactory factory = new AnimationFactory(this);
    private int life = 0;

    public EnergyBoltEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
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
            this.remove();
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
    protected void onHitEntity(EntityRayTraceResult pResult) {
        super.onHitEntity(pResult);
        pResult.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), AtkDamage);
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
