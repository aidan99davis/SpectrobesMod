package com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko;

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

public class EntitySpikan extends EntityMammalSpectrobe {


    public EntitySpikan(EntityType<EntitySpikan> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Spikan.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_spikan";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntitySpikan.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_SPIKO.get();
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntitySpectrobe> PlayState moveController(AnimationEvent<ENTITY> event)
    {
        event.getController().transitionLengthTicks = 2;
        if(event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spikan.walk", true));
            return PlayState.CONTINUE;
        }
        else if(this.isOrderedToSit()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spikan.sit", false));
            return PlayState.CONTINUE;
        } else {
            if(this.getKillCredit() != null) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spikan.walk", true));
                return PlayState.CONTINUE;
            }
        }
        return PlayState.STOP;
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesItemsRegistry.spiko_fossil_item.get().getItem();
    }

    @Override
    public int getLitterSize() {
        return 3;
    }
}
