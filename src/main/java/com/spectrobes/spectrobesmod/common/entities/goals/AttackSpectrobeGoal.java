package com.spectrobes.spectrobesmod.common.entities.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.TargetGoal;

import java.util.List;

public class AttackSpectrobeGoal extends TargetGoal {
    EntitySpectrobe target;
    boolean tryKill;


    public AttackSpectrobeGoal(MobEntity mobIn, boolean checkSight, boolean toKill) {
        super(mobIn, checkSight, false);
        tryKill = toKill;
    }


    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        if(goalOwner instanceof EntitySpectrobe && ((EntitySpectrobe)goalOwner).getStage() == SpectrobeProperties.Stage.CHILD)
            return false;

        List<EntitySpectrobe> nearbyMobs = goalOwner.world.getEntitiesWithinAABB(EntitySpectrobe.class, goalOwner.getBoundingBox().grow(20, 20, 20));
        if (!nearbyMobs.isEmpty()) {
            this.target = nearbyMobs.get(0);
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        if(tryKill) {
            return super.shouldContinueExecuting();
        } else {
            //spectrobe v spectrobe fights should culminate when one reaches 20% health,
            // as its more a territory fight than a death brawl.
            return target.getHealth() / target.getMaxHealth() > 0.2f && super.shouldContinueExecuting();
        }
    }

    @Override
    public void startExecuting() {
        this.goalOwner.setAttackTarget(this.target);
        this.goalOwner.getNavigator().setPath(this.goalOwner.getNavigator().getPathToEntity(this.target, 1), 3);
        this.goalOwner.setAggroed(true);
        super.startExecuting();
    }
}
