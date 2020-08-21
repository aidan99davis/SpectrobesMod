package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.client.entity.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.entities.EntitySpectrobe;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class KomainuFossilItem extends Item {

    public KomainuFossilItem(Properties properties) {
        super(properties);
        setRegistryName("komainu_fossil_item");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = new ItemStack(playerIn.getHeldItem(handIn).getItem(), 1);
        if(!worldIn.isRemote) {
            EntitySpectrobe komainu = (EntitySpectrobe) SpectrobesEntities.ENTITY_KOMAINU.get().spawn(worldIn,
                    null,
                    new StringTextComponent("Komainu"),
                    playerIn,
                    playerIn.getPosition(),
                    SpawnReason.MOB_SUMMONED,
                    true,true
            );
            komainu.setCustomName(new StringTextComponent(komainu.getSpectrobeData().name));
            komainu.getSpectrobeData().setActive();
            komainu.setOwnerId(playerIn.getUniqueID());
            playerIn.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(playerCap -> {
                playerCap.addSpectrobe(komainu.getSpectrobeData());
            });
        }

        return new ActionResult<>(ActionResultType.SUCCESS, itemStack);
    }


}
