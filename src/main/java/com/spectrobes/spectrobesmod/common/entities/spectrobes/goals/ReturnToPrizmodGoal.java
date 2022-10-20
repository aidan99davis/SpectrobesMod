package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals;

import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.ai.goal.Goal;

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
        List<EntityKrawl> nearbyKrawl =
                goalOwner.level.getEntitiesOfClass(EntityKrawl.class,
                        goalOwner.getBoundingBox()
                                .inflate(10, 10, 5));
        if((goalOwner).getStage() == SpectrobeProperties.Stage.CHILD ) {
            if(!nearbyKrawl.isEmpty()) {
                return true;
            }
        } else {
            if(nearbyKrawl.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void start() {
        goalOwner.despawn();
    }
}
