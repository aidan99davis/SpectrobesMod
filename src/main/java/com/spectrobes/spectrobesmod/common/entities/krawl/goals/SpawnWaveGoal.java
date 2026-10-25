package com.spectrobes.spectrobesmod.common.entities.krawl.goals;

import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.player.Player;

import java.util.EnumSet;

public class SpawnWaveGoal extends TargetGoal {

    private SpectrobeProperties.Nature vortexNature;

    public SpawnWaveGoal(Mob mobIn) {
        super(mobIn, true, false);
        this.setFlags(EnumSet.of(Flag.TARGET, Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!(mob instanceof EntityVortex vortex)) {
            return false;
        }

        return vortex.getWaves() > 0 && mob.getTarget() != null;
    }

    @Override
    public boolean canContinueToUse() {
        if (!(mob instanceof EntityVortex vortex)) return false;

        // Keep fighting while the target is around or a wave is still alive.
        return vortex.getWaves() > 0 && (mob.getTarget() != null || vortex.isWaveInProgress());
    }

    @Override
    public void start() {
        if (mob instanceof EntityKrawl krawl) {
            vortexNature = krawl.getNature();
        }

        mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (!(mob instanceof EntityVortex vortex)) {
            return;
        }

        vortex.validateWave();

        if (vortex.getWaves() <= 0) {
            vortex.onWavesDefeated();
            return;
        }

        if (!vortex.isWaveInProgress() && mob.getTarget() != null) {
            spawnWave(vortex);
        }
    }

    private void spawnWave(EntityVortex vortex) {
        if (!(mob.level() instanceof ServerLevel serverLevel)) return;

        RandomSource random = mob.getRandom();
        int levelToSpawnAt = 1;

        if (mob.getTarget() instanceof Player player) {
            PlayerSpectrobeMaster pMaster = player.getCapability(SpectrobeMaster.INSTANCE);
            if (pMaster != null && pMaster.getLevel() > levelToSpawnAt) {
                levelToSpawnAt = pMaster.getLevel();
            }
        }

        if (mob.getTarget() instanceof EntitySpectrobe spectrobeTarget) {
            if (spectrobeTarget.getOwner() != null) {
                PlayerSpectrobeMaster pMaster =
                        spectrobeTarget.getOwner().getCapability(SpectrobeMaster.INSTANCE);
                if (pMaster != null && pMaster.getLevel() > levelToSpawnAt) {
                    levelToSpawnAt = pMaster.getLevel();
                }
            } else if (spectrobeTarget.getSpectrobeLevel() > levelToSpawnAt) {
                levelToSpawnAt = spectrobeTarget.getSpectrobeLevel();
            }
        }

        int krawlInWave = random.nextInt(2) + 1;

        for (int i = 0; i < krawlInWave; i++) {
            EntityType<? extends EntityKrawl> krawlType =
                    KrawlEntities.getByLevel(levelToSpawnAt, serverLevel);
            EntityKrawl krawl = krawlType.create(serverLevel);

            if (krawl != null) {
                // Spawn near the vortex with a small random offset so krawl
                // don't all stack on the same block.
                double offsetX = (random.nextDouble() - 0.5D) * 4.0D;
                double offsetZ = (random.nextDouble() - 0.5D) * 4.0D;
                krawl.moveTo(
                        mob.getX() + offsetX,
                        mob.getY(),
                        mob.getZ() + offsetZ,
                        random.nextFloat() * 360.0F, 0.0F);

                serverLevel.addFreshEntity(krawl);
                krawl.setTarget(mob.getTarget());
                vortex.addKrawl(krawl);
            }
        }

        vortex.setWaveInProgress(true);
    }
}