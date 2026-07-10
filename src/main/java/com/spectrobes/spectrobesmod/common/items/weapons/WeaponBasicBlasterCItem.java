package com.spectrobes.spectrobesmod.common.items.weapons;

import com.spectrobes.spectrobesmod.client.items.weapons.renderer.WeaponBasicBlasterCItemRenderer;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.WeaponStats;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;

import java.util.function.Consumer;

public class WeaponBasicBlasterCItem extends AbstractSpectrobesRangedWeapon {
    public WeaponBasicBlasterCItem(Properties pProperties) { super(pProperties); }

    // = = = STATS = = =
    @Override
    public WeaponStats GetWeaponStats() {
        // Like the basic blaster in every way, but is CORONA property.
        return new WeaponStats(5, 1, 1, WeaponStats.WeaponType.BLASTER, SpectrobeProperties.Nature.CORONA);
    }

    // = = = VISUAL = = =
    @Override public String getControllerName() { return "weapon_basic_c_blaster"; }
    @Override public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController(this, getControllerName(), 1, super::predicate));
    }
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private WeaponBasicBlasterCItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                System.out.println("Getting renderer");

                if (this.renderer == null) {
                    this.renderer = new WeaponBasicBlasterCItemRenderer();
                }

                return this.renderer;
            }
        });
    }
}