package com.spectrobes.spectrobesmod.common.items.tools.healing;

import com.spectrobes.spectrobesmod.client.items.healing.renderer.SerumItemRenderer;
import com.spectrobes.spectrobesmod.common.items.minerals.IWorthGura;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class SpectrobeSerumHealingItem extends Item implements GeoAnimatable, IWorthGura {
    public AnimatableInstanceCache animationControllers = GeckoLibUtil.createInstanceCache(this);

    private int healAmount;
    private int guraWorth;
    private int tier;

    public int getSpectrobeHealAmount() {
        return healAmount;
    }

    public int getGuraWorth() {
        return guraWorth;
    }

    @Override
    public String getName() {
        return switch (getTier()) {
            default -> "basic_antidote";
        };
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
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController(this, "controller", 0, this::controller));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animationControllers;
    }

    @Override
    public double getTick(Object object) {
        return 0;
    }

    private PlayState controller(AnimationState animationEvent) {
        animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.serum.particle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }
}
