package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.KrawlRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityGrisen extends EntityKrawl {
    public EntityGrisen(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntityKrawl> PlayState moveController(AnimationEvent<ENTITY> event) {
//        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.subar.idle", true));
        return PlayState.STOP;
    }

    @Override
    public KrawlProperties GetKrawlProperties() {
        return KrawlRegistry.Grisen_Properties.copy();
    }

}
