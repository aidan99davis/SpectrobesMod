package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals;


import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;

public class SpectrobeFindWaterGoal extends TryFindWaterGoal {
    private final EntitySpectrobe spectrobe;
    public SpectrobeFindWaterGoal(EntitySpectrobe entitySpectrobe) {
        super(entitySpectrobe);
        this.spectrobe = entitySpectrobe;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && spectrobe.getOwner() == null;
    }
}
