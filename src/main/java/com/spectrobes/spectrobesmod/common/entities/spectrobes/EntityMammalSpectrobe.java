package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.movement.SpectrobeRandomStrollGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.movement.SpectrobeWaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;

public abstract class EntityMammalSpectrobe extends EntitySpectrobe {

    public EntityMammalSpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    // -------------------------------------------------------------------------
    // Goals
    //
    // Mammals are pure land-dwellers.  Tamed ones walk alongside their master
    // (FollowMasterGoal at priority 3 in the base class supersedes these).
    // Wild ones roam freely and avoid water.
    //
    //   0  FloatGoal                    — prevents drowning
    //   2  RandomStroll (low speed)     — slow idle wander, tamed & wild
    //   6  WaterAvoidingRandomStroll    — wild-only faster exploration
    // -------------------------------------------------------------------------

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new FloatGoal(this));

        // Slow idle wander — priority is below FollowMaster (3), so tamed
        // spectrobes will only wander when they are not following
        this.goalSelector.addGoal(2, new SpectrobeRandomStrollGoal(this, 0.2D));

        // Faster exploration — wild-only so tamed spectrobes don't sprint off
        this.goalSelector.addGoal(6, new SpectrobeWaterAvoidingRandomStrollGoal(this, 0.5D) {
            @Override public boolean canUse() { return !isTame() && super.canUse(); }
        });
    }

    // -------------------------------------------------------------------------
    // Breeding
    //
    // Fixed: the cooldown gate was previously `<= 100`, inconsistent with
    // every other spectrobe type which uses `<= 0`.  Using `<= 0` means a
    // spectrobe must have fully cooled down before it can be chosen as a mate.
    // Also: `setTicksTillMate` is now called on `this` as well as `mate` so
    // both participants enter cooldown after breeding.
    // -------------------------------------------------------------------------

    public abstract int getLitterSize();

    @Override
    public void mate() {
        List<? extends EntityMammalSpectrobe> mates =
                level().getEntitiesOfClass(getClass(),
                        this.getBoundingBox().inflate(10, 10, 10));

        if (mates.isEmpty() || mates.size() == 1) {
            this.setTicksTillMate(16000);
            return;
        }

        EntityMammalSpectrobe mate = null;
        for (EntityMammalSpectrobe spec : mates) {
            // Use <= 0 to match all other spectrobe types
            if (mate == null && spec.getTicksTillMate() <= 0) {
                mate = spec;
            }
        }

        if (mate == null) {
            this.setTicksTillMate(16000);
            return;
        }

        this.entityData.set(HAS_MATED, true);
        // Both participants cool down — previously `this` was missing its reset
        this.setTicksTillMate(16000);
        mate.setTicksTillMate(16000);

        Random random = new Random();
        int litterSize = random.nextInt(getLitterSize()) + 1;
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
        return 8;
    }
}