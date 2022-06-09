package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals;

import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.TargetGoal;

import java.util.List;
import java.util.stream.Collectors;

public class AttackKrawlGoal extends TargetGoal {
    EntityKrawl target;
    boolean tryKill;


    public AttackKrawlGoal(MobEntity mobIn, boolean checkSight, boolean toKill) {
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

        List<EntityKrawl> nearbyMobs = mob.level.getEntitiesOfClass(EntityKrawl.class, mob.getBoundingBox().inflate(5, 5, 5));
        List<EntityKrawl> nonVortexKrawl = nearbyMobs.stream().filter(entityKrawl -> !entityKrawl.isVortex()).collect(Collectors.toList());
        if (!nonVortexKrawl.isEmpty()) {
            this.target = nonVortexKrawl.get(0);
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
    public void tick() {
        super.tick();
        this.mob.setTarget(this.target);
        ((EntitySpectrobe)this.mob).setIsAttacking(true);
        this.mob.getMoveControl().setWantedPosition(this.target.getX(), this.target.getY(), this.target.getZ(), 1);
        this.mob.setAggressive(true);
    }

    @Override
    public void start() {
        this.mob.setTarget(this.target);
        ((EntitySpectrobe)this.mob).setIsAttacking(true);
        this.mob.getMoveControl().setWantedPosition(this.target.getX(), this.target.getY(), this.target.getZ(), 1);
        this.mob.setAggressive(true);
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        ((EntitySpectrobe)this.mob).setIsAttacking(false);
        this.mob.setAggressive(false);
    }
}
