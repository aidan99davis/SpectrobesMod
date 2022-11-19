package com.spectrobes.spectrobesmod.common.items.tools.healing;

import com.spectrobes.spectrobesmod.client.items.healing.renderer.SerumItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.util.NonNullLazy;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.function.Consumer;

public class SpectrobeSerumHealingItem extends Item implements IAnimatable {
    public AnimationFactory animationControllers = GeckoLibUtil.createFactory(this);

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

    public SpectrobeSerumHealingItem(int healAmount, int guraWorth, int tier, Item.Properties pProperties) {
        super(pProperties);
        this.healAmount = healAmount;
        this.guraWorth = guraWorth;
        this.tier = tier;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions()
        {
            private final NonNullLazy<BlockEntityWithoutLevelRenderer> ister = NonNullLazy.of(SerumItemRenderer::new);

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return ister.get();
            }
        });
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::controller));
    }

    private PlayState controller(AnimationEvent animationEvent) {
        animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.serum.particle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }
}
