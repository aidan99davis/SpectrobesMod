package com.spectrobes.spectrobesmod.common.entities.krawl.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;

public class MoveToTargetGoal extends Goal {
    Monster owner;

    public MoveToTargetGoal(Monster mob) {
        owner = mob;
    }

    @Override
    public boolean canUse() {
        return owner.getTarget() != null;
    }

    @Override
    public void start() {
        super.start();
        LivingEntity target = owner.getTarget();
        owner.getMoveControl().setWantedPosition(target.getX(),
                target.getY(),
                target.getZ(),
                0.5);
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity target = owner.getTarget();
        owner.getMoveControl().setWantedPosition(target.getX(),
                target.getY(),
                target.getZ(),
                0.5);
    }
}
