package com.spectrobes.spectrobesmod.common.entities.goals;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.ai.goal.TargetGoal;

import java.util.List;

public class AttackKrawlGoal extends TargetGoal {
    EntityKrawl target;


    public AttackKrawlGoal(EntitySpectrobe mobIn, boolean checkSight) {
        super(mobIn, checkSight);
    }

    public AttackKrawlGoal(EntitySpectrobe mobIn, boolean checkSight, boolean nearbyOnlyIn) {
        super(mobIn, checkSight, nearbyOnlyIn);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        if(((EntitySpectrobe)goalOwner).getStage() == SpectrobeProperties.Stage.CHILD )
            return false;

        List<EntityKrawl> nearbyMobs = goalOwner.world.getEntitiesWithinAABB(EntityKrawl.class, goalOwner.getBoundingBox().grow(20, 20, 20));
        if (!nearbyMobs.isEmpty()) {
            SpectrobesInfo.LOGGER.info("GOT HERE 1");
            this.target = nearbyMobs.get(0);
            return true;
        }
        SpectrobesInfo.LOGGER.info("GOT HERE 2");
        return false;
    }

    @Override
    public void startExecuting() {
        SpectrobesInfo.LOGGER.info("GOT HERE 3");

        this.goalOwner.setAttackTarget(this.target);
        this.goalOwner.getNavigator().setPath(this.goalOwner.getNavigator().getPathToEntity(this.target, 1), 3);
        this.goalOwner.setAggroed(true);
        super.startExecuting();
    }
}
