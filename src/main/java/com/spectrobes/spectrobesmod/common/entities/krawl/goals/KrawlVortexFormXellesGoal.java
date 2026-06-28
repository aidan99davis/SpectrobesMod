package com.spectrobes.spectrobesmod.common.entities.krawl.goals;

import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import com.spectrobes.spectrobesmod.common.krawl.KrawlInfectionManager;
import com.spectrobes.spectrobesmod.common.save_data.SpectrobesWorldSaveData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class KrawlVortexFormXellesGoal extends Goal {
    EntityVortex vortex;

    public KrawlVortexFormXellesGoal(EntityVortex vortex) {
        this.vortex = vortex;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (vortex.level().isClientSide()) return false;

        SpectrobesWorldSaveData worldData =
                SpectrobesWorldSaveData.getWorldData((ServerLevel) vortex.level());

        return vortex.getAge() >= 1
                && (worldData.canSpawnNest(vortex.blockPosition()));
    }

    @Override
    public void tick() {
        KrawlInfectionManager.tryCreateNestFromVortexCluster(vortex);
    }
}