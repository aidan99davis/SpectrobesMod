package com.spectrobes.spectrobesmod.common.entities.goals;

import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AttackSpectrobeMasterGoal extends TargetGoal {
    PlayerEntity target;
    boolean tryKill;


    public AttackSpectrobeMasterGoal(MobEntity mobIn, boolean checkSight, boolean toKill) {
        super(mobIn, checkSight, false);
        tryKill = toKill;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        if(!(goalOwner instanceof EntityKrawl))
            return false;

        List<PlayerEntity> nearbyPlayers = goalOwner.world.getEntitiesWithinAABB(PlayerEntity.class, goalOwner.getBoundingBox().grow(20, 20, 20));

        AtomicReference<PlayerEntity> toAttack = new AtomicReference<>();
        toAttack.set(null);

        for(PlayerEntity player : nearbyPlayers) {
            if (toAttack.get() != null || player.isCreative()) {
                break;
            }
            player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                if(sm.canFight()) {
                    toAttack.set(player);
                }
            });
        }

        if (toAttack.get() != null) {
            this.target = toAttack.get();
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
        ((EntityKrawl)this.goalOwner).setIsAttacking(true);
        this.goalOwner.getNavigator().setPath(this.goalOwner.getNavigator().getPathToEntity(this.target, 1), 3);
        this.goalOwner.setAggroed(true);
        super.startExecuting();
    }
}
