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
import net.minecraft.world.World;

public class SamukabuFossilItem extends Item {

    public SamukabuFossilItem(Properties properties) {
        super(properties);
        setRegistryName("samukabu_fossil_item");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = new ItemStack(playerIn.getHeldItem(handIn).getItem(), 1);
        if(!worldIn.isRemote) {
            EntitySpectrobe samukabu = (EntitySpectrobe) SpectrobesEntities.ENTITY_SAMUKABU.get().spawn(worldIn,
                    itemStack,
                    playerIn,
                    playerIn.getPosition(),
                    SpawnReason.MOB_SUMMONED,
                    true,true
            );
            samukabu.setOwnerId(playerIn.getUniqueID());
            playerIn.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(playerCap -> {
                playerCap.addSpectrobe(samukabu.getRegistryName(), samukabu.getSpectrobeData());
            });
        }

        return new ActionResult<>(ActionResultType.SUCCESS, itemStack);
    }


}
