package com.spectrobes.spectrobesmod.common.entities.spectrobes.grilda;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityMammalSpectrobe;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.fossils.FossilBlockItem;
import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesItemsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.EvolutionRequirements;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityGrilden extends EntityMammalSpectrobe {


    public EntityGrilden(EntityType<EntityGrilden> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Grilden.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_grilden";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntityGrilden.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_GRILDA.get();
    }

    public <ENTITY extends EntitySpectrobe> PlayState headController(AnimationEvent<ENTITY> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grilden.idle", true));
        return PlayState.CONTINUE;
    }

    protected AnimationController headAnimationController = new AnimationController(this, "headAnimationController", 10F, this::headController);

    @Override
    public void registerControllers(AnimationData data)
    {
        super.registerControllers(data);
        data.addAnimationController(headAnimationController);
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntitySpectrobe> PlayState moveController(AnimationEvent<ENTITY> event)
    {
        if(event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grilden.walk", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesItemsRegistry.grilda_fossil_item.get();
    }

    @Override
    public int getLitterSize() {
        return 3;
    }
}
