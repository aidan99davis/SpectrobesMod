package com.spectrobes.spectrobesmod.common.entities.spectrobes.aoi;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityAvianSpectrobe;
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

public class EntityAoi extends EntityAvianSpectrobe {

    public EntityAoi(EntityType<EntityAoi> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Aoi.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
//        return SpectrobesEntities.ENTITY_AOBA.get();
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_aoi";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntityAoi.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_AOI.get();
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntitySpectrobe> PlayState moveController(AnimationEvent<ENTITY> event) {
        if(event.getAnimatable().isOrderedToSit())
        {
            event.getController().setAnimationSpeed(2);
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.aoi.sit", true));
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimationSpeed(5);
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.aoi.idle", true));
            return PlayState.CONTINUE;
        }

    }

    @Override
    protected EvolutionRequirements getEvolutionRequirements() {
        return new EvolutionRequirements(1, 6, 0);
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesItemsRegistry.aoi_fossil_item.get();
    }

    @Override
    protected int getMaxLitterSize() {
        return 0;
    }
}
