package com.spectrobes.spectrobesmod.common.entities.krawl.goals;

import com.spectrobes.spectrobesmod.common.blocks.krawl.MiniXellesBlockEntity;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

/**
 * A krawl from a mini xelles defence wave walks back to its mini xelles once it has had no enemy for a while,
 * and sinks into it. A wave that returns this way resets instead of counting as defeated.
 */
public class KrawlReturnToXellesGoal extends Goal {
    /** Ticks without an enemy before a wave krawl heads home. */
    private static final int CALM_TICKS = 100;
    /** Close enough to sink into the mini xelles. */
    private static final double ARRIVE_DISTANCE_SQR = 2.5D * 2.5D;
    /** Gives up walking and sinks in anyway, so a stuck krawl cannot hold a wave open forever. */
    private static final int MAX_RETURN_TICKS = 600;

    private final EntityKrawl krawl;
    private int returnTicks;

    public KrawlReturnToXellesGoal(EntityKrawl krawl) {
        this.krawl = krawl;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return krawl.getGuardPos() != null
                && krawl.getTarget() == null
                && krawl.getTicksWithoutTarget() >= CALM_TICKS;
    }

    @Override
    public boolean canContinueToUse() {
        return krawl.getGuardPos() != null && krawl.getTarget() == null;
    }

    @Override
    public void start() {
        returnTicks = 0;
        moveHome();
    }

    @Override
    public void stop() {
        krawl.getNavigation().stop();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        BlockPos home = krawl.getGuardPos();

        if (home == null) {
            return;
        }

        if (krawl.level().hasChunkAt(home)) {
            MiniXellesBlockEntity xelles = krawl.getGuardedXelles();

            if (xelles == null || xelles.isDead()) {
                // Its mini xelles is gone: the krawl is on its own now.
                krawl.clearGuardPos();
                return;
            }
        }

        returnTicks++;
        krawl.getLookControl().setLookAt(home.getX() + 0.5D, home.getY() + 0.5D, home.getZ() + 0.5D);

        if (krawl.distanceToSqr(home.getX() + 0.5D, home.getY(), home.getZ() + 0.5D) <= ARRIVE_DISTANCE_SQR
                || returnTicks >= MAX_RETURN_TICKS) {
            krawl.returnToXelles();
            return;
        }

        if (returnTicks % 20 == 0 || krawl.getNavigation().isDone()) {
            moveHome();
        }
    }

    private void moveHome() {
        BlockPos home = krawl.getGuardPos();

        if (home != null) {
            krawl.getNavigation().moveTo(home.getX() + 0.5D, home.getY(), home.getZ() + 0.5D, 0.5D);
        }
    }
}
