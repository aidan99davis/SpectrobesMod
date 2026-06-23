package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.child;

import com.mojang.datafixers.DataFixUtils;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.List;
import java.util.function.Predicate;

public class SpectrobeFollowLeaderGoal extends Goal {
    private final EntitySpectrobe mob;
    private int timeToRecalcPath;

    public SpectrobeFollowLeaderGoal(EntitySpectrobe pFish) {
        this.mob = pFish;
    }

    @Override
    public boolean canUse() {
        if (this.mob.getStage() != SpectrobeProperties.Stage.CHILD) {
            return false;
        } else if (this.mob.hasFollowers()) {
            return false;
        } else if (this.mob.isFollower()) {
            return true;
        }

        if (this.mob.getEvolutionRegistry() == null) {
            return false;
        }

        Predicate<EntitySpectrobe> canFollow =
                s -> s.canBeFollowed() || !s.isFollower();

        // Look for the evolved (adult) form first, then the direct evolution
        // as the leader. Both queries use getSpectrobeClass() to avoid spawning.
        EntitySpectrobe evolutionInstance =
                this.mob.getEvolutionRegistry().create(this.mob.level());
        if (evolutionInstance == null) {
            return false;
        }

        // Check for a twice-evolved leader (fully adult form)
        if (evolutionInstance.getEvolutionRegistry() != null) {
            EntitySpectrobe fullyEvolvedInstance =
                    evolutionInstance.getEvolutionRegistry().create(this.mob.level());
            if (fullyEvolvedInstance != null) {
                List<? extends EntitySpectrobe> adults =
                        this.mob.level().getEntitiesOfClass(
                                fullyEvolvedInstance.getSpectrobeClass(),
                                this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D),
                                canFollow);
                // Immediately discard the temporary instance — do not add to world
                fullyEvolvedInstance.discard();
                EntitySpectrobe leader = DataFixUtils.orElse(
                        adults.stream().filter(EntitySpectrobe::canBeFollowed).findAny(), null);
                if (leader != null) {
                    evolutionInstance.discard();
                    this.mob.startFollowing(leader);
                    return this.mob.isFollower();
                }
            }
        }

        // Fall back to direct evolution as leader
        List<? extends EntitySpectrobe> nearAdults =
                this.mob.level().getEntitiesOfClass(
                        evolutionInstance.getSpectrobeClass(),
                        this.mob.getBoundingBox().inflate(16.0D, 16.0D, 16.0D),
                        canFollow);
        evolutionInstance.discard();

        EntitySpectrobe leader = DataFixUtils.orElse(
                nearAdults.stream().filter(EntitySpectrobe::canBeFollowed).findAny(), null);
        if (leader == null) {
            return false;
        }
        this.mob.startFollowing(leader);
        return this.mob.isFollower();
    }

    @Override
    public boolean canContinueToUse() {
        return this.mob.isFollower() && this.mob.inRangeOfLeader();
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
    }

    @Override
    public void stop() {
        this.mob.stopFollowing();
    }

    @Override
    public void tick() {
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            this.mob.pathToLeader();
        }
    }
}