package com.spectrobes.spectrobesmod.common.entities.goals;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.passive.TameableEntity;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class TargetKrawlGoal extends NearestAttackableTargetGoal {
    public TargetKrawlGoal(TameableEntity entity, Class targetClass, boolean mustSee, @Nullable Predicate predicate) {
        super(entity, targetClass, 0, mustSee, false,  predicate);
    }

    @Override
    public boolean canUse() {
        return super.canUse() && !(((EntitySpectrobe)mob).getStage() == SpectrobeProperties.Stage.CHILD);
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetConditions.test(this.mob, this.target);
    }
}
