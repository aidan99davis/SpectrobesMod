package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.KrawlRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntitySwar extends EntityKrawl {
    public EntitySwar(EntityType<? extends EntityKrawl> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntityKrawl> PlayState moveController(AnimationEvent<ENTITY> event) {
        event.getController().transitionLengthTicks = 2;
        if(event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.swar.walk", true));
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
