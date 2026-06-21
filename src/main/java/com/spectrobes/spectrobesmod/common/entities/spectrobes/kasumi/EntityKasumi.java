package com.spectrobes.spectrobesmod.common.entities.spectrobes.kasumi;

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

public class EntityKasumi extends EntityMammalSpectrobe {
    private static final RawAnimation WALK_ANIMATION =
            RawAnimation.begin().thenLoop("animation.kasumi.walk");

    private static final RawAnimation SITTING_ANIMATION =
            RawAnimation.begin()
                    .thenPlay("animation.kasumi.sitting")
                    .thenLoop("animation.kasumi.sit");

    private static final RawAnimation IDLE_ANIMATION =
            RawAnimation.begin().thenLoop("animation.kasumi.idle");

    public EntityKasumi(EntityType<EntityKasumi> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Kasumi.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        // return SpectrobesEntities.ENTITY_KASUMITE.get();
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_kasumi";
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Class getSpectrobeClass() {
        return EntityKasumi.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_KASUMI.get();
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
        return (FossilBlockItem) SpectrobesFossilsRegistry.kasumi_fossil_item.get().asItem();
    }

    @Override
    public int getLitterSize() {
        return 0;
    }
}