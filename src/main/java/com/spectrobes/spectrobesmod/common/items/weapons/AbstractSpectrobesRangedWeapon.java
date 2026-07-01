package com.spectrobes.spectrobesmod.common.items.weapons;

import com.spectrobes.spectrobesmod.client.entity.attacks.AttackEntities;
import com.spectrobes.spectrobesmod.common.entities.attacks.BasicBlasterEnergyBoltEntity;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.WeaponStats;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Random;

import static net.neoforged.neoforge.event.EventHooks.onArrowLoose;

public abstract class AbstractSpectrobesRangedWeapon extends Item implements GeoItem, ISpectrobeWeapon {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected float projectileSpeed = 0.5F;

    public AbstractSpectrobesRangedWeapon(Properties pProperties) {
        super(pProperties);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player playerentity) {

            int i = this.getUseDuration(pStack, playerentity) - pTimeLeft;
            i = onArrowLoose(pStack, pLevel, playerentity, i, true);
            if (i < 0) return;

//            float f = getPowerForTime(i); // BowItem
//            if (!((double)f < 0.1D)) {
            if (!((double)projectileSpeed < 0.1D)) {
                // V1 - OG
//                EnergyBoltEntity abstractarrowentity = new EnergyBoltEntity(AttackEntities.ENTITY_ENERGY_BOLT.get(), pLevel);

                // V2 - BasicBlasterEnergyBoltEntity
                BasicBlasterEnergyBoltEntity abstractarrowentity = new BasicBlasterEnergyBoltEntity(AttackEntities.ENTITY_PROJECTILE_BASIC_BLASTER.get(), pLevel);

                abstractarrowentity.setOwner(playerentity);

//                // V1 - OG
//                abstractarrowentity.AtkDamage = GetWeaponStats().AtkDamage;
//                abstractarrowentity.Nature = GetWeaponStats().Nature;

                // V2 - BasicBlasterEnergyBoltEntity
                abstractarrowentity.setAtkDamage(GetWeaponStats().AtkDamage);
                abstractarrowentity.setNature(GetWeaponStats().Nature);

                abstractarrowentity.setPos(playerentity.getX(), playerentity.getY() + 1.5, playerentity.getZ());

//                // V1 - OG
//                abstractarrowentity.shootFromRotation(playerentity, playerentity.xRotO, playerentity.yRotO, 0.0F, f, 1.0F);

                // V2 - BasicBlasterEnergyBoltEntity
                Vec3 look = pEntityLiving.getLookAngle();
                abstractarrowentity.shootFromRotation(playerentity, (float) look.x, (float) look.y, (float) look.z, projectileSpeed, 1.0F);

                pLevel.addFreshEntity(abstractarrowentity);

                pLevel.playSound(null, playerentity.getX(), playerentity.getY(), playerentity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (new Random().nextFloat() * 0.4F + 1.2F) + projectileSpeed * 0.5F);
                playerentity.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext context, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, context, pTooltip, pFlag);
        pTooltip.add(Component.literal("Weapon Tier: " + GetWeaponStats().Tier));
        pTooltip.add(Component.literal("Attack Stat: " + GetWeaponStats().AtkDamage));
        pTooltip.add(Component.literal("Weapon Speed: " + GetWeaponStats().Speed));
    }


    // = = = FROM BOW ITEM = = =
//    @Override
//    public Predicate<ItemStack> getAllSupportedProjectiles() {
//        return stack -> false;
//    }
//
//    @Override
//    public int getDefaultProjectileRange() {
//        return GetWeaponStats().Tier * 7;
//    }

    // = = = OTHER, MANDATORY METHODS = = =
    @Override
    public abstract void registerControllers(AnimatableManager.ControllerRegistrar controllers);
//    @Override public AnimationFactory getFactory() {
//        return this.factory;
//    }
//
//    public <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
//        return event.isMoving() ? PlayState.CONTINUE : PlayState.STOP;
//    }

    public abstract WeaponStats GetWeaponStats();

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

//    @Override
//    public void onAnimationSync(int id, int state) {
//        if (state == ANIM_OPEN) {
//            final AnimationController<?> controller = GeckoLibUtil.getControllerForID(this.factory, id, getControllerName());
//            if (controller.getAnimationState() == AnimationState.Stopped) {
//                runAnimation(controller);
//            }
//        }
//    }
//
//    private void runAnimation(AnimationController<?> controller) {
//        controller.markNeedsReload();
//    }

    public abstract String getControllerName();

    public SpectrobeProperties.Nature getNature() {
        return GetWeaponStats().Nature;
    }
}
