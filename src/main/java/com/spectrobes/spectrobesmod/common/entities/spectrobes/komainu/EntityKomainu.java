package com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu;

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

public class EntityKomainu extends EntityMammalSpectrobe {

    public EntityKomainu(EntityType<EntityKomainu> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Komainu.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return SpectrobesEntities.ENTITY_KOMANOTO.get();
    }

    @Override
    public String getRegistryName() {
        return "entity_komainu";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntityKomainu.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_KOMAINU.get();
    }

    @Override
    public PlayState moveController(AnimationState<EntitySpectrobe> animationState) {
        animationState.getController().transitionLengthTicks = 2;
        if(animationState.isMoving())
        {
            animationState.getController().setAnimation(new AnimationBuilder().addAnimation("animation.komainu.walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        else if(animationState.getAnimatable().isOrderedToSit()) {
            animationState.getController().setAnimation(new AnimationBuilder().addAnimation("animation.komainu.sit", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
            return PlayState.CONTINUE;
        } else {
            animationState.getController().setAnimation(new AnimationBuilder().addAnimation("animation.komainu.idle", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesFossilsRegistry.komainu_fossil_item.get();
    }

    @Override
    public int getLitterSize() {
        return 0;
    }
}
