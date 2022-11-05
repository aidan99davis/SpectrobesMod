package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class SpectrobeMeleeAttackGoal extends MeleeAttackGoal {
    public SpectrobeMeleeAttackGoal(PathfinderMob p_i1636_1_, double p_i1636_2_, boolean p_i1636_4_) {
        super(p_i1636_1_, p_i1636_2_, p_i1636_4_);
    }

    @Override
    public boolean canUse() {
        return super.canUse() && (((EntitySpectrobe)mob).getStage() != SpectrobeProperties.Stage.CHILD);
    }
}
