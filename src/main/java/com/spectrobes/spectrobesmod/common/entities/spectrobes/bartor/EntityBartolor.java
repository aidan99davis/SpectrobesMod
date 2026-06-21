package com.spectrobes.spectrobesmod.common.entities.spectrobes.bartor;

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

public class EntityBartolor extends EntityMammalSpectrobe {
    private static final RawAnimation WALK_ANIMATION =
            RawAnimation.begin().thenLoop("animation.bartolor.walk");

    private static final RawAnimation SITTING_ANIMATION =
            RawAnimation.begin()
                    .thenPlay("animation.bartolor.sitting")
                    .thenLoop("animation.bartolor.sit");

    private static final RawAnimation ATTACK_ANIMATION =
            RawAnimation.begin().thenLoop("animation.bartolor.attack");

    private static final RawAnimation IDLE_ANIMATION =
            RawAnimation.begin().thenLoop("animation.bartolor.idle");

    public EntityBartolor(EntityType<EntityBartolor> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Bartolor.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        // return SpectrobesEntities.ENTITY_BARTOLOR.get();
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_bartolor";
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Class getSpectrobeClass() {
        return EntityBartolor.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_BARTOR.get();
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

        if (spectrobe.isAttacking()) {
            return animationState.setAndContinue(ATTACK_ANIMATION);
        }

        return animationState.setAndContinue(IDLE_ANIMATION);
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesFossilsRegistry.bartor_fossil_item.get();
    }

    @Override
    public int getLitterSize() {
        return 3;
    }

    @Override
    public double getTick(Object object) {
        return this.tickCount;
    }
}