package com.spectrobes.spectrobesmod.common.entities.spectrobes.zoza;

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

public class EntityZoza extends EntityMammalSpectrobe {
    private static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.zoza.walk");
    private static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.zoza.idle");

    public EntityZoza(EntityType<EntityZoza> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Zoza.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return SpectrobesEntities.ENTITY_ZOZANE.get();
    }

    @Override
    public String getRegistryName() {
        return "entity_zoza";
    }

    @Override
    public Class<? extends EntitySpectrobe> getSpectrobeClass() {
        return EntityZoza.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_ZOZA.get();
    }

    @Override
    public PlayState moveController(AnimationState<EntitySpectrobe> animationState) {
        if (animationState.isMoving()) {
            return animationState.setAndContinue(WALK_ANIM);
        }

        if (animationState.getAnimatable().isOrderedToSit()) {
            return animationState.setAndContinue(IDLE_ANIM);
        }

        return PlayState.STOP;
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesFossilsRegistry.zoza_fossil_item.get();
    }

    @Override
    public int getLitterSize() {
        return 0;
    }
}