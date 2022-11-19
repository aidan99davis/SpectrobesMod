package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals;

import com.mojang.datafixers.DataFixUtils;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.Collection;
import java.util.function.Predicate;

public class SpectrobeFollowLeaderGoal extends Goal {
    private static final int INTERVAL_TICKS = 200;
    private final EntitySpectrobe mob;
    private int timeToRecalcPath;

    public SpectrobeFollowLeaderGoal(EntitySpectrobe pFish) {
        this.mob = pFish;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if(this.mob.getStage() != SpectrobeProperties.Stage.CHILD) {
            return false;
        }else if (this.mob.hasFollowers()) {
            return false;
        } else if (this.mob.isFollower()) {
            return true;
        } else {
            Predicate<EntitySpectrobe> predicate = (p_25258_) -> {
                return p_25258_.canBeFollowed() || !p_25258_.isFollower();
            };
            EntitySpectrobe adult = this.mob.getEvolutionRegistry().create(mob.level);
            EntitySpectrobe evolved = null;//adult.getEvolutionRegistry().create(mob.level);

            Collection<? extends EntitySpectrobe> list = this.mob.level.getEntitiesOfClass(adult.getSpectrobeClass(), this.mob.getBoundingBox().inflate(16.0D, 16.0D, 16.0D), predicate);
            if(evolved != null) {
                Collection<? extends EntitySpectrobe> list2 = this.mob.level.getEntitiesOfClass(evolved.getSpectrobeClass(), this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
                EntitySpectrobe abstractschoolingfish = DataFixUtils.orElse(list2.stream().filter(EntitySpectrobe::canBeFollowed).findAny(), null);
                if(abstractschoolingfish == null) return false;
                this.mob.startFollowing(abstractschoolingfish);
                return this.mob.isFollower();
            }
            EntitySpectrobe abstractschoolingfish = DataFixUtils.orElse(list.stream().filter(EntitySpectrobe::canBeFollowed).findAny(), null);
            if(abstractschoolingfish == null) return false;
            this.mob.startFollowing(abstractschoolingfish);
            return this.mob.isFollower();
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        return this.mob.isFollower() && this.mob.inRangeOfLeader();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        this.timeToRecalcPath = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.mob.stopFollowing();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            this.mob.pathToLeader();
        }
    }
}
