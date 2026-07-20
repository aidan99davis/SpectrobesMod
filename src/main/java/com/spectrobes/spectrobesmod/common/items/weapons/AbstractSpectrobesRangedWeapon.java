package com.spectrobes.spectrobesmod.common.items.weapons;

import com.spectrobes.spectrobesmod.client.entity.attacks.AttackEntities;
import com.spectrobes.spectrobesmod.common.entities.attacks.AbstractEnergyBoltEntity;
import com.spectrobes.spectrobesmod.common.entities.attacks.BasicEnergyBoltEntity;
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
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Random;

public abstract class AbstractSpectrobesRangedWeapon extends Item implements GeoItem, ISpectrobeWeapon {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // Controls how much the projectile's trajectory will depend on player movement.
    // 0 = Will just fly in direction player is moving (if they are moving)
    // 1 = Will fly in direction the player is facing
    // This is a gradient, but should be kept closer to 1 cause it's hard to hit stuff beyond 5 blocks otherwise.
    protected float projectileSpeed = 1F;

    public AbstractSpectrobesRangedWeapon(Properties pProperties) {
        super(pProperties);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext context, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, context, pTooltip, pFlag);
        pTooltip.add(Component.literal("Weapon Tier: " + GetWeaponStats().Tier));
        pTooltip.add(Component.literal("Attack Stat: " + GetWeaponStats().AtkDamage));
        pTooltip.add(Component.literal("Weapon Speed: " + GetWeaponStats().Speed));
    }

    /** Starts the "using" process. */
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(stack);
    }

    /** Determines how long before use is at max value. */
    @Override public int getUseDuration(ItemStack stack, LivingEntity entity) { return 72000; }

    /** Fires the blaster bolt. */
    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (!(pEntityLiving instanceof Player player)) return;

        // The power stuff could be cool, but like... Blasters don't have charge mechanics in Spectrobes.
        //      Just set speed and damage scaling off rallen's stats.
        //      Completely fine with re-enabling this if ppl want it tho.
//        int charge = this.getUseDuration(pStack, player) - pTimeLeft;
//        charge = EventHooks.onArrowLoose(pStack, pLevel, player, charge, true);
//        if (charge < 0) return;
//
//        float power = getPowerForTime(charge); // BowItem
//        if ((double)power < 0.1D) return; // Speed determined by charge power
//        if (projectileSpeed < 0.1D) return; // Set speed
        fireProjectile(pLevel, player);

        player.awardStat(Stats.ITEM_USED.get(this));
    }

    /** Override this to replace the projectile that this weapon shoots. */
    protected AbstractEnergyBoltEntity createProjectile(Level pLevel) {
        return new BasicEnergyBoltEntity(AttackEntities.ENTITY_PROJECTILE_BASIC.get(), pLevel);
    }

    /** Firing behavior + setting projectile stats. */
    protected void fireProjectile(Level pLevel, Player player) {
        // Safety - Return if not on server
        if (pLevel.isClientSide) return;

        // Create Projectile
        AbstractEnergyBoltEntity projectile = createProjectile(pLevel);

        // Set Projectile Stats
        projectile.setOwner(player);
        projectile.setPos(player.getX(), player.getY() + 1.5D, player.getZ());
        projectile.setAtkDamage(GetWeaponStats().AtkDamage);
        projectile.setNature(GetWeaponStats().Nature);

        // Spawn Projectile
//        projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, projectileSpeed, 1.0F);
        projectile.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, projectileSpeed, 1.0F);
        pLevel.addFreshEntity(projectile);

        // Firing SFX
        pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (new Random().nextFloat() * 0.4F + 1.2F) + projectileSpeed * 0.5F);
    }

    // = = = OTHER, MANDATORY METHODS = = =
    @Override public abstract void registerControllers(AnimatableManager.ControllerRegistrar controllers);
    @Override public AnimatableInstanceCache getAnimatableInstanceCache() { return this.cache; }
    @Override public UseAnim getUseAnimation(ItemStack pStack) { return UseAnim.BOW; }

    public abstract String getControllerName();
    public abstract WeaponStats GetWeaponStats();
    public SpectrobeProperties.Nature getNature() { return GetWeaponStats().Nature; }
//    @Override public Predicate<ItemStack> getAllSupportedProjectiles() { return stack -> false; }
    public PlayState predicate(AnimationState animationState) {
        return animationState.isMoving() ? PlayState.CONTINUE : PlayState.STOP;
    }
}
