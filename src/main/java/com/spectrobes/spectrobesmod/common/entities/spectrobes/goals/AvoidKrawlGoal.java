package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;

public class AvoidKrawlGoal extends AvoidEntityGoal {
    public AvoidKrawlGoal(CreatureEntity entity, Class toAvoid, float maxDist, double walkSpeedModifier, double sprintSpeedModifier) {
        super(entity, toAvoid, maxDist, walkSpeedModifier, sprintSpeedModifier);
    }

    @Override
    public boolean canUse() {
        return ((EntitySpectrobe)mob).getStage() == SpectrobeProperties.Stage.CHILD && super.canUse();
    }
}
