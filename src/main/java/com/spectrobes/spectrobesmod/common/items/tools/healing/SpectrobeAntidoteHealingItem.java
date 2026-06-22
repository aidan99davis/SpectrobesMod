package com.spectrobes.spectrobesmod.common.items.tools.healing;

import com.spectrobes.spectrobesmod.client.items.healing.renderer.AntidoteItemRenderer;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.minerals.IWorthGura;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

public class SpectrobeAntidoteHealingItem extends Item implements GeoItem, IWorthGura {
    private static final RawAnimation PARTICLE_ANIMATION =
            RawAnimation.begin().thenLoop("animation.serum.particle");

    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    private final int healAmount;
    private final int guraWorth;
    private final int tier;

    public SpectrobeAntidoteHealingItem(int healAmount, int guraWorth, int tier, Properties properties) {
        super(properties);

        this.healAmount = healAmount;
        this.guraWorth = guraWorth;
        this.tier = tier;
    }

    public int getSpectrobeHealAmount() {
        return this.healAmount;
    }

    @Override
    public int getGuraWorth() {
        return this.guraWorth;
    }

    @Override
    public String getName() {
        return switch (getTier()) {
            default -> "basic_antidote";
        };
    }

    public int getTier() {
        return this.tier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        if (level.isClientSide()) {
            PlayerSpectrobeMaster playerSpectrobeMaster = player.getCapability(SpectrobeMaster.INSTANCE);

            if (playerSpectrobeMaster != null) {
                playerSpectrobeMaster.getCurrentTeamUuids().forEach((slot, uuid) -> {
                    if (uuid == null) {
                        return;
                    }

                    Spectrobe spectrobe = playerSpectrobeMaster.getSpectrobeByUuid(uuid);

                    if (spectrobe != null) {
                        spectrobe.addHealth(getSpectrobeHealAmount());
                    }

                    if (playerSpectrobeMaster.getCurrentTeamMember() != null
                            && playerSpectrobeMaster.getCurrentTeamMember().active) {
                        List<EntitySpectrobe> spectrobeEntities = level.getEntitiesOfClass(
                                EntitySpectrobe.class,
                                player.getBoundingBox().inflate(15.0D)
                        );

                        for (EntitySpectrobe entitySpectrobe : spectrobeEntities) {
                            LivingEntity owner = entitySpectrobe.getOwner();

                            if (owner != null && owner.getUUID().equals(player.getUUID())) {
                                entitySpectrobe.healSpectrobe(getSpectrobeHealAmount());
                            }
                        }
                    }
                });

                SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(playerSpectrobeMaster));
            }
        }

        return InteractionResultHolder.consume(stack);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::controller));
    }

    private PlayState controller(AnimationState<SpectrobeAntidoteHealingItem> animationState) {
        animationState.setAnimation(PARTICLE_ANIMATION);
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationCache;
    }
}