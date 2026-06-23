package com.spectrobes.spectrobesmod.common.entities.spectrobes.segu;

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

public class EntitySegulos extends EntityMammalSpectrobe {
    private static final RawAnimation BODY_IDLE_ANIM = RawAnimation.begin().thenLoop("animation.segulos.idle");
    private static final RawAnimation ATTACK_ANIM = RawAnimation.begin().thenLoop("animation.segulos.attack");
    private static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.segulos.walk");

    public EntitySegulos(EntityType<EntitySegulos> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Segulos.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_segulos";
    }

    @Override
    public Class<? extends EntitySpectrobe> getSpectrobeClass() {
        return EntitySegulos.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_SEGU.get();
    }

    public PlayState bodyController(AnimationState<EntitySpectrobe> animationState) {
        return animationState.setAndContinue(BODY_IDLE_ANIM);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        super.registerControllers(data);
        data.add(new AnimationController<>(this, "bodyAnimationController", 10, this::bodyController));
    }

    @Override
    public PlayState moveController(AnimationState<EntitySpectrobe> animationState) {
        if (animationState.getAnimatable().isAttacking()) {
            return animationState.setAndContinue(ATTACK_ANIM);
        }

        if (animationState.isMoving()) {
            return animationState.setAndContinue(WALK_ANIM);
        }

        return PlayState.STOP;
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesFossilsRegistry.segu_fossil_item.get();
    }

    @Override
    public int getLitterSize() {
        return 5;
    }
}