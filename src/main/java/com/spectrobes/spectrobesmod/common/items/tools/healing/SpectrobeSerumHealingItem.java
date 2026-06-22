package com.spectrobes.spectrobesmod.common.items.tools.healing;

import com.spectrobes.spectrobesmod.client.items.healing.renderer.SerumItemRenderer;
import com.spectrobes.spectrobesmod.common.items.minerals.IWorthGura;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class SpectrobeSerumHealingItem extends Item implements GeoItem, IWorthGura {

    private static final RawAnimation SERUM_PARTICLE_ANIMATION =
            RawAnimation.begin().thenLoop("animation.serum.particle");

    private final AnimatableInstanceCache animationControllers = GeckoLibUtil.createInstanceCache(this);

    private final int healAmount;
    private final int guraWorth;
    private final int tier;

    public SpectrobeSerumHealingItem(int healAmount, int guraWorth, int tier, Item.Properties properties) {
        super(properties);

        this.healAmount = healAmount;
        this.guraWorth = guraWorth;
        this.tier = tier;

        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    public int getSpectrobeHealAmount() {
        return this.healAmount;
    }

    @Override
    public int getGuraWorth() {
        return this.guraWorth;
    }

    @Override
    public String getName() {
        return switch (getTier()) {
            default -> "basic_antidote";
        };
    }

    public int getTier() {
        return this.tier;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private SerumItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null) {
                    this.renderer = new SerumItemRenderer();
                }

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::controller));
    }

    private PlayState controller(AnimationState<SpectrobeSerumHealingItem> animationState) {
        return animationState.setAndContinue(SERUM_PARTICLE_ANIMATION);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationControllers;
    }
}