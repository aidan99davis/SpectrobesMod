package com.spectrobes.spectrobesmod.common.items.weapons;

import com.spectrobes.spectrobesmod.client.entity.attacks.AttackEntities;
import com.spectrobes.spectrobesmod.common.entities.attacks.EnergyBoltEntity;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.WeaponStats;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Predicate;

public abstract class SpectrobesRangedWeapon extends BowItem implements GeoItem, ISpectrobeWeapon {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public SpectrobesRangedWeapon(Item.Properties properties) {
        super(properties);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeLeft) {
        if (!(livingEntity instanceof Player player)) {
            return;
        }

        int charge = this.getUseDuration(stack, player) - timeLeft;
        charge = EventHooks.onArrowLoose(stack, level, player, charge, true);

        if (charge < 0) {
            return;
        }

        float power = BowItem.getPowerForTime(charge);

        if (power < 0.1F) {
            return;
        }

        if (!level.isClientSide) {
            EnergyBoltEntity energyBolt = new EnergyBoltEntity(AttackEntities.ENTITY_ENERGY_BOLT.get(), level);

            energyBolt.setOwner(player);
            energyBolt.setPos(player.getX(), player.getY() + 1.5D, player.getZ());
            energyBolt.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, power, 1.0F);

            energyBolt.AtkDamage = getWeaponStats().AtkDamage;
            energyBolt.Nature = getWeaponStats().Nature;

            level.addFreshEntity(energyBolt);
        }

        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ARROW_SHOOT,
                SoundSource.PLAYERS,
                1.0F,
                1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + power * 0.5F
        );

        player.awardStat(Stats.ITEM_USED.get(this));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        InteractionResultHolder<ItemStack> eventResult = EventHooks.onArrowNock(stack, level, player, hand, true);

        if (eventResult != null) {
            return eventResult;
        }

        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);

        WeaponStats stats = getWeaponStats();

        tooltip.add(Component.literal("Weapon Tier: " + stats.Tier));
        tooltip.add(Component.literal("Attack Stat: " + stats.AtkDamage));
        tooltip.add(Component.literal("Weapon Speed: " + stats.Speed));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return stack -> false;
    }

    @Override
    public int getDefaultProjectileRange() {
        return getWeaponStats().Tier * 7;
    }

    @Override
    public abstract void registerControllers(AnimatableManager.ControllerRegistrar controllers);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public abstract WeaponStats getWeaponStats();

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    public abstract String getControllerName();

    public SpectrobeProperties.Nature getNature() {
        return getWeaponStats().Nature;
    }
}