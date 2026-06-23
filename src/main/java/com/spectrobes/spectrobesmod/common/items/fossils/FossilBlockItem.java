package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.client.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.network.PacketDistributor;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public abstract class FossilBlockItem extends BlockItem implements GeoItem {
    private static final Component AWAKEN_MESSAGE =
            Component.literal("A new spectrobe has been sent to your prizmod.");

    private static final Component TOOLTIP_AWAKEN =
            Component.literal("Shift right click air to awaken this fossil.");

    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    public FossilBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack fossilStack = player.getItemInHand(usedHand);

        if (!player.isShiftKeyDown()) {
            return super.use(level, player, usedHand);
        }

        if (level.isClientSide()) {
            return InteractionResultHolder.success(fossilStack);
        }

        if (!(player instanceof ServerPlayer serverPlayer)) {
            return InteractionResultHolder.fail(fossilStack);
        }

        var playerCap = serverPlayer.getCapability(SpectrobeMaster.INSTANCE);

        if (playerCap == null) {
            return InteractionResultHolder.fail(fossilStack);
        }

        Spectrobe spectrobe = this.getSpectrobeInstance();
        playerCap.addSpectrobe(spectrobe);

        fossilStack.shrink(1);

        PacketDistributor.sendToPlayer(
                serverPlayer,
                new CSyncSpectrobeMasterPacket(playerCap)
        );

        serverPlayer.sendSystemMessage(AWAKEN_MESSAGE);

        return InteractionResultHolder.consume(fossilStack);
    }

    public abstract Spectrobe getSpectrobeInstance();

    @Override
    public void appendHoverText(
            ItemStack stack,
            Item.TooltipContext context,
            List<Component> tooltip,
            TooltipFlag tooltipFlag
    ) {
        super.appendHoverText(stack, context, tooltip, tooltipFlag);
        tooltip.add(TOOLTIP_AWAKEN);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // Fossil block items currently have no animation controllers.
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationCache;
    }

    @Override
    public double getTick(Object object) {
        return 0.0D;
    }
}