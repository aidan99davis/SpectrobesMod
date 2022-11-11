package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.List;

public abstract class FossilBlockItem extends BlockItem implements IAnimatable {

    public AnimationFactory factory = new AnimationFactory(this);

    public FossilBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pPlayer.isShiftKeyDown()) {
            ItemStack fossilStack = pPlayer.getItemInHand(pUsedHand);
            fossilStack.shrink(1);
            if(!pLevel.isClientSide) {
                Spectrobe spectrobe = getSpectrobeInstance();
                pPlayer.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(playerCap -> {
                    playerCap.addSpectrobe(spectrobe);
                    SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(playerCap),
                            (ServerPlayer) pPlayer);
                });
            } else {
                pPlayer.sendSystemMessage(Component.literal("A new spectrobe has been sent to your prizmod."));
            }
            return InteractionResultHolder.consume(fossilStack);
        } else {
            return super.use(pLevel, pPlayer, pUsedHand);
        }
    }

    public abstract Spectrobe getSpectrobeInstance();

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        pTooltip.add(Component.literal("Shift right click air to awaken this fossil."));
    }

    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
