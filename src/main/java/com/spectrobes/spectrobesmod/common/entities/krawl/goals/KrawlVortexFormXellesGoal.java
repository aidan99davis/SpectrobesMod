package com.spectrobes.spectrobesmod.common.entities.krawl.goals;

import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import com.spectrobes.spectrobesmod.common.save_data.KrawlNest;
import com.spectrobes.spectrobesmod.common.save_data.SpectrobesWorldSaveData;
import com.spectrobes.spectrobesmod.common.world.structures.krawl_nest.WorldGenKrawlNest;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class KrawlVortexFormXellesGoal extends Goal {
    EntityVortex vortex;

    public KrawlVortexFormXellesGoal(EntityVortex vortex) {
        this.vortex = vortex;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        SpectrobesWorldSaveData worldData = (SpectrobesWorldSaveData.getWorldData((ServerLevel) vortex.level));

        return vortex.getAge() >= 1 //TODO: Make this configurable
                && ((worldData.canSpawnNest((vortex.blockPosition()))
                || (worldData.getNest(vortex.blockPosition()) != null
                && worldData.getNest(vortex.blockPosition()).stage == 1)));
    }

    @Override
    public void tick() {
        List<EntityVortex> nearbyMobs = vortex.level.getEntitiesOfClass(EntityVortex.class, vortex.getBoundingBox().inflate(15, 3, 15));
        List<EntityVortex> nestingVortexes = nearbyMobs.stream().filter(v -> v.getAge() >= 0).collect(Collectors.toList());

        if (!nestingVortexes.isEmpty()) {

            if(nestingVortexes.size() >= 3) {

                int extraVortexes = nestingVortexes.size() - 3;
                //delete the vortexes.
                BlockPos vortexPos = vortex.blockPosition();
                Level level = vortex.level;
                WorldGenKrawlNest nestGen = new WorldGenKrawlNest(NoneFeatureConfiguration.CODEC);
                if (!level.isClientSide() && level instanceof ServerLevel) {
                    if(SpectrobesWorldSaveData.getWorldData((ServerLevel) level).canSpawnNest((vortexPos))) {

                        nestingVortexes.forEach(entityVortex -> entityVortex.remove(Entity.RemovalReason.DISCARDED));
                        if(vortexPos.getY() < 10) vortexPos.offset(0, 10 - vortexPos.getY(), 0);
                        nestGen.placeSmallGen((ServerLevel) level, new LegacyRandomSource(224752345), vortexPos);

                        SpectrobesWorldSaveData data = SpectrobesWorldSaveData.getWorldData((ServerLevel) level);

                        //create xelle mob.
                        data.addNest(new KrawlNest(vortexPos, level.dimension().toString()));
                        data.getNest(vortexPos).absorbVortexes(extraVortexes);
                        data.setDirty();
                    }
                }
            } else {
                EntityVortex nearbyVortex = nestingVortexes.get(0);
                this.vortex.getMoveControl().setWantedPosition(nearbyVortex.getX(), nearbyVortex.getY(), nearbyVortex.getZ(), 1);
            }
        }
    }
}
