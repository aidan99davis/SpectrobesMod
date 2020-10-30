package com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityMammalSpectrobe;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.EvolutionRequirements;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import software.bernie.geckolib.core.PlayState;
import software.bernie.geckolib.core.builder.AnimationBuilder;
import software.bernie.geckolib.core.event.predicate.AnimationEvent;
import software.bernie.geckolib.core.manager.AnimationFactory;

public class EntitySamurite extends EntityMammalSpectrobe {


    public EntitySamurite(EntityType<EntitySamurite> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Samurite.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_samurite";
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
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntitySpectrobe> PlayState moveController(AnimationEvent<ENTITY> entityAnimationTestEvent)
    {
        moveAnimationController.transitionLengthTicks = 2;
        if(entityAnimationTestEvent.isMoving())
        {
            moveAnimationController.setAnimation(new AnimationBuilder().addAnimation("animation.samurite.walk", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    protected EvolutionRequirements getEvolutionRequirements() {
        //returning null makes canEvolve always evaluate to false.
        return null;
    }

    @Override
    public int getLitterSize() {
        return 3;
    }
}
