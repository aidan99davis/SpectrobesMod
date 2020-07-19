package com.spectrobes.spectrobesmod.common.entities.komainu;

import com.spectrobes.spectrobesmod.client.entity.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.EntityMammalSpectrobe;
import com.spectrobes.spectrobesmod.common.entities.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import software.bernie.geckolib.animation.model.AnimationControllerCollection;

public class EntityKomainu extends EntityMammalSpectrobe {


    public EntityKomainu(EntityType<EntityKomainu> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected boolean canEvolve() {
        return getSpectrobeData().stats.getLevel() > 5;
    }


    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Komainu.copy();
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return SpectrobesEntities.ENTITY_KOMANOTO.get();
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
}
