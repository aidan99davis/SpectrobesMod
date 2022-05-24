package com.spectrobes.spectrobesmod.common.entities.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.passive.TameableEntity;

public class MasterHurtTargetGoal extends OwnerHurtTargetGoal {
    public MasterHurtTargetGoal(TameableEntity entity) {
        super(entity);
    }

    @Override
    public boolean canUse() {
        return super.canUse() && (((EntitySpectrobe)mob).getStage() != SpectrobeProperties.Stage.CHILD);
    }
}
