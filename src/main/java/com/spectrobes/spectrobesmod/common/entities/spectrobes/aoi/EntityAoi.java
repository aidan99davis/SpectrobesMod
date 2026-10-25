package com.spectrobes.spectrobesmod.common.entities.spectrobes.aoi;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityAvianSpectrobe;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.fossils.FossilBlockItem;
import com.spectrobes.spectrobesmod.common.registry.data.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesFossilsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;

public class EntityAoi extends EntityAvianSpectrobe {
    private static final RawAnimation SIT_ANIMATION =
            RawAnimation.begin().thenLoop("animation.aoi.sit");

    private static final RawAnimation IDLE_ANIMATION =
            RawAnimation.begin().thenLoop("animation.aoi.idle");

    public EntityAoi(EntityType<EntityAoi> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Aoi.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        // return SpectrobesEntities.ENTITY_AOBA.get();
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_aoi";
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Class getSpectrobeClass() {
        return EntityAoi.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_AOI.get();
    }

    @Override
    public PlayState moveController(AnimationState<EntitySpectrobe> animationState) {
        EntitySpectrobe spectrobe = animationState.getAnimatable();

        if (spectrobe.isOrderedToSit()) {
            animationState.getController().setAnimationSpeed(2.0D);
            return animationState.setAndContinue(SIT_ANIMATION);
        }

        animationState.getController().setAnimationSpeed(5.0D);
        return animationState.setAndContinue(IDLE_ANIMATION);
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesFossilsRegistry.aoi_fossil_item.get();
    }

    @Override
    protected int getMaxLitterSize() {
        return 0;
    }
}