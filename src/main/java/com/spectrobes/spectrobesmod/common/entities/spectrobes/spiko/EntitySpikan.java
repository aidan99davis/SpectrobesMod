package com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko;

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

public class EntitySpikan extends EntityMammalSpectrobe {


    public EntitySpikan(EntityType<EntitySpikan> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Spikan.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_spikan";
    }

    @Override
    protected EntitySpectrobe getChildForLineage() {
        return SpectrobesEntities.ENTITY_SPIKO.get().create(world);
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
    public <ENTITY extends EntitySpectrobe> boolean moveController(AnimationTestEvent<ENTITY> entityAnimationTestEvent)
    {
        moveController.transitionLengthTicks = 2;
        if(entityAnimationTestEvent.isWalking())
        {
            moveController.setAnimation(new AnimationBuilder().addAnimation("animation.spikan.walk", true));
            return true;
        }
        else if(this.isSitting()) {
            moveController.setAnimation(new AnimationBuilder().addAnimation("animation.spikan.sit", false));
            return true;
        } else {
            if(this.getAttackingEntity() != null) {
                moveController.setAnimation(new AnimationBuilder().addAnimation("animation.spikan.walk", true));
                return true;
            }
        }
        return false;
    }

    @Override
    protected EvolutionRequirements getEvolutionRequirements() {
        //returning null makes canEvolve always evaluate to false.
        return null;
    }
}