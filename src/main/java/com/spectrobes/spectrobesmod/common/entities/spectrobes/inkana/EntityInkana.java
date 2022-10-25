package com.spectrobes.spectrobesmod.common.entities.spectrobes.inkana;

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

public class EntityInkana extends EntityMammalSpectrobe {

    public EntityInkana(EntityType<EntityInkana> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Inkana.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
//        return SpectrobesEntities.ENTITY_DONGORA.get();
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_inkana";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntityInkana.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_INKANA.get();
    }

    @Override
    public <ENTITY extends EntitySpectrobe> PlayState moveController(AnimationEvent<ENTITY> event) {
        if(event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.inkana.walk", true));
            return PlayState.CONTINUE;
        }
        else if(event.getAnimatable().isOrderedToSit()) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.inkana.sitting", false)
                    .addAnimation("animation.inkana.sit", true));
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.inkana.idle", true));
            return PlayState.CONTINUE;
        }
    }


    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesItemsRegistry.inkana_fossil_item.get().getItem();
    }

    @Override
    public int getLitterSize() {
        return 0;
    }
}
