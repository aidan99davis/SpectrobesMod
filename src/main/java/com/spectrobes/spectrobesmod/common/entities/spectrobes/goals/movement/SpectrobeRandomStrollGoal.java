package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.movement;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;

public class SpectrobeRandomStrollGoal extends RandomStrollGoal {
    EntitySpectrobe spectrobe;

    public SpectrobeRandomStrollGoal(EntitySpectrobe pMob, double pSpeedModifier) {
        super(pMob, pSpeedModifier);
        spectrobe = pMob;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && spectrobe.getOwner() == null;
    }
}
