package com.spectrobes.spectrobesmod.common.items.weapons;

import com.spectrobes.spectrobesmod.client.items.renderer.XellesTrophyItemRenderer;
import com.spectrobes.spectrobesmod.client.items.weapons.renderer.BasicBlasterItemRenderer;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.WeaponStats;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.util.NonNullLazy;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.manager.AnimationData;

import java.util.function.Consumer;

public class BasicBlasterItem extends SpectrobesRangedWeapon {
    public BasicBlasterItem(Item.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions()
        {
            private final NonNullLazy<BlockEntityWithoutLevelRenderer> ister = NonNullLazy.of(() -> new BasicBlasterItemRenderer());

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return ister.get();
            }
        });
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, getControllerName(), 1, super::predicate));
    }

    @Override
    public WeaponStats GetWeaponStats() {
        return new WeaponStats(20, 1, 1, true, false, SpectrobeProperties.Nature.OTHER);
    }

    @Override
    public String getControllerName() {
        return "basic_blaster";
    }
}
