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

public class EntityZozane extends EntityMammalSpectrobe {
    public EntityZozane(EntityType<EntityZozane> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Zozane.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_zozane";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntityZozane.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_ZOZA.get();
    }

    @Override
    public PlayState moveController(AnimationState<EntitySpectrobe> animationState)
    {
        if(animationState.getAnimatable().isAttacking()) {
            animationState.getController().setAnimation(new AnimationBuilder().addAnimation("animation.zozane.attack", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }
        if(animationState.isMoving())
        {
            animationState.getController().setAnimation(new AnimationBuilder().addAnimation("animation.zozane.walk", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        } else {
            return PlayState.STOP;
        }
    }

    @Override
    protected FossilBlockItem getFossil() {
        return (FossilBlockItem) SpectrobesFossilsRegistry.zoza_fossil_item.get();
    }

    @Override
    public int getLitterSize() {
        return 4;
    }
}
