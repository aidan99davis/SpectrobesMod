package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.KrawlRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;

public class EntitySwar extends EntityKrawl {
    public EntitySwar(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public EntityAnimationManager getAnimationManager() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntityKrawl> boolean moveController(AnimationTestEvent<ENTITY> entityAnimationTestEvent) {
        moveController.transitionLengthTicks = 2;
        if(!IsAttacking()) {
            animationControllers.setAnimationSpeed(0.125);
            moveController.setAnimation(new AnimationBuilder().addAnimation("animation.swar.idle", true));
            return true;

        } else {
            animationControllers.setAnimationSpeed(1);
            moveController.setAnimation(new AnimationBuilder().addAnimation("animation.swar.attack", true));
            return true;
        }
    }

    @Override
    protected KrawlProperties GetKrawlProperties() {
        return KrawlRegistry.Swar_Properties.copy();
    }

}
