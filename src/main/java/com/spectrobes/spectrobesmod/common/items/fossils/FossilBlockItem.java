package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.UUID;

import net.minecraft.item.Item.Properties;

public abstract class FossilBlockItem extends BlockItem implements IAnimatable {

    public AnimationFactory factory = new AnimationFactory(this);

    public FossilBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        if(context.getPlayer().isShiftKeyDown()) {
            context.getItemInHand().shrink(1);
            if(!context.getLevel().isClientSide) {
                Spectrobe spectrobe = getSpectrobeInstance();
                context.getPlayer().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(playerCap -> {
                    playerCap.addSpectrobe(spectrobe);
                    SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(playerCap),
                            (ServerPlayerEntity) context.getPlayer());
                });
            } else {
                UUID playerUUID = Minecraft.getInstance().player.getUUID();
                Minecraft.getInstance().player.sendMessage(new StringTextComponent("A new spectrobe has been sent to your prizmod."), playerUUID);
            }
            return ActionResultType.CONSUME;
        } else {
            return super.useOn(context);
        }
    }

    public abstract Spectrobe getSpectrobeInstance();

    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
