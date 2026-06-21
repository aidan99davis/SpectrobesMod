package com.spectrobes.spectrobesmod.common.entities.spectrobes.dongor;

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

public class EntityDongora extends EntityMammalSpectrobe {
    private static final RawAnimation WALK_ANIMATION =
            RawAnimation.begin().thenLoop("animation.dongora.walk");

    private static final RawAnimation SITTING_ANIMATION =
            RawAnimation.begin()
                    .thenPlay("animation.dongora.sitting")
                    .thenLoop("animation.dongora.sit");

    private static final RawAnimation IDLE_ANIMATION =
            RawAnimation.begin().thenLoop("animation.dongora.idle");

    public EntityDongora(EntityType<EntityDongora> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Dongora.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        // return SpectrobesEntities.ENTITY_DONGIGA.get();
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_dongora";
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Class getSpectrobeClass() {
        return EntityDongora.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_DONGOR.get();
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
        return (FossilBlockItem) SpectrobesFossilsRegistry.dongor_fossil_item.get();
    }

    @Override
    public int getLitterSize() {
        return 3;
    }
}