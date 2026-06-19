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

public class EntitySegulos extends EntityMammalSpectrobe {
    public EntitySegulos(EntityType<EntitySegulos> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

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
    public Class getSpectrobeClass() {
        return EntitySegulos.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_SEGU.get();
    }

    public PlayState bodyController(AnimationState<EntitySpectrobe> animationState) {
        animationState.getController().setAnimation(new AnimationBuilder().addAnimation("animation.segulos.idle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    protected AnimationController bodyAnimationController = new AnimationController(this, "bodyAnimationController", 10F, this::bodyController);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data)
    {
        super.registerControllers(data);
        data.add(bodyAnimationController);
    }

    @Override
    public PlayState moveController(AnimationState<EntitySpectrobe> animationState)
    {
        if(animationState.getAnimatable().isAttacking()) {
            animationState.getController().setAnimation(new AnimationBuilder().addAnimation("animation.segulos.attack", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if(animationState.isMoving())
        {
            animationState.getController().setAnimation(new AnimationBuilder().addAnimation("animation.segulos.walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        } else {
            return PlayState.STOP;
        }
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
