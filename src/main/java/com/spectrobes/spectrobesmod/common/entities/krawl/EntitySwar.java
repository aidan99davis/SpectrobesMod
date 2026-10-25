package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.data.KrawlRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public class EntitySwar extends EntityKrawl {
    private static final RawAnimation WALK_ANIMATION =
            RawAnimation.begin().thenLoop("animation.swar.walk");

    public EntitySwar(EntityType<? extends EntityKrawl> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationControllers;
    }

    @Override
    public PlayState moveController(AnimationState<EntityKrawl> animationState) {
        animationState.getController().setAnimationSpeed(1.0D);
        animationState.getController().transitionLength(2);

        if (animationState.isMoving()) {
            return animationState.setAndContinue(WALK_ANIMATION);
        }

        return PlayState.STOP;
    }

    @Override
    public KrawlProperties GetKrawlProperties() {
        return KrawlRegistry.Swar_Properties.copy();
    }
}