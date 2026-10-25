package com.spectrobes.spectrobesmod.common.entities.krawl.goals;

import com.spectrobes.spectrobesmod.common.config.KrawlConfig;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.krawl.KrawlInfectionManager;
import com.spectrobes.spectrobesmod.common.krawl.KrawlTerrain;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.neoforged.neoforge.common.Tags;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.EnumSet;

/**
 * A vortex hunts nearby mobs and consumes them, gaining biomass from their health.
 */
public class VortexForageGoal extends Goal {
    private static final int MAX_CHASE_TICKS = 600;

    private final EntityVortex vortex;
    @Nullable
    private Mob prey;
    private int searchCooldown;
    private int chaseTicks;

    public VortexForageGoal(EntityVortex vortex) {
        this.vortex = vortex;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (vortex.level().isClientSide() || vortex.isReadyToMerge() || vortex.getTarget() != null) {
            return false;
        }

        if (--searchCooldown > 0) {
            return false;
        }

        searchCooldown = 20 + vortex.getRandom().nextInt(20);
        prey = findPrey();
        return prey != null;
    }

    @Override
    public boolean canContinueToUse() {
        int range = KrawlConfig.VORTEX_FORAGE_RANGE.get() * 2;

        return prey != null
                && prey.isAlive()
                && vortex.getTarget() == null
                && !vortex.isReadyToMerge()
                && chaseTicks < MAX_CHASE_TICKS
                && vortex.distanceToSqr(prey) < range * range;
    }

    @Override
    public void start() {
        chaseTicks = 0;
        vortex.getNavigation().moveTo(prey, 1.0D);
    }

    @Override
    public void stop() {
        prey = null;
        vortex.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (prey == null) {
            return;
        }

        chaseTicks++;
        vortex.getLookControl().setLookAt(prey, 30.0F, 30.0F);

        double reach = vortex.getBbWidth() / 2.0D + prey.getBbWidth() / 2.0D + 1.0D;

        if (vortex.distanceToSqr(prey) <= reach * reach) {
            consume(prey);
            prey = null;
            return;
        }

        if (chaseTicks % 10 == 0) {
            vortex.getNavigation().moveTo(prey, 1.0D);
        }
    }

    private void consume(Mob food) {
        if (!(vortex.level() instanceof ServerLevel level)) {
            return;
        }

        vortex.addBiomass(food.getMaxHealth() * KrawlConfig.VORTEX_BIOMASS_PER_HEALTH.get());
        KrawlInfectionManager.puff(level, food);
        level.playSound(null, food.blockPosition(), SoundEvents.SCULK_CATALYST_BLOOM, SoundSource.HOSTILE, 1.0F, 1.4F);
        food.discard();
    }

    @Nullable
    private Mob findPrey() {
        int range = KrawlConfig.VORTEX_FORAGE_RANGE.get();

        return vortex.level()
                .getEntitiesOfClass(Mob.class, vortex.getBoundingBox().inflate(range, range / 2.0D, range), VortexForageGoal::isEdible)
                .stream()
                .min(Comparator.comparingDouble(vortex::distanceToSqr))
                .orElse(null);
    }

    /** Any mob except krawl, spectrobes, pets, bosses, named or leashed mobs and the krawl_inedible tag. */
    public static boolean isEdible(Mob mob) {
        if (!mob.isAlive() || mob instanceof EntityKrawl || mob instanceof EntitySpectrobe) {
            return false;
        }

        if (mob instanceof OwnableEntity ownable && ownable.getOwnerUUID() != null) {
            return false;
        }

        return !mob.getType().is(Tags.EntityTypes.BOSSES)
                && !mob.getType().is(KrawlTerrain.KRAWL_INEDIBLE)
                && !mob.hasCustomName()
                && !mob.isLeashed();
    }
}
