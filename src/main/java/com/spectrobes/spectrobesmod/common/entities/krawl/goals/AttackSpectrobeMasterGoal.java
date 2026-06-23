package com.spectrobes.spectrobesmod.common.entities.krawl.goals;

import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class AttackSpectrobeMasterGoal extends TargetGoal {
    Player target;
    boolean tryKill;

    public AttackSpectrobeMasterGoal(Mob mobIn, boolean checkSight, boolean toKill) {
        super(mobIn, checkSight, false);
        tryKill = toKill;
    }

    @Override
    public boolean canUse() {
        if (!(mob instanceof EntityKrawl)) return false;

        List<Player> nearbyPlayers = mob.level().getEntitiesOfClass(
                Player.class, mob.getBoundingBox().inflate(20, 20, 20));

        // AtomicReference was previously used here for no reason in a
        // single-threaded context. It also broke on creative players: the old
        // `break` fired on the first creative player encountered, skipping any
        // valid non-creative players that appeared later in the list.
        for (Player player : nearbyPlayers) {
            if (player.isCreative() || player.isSpectator()) continue;
            this.target = player;
            return true;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (tryKill) {
            return super.canContinueToUse();
        }
        return target != null
                && target.isAlive()
                && target.getHealth() / target.getMaxHealth() > 0.2f
                && super.canContinueToUse();
    }

    @Override
    public void tick() {
        if (this.target != null && this.target.isAlive()) {
            this.mob.setTarget(this.target);
            this.mob.getNavigation().moveTo(
                    this.mob.getNavigation().createPath(this.target, 1), 0.5);
        }
        super.tick();
    }

    @Override
    public void start() {
        this.mob.setTarget(this.target);
        ((EntityKrawl)this.mob).setIsAttacking(true);
        this.mob.getNavigation().moveTo(this.mob.getNavigation().createPath(this.target, 1), 0.5);
        this.mob.setAggressive(true);
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        ((EntityKrawl)this.mob).setIsAttacking(false);
        this.mob.setAggressive(false);
    }
}