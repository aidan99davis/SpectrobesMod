package com.spectrobes.spectrobesmod.common.items.weapons;

import com.spectrobes.spectrobesmod.client.entity.attacks.AttackEntities;
import com.spectrobes.spectrobesmod.client.items.weapons.renderer.WeaponHomingBlasterItemRenderer;
import com.spectrobes.spectrobesmod.common.entities.attacks.AbstractEnergyBoltEntity;
import com.spectrobes.spectrobesmod.common.entities.attacks.HomingEnergyBoltEntity;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.WeaponStats;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;

import java.util.function.Consumer;

public class WeaponHomingBlasterItem extends AbstractSpectrobesRangedWeapon {
    public WeaponHomingBlasterItem(Properties pProperties) { super(pProperties); }

    // = = = STATS = = =
    @Override
    public WeaponStats GetWeaponStats() {
        return new WeaponStats(10, 1, 1, WeaponStats.WeaponType.BLASTER, SpectrobeProperties.Nature.OTHER);
    }

    @Override
    protected AbstractEnergyBoltEntity createProjectile(Level pLevel) {
        return new HomingEnergyBoltEntity(AttackEntities.ENTITY_PROJECTILE_HOMING.get(), pLevel);
    }

    // = = = VISUAL = = =
    @Override public String getControllerName() { return "weapon_homing_blaster"; }
    @Override public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController(this, getControllerName(), 1, super::predicate));
    }
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private WeaponHomingBlasterItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null) {
                    this.renderer = new WeaponHomingBlasterItemRenderer();
                }

                return this.renderer;
            }
        });
    }
}