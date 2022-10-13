package com.spectrobes.spectrobesmod.common.entities.spectrobes.nagu;

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

public class EntityNaguryu extends EntityMammalSpectrobe {

    public EntityNaguryu(EntityType<EntityNaguryu> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Naguryu.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_naguryu";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntityNaguryu.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_NAGU.get();
    }

    @Override
    public <ENTITY extends EntitySpectrobe> PlayState moveController(AnimationEvent<ENTITY> event) {
        if(event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.Naguryu.walk", true));
            return PlayState.CONTINUE;
        }
        else if(event.getAnimatable().isOrderedToSit()) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.Naguryu.sitting", false)
                    .addAnimation("animation.Naguryu.sit", true));
            return PlayState.CONTINUE;
        } else if(event.getAnimatable().isAttacking()) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.Naguryu.game_1_attack", true));
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.Naguryu.idle", true));
            return PlayState.CONTINUE;
        }
    }


    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    protected EvolutionRequirements getEvolutionRequirements() {
        return new EvolutionRequirements(46, 15, 10);
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesItemsRegistry.nagu_fossil_item.get().getItem();
    }

    @Override
    public int getLitterSize() {
        return 2;
    }
}
