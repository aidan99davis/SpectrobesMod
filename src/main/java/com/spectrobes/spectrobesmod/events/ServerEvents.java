package com.spectrobes.spectrobesmod.events;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.IPlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.armour.ISpectrobeArmour;
import com.spectrobes.spectrobesmod.common.krawl.KrawlInfectionManager;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.client.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.DamageUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber(modid = SpectrobesInfo.MOD_ID)
public class ServerEvents {

    @SubscribeEvent
    public static void OnServerTick(ServerTickEvent.Post event) {
        for (ServerLevel level : event.getServer().getAllLevels()) {
            KrawlInfectionManager.tickLevel(level);
        }
    }

    @SubscribeEvent
    public static void OnPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) {
            return;
        }

        IPlayerSpectrobeMaster spectrobeMaster = serverPlayer.getCapability(SpectrobeMaster.INSTANCE);

        if (spectrobeMaster == null) {
            return;
        }

        int currentHealth = spectrobeMaster.getCurrentHealth();
        spectrobeMaster.setMaxHealth(200);
        spectrobeMaster.setCurrentHealth(currentHealth);

        SpectrobesNetwork.sendToClient(
                new CSyncSpectrobeMasterPacket(spectrobeMaster),
                serverPlayer
        );
    }

    @SubscribeEvent
    public static void OnLivingEntityDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof EntityKrawl krawl)) {
            return;
        }

        KrawlProperties krawlProperties = krawl.krawlProperties;

        if (event.getEntity().getKillCredit() instanceof EntitySpectrobe spectrobe) {
            spectrobe.awardKillStats(krawlProperties);
            return;
        }

        if (!(event.getEntity().getKillCredit() instanceof ServerPlayer player)) {
            return;
        }

        IPlayerSpectrobeMaster spectrobeMaster = player.getCapability(SpectrobeMaster.INSTANCE);

        if (spectrobeMaster == null) {
            return;
        }

        spectrobeMaster.addXp(krawlProperties.getXpWorth());
        spectrobeMaster.addGura(krawlProperties.getGuraWorth());

        SpectrobesNetwork.sendToClient(
                new CSyncSpectrobeMasterPacket(spectrobeMaster),
                player
        );
    }

    @SubscribeEvent
    public static void OnPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide()) {
            return;
        }

        if (player.getY() > 150.0D) {
            // give aurora damage effect.
        }
    }

    @SubscribeEvent
    public static void OnLivingEntityHurt(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        if (!(event.getSource().getDirectEntity() instanceof EntityKrawl attackingKrawl)) {
            return;
        }

        int beneficialArmours = 0;
        int detrimentalArmours = 0;

        Iterable<ItemStack> armourItems = player.getArmorSlots();

        for (ItemStack armourItem : armourItems) {
            if (!(armourItem.getItem() instanceof IHasNature armourWithNature)) {
                continue;
            }

            beneficialArmours += 10;

            SpectrobeProperties.Nature attackerNature = attackingKrawl.krawlProperties.getNature();
            SpectrobeProperties.Nature armourNature = armourWithNature.getNature();

            if (DamageUtils.hasAdvantage(attackerNature, armourNature)) {
                detrimentalArmours += 15;
            }

            if (DamageUtils.hasDisadvantage(attackerNature, armourNature)) {
                beneficialArmours += 15;
            }
        }

        float armourBenefitVal = (beneficialArmours - detrimentalArmours) / 100.0F;

        if (armourBenefitVal <= 0.0F) {
            armourBenefitVal = 0.0F;
        }

        float finalAmount = (event.getAmount() - (event.getAmount() * armourBenefitVal)) / 4.0F;

        IPlayerSpectrobeMaster spectrobeMaster = player.getCapability(SpectrobeMaster.INSTANCE);

        if (spectrobeMaster == null) {
            return;
        }

        int newHealth = spectrobeMaster.getCurrentHealth() - Math.round(finalAmount);

        if (newHealth < 0) {
            newHealth = 0;
        }

        if (newHealth == 0) {
            event.setAmount(player.getMaxHealth());
        } else {
            event.setAmount(0.0F);
        }

        spectrobeMaster.setCurrentHealth(newHealth);

        SpectrobesNetwork.sendToClient(
                new CSyncSpectrobeMasterPacket(spectrobeMaster),
                player
        );
    }

    @SubscribeEvent
    public static void OnLivingArmourEquip(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        IPlayerSpectrobeMaster spectrobeMaster = player.getCapability(SpectrobeMaster.INSTANCE);

        if (spectrobeMaster == null) {
            return;
        }

        boolean changed = false;

        if (event.getFrom().getItem() instanceof ISpectrobeArmour armourItem) {
            spectrobeMaster.setMaxHealth(
                    spectrobeMaster.getMaxHealth() - armourItem.GetHealthBonus()
            );
            changed = true;
        }

        if (event.getTo().getItem() instanceof ISpectrobeArmour armourItem) {
            spectrobeMaster.setMaxHealth(
                    spectrobeMaster.getMaxHealth() + armourItem.GetHealthBonus()
            );
            changed = true;
        }

        if (changed) {
            SpectrobesNetwork.sendToClient(
                    new CSyncSpectrobeMasterPacket(spectrobeMaster),
                    player
            );
        }
    }
}