package com.spectrobes.spectrobesmod.common.entities.krawl.goals;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityXelles;
import net.minecraft.entity.ai.goal.Goal;

import java.util.List;
import java.util.stream.Collectors;

public class HealKrawlGoal extends Goal {
    EntityXelles owner;

    public HealKrawlGoal(EntityXelles mob) {
        owner = mob;
    }

    @Override
    public boolean canUse() {
        return owner.getStage() > 1 && owner.canSpawnHealSpores() && owner.getTarget() != null;
    }

    @Override
    public void start() {
        super.start();

        List<EntityKrawl> nearbyKrawl = owner.level.getEntitiesOfClass(EntityKrawl.class, owner.getBoundingBox().inflate(10, 2, 10));

        List<EntityKrawl> filteredKrawl = nearbyKrawl.stream().filter(entityKrawl -> !entityKrawl.isVortex() && !(entityKrawl instanceof EntityXelles)).collect(Collectors.toList());

        if(filteredKrawl.size() > 0) {
            owner.spawnHealingSpores(filteredKrawl);
        }
    }
}
