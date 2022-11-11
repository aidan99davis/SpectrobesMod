package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;

public class MasterHurtTargetGoal extends OwnerHurtTargetGoal {
    public MasterHurtTargetGoal(TamableAnimal entity) {
        super(entity);
    }

    @Override
    public boolean canUse() {
        return super.canUse() && (((EntitySpectrobe)mob).getStage() != SpectrobeProperties.Stage.CHILD);
    }
}
