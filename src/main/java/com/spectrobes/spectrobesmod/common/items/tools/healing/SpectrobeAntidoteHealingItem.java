package com.spectrobes.spectrobesmod.common.items.tools.healing;

import com.spectrobes.spectrobesmod.client.items.healing.renderer.AntidoteItemRenderer;
import com.spectrobes.spectrobesmod.client.items.healing.renderer.SerumItemRenderer;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.minerals.IWorthGura;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.util.NonNullLazy;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

public class SpectrobeAntidoteHealingItem extends Item implements IAnimatable, IWorthGura {
    public AnimationFactory animationControllers = GeckoLibUtil.createFactory(this);

    private int healAmount;
    private int guraWorth;
    private int tier;

    public int getSpectrobeHealAmount() {
        return healAmount;
    }

    public int getGuraWorth() {
        return guraWorth;
    }

    @Override
    public String getName() {
        return switch (getTier()) {
            default -> "basic_antidote";
        };
    }

    public int getTier() {
        return tier;
    }

    public SpectrobeAntidoteHealingItem(int healAmount, int guraWorth, int tier, Properties pProperties) {
        super(pProperties);
        this.healAmount = healAmount;
        this.guraWorth = guraWorth;
        this.tier = tier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pPlayer.level.isClientSide()) {
            pPlayer.getCapability(SpectrobeMaster.INSTANCE).ifPresent(playerSpectrobeMaster -> {
                playerSpectrobeMaster.getCurrentTeamUuids().forEach((integer, uuid) -> {
                    if(uuid != null) {
                        Spectrobe spectrobe = playerSpectrobeMaster.getSpectrobeByUuid(uuid);
                        if(spectrobe != null) spectrobe.addHealth(getSpectrobeHealAmount());
                        if(playerSpectrobeMaster.getCurrentTeamMember() != null && playerSpectrobeMaster.getCurrentTeamMember().active) {
                            List<EntitySpectrobe> spectrobes = pPlayer.level.getEntitiesOfClass(EntitySpectrobe.class, pPlayer.getBoundingBox().inflate(15));
                            if(spectrobes.size() > 0) {
                                spectrobes.forEach(entitySpectrobe -> {
                                    if(entitySpectrobe.getOwner().getUUID().equals(pPlayer.getUUID())) {
                                        entitySpectrobe.healSpectrobe(getSpectrobeHealAmount());
                                    }
                                });
                            }
                        }
                    }
                });
                SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(playerSpectrobeMaster));
            });

        }
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions()
        {
            private final NonNullLazy<BlockEntityWithoutLevelRenderer> ister = NonNullLazy.of(AntidoteItemRenderer::new);

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return ister.get();
            }
        });
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::controller));
    }

    private PlayState controller(AnimationEvent animationEvent) {
        animationEvent.getController().setAnimation(new AnimationBuilder().addAnimation("animation.serum.particle", ILoopType.EDefaultLoopTypes.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }
}
