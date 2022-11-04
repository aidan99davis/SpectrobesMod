package com.spectrobes.spectrobesmod.common.entities.spectrobes.harumi;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityCrustaceanSpectrobe;
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

public class EntityHarumite extends EntityCrustaceanSpectrobe {

    public EntityHarumite(EntityType<EntityHarumite> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Harumite.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_harumite";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntityHarumite.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_HARUMI.get();
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
    public <ENTITY extends EntitySpectrobe> PlayState moveController(AnimationEvent<ENTITY> event) {
        if(event.isMoving() || event.getAnimatable().isSwimming())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.harumite.walk", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;

    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesFossilsRegistry.harumi_fossil_item.get();
    }
}
