package com.spectrobes.spectrobesmod.common.entities.goals;

import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.TargetGoal;

import java.util.Random;

public class SpawnWaveGoal extends TargetGoal {
    SpectrobeProperties.Nature vortexNature;


    public SpawnWaveGoal(MobEntity mobIn) {
        super(mobIn, true, false);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean canUse() {
        if(!(mob instanceof EntityKrawl)) {
            return false;
        }

        if(((EntityVortex)mob).getWaves() > 0 && (mob).getTarget() != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        EntityVortex owner = ((EntityVortex)mob);
        boolean shouldContinue = owner.getWaves() > 0;
        if(!shouldContinue) {
            mob.kill();
        }

        return shouldContinue;
    }

    @Override
    public void start() {
        vortexNature = ((EntityKrawl)mob).getNature();
        mob.setInvulnerable(true);
        mob.setSpeed(0);
    }

    @Override
    public void tick() {
        ((EntityVortex)mob).validateWave();

        if (((EntityVortex)mob).getKrawlWave().isEmpty()) {
            spawnWave();
        }
    }

    private void spawnWave() {
        Random random = new Random();
        int krawlInWave = random.nextInt(2) + 1;
        for (int i = 0; i < krawlInWave; i++) {
            EntityType<? extends EntityKrawl> krawl = KrawlEntities.getByNature(vortexNature);
            ((EntityVortex)mob).addKrawl(krawl.create(mob.level));
        }
    }
}
