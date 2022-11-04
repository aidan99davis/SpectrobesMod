package com.spectrobes.spectrobesmod.common.entities.spectrobes.vilar;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityMammalSpectrobe;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.fossils.FossilBlockItem;
import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesFossilsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityVilar extends EntityMammalSpectrobe {

    public EntityVilar(EntityType<EntityVilar> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Vilar.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return SpectrobesEntities.ENTITY_VILAMASTA.get();
    }

    @Override
    public String getRegistryName() {
        return "entity_vilar";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntityVilar.class;
    }

    @Override
    protected EntityType<EntityVilar> getChildForLineage() {
        return SpectrobesEntities.ENTITY_VILAR.get();
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntitySpectrobe> PlayState moveController(AnimationEvent<ENTITY> event) {
        event.getController().transitionLengthTicks = 2;
        if(event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.vilar.walk", true));
            return PlayState.CONTINUE;
        }
        else if(this.isOrderedToSit()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.vilar.idle", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;

    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesFossilsRegistry.vilar_fossil_item.get();
    }

    @Override
    public int getLitterSize() {
        return 0;
    }
}
