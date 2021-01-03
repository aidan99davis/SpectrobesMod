package com.spectrobes.spectrobesmod.common.entities.spectrobes.harumi;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntityCrustaceanSpectrobe;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.items.fossils.FossilItem;
import com.spectrobes.spectrobesmod.common.registry.SpectrobeRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.EvolutionRequirements;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityHarumite extends EntityCrustaceanSpectrobe {

    public EntityHarumite(EntityType<EntityHarumite> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public Spectrobe GetNewSpectrobeInstance() {
        return SpectrobeRegistry.Harumite.copy(false);
    }

    @Override
    public EntityType<? extends EntitySpectrobe> getEvolutionRegistry() {
        return null;
    }

    @Override
    public String getRegistryName() {
        return "entity_harumite";
    }

    @Override
    public Class getSpectrobeClass() {
        return EntityHarumite.class;
    }

    @Override
    protected EntityType<? extends EntitySpectrobe> getChildForLineage() {
        return SpectrobesEntities.ENTITY_HARUMI.get();
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
        return 0;
    }


    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntitySpectrobe> PlayState moveController(AnimationEvent<ENTITY> event) {
        if(event.isMoving() || event.getAnimatable().isSwimming())
        {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.harumite.walk", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;

    }

    @Override
    protected FossilItem getFossil() {
        return (FossilItem) SpectrobesItems.harumi_fossil_item.getItem();
    }

    @Override
    protected EvolutionRequirements getEvolutionRequirements() {
        return new EvolutionRequirements(1, 5, 0);
    }
}
