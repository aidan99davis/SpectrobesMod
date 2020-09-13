package com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityMammalSpectrobe;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomainu;
import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.EvolutionRequirements;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;

public class EntitySamukabu extends EntityMammalSpectrobe {

    public EntitySamukabu(EntityType<EntitySamukabu> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Samukabu.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return SpectrobesEntities.ENTITY_SAMURITE.get();
    }

    @Override
    public String getRegistryName() {
        return "entity_samukabu";
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_SAMUKABU.get();
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
        if(entityAnimationTestEvent.isWalking())
        {
            animationControllers.setAnimationSpeed(2);
            moveAnimationController.setAnimation(new AnimationBuilder().addAnimation("animation.samukabu.walk", true));
            return true;
        }
        else if(entityAnimationTestEvent.getEntity().isSitting()) {
            animationControllers.setAnimationSpeed(1);
            moveAnimationController.setAnimation(new AnimationBuilder().addAnimation("animation.samukabu.sitting", true));
            return true;
        }
        return false;

    }

    @Override
    protected EvolutionRequirements getEvolutionRequirements() {
        return new EvolutionRequirements(1, 5, 0);
    }

    @Override
    public int getLitterSize() {
        return 0;
    }
}
