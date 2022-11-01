package com.spectrobes.spectrobesmod.common.items.tools.healing;

import net.minecraft.item.Item;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SpectrobeSerumHealingItem extends Item implements IAnimatable {
    public AnimationFactory animationControllers = new AnimationFactory(this);

    private int healAmount;
    private int guraWorth;
    private int tier;

    public int getSpectrobeHealAmount() {
        return healAmount;
    }

    public int getGuraWorth() {
        return guraWorth;
    }

    public int getTier() {
        return tier;
    }

    public SpectrobeSerumHealingItem(int healAmount, int guraWorth, int tier, Properties pProperties) {
        super(pProperties);
        this.healAmount = healAmount;
        this.guraWorth = guraWorth;
        this.tier = tier;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::controller));
    }

    private PlayState controller(AnimationEvent animationEvent) {
        animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.serum.particle", true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }
}
