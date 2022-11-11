package com.spectrobes.spectrobesmod.common.items.weapons;

import com.spectrobes.spectrobesmod.client.entity.attacks.AttackEntities;
import com.spectrobes.spectrobesmod.common.entities.attacks.EnergyBoltEntity;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.WeaponStats;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
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
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public abstract class SpectrobesRangedWeapon extends BowItem implements IAnimatable, ISyncable, ISpectrobeWeapon {
    private static final int ANIM_OPEN = 0;
    public AnimationFactory factory = new AnimationFactory(this);

    public SpectrobesRangedWeapon(Item.Properties pProperties) {
        super(pProperties);
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player playerentity) {

            int i = this.getUseDuration(pStack) - pTimeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(pStack, pLevel, playerentity, i, true);
            if (i < 0) return;

            float f = getPowerForTime(i);
            if (!((double)f < 0.1D)) {
                EnergyBoltEntity abstractarrowentity = new EnergyBoltEntity(AttackEntities.ENTITY_ENERGY_BOLT.get(), pLevel);

                abstractarrowentity.setOwner(playerentity);
                abstractarrowentity.setPos(playerentity.getX(), playerentity.getY() + 1.5, playerentity.getZ());
                abstractarrowentity.shootFromRotation(playerentity, playerentity.xRotO, playerentity.yRotO, 0.0F, f, 1.0F);
                abstractarrowentity.AtkDamage = GetWeaponStats().AtkDamage;
                abstractarrowentity.Nature = GetWeaponStats().Nature;

                pLevel.addFreshEntity(abstractarrowentity);

                pLevel.playSound(null, playerentity.getX(), playerentity.getY(), playerentity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (new Random().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                playerentity.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        pTooltip.add(Component.literal("Weapon Tier: " + GetWeaponStats().Tier));
        pTooltip.add(Component.literal("Attack Stat: " + GetWeaponStats().AtkDamage));
        pTooltip.add(Component.literal("Weapon Speed: " + GetWeaponStats().Speed));
    }


    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return x -> true;
//        return null;
    }

    @Override
    public int getDefaultProjectileRange() {
        return GetWeaponStats().Tier * 7;
    }

    @Override
    public abstract void registerControllers(AnimationData data);

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        return event.isMoving() ? PlayState.CONTINUE : PlayState.STOP;
    }

    public abstract WeaponStats GetWeaponStats();

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
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
