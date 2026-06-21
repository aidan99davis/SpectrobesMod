package com.spectrobes.spectrobesmod.common.entities.spectrobes.shakin;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityAquaticSpectrobe;
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

public class EntityShakor extends EntityAquaticSpectrobe {
    private static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.shakor.walk");
    private static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.shakor.idle");
    private static final RawAnimation ATTACK_ANIM = RawAnimation.begin().thenLoop("animation.shakor.attack");

    public EntityShakor(EntityType<EntityShakor> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Shakor.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_shakor";
    }

    @Override
    public Class<? extends EntitySpectrobe> getSpectrobeClass() {
        return EntityShakor.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_SHAKIN.get();
    }

    @Override
    protected boolean isShallowSwimmer() {
        return false;
    }

    @Override
    protected int getMaxLitterSize() {
        return 4;
    }

    @Override
    public PlayState moveController(AnimationState<EntitySpectrobe> animationState) {
        if (animationState.isMoving()) {
            return animationState.setAndContinue(WALK_ANIM);
        }

        if (animationState.getAnimatable().isOrderedToSit()) {
            return animationState.setAndContinue(IDLE_ANIM);
        }

        if (animationState.getAnimatable().isSwimming()) {
            return animationState.setAndContinue(WALK_ANIM);
        }

        if (this.IsAttacking()) {
            return animationState.setAndContinue(ATTACK_ANIM);
        }

        return PlayState.STOP;
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesFossilsRegistry.shakin_fossil_item.get();
    }
}