package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.KrawlRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public class EntityVizbar extends EntityKrawl {
    private static final RawAnimation IDLE_ANIMATION =
            RawAnimation.begin().thenLoop("animation.subar.idle");

    public EntityVizbar(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animationControllers;
    }

    @Override
    public PlayState moveController(AnimationState<EntityKrawl> animationState) {
        return animationState.setAndContinue(IDLE_ANIMATION);
    }

    @Override
    public KrawlProperties GetKrawlProperties() {
        return KrawlRegistry.Vizbar_Properties.copy();
    }

}
