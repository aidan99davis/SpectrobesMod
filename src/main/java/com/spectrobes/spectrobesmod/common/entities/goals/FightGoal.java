package com.spectrobes.spectrobesmod.common.entities.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class FightGoal extends MeleeAttackGoal {
    public FightGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
        super(creature, speedIn, useLongMemory);
    }

    @Override
    public boolean shouldContinueExecuting() {
        boolean shouldContinue = super.shouldContinueExecuting();

        if(shouldContinue) {
            ((EntitySpectrobe)this.attacker).setIsAttacking(true);
        } else {
            ((EntitySpectrobe)this.attacker).setIsAttacking(false);
        }

        return shouldContinue;
    }

    @Override
    public void startExecuting() {
        super.startExecuting();
    }
}
