package com.spectrobes.spectrobesmod.common.items.armour;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

public class BasicNppArmourItem extends GeoArmorItem implements IAnimatable, ISpectrobeArmour {
    private AnimationFactory factory = new AnimationFactory(this);
    private final SpectrobeProperties.Nature ArmourNature;
    private final int ArmourHealthBonus;

    public BasicNppArmourItem(SpectrobeProperties.Nature nature, int healthBonus, ArmorMaterial materialIn, EquipmentSlot slot, Properties builder) {
        super(materialIn, slot, builder);
        this.ArmourNature = nature;
        this.ArmourHealthBonus = healthBonus;
    }

    @Override
    public void registerControllers(AnimationData data) {
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public SpectrobeProperties.Nature getNature() {
        return ArmourNature;
    }

    public int GetHealthBonus() { return ArmourHealthBonus; }
}
