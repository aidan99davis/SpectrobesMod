package com.spectrobes.spectrobesmod.common.entities.krawl.goals;

import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityXelles;
import com.spectrobes.spectrobesmod.common.save_data.SpectrobesWorldSaveData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.List;
import java.util.stream.Collectors;

public class AbsorbKrawlGoal extends Goal {
    EntityXelles owner;

    public AbsorbKrawlGoal(EntityXelles mob) {
        owner = mob;
    }

    @Override
    public boolean canUse() {
        return owner.getStage() == 1;
    }

    @Override
    public void tick() {
        super.tick();
        List<EntityKrawl> nearbyKrawl = owner.level.getEntitiesOfClass(EntityKrawl.class, owner.getBoundingBox().inflate(10, 2, 10));
        List<EntityKrawl> filteredKrawl = nearbyKrawl.stream().filter(entityKrawl -> entityKrawl.isVortex() && !(entityKrawl instanceof EntityXelles)).collect(Collectors.toList());

        if(filteredKrawl.size() > 0) {
            SpectrobesWorldSaveData worldData = SpectrobesWorldSaveData.getWorldData((ServerLevel) owner.level);
            worldData.getNest(owner.blockPosition()).absorbVortexes(filteredKrawl.size());
            worldData.setDirty();

            filteredKrawl.forEach(entityKrawl -> entityKrawl.remove(Entity.RemovalReason.DISCARDED));
        }
    }
}
