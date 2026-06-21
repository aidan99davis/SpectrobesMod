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
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public class EntityGejio extends EntityMammalSpectrobe {
    private static final RawAnimation WALK_ANIMATION =
            RawAnimation.begin().thenLoop("animation.gejio.walk");

    private static final RawAnimation SITTING_ANIMATION =
            RawAnimation.begin()
                    .thenPlay("animation.gejio.sitting")
                    .thenLoop("animation.gejio.sit");

    private static final RawAnimation IDLE_ANIMATION =
            RawAnimation.begin().thenLoop("animation.gejio.idle");

    public EntityGejio(EntityType<EntityGejio> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Gejio.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        // return SpectrobesEntities.ENTITY_GEJIGEN.get();
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_gejio";
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Class getSpectrobeClass() {
        return EntityGejio.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_GEJIO.get();
    }

    @Override
    public PlayState moveController(AnimationState<EntitySpectrobe> animationState) {
        EntitySpectrobe spectrobe = animationState.getAnimatable();

        if (animationState.isMoving()) {
            return animationState.setAndContinue(WALK_ANIMATION);
        }

        if (spectrobe.isOrderedToSit()) {
            return animationState.setAndContinue(SITTING_ANIMATION);
        }

        return animationState.setAndContinue(IDLE_ANIMATION);
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