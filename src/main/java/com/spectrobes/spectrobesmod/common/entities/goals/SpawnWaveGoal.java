package com.spectrobes.spectrobesmod.common.entities.goals;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class SpawnWaveGoal extends TargetGoal {
    SpectrobeProperties.Nature vortexNature;


    public SpawnWaveGoal(MobEntity mobIn) {
        super(mobIn, true, false);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        if(!(goalOwner instanceof EntityKrawl)) {
            return false;
        }

        if(((EntityVortex)goalOwner).getWaves() > 0 && (goalOwner).getAttackTarget() != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldContinueExecuting() {
        boolean shouldContinue = ((EntityVortex)goalOwner).getWaves() > 0;

        if(!shouldContinue) {
            goalOwner.onKillCommand();
        }

        return shouldContinue;
    }

    @Override
    public void startExecuting() {
        vortexNature = ((EntityKrawl)goalOwner).getNature();
        goalOwner.setInvulnerable(true);
        goalOwner.setAIMoveSpeed(0);
    }

    @Override
    public void tick() {
        ((EntityVortex)goalOwner).validateWave();

        if (((EntityVortex)goalOwner).getKrawlWave().isEmpty()) {
            spawnWave();
        }
    }

    private void spawnWave() {
        Random random = new Random();
        int krawlInWave = random.nextInt(2) + 1;

        for (int i = 0; i < krawlInWave; i++) {
            EntityType<? extends EntityKrawl> krawl = KrawlEntities.getByNature(vortexNature);
            ((EntityVortex)goalOwner).addKrawl(krawl.create(goalOwner.world));
        }

    }
}
