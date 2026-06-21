package com.spectrobes.spectrobesmod.common.items.weapons;

import com.spectrobes.spectrobesmod.client.items.renderer.XellesTrophyItemRenderer;
import com.spectrobes.spectrobesmod.client.items.weapons.renderer.BasicSwordItemRenderer;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.WeaponStats;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;

import java.util.function.Consumer;

public class BasicSwordItem extends SpectrobesWeapon {
    public BasicSwordItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions()
        {
            private final NonNullLazy<BlockEntityWithoutLevelRenderer> ister = NonNullLazy.of(() -> new BasicSwordItemRenderer());

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return ister.get();
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController(this, getControllerName(), 1, super::predicate));
    }

    @Override
    public WeaponStats GetWeaponStats() {
        return new WeaponStats(15, 1, 1, false, false, SpectrobeProperties.Nature.OTHER);
    }

    @Override
    public String getControllerName() {
        return "basic_sword";
    }
}
