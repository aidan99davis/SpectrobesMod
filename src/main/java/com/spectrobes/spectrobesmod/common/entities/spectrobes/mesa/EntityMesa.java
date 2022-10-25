package com.spectrobes.spectrobesmod.common.entities.spectrobes.mesa;

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

public class EntityMesa extends EntityMammalSpectrobe {

    public EntityMesa(EntityType<EntityMesa> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Mesa.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
//        return SpectrobesEntities.ENTITY_MESABONE.get();
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_mesa";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntityMesa.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_MESA.get();
    }

    @Override
    public <ENTITY extends EntitySpectrobe> PlayState moveController(AnimationEvent<ENTITY> event) {
        if(event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.mesa.walk", true));
            return PlayState.CONTINUE;
        }
        else if(event.getAnimatable().isOrderedToSit()) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.mesa.sitting", false)
                    .addAnimation("animation.mesa.sit", true));
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.mesa.idle", true));
            return PlayState.CONTINUE;
        }
    }


    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesItemsRegistry.mesa_fossil_item.get().getItem();
    }

    @Override
    public int getLitterSize() {
        return 0;
    }
}
