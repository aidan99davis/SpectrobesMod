package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.child;

import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.List;

public class ReturnToPrizmodGoal extends Goal {
    EntitySpectrobe goalOwner;

    public ReturnToPrizmodGoal(EntitySpectrobe goalOwner) {
        super();
        this.goalOwner = goalOwner;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean canUse() {
        if((goalOwner).getStage() != SpectrobeProperties.Stage.CHILD) return false;

        List<EntityKrawl> nearbyKrawl =
                goalOwner.level().getEntitiesOfClass(EntityKrawl.class,
                        goalOwner.getBoundingBox()
                                .inflate(10, 10, 5));

        return !nearbyKrawl.isEmpty();
    }

    @Override
    public void start() {
        goalOwner.despawn();
    }
}
