package com.spectrobes.spectrobesmod.common.entities.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.FindWaterGoal;

public class SpectrobeFindWaterGoal extends FindWaterGoal {
    private final CreatureEntity spectrobe;

    public SpectrobeFindWaterGoal(CreatureEntity p_i48936_1_) {
        super(p_i48936_1_);
        this.spectrobe = p_i48936_1_;
    }

    @Override
    public boolean canUse() {
        EntitySpectrobe spectrobeEntity = ((EntitySpectrobe)this.spectrobe);
        if(spectrobeEntity.getOwner() != null &&
            spectrobeEntity.getState() == 0){
            return false;
        }
        return super.canUse();
    }
}
