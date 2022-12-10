package com.spectrobes.spectrobesmod.common.entities.krawl.goals;

import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.save_data.KrawlNest;
import com.spectrobes.spectrobesmod.common.save_data.SpectrobesWorldSaveData;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.player.Player;

import java.util.Random;

public class SpawnWaveGoal extends TargetGoal {
    SpectrobeProperties.Nature vortexNature;


    public SpawnWaveGoal(Mob mobIn) {
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

        return ((EntityVortex) mob).getWaves() > 0 && (mob).getTarget() != null;
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

        final int[] levelToSpawnAt = {1};

        if(!mob.level.isClientSide()) {
            SpectrobesWorldSaveData spectrobesWorldSaveData = SpectrobesWorldSaveData.getWorldData((ServerLevel) mob.level);

            KrawlNest nest = spectrobesWorldSaveData.getNest(mob.blockPosition());

            if(nest != null && nest.isAlive()) {
                if(mob.blockPosition().closerThan(nest.position, 500)) {
                    levelToSpawnAt[0] = 5;
                } else if(mob.blockPosition().closerThan(nest.position, 300)) {
                    levelToSpawnAt[0] = 10;
                } else if (mob.blockPosition().closerThan(nest.position, 100)) {
                    levelToSpawnAt[0] = 20;
                }
            }

            //check if player average spectrobes level is higher
            if(mob.getTarget() instanceof Player) {
                mob.getTarget().getCapability(SpectrobeMaster.INSTANCE).ifPresent(playerSpectrobeMaster -> {
                    if(playerSpectrobeMaster.getLevel() > levelToSpawnAt[0]) {
                        levelToSpawnAt[0] = playerSpectrobeMaster.getLevel();
                    }
                });
            }
            if(mob.getTarget() instanceof EntitySpectrobe) {
                if(((EntitySpectrobe)mob.getTarget()).getOwner() != null) {
                    ((EntitySpectrobe)mob.getTarget()).getOwner().getCapability(SpectrobeMaster.INSTANCE).ifPresent(playerSpectrobeMaster -> {
                        if(playerSpectrobeMaster.getLevel() > levelToSpawnAt[0]) levelToSpawnAt[0] = playerSpectrobeMaster.getLevel();
                    });
                } else {
                    if(((EntitySpectrobe)mob.getTarget()).getSpectrobeLevel() > levelToSpawnAt[0]) levelToSpawnAt[0] = ((EntitySpectrobe)mob.getTarget()).getSpectrobeLevel();
                }
            }

            int krawlInWave = random.nextInt(2) + 1;
            for (int i = 0; i < krawlInWave; i++) {
                EntityType<? extends EntityKrawl> krawl = KrawlEntities.getByLevel(levelToSpawnAt[0], mob.level);
                EntityKrawl krawl1 = krawl.create(mob.level);
                ((EntityVortex)mob).addKrawl(krawl1);
            }
        }


    }
}
