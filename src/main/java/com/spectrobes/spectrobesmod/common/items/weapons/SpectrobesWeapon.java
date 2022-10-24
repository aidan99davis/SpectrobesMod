package com.spectrobes.spectrobesmod.common.items.weapons;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.WeaponStats;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public abstract class SpectrobesWeapon extends ToolItem implements IAnimatable, ISyncable, ISpectrobeWeapon {
    private static final int ANIM_OPEN = 0;
    public AnimationFactory factory = new AnimationFactory(this);

    public SpectrobesWeapon(Properties pProperties) {
        super(5, 1, ItemTier.DIAMOND, new HashSet<>(), pProperties);
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public abstract void registerControllers(AnimationData data);

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return PlayState.CONTINUE;
    }

    public abstract WeaponStats GetWeaponStats();

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        pTooltip.add(new StringTextComponent("Weapon Tier: " + GetWeaponStats().Tier));
        pTooltip.add(new StringTextComponent("Attack Stat: " + GetWeaponStats().AtkDamage));
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public void onAnimationSync(int id, int state) {
        if (state == ANIM_OPEN) {
            final AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, getControllerName());
            if (controller.getAnimationState() == AnimationState.Stopped) {
                runAnimation(controller);
            }
        }
    }

    private void runAnimation(AnimationController<?> controller) {
        controller.markNeedsReload();
    }

    public abstract String getControllerName();

    public SpectrobeProperties.Nature getNature() {
        return GetWeaponStats().Nature;
    }
}
