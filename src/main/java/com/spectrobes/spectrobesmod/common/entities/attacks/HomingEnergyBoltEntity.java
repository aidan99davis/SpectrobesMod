package com.spectrobes.spectrobesmod.common.entities.attacks;

import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Comparator;
import java.util.List;

public class HomingEnergyBoltEntity extends AbstractEnergyBoltEntity {

    private static final double HOMING_RANGE = 8.0;
    private static final double HOMING_STRENGTH = 0.15;

    public HomingEnergyBoltEntity(EntityType<? extends AbstractEnergyBoltEntity> type, Level level) {
        super(type, level);
//        this.maxLifetime = 50;
    }

    @Override
    public void tick() {
        // Safety - Only tick on server
        if(!this.level().isClientSide) {

            // Homing (directional change)
            Entity target = findTarget();
            if (target != null) {

                // - Movement -
                // Direction
                Vec3 direction = target.position()
                        .add(0, target.getBbHeight() * 0.5, 0)
                        .subtract(this.position())
                        .normalize();

                // Velocity
                Vec3 velocity = this.getDeltaMovement();
                Vec3 newVelocity = velocity.add(direction.scale(HOMING_STRENGTH));

                // Prevent the projectile from accelerating forever
                double speed = velocity.length();
                if (newVelocity.length() > speed) newVelocity = newVelocity.normalize().scale(speed);
                this.setDeltaMovement(newVelocity);
            }
        }

        // Do normal tick stuff
        super.tick();
    }

    /** Gets a list of Krawl entities within homing range, and chooses the closest. */
    private Entity findTarget() {
        // Collect all Krawl entities within HOMING_RANGE
        List<Entity> targets = this.level().getEntities(
                this,
                this.getBoundingBox().inflate(HOMING_RANGE), // Adds 2x HOMING_RANGE to x, y, and z dimensions
                entity ->
//                        entity instanceof EntityBossKrawl // <-- Some check to make boss Krawl tracking a priority over other Krawl may be good?
                        entity instanceof EntityKrawl // Only target Krawl entities
                        && entity.isAlive()
                        && entity != this.getOwner()
                        && entity.canBeHitByProjectile()
        );

        // Get nearest entity on list
        return targets.stream()
                .min(Comparator.comparingDouble(this::distanceTo))
                .orElse(null);
    }
}
