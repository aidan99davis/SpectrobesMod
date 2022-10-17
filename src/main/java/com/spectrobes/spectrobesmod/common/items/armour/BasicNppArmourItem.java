package com.spectrobes.spectrobesmod.common.items.armour;

import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.item.GeoArmorItem;

public class BasicNppArmourItem extends GeoArmorItem implements IAnimatable, IHasNature {
    private AnimationFactory factory = new AnimationFactory(this);
    private final SpectrobeProperties.Nature ArmourNature;

    public BasicNppArmourItem(SpectrobeProperties.Nature nature, IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
        super(materialIn, slot, builder);
        this.ArmourNature = nature;
    }

    @Override
    public void registerControllers(AnimationData data) {
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public SpectrobeProperties.Nature getNature() {
        return ArmourNature;
    }
}
