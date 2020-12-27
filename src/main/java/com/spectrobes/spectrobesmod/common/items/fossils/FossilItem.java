package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public abstract class FossilItem extends Item {

    public FossilItem(Properties properties, String registryName) {
        super(properties);
        setRegistryName(registryName);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        itemStack.shrink(1);
        if(!worldIn.isRemote) {
            Spectrobe spectrobe = getSpectrobeInstance();
            playerIn.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(playerCap -> {
                playerCap.addSpectrobe(spectrobe);
                SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(playerCap),
                        (ServerPlayerEntity) playerIn);
            });
        } else {
            Minecraft.getInstance().player.sendMessage(new StringTextComponent("A new spectrobe has been sent to your prizmod."));
        }
        return new ActionResult<>(ActionResultType.SUCCESS, itemStack);
    }

    public abstract Spectrobe getSpectrobeInstance();
}
