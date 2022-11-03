package com.spectrobes.spectrobesmod.common.entities.krawl.goals;

import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.player.Player;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AttackSpectrobeMasterGoal extends TargetGoal {
    Player target;
    boolean tryKill;


    public AttackSpectrobeMasterGoal(Mob mobIn, boolean checkSight, boolean toKill) {
        super(mobIn, checkSight, false);
        tryKill = toKill;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean canUse() {
        if(!(mob instanceof EntityKrawl))
            return false;

        List<Player> nearbyPlayers = mob.level.getEntitiesOfClass(Player.class, mob.getBoundingBox().inflate(20, 20, 20));

        AtomicReference<Player> toAttack = new AtomicReference<>();
        toAttack.set(null);

        for(Player player : nearbyPlayers) {
            if (toAttack.get() != null || player.isCreative()) {
                break;
            }
            toAttack.set(player);

        }

        if (toAttack.get() != null) {
            this.target = toAttack.get();
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
        this.mob.getNavigation().moveTo(this.mob.getNavigation().createPath(this.target, 1), 0.5);
        this.mob.setAggressive(true);
        super.start();
    }
}
