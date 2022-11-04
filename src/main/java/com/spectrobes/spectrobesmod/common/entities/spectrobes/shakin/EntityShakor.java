package com.spectrobes.spectrobesmod.common.entities.spectrobes.shakin;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityAquaticSpectrobe;
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

public class EntityShakor extends EntityAquaticSpectrobe {

    public EntityShakor(EntityType<EntityShakor> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Shakor.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_shakor";
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_SHAKIN.get();
    }

    @Override
    protected int getMaxLitterSize() {
        return 4;
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
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.shakor.walk", true));
            return PlayState.CONTINUE;
        }
        else if(event.getAnimatable().isOrderedToSit()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.shakor.idle", true));
            return PlayState.CONTINUE;
        } else if(event.getAnimatable().isSwimming()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.shakor.walk", true));
            return PlayState.CONTINUE;
        } else {
            if(this.IsAttacking()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.shakor.attack", true));
                return PlayState.CONTINUE;
            }
        }
        return PlayState.STOP;
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesFossilsRegistry.shakin_fossil_item.get();
    }

    @Override
    public Class getSpectrobeClass() {
        return EntityShakor.class;
    }
}
