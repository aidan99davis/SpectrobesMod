package com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityMammalSpectrobe;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.fossils.FossilBlockItem;
import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesItemsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.EvolutionRequirements;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.*;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntitySpiko extends EntityMammalSpectrobe {

    public EntitySpiko(EntityType<EntitySpiko> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Spiko.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return SpectrobesEntities.ENTITY_SPIKAN.get();
    }

    @Override
    public String getRegistryName() {
        return "entity_spiko";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntitySpiko.class;
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
    public <ENTITY extends EntitySpectrobe> PlayState moveController(AnimationEvent<ENTITY> event) {
        event.getController().transitionLengthTicks = 2;
        if(!(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F))
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spiko.walk", true));
            return PlayState.CONTINUE;
        }
        if(this.isSitting()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spiko.sit", false));
            return PlayState.CONTINUE;
        }
        else if(isJumping) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spiko.jump", true));
            return PlayState.CONTINUE;
        }
        else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.spiko.idle", true));
            return PlayState.CONTINUE;
        }
    }

    @Override
    protected EvolutionRequirements getEvolutionRequirements() {
        return new EvolutionRequirements(1, 3, 0);
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesItemsRegistry.spiko_fossil_item.get().getItem();
    }

    @Override
    public int getLitterSize() {
        return 0;
    }
}
