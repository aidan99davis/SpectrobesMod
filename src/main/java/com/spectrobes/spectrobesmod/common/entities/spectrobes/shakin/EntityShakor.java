package com.spectrobes.spectrobesmod.common.entities.spectrobes.shakin;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityAquaticSpectrobe;
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

public class EntityShakor extends EntityAquaticSpectrobe {


    public EntityShakor(EntityType<EntityShakor> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

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
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_SHAKIN.get();
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);
    }

    @Override
    protected int getMaxLitterSize() {
        return 3;
    }


    @Override
    public EntityAnimationManager getAnimationManager() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntitySpectrobe> boolean moveController(AnimationTestEvent<ENTITY> entityAnimationTestEvent)
    {
        if(entityAnimationTestEvent.isWalking())
        {
            moveAnimationController.setAnimation(new AnimationBuilder().addAnimation("animation.shakor.walk", true));
            return true;
        }
        else if(entityAnimationTestEvent.getEntity().isSitting()) {
            moveAnimationController.setAnimation(new AnimationBuilder().addAnimation("animation.shakor.idle", true));
            return true;
        } else if(entityAnimationTestEvent.getEntity().isSwimming()) {
            moveAnimationController.setAnimation(new AnimationBuilder().addAnimation("animation.shakor.walk", true));
            return true;
        } else {
            if(this.IsAttacking()) {
                moveAnimationController.setAnimation(new AnimationBuilder().addAnimation("animation.shakor.attack", true));
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
