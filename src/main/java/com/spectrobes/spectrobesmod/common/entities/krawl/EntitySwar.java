package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.KrawlRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;

public class EntitySwar extends EntityKrawl {
    public EntitySwar(EntityType<? extends EntityKrawl> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animationControllers;
    }

    @Override
    public PlayState moveController(AnimationState<EntityKrawl> animationState) {
        animationState.getController().transitionLengthTicks = 2;
        if(animationState.isMoving()) {
            animationState.getController().setAnimation(new AnimationBuilder().addAnimation("animation.swar.walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;

        } else {
//            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.swar.attack", true));
            return PlayState.STOP;
        }
    }

    @Override
    public KrawlProperties GetKrawlProperties() {
        return KrawlRegistry.Swar_Properties.copy();
    }

}
