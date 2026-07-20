package com.spectrobes.spectrobesmod.common.items.weapons;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.items.weapons.renderer.WeaponBasicBlasterItemRenderer;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.WeaponStats;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;

import java.util.function.Consumer;

public class WeaponBasicBlasterItem extends AbstractSpectrobesRangedWeapon {
    public WeaponBasicBlasterItem(Properties pProperties) { super(pProperties); }

    // = = = STATS = = =
    @Override
    public WeaponStats GetWeaponStats() {
        // Originally set to 20, but in S1 it's 5
        // Change back if the stat scale is different for this mod.
        return new WeaponStats(5, 1, 1, WeaponStats.WeaponType.BLASTER, SpectrobeProperties.Nature.OTHER);
    }

    // = = = VISUAL = = =
    @Override public String getControllerName() { return "weapon_basic_blaster"; }
    @Override public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController(this, getControllerName(), 1, super::predicate));
    }
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private WeaponBasicBlasterItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                System.out.println("Getting renderer");

                if (this.renderer == null) {
                    this.renderer = new WeaponBasicBlasterItemRenderer();
                }

                return this.renderer;
            }
        });
    }
}