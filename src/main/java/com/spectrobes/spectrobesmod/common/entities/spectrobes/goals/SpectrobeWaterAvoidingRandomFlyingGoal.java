package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;

public class SpectrobeWaterAvoidingRandomFlyingGoal extends WaterAvoidingRandomFlyingGoal {
    EntitySpectrobe spectrobe;

    public SpectrobeWaterAvoidingRandomFlyingGoal(EntitySpectrobe pMob, double pSpeedModifier) {
        super(pMob, pSpeedModifier);
        spectrobe = pMob;
    }

    @Override
    public boolean canUse() {
        return super.canUse()
                && (spectrobe.getOwner() == null
                && spectrobe.getNature() == SpectrobeProperties.Nature.CORONA);
    }
}
