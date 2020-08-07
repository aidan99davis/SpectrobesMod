package com.spectrobes.spectrobesmod.common.entities.spiko;

import com.spectrobes.spectrobesmod.client.entity.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.EntityMammalSpectrobe;
import com.spectrobes.spectrobesmod.common.entities.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.EvolutionRequirements;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.*;
import net.minecraft.world.World;
import software.bernie.geckolib.animation.AnimationBuilder;
import software.bernie.geckolib.animation.AnimationTestEvent;
import software.bernie.geckolib.animation.model.AnimationControllerCollection;

public class EntitySpiko extends EntityMammalSpectrobe {

    public EntitySpiko(EntityType<EntitySpiko> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Spiko.copy();
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return SpectrobesEntities.ENTITY_SPIKAN.get();
    }

    @Override
    public String getRegistryName() {
        return "entity_spiko";
    }

    @Override
    protected EntitySpectrobe getChildForLineage() {
        return this;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);
    }


    @Override
    public AnimationControllerCollection getAnimationControllers() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends Entity> boolean moveController(AnimationTestEvent<ENTITY> entityAnimationTestEvent) {
        moveController.transitionLength = 2;
        if(!(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F))
        {
            moveController.setAnimation(new AnimationBuilder().addAnimation("animation.spiko.walk", true));
            return true;
        }
        else if(this.isSitting()) {
            moveController.setAnimation(new AnimationBuilder().addAnimation("animation.spiko.sit", false));
            return true;
        }
        else if(isJumping) {
            moveController.setAnimation(new AnimationBuilder().addAnimation("animation.spiko.jump", true));
            return true;
        }
        else {
            moveController.setAnimation(new AnimationBuilder().addAnimation("animation.spiko.idle", true));
            return true;
        }
    }

    @Override
    protected EvolutionRequirements getEvolutionRequirements() {
        return new EvolutionRequirements(10, 5, 0);
    }

    @Override
    public EntitySize getSize(Pose poseIn) {
        return super.getSize(poseIn).scale(0.5F, 0.5F);
    }
}
