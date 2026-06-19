package com.spectrobes.spectrobesmod.common.items.armour;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

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
}
