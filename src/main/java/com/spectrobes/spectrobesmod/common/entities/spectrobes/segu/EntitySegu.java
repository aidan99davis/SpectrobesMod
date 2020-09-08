package com.spectrobes.spectrobesmod.common.entities.spectrobes.segu;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityMammalSpectrobe;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.EvolutionRequirements;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;

public class EntitySegu extends EntityMammalSpectrobe {

    public EntitySegu(EntityType<EntitySegu> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Segu.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;//SpectrobesEntities.ENTITY_SPIKAN.get();
    }

    @Override
    public String getRegistryName() {
        return "entity_segu";
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_SEGU.get();
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);
    }

    @Override
    public EntityAnimationManager getAnimationManager() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntitySpectrobe> boolean moveController(AnimationTestEvent<ENTITY> entityAnimationTestEvent) {
        moveAnimationController.transitionLengthTicks = 2;
        if(!(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F))
        {
            animationControllers.setAnimationSpeed(1);
            moveAnimationController.setAnimation(new AnimationBuilder().addAnimation("animation.segu.walk", true));
            return true;
        }
        if(this.isSitting()) {
            animationControllers.setAnimationSpeed(0.5);
            moveAnimationController.setAnimation(new AnimationBuilder().addAnimation("animation.segu.idle", true));
            return true;
        }
        return false;
    }

    @Override
    protected EvolutionRequirements getEvolutionRequirements() {
        return new EvolutionRequirements(1, 4, 0);
    }
}
