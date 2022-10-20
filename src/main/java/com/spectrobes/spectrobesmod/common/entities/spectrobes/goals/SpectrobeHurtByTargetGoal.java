package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;

public class SpectrobeHurtByTargetGoal extends HurtByTargetGoal {
    public SpectrobeHurtByTargetGoal(CreatureEntity p_i50317_1_, Class<?>... p_i50317_2_) {
        super(p_i50317_1_, p_i50317_2_);
    }

    @Override
    public boolean canUse() {
        return super.canUse() && (((EntitySpectrobe)mob).getStage() != SpectrobeProperties.Stage.CHILD);
    }
}
