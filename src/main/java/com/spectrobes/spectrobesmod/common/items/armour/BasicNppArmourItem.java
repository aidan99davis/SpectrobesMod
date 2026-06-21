package com.spectrobes.spectrobesmod.common.items.armour;

import com.spectrobes.spectrobesmod.client.armour.renderer.BasicNppArmourRenderer;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class BasicNppArmourItem extends Item implements GeoItem, ISpectrobeArmour {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final SpectrobeProperties.Nature ArmourNature;
    private final int ArmourHealthBonus;

    public BasicNppArmourItem(SpectrobeProperties.Nature nature, int healthBonus, ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(builder);
        this.ArmourNature = nature;
        this.ArmourHealthBonus = healthBonus;
    }

    public SpectrobeProperties.Nature getNature() {
        return ArmourNature;
    }

    public int GetHealthBonus() { return ArmourHealthBonus; }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public <T extends LivingEntity> HumanoidModel<?> getGeoArmorRenderer(
                    @Nullable T livingEntity,
                    ItemStack itemStack,
                    @Nullable EquipmentSlot equipmentSlot,
                    @Nullable HumanoidModel<T> original
            ) {
                if (this.renderer == null) {
                    this.renderer = new BasicNppArmourRenderer();
                }

                return this.renderer;
            }
        });
    }
}
