package com.spectrobes.spectrobesmod.common.entities.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.passive.TameableEntity;

public class MasterHurtByTargetGoal extends OwnerHurtByTargetGoal {
    public MasterHurtByTargetGoal(TameableEntity entity) {
        super(entity);
    }

    @Override
    public boolean canUse() {
        return super.canUse() && (((EntitySpectrobe)mob).getStage() != SpectrobeProperties.Stage.CHILD);
    }
}
