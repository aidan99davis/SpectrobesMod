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
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;

public class EntityVilamasta extends EntityMammalSpectrobe {
    public EntityVilamasta(EntityType<EntityVilamasta> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Vilamasta.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_vilamasta";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntityVilamasta.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_VILAR.get();
    }

    @Override
    public PlayState moveController(AnimationState<EntitySpectrobe> animationState)
    {
        animationState.getController().transitionLength(2);
        if(animationState.isMoving())
        {
            animationState.getController().setAnimation(new AnimationBuilder().addAnimation("animation.vilamasta.walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        else if(this.isOrderedToSit()) {
            animationState.getController().setAnimation(new AnimationBuilder().addAnimation("animation.vilamasta.idle", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        } else {
            if(this.IsAttacking()) {
                animationState.getController().setAnimation(new AnimationBuilder().addAnimation("animation.vilamasta.attack", ILoopType.EDefaultLoopTypes.LOOP));
                return PlayState.CONTINUE;
            }
        }
        return PlayState.STOP;
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesFossilsRegistry.vilar_fossil_item.get();
    }

    @Override
    public int getLitterSize() {
        return 4;
    }
}
