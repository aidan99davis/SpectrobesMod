package com.spectrobes.spectrobesmod.common.entities.spectrobes.aoi;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityAvianSpectrobe;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.fossils.FossilBlockItem;
import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesFossilsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;

public class EntityAoi extends EntityAvianSpectrobe {

    public EntityAoi(EntityType<EntityAoi> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Aoi.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
//        return SpectrobesEntities.ENTITY_AOBA.get();
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_aoi";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntityAoi.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_AOI.get();
    }


    @Override
    public PlayState moveController(AnimationState<EntitySpectrobe> animationState) {
        if(animationState.getAnimatable().isOrderedToSit())
        {
            animationState.getController().setAnimationSpeed(2);
            animationState.getController().setAnimation(new AnimationBuilder().addAnimation("animation.aoi.sit", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        } else {
            animationState.getController().setAnimationSpeed(5);
            animationState.getController().setAnimation(new AnimationBuilder().addAnimation("animation.aoi.idle", ILoopType.EDefaultLoopTypes.LOOP));
            return PlayState.CONTINUE;
        }

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
