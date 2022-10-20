package com.spectrobes.spectrobesmod.common.entities.krawl.goals;

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
    public boolean canUse() {
        if(!(mob instanceof EntityKrawl))
            return false;

        List<PlayerEntity> nearbyPlayers = mob.level.getEntitiesOfClass(PlayerEntity.class, mob.getBoundingBox().inflate(20, 20, 20));

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
        this.mob.getNavigation().moveTo(this.mob.getNavigation().createPath(this.target, 1), 1.2);
        this.mob.setAggressive(true);
        super.start();
    }
}
