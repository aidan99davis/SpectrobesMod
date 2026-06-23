package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.movement.SpectrobeWaterAvoidingRandomFlyingGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.movement.SpectrobeWaterAvoidingRandomStrollGoal;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

public abstract class EntityAvianSpectrobe extends EntitySpectrobe implements FlyingAnimal {

    // Wing animation state — read by the renderer
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    private float flapping = 1.0F;

    public EntityAvianSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        // FlyingMoveControl handles both ground walking and airborne movement.
        // The second parameter (10) is the y-turn speed; `true` = can walk on land.
        this.moveControl = new FlyingMoveControl(this, 10, true);
        this.setPathfindingMalus(PathType.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, -1.0F);
        this.setPathfindingMalus(PathType.COCOA, -1.0F);
    }

    // -------------------------------------------------------------------------
    // Attributes — avians need FLYING_SPEED on top of the base set
    // -------------------------------------------------------------------------

    public static AttributeSupplier.Builder setCustomAttributes() {
        return EntitySpectrobe.setCustomAttributes().add(Attributes.FLYING_SPEED, 1);
    }

    // -------------------------------------------------------------------------
    // canFly — tells FollowMasterGoal in the base class to use a flying
    //          navigator so tamed avians can fly alongside their master.
    // -------------------------------------------------------------------------

    @Override
    public boolean canFly() {
        return true;
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    // -------------------------------------------------------------------------
    // Goals
    //
    //   0  FloatGoal                   — prevents drowning
    //   6  RandomFlyingGoal (wild)     — wild aerial roaming
    //   7  RandomStrollGoal (wild)     — ground fallback for wild avians
    //
    // Tamed avians are handled entirely by FollowMasterGoal (priority 3) from
    // the base class, which already receives canFly() = true.  We guard the
    // wild goals explicitly so a tamed bird doesn't randomly fly off.
    //
    // Note: the ground stroll goal is intentionally wild-only because tamed
    // avians walking on the ground is handled by FollowMasterGoal's own land
    // pathing when the master is nearby.
    // -------------------------------------------------------------------------

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(6, new SpectrobeWaterAvoidingRandomFlyingGoal(this, 1.2) {
            @Override public boolean canUse() { return !isTame() && super.canUse(); }
        });

        this.goalSelector.addGoal(7, new SpectrobeWaterAvoidingRandomStrollGoal(this, 1.2) {
            @Override public boolean canUse() { return !isTame() && super.canUse(); }
        });
    }

    // -------------------------------------------------------------------------
    // Breeding
    // -------------------------------------------------------------------------

    @Override
    public void mate() {
        List<? extends EntityAvianSpectrobe> mates =
                level().getEntitiesOfClass(getClass(),
                        this.getBoundingBox().inflate(10, 10, 10));

        if (mates.isEmpty() || mates.size() == 1) {
            this.setTicksTillMate(16000);
            return;
        }

        EntityAvianSpectrobe mate = null;
        for (EntityAvianSpectrobe spec : mates) {
            if (mate == null && spec.getTicksTillMate() <= 0) {
                mate = spec;
            }
        }

        if (mate == null) {
            this.setTicksTillMate(16000);
            return;
        }

        this.entityData.set(HAS_MATED, true);
        this.setTicksTillMate(16000);
        mate.setTicksTillMate(16000);

        Random random = new Random();
        int litterSize = random.nextInt(getMaxLitterSize()) + 1;
        for (int i = 0; i < litterSize; i++) {
            EntitySpectrobe child = getChildForLineage().create(level());
            if (child != null) {
                this.level().addFreshEntity(child);
                child.teleportTo(getX(), getY(), getZ());
            }
        }
    }

    @Override
    protected int getMaxSchoolSize() {
        return 5;
    }

    protected abstract int getMaxLitterSize();

    // -------------------------------------------------------------------------
    // Wing animation
    // -------------------------------------------------------------------------

    @Override
    public void aiStep() {
        super.aiStep();
        calculateFlapping();
    }

    private void calculateFlapping() {
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        // Flap speed rises while airborne, falls while grounded
        this.flapSpeed = (float) ((double) this.flapSpeed
                + (double) (!this.onGround() && !this.isPassenger() ? 4 : -1) * 0.3D);
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);

        if (!this.onGround() && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping = (float) ((double) this.flapping * 0.9D);

        // Dampen downward velocity when airborne — gives a gliding feel
        Vec3 movement = this.getDeltaMovement();
        if (!this.onGround() && movement.y < 0.0D) {
            this.setDeltaMovement(movement.multiply(1.0D, 0.6D, 1.0D));
        }

        this.flap += this.flapping * 2.0F;
    }
}