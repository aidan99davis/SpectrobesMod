package com.spectrobes.spectrobesmod.common.items.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.WeaponStats;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;

public class BasicGloveItem extends SpectrobesWeapon {
    public BasicGloveItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
        if(slot.equals(EquipmentSlotType.MAINHAND) || slot.equals(EquipmentSlotType.OFFHAND)) {
            Multimap<Attribute, AttributeModifier> customAttributes = new ImmutableMultimap.Builder<Attribute, AttributeModifier>()
                    .putAll(super.getAttributeModifiers(slot, stack))
                    .put(Attributes.ATTACK_KNOCKBACK,
                            new AttributeModifier("Knockback Bonus",
                                    1.5D,
                                    AttributeModifier.Operation.MULTIPLY_BASE))
                    .build();

            return customAttributes;
        }
        return super.getAttributeModifiers(slot, stack);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, getControllerName(), 1, super::predicate));
    }

    @Override
    public WeaponStats GetWeaponStats() {
        return new WeaponStats(5, 1, 1, false, true, SpectrobeProperties.Nature.OTHER);
    }

    @Override
    public String getControllerName() {
        return "basic_glove";
    }
}
