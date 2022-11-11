package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;

public class SpectrobeRandomSwimmingGoal extends RandomSwimmingGoal {
    EntitySpectrobe spectrobe;

    public SpectrobeRandomSwimmingGoal(EntitySpectrobe p_25753_, double p_25754_, int p_25755_) {
        super(p_25753_, p_25754_, p_25755_);
        this.spectrobe = p_25753_;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && spectrobe.getOwner() == null;
    }
}
