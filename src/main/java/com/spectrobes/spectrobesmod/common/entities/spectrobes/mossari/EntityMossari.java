package com.spectrobes.spectrobesmod.common.entities.spectrobes.mossari;

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
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityMossari extends EntityMammalSpectrobe {

    public EntityMossari(EntityType<EntityMossari> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Mossari.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_mossari";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntityMossari.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_MOSSARI.get();
    }

    @Override
    public <ENTITY extends EntitySpectrobe> PlayState moveController(AnimationEvent<ENTITY> event) {
        if(event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.mossari.walk", true));
            return PlayState.CONTINUE;
        } else if(event.getAnimatable().isSitting()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.mossari.sit", false));
            return PlayState.CONTINUE;
        }else {
            return PlayState.STOP;
        }
    }


    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    protected EvolutionRequirements getEvolutionRequirements() {
        return new EvolutionRequirements(1, 4, 0);
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesItemsRegistry.nagu_fossil_item.get().getItem();
    }

    @Override
    public int getLitterSize() {
        return 0;
    }
}
