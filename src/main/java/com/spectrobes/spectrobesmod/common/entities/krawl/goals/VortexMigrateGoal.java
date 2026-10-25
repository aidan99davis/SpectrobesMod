package com.spectrobes.spectrobesmod.common.entities.krawl.goals;

import com.spectrobes.spectrobesmod.common.config.KrawlConfig;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import com.spectrobes.spectrobesmod.common.krawl.KrawlInfectionManager;
import com.spectrobes.spectrobesmod.common.save_data.KrawlNest;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.UUID;

/**
 * A full or aged vortex heads for the nearest nest (active or inactive) and merges into it. With no nest in
 * range it founds one if it carries enough biomass. If it cannot make progress toward the nest and no player
 * can see it, it merges remotely.
 */
public class VortexMigrateGoal extends Goal {
    private static final double MERGE_DISTANCE = 4.0D;
    private static final int WAYPOINT_DISTANCE = 24;

    private final EntityVortex vortex;
    @Nullable
    private UUID nestId;
    @Nullable
    private BlockPos targetPos;
    private double bestDistanceSqr;
    private int stuckTicks;
    private int retryCooldown;

    public VortexMigrateGoal(EntityVortex vortex) {
        this.vortex = vortex;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!(vortex.level() instanceof ServerLevel level) || !vortex.isReadyToMerge() || vortex.getTarget() != null) {
            return false;
        }

        if (--retryCooldown > 0) {
            return false;
        }

        retryCooldown = 200;
        KrawlNest nest = KrawlInfectionManager.findNestInRange(level, vortex.blockPosition());

        if (nest != null) {
            nestId = nest.id;
            targetPos = nest.heart;
            return true;
        }

        if (vortex.getBiomass() >= KrawlConfig.NEST_FOUNDING_BIOMASS.get()) {
            BlockPos spot = KrawlInfectionManager.findFoundingSpot(level, vortex.blockPosition(), vortex.getRandom());

            if (spot != null) {
                KrawlNest founded = KrawlInfectionManager.foundNest(level, spot, 0);

                if (founded != null) {
                    vortex.mergeInto(level, founded);
                }
            }
        }

        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return targetPos != null && vortex.isAlive() && vortex.getTarget() == null;
    }

    @Override
    public void start() {
        bestDistanceSqr = vortex.distanceToSqr(Vec3.atCenterOf(targetPos));
        stuckTicks = 0;
        moveTowardTarget();
    }

    @Override
    public void stop() {
        targetPos = null;
        nestId = null;
        retryCooldown = 40;
        vortex.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (!(vortex.level() instanceof ServerLevel level) || targetPos == null) {
            return;
        }

        KrawlNest nest = KrawlInfectionManager.getNest(level, nestId);

        if (nest == null) {
            targetPos = null;
            return;
        }

        double distanceSqr = vortex.distanceToSqr(Vec3.atCenterOf(targetPos));

        if (distanceSqr <= MERGE_DISTANCE * MERGE_DISTANCE) {
            vortex.mergeInto(level, nest);
            return;
        }

        stuckTicks++;

        if (distanceSqr < bestDistanceSqr - 1.0D) {
            bestDistanceSqr = distanceSqr;
            stuckTicks = 0;
        }

        int stuckLimit = KrawlConfig.VORTEX_STUCK_TICKS.get();

        if (stuckTicks >= stuckLimit) {
            if (isUnseen(level)) {
                vortex.mergeInto(level, nest);
                return;
            }

            // Someone is watching: keep trying for a while longer.
            stuckTicks = stuckLimit / 2;
        }

        if (vortex.tickCount % 20 == 0 && (vortex.getNavigation().isDone() || stuckTicks > 40)) {
            moveTowardTarget();
        }
    }

    private void moveTowardTarget() {
        Vec3 position = vortex.position();
        Vec3 target = Vec3.atBottomCenterOf(targetPos);
        Vec3 delta = target.subtract(position);
        double distance = delta.horizontalDistance();

        if (distance <= WAYPOINT_DISTANCE) {
            vortex.getNavigation().moveTo(target.x, target.y, target.z, 1.0D);
            return;
        }

        double x = position.x + delta.x / distance * WAYPOINT_DISTANCE;
        double z = position.z + delta.z / distance * WAYPOINT_DISTANCE;
        BlockPos column = BlockPos.containing(x, position.y, z);
        double y = vortex.level().hasChunkAt(column)
                ? vortex.level().getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, column.getX(), column.getZ())
                : position.y;

        vortex.getNavigation().moveTo(x, y, z, 1.0D);
    }

    private boolean isUnseen(ServerLevel level) {
        int distance = KrawlConfig.VORTEX_UNSEEN_DISTANCE.get();

        for (Player player : level.players()) {
            if (player.isSpectator()) {
                continue;
            }

            if (player.distanceToSqr(vortex) <= distance * distance && player.hasLineOfSight(vortex)) {
                return false;
            }
        }

        return true;
    }
}
