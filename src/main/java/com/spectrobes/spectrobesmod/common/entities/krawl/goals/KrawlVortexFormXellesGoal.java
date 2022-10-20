package com.spectrobes.spectrobesmod.common.entities.krawl.goals;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import com.spectrobes.spectrobesmod.common.save_data.KrawlNest;
import com.spectrobes.spectrobesmod.common.save_data.SpectrobesWorldSaveData;
import com.spectrobes.spectrobesmod.common.world.structures.krawl_nest.WorldGenKrawlNest;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.server.ServerWorld;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class KrawlVortexFormXellesGoal extends Goal {
    EntityVortex vortex;

    public KrawlVortexFormXellesGoal(EntityVortex vortex) {
        this.vortex = vortex;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        SpectrobesWorldSaveData worldData = (SpectrobesWorldSaveData.getWorldData((ServerWorld) vortex.level));

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
                IWorld level = vortex.level;
                WorldGenKrawlNest nestGen = new WorldGenKrawlNest(NoFeatureConfig.CODEC);
                if (!level.isClientSide() && level instanceof ServerWorld) {
                    if(SpectrobesWorldSaveData.getWorldData((ServerWorld) level).canSpawnNest((vortexPos))) {

                        nestingVortexes.forEach(entityVortex -> entityVortex.remove());
                        if(vortexPos.getY() < 10) vortexPos.offset(0, 10 - vortexPos.getY(), 0);
                        nestGen.placeSmallGen((ServerWorld) level, new Random(), vortexPos);

                        SpectrobesWorldSaveData data = SpectrobesWorldSaveData.getWorldData((ServerWorld) level);

                        //create xelle mob.
                        SpectrobesInfo.LOGGER.debug("SPAWNING XELLES AT: " + vortexPos.getX() + ", " + vortexPos.getY() + ", " + vortexPos.getZ());

                        data.addNest(new KrawlNest(vortexPos, ((ServerWorld) level).dimension().getRegistryName().toString()));
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
