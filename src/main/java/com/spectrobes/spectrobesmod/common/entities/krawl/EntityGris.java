package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.KrawlRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class EntityGris extends EntityKrawl {
    public EntityGris(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntityKrawl> PlayState moveController(AnimationEvent<ENTITY> event) {
//        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.subar.idle", true));
        return PlayState.STOP;
    }

    @Override
    public KrawlProperties GetKrawlProperties() {
        return KrawlRegistry.Gris_Properties.copy();
    }

}
