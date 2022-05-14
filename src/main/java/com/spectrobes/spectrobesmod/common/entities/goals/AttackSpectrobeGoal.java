package com.spectrobes.spectrobesmod.common.entities.goals;

import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
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
    public boolean canUse() {
        if(mob instanceof EntitySpectrobe && ((EntitySpectrobe)mob).getStage() == SpectrobeProperties.Stage.CHILD)
            return false;

        List<EntitySpectrobe> nearbyMobs = mob.level.getEntitiesOfClass(EntitySpectrobe.class, mob.getBoundingBox().inflate(20, 20, 20));
        if (!nearbyMobs.isEmpty()) {
            this.target = nearbyMobs.get(0);
            return true;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if(tryKill) {
            return super.canContinueToUse();
        } else {
            //spectrobe v spectrobe fights should culminate when one reaches 20% health,
            // as its more a territory fight than a death brawl.
            return target.getHealth() / target.getMaxHealth() > 0.2f && super.canContinueToUse();
        }
    }

    @Override
    public void start() {
        this.mob.setTarget(this.target);
        ((EntityKrawl)this.mob).setIsAttacking(true);
        this.mob.getNavigation().moveTo(this.mob.getNavigation().createPath(this.target, 1), 3);
        this.mob.setAggressive(true);
        super.start();
    }
}
