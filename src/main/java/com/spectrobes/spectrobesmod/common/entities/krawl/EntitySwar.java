package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.KrawlRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.core.PlayState;
import software.bernie.geckolib.core.builder.AnimationBuilder;
import software.bernie.geckolib.core.event.predicate.AnimationEvent;
import software.bernie.geckolib.core.manager.AnimationFactory;

public class EntitySwar extends EntityKrawl {
    public EntitySwar(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntityKrawl> PlayState moveController(AnimationEvent<ENTITY> entityAnimationTestEvent) {
        moveController.transitionLengthTicks = 2;
        if(!IsAttacking()) {
            moveController.setAnimation(new AnimationBuilder().addAnimation("animation.swar.idle", true));
            return PlayState.CONTINUE;

        } else {
            moveController.setAnimation(new AnimationBuilder().addAnimation("animation.swar.attack", true));
            return PlayState.CONTINUE;
        }
    }

    @Override
    protected KrawlProperties GetKrawlProperties() {
        return KrawlRegistry.Swar_Properties.copy();
    }

}
