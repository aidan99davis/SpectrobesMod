package com.spectrobes.spectrobesmod.common.entities.spectrobes.grilda;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityMammalSpectrobe;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.fossils.FossilBlockItem;
import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesFossilsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public class EntityGrilda extends EntityMammalSpectrobe {
    private static final RawAnimation HEAD_IDLE_ANIMATION =
            RawAnimation.begin().thenLoop("animation.grilda.idle");

    private static final RawAnimation WALK_ANIMATION =
            RawAnimation.begin().thenLoop("animation.grilda.walk");

    public EntityGrilda(EntityType<EntityGrilda> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Grilda.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return SpectrobesEntities.ENTITY_GRILDEN.get();
    }

    @Override
    public String getRegistryName() {
        return "entity_grilda";
    }

    @Override
    public Class<? extends EntitySpectrobe> getSpectrobeClass() {
        return EntityGrilda.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_GRILDA.get();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        super.registerControllers(controllers);
        controllers.add(new AnimationController<>(this, "head_animation_controller", 10, this::headController));
    }

    public PlayState headController(AnimationState<EntitySpectrobe> animationState) {
        animationState.getController().setAnimationSpeed(0.5D);
        return animationState.setAndContinue(HEAD_IDLE_ANIMATION);
    }

    @Override
    public PlayState moveController(AnimationState<EntitySpectrobe> animationState) {
        animationState.getController().setAnimationSpeed(0.5D);

        if (animationState.isMoving()) {
            return animationState.setAndContinue(WALK_ANIMATION);
        }

        return PlayState.STOP;
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesFossilsRegistry.grilda_fossil_item.get();
    }

    @Override
    public int getLitterSize() {
        return 0;
    }
}