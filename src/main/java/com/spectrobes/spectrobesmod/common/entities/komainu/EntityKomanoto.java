package com.spectrobes.spectrobesmod.common.entities.komainu;

import com.spectrobes.spectrobesmod.client.entity.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.EntityMammalSpectrobe;
import com.spectrobes.spectrobesmod.common.entities.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import software.bernie.geckolib.animation.AnimationBuilder;
import software.bernie.geckolib.animation.AnimationTestEvent;
import software.bernie.geckolib.animation.model.AnimationControllerCollection;

public class EntityKomanoto extends EntityMammalSpectrobe {


    public EntityKomanoto(EntityType<EntityKomanoto> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected boolean canEvolve() {
        return false;
    }


    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Komanoto.copy();
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_komanoto";
    }

    @Override
    protected EntitySpectrobe getChildForLineage() {
        return SpectrobesEntities.ENTITY_KOMAINU.get().create(world);
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
    public <ENTITY extends Entity> boolean moveController(AnimationTestEvent<ENTITY> entityAnimationTestEvent)
    {
        moveController.transitionLength = 2;
        if(!(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F))
        {
            moveController.setAnimation(new AnimationBuilder().addAnimation("animation.komanoto.walking", true));
            return true;
        }
        else if(this.isSitting()) {
            moveController.setAnimation(new AnimationBuilder().addAnimation("animation.komanoto.sit", false));
            return true;
        } else {
            if(this.getAttackingEntity() != null) {
                moveController.setAnimation(new AnimationBuilder().addAnimation("animation.komanoto.attack", true));
                return true;
            }
        }
        return false;
    }
}
