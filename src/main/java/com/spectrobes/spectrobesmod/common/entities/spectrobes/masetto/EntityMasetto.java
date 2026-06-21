package com.spectrobes.spectrobesmod.common.entities.spectrobes.masetto;

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

public class EntityMasetto extends EntityMammalSpectrobe {
    private static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.Masetto.walk");
    private static final RawAnimation SIT_ANIM = RawAnimation.begin()
            .thenPlay("animation.Masetto.sitting")
            .thenLoop("animation.Masetto.sit");
    private static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.Masetto.idle");

    public EntityMasetto(EntityType<EntityMasetto> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Masetto.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_masetto";
    }

    @Override
    public Class<? extends EntitySpectrobe> getSpectrobeClass() {
        return EntityMasetto.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_MASETTO.get();
    }

    @Override
    public PlayState moveController(AnimationState<EntitySpectrobe> animationState) {
        if (animationState.isMoving()) {
            return animationState.setAndContinue(WALK_ANIM);
        }

        if (animationState.getAnimatable().isOrderedToSit()) {
            return animationState.setAndContinue(SIT_ANIM);
        }

        return animationState.setAndContinue(IDLE_ANIM);
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesFossilsRegistry.masetto_fossil_item.get();
    }

    @Override
    public int getLitterSize() {
        return 0;
    }
}