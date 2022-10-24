package com.spectrobes.spectrobesmod.common.items.weapons;

import com.spectrobes.spectrobesmod.client.entity.attacks.AttackEntities;
import com.spectrobes.spectrobesmod.common.entities.attacks.EnergyBoltEntity;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.WeaponStats;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
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
import java.util.function.Predicate;

public abstract class SpectrobesRangedWeapon extends BowItem implements IAnimatable, ISyncable, ISpectrobeWeapon {
    private static final int ANIM_OPEN = 0;
    public AnimationFactory factory = new AnimationFactory(this);

    public SpectrobesRangedWeapon(Properties pProperties) {
        super(pProperties);
        GeckoLibNetwork.registerSyncable(this);
    }

    @Override
    public void releaseUsing(ItemStack pStack, World pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)pEntityLiving;

            int i = this.getUseDuration(pStack) - pTimeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(pStack, pLevel, playerentity, i, true);
            if (i < 0) return;

            float f = getPowerForTime(i);
            if (!((double)f < 0.1D)) {
                EnergyBoltEntity abstractarrowentity = new EnergyBoltEntity(AttackEntities.ENTITY_ENERGY_BOLT.get(), pLevel);

                abstractarrowentity.setOwner(playerentity);
                abstractarrowentity.setPos(playerentity.getX(), playerentity.getY() + 1.5, playerentity.getZ());
                abstractarrowentity.shootFromRotation(playerentity, playerentity.xRot, playerentity.yRot, 0.0F, f, 1.0F);
                abstractarrowentity.AtkDamage = GetWeaponStats().AtkDamage;
                abstractarrowentity.Nature = GetWeaponStats().Nature;

                pLevel.addFreshEntity(abstractarrowentity);
//                if (!pLevel.isClientSide) {
//
//                }

                pLevel.playSound((PlayerEntity)null, playerentity.getX(), playerentity.getY(), playerentity.getZ(), SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                playerentity.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        pTooltip.add(new StringTextComponent("Weapon Tier: " + GetWeaponStats().Tier));
        pTooltip.add(new StringTextComponent("Attack Stat: " + GetWeaponStats().AtkDamage));
        pTooltip.add(new StringTextComponent("Weapon Speed: " + GetWeaponStats().Speed));
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
        return PlayState.CONTINUE;
    }

    public abstract WeaponStats GetWeaponStats();

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
