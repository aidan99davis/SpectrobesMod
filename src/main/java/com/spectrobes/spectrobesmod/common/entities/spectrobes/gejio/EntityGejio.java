package com.spectrobes.spectrobesmod.common.entities.spectrobes.gejio;

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
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityGejio extends EntityMammalSpectrobe {

    public EntityGejio(EntityType<EntityGejio> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Gejio.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
//        return SpectrobesEntities.ENTITY_GEJIGEN.get();
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_gejio";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntityGejio.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_GEJIO.get();
    }

    @Override
    public <ENTITY extends EntitySpectrobe> PlayState moveController(AnimationEvent<ENTITY> event) {
        if(event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gejio.walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        else if(event.getAnimatable().isOrderedToSit()) {
            event.getController().setAnimation(new AnimationBuilder()
                    .addAnimation("animation.gejio.sitting", ILoopType.EDefaultLoopTypes.PLAY_ONCE)
                    .addAnimation("animation.gejio.sit", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.gejio.idle", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
    }


    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesFossilsRegistry.gejio_fossil_item.get();
    }

    @Override
    public int getLitterSize() {
        return 0;
    }
}
