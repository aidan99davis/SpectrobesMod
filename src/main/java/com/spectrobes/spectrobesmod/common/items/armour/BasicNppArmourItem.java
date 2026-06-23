package com.spectrobes.spectrobesmod.common.items.armour;

import com.spectrobes.spectrobesmod.client.armour.renderer.BasicNppArmourRenderer;
import com.spectrobes.spectrobesmod.client.items.armour.basic.BasicNppArmourItemRenderer;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class BasicNppArmourItem extends ArmorItem implements GeoItem, ISpectrobeArmour {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private final SpectrobeProperties.Nature armourNature;
    private final int armourHealthBonus;

    public BasicNppArmourItem(
            SpectrobeProperties.Nature nature,
            int healthBonus,
            Holder<ArmorMaterial> material,
            ArmorItem.Type type,
            Properties properties
    ) {
        super(material, type, properties);

        this.armourNature = nature;
        this.armourHealthBonus = healthBonus;
    }

    public SpectrobeProperties.Nature getNature() {
        return armourNature;
    }

    public int GetHealthBonus() {
        return armourHealthBonus;
    }

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
            private BlockEntityWithoutLevelRenderer itemRenderer;

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

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.itemRenderer == null) {
                    this.itemRenderer = new BasicNppArmourItemRenderer();
                }

                return this.itemRenderer;
            }
        });
    }
}