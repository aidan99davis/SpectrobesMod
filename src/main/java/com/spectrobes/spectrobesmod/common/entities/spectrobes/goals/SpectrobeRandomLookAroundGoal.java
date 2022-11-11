package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;

public class SpectrobeRandomLookAroundGoal extends RandomLookAroundGoal {
    EntitySpectrobe spectrobe;
    public SpectrobeRandomLookAroundGoal(EntitySpectrobe pMob) {
        super(pMob);
        spectrobe = pMob;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && spectrobe.getOwner() == null;
    }
}
