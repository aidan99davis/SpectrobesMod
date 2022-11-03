package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class TargetKrawlGoal extends NearestAttackableTargetGoal {
    public TargetKrawlGoal(TamableAnimal entity, Class targetClass, boolean mustSee, @Nullable Predicate predicate) {
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
