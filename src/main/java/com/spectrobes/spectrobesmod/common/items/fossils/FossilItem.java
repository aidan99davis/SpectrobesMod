package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
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
        if(!worldIn.isRemote) {
//            EntitySpectrobe spectrobe = getSpectrobeInstance().spawn(worldIn,
//                    null,
//                    new StringTextComponent("Komainu"),
//                    playerIn,
//                    playerIn.getPosition(),
//                    SpawnReason.MOB_SUMMONED,
//                    true,true
//            );
//            spectrobe.setCustomName(new StringTextComponent(spectrobe.getSpectrobeData().name));
//            spectrobe.getSpectrobeData().setActive();
//            spectrobe.setOwnerId(playerIn.getUniqueID());
            Spectrobe spectrobe = getSpectrobeInstance();
            playerIn.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(playerCap -> {
                playerCap.addSpectrobe(spectrobe);
                itemStack.shrink(1);
            });
        } else {
            Minecraft.getInstance().player.sendChatMessage("A new spectrobe has been sent to your prizmod.");
        }
        return new ActionResult<>(ActionResultType.SUCCESS, itemStack);
    }

    public abstract Spectrobe getSpectrobeInstance();
}
