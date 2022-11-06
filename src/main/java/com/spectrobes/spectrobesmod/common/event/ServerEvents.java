package com.spectrobes.spectrobesmod.common.event;


import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.armour.ISpectrobeArmour;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.DamageUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvents {

    @SubscribeEvent
    public static void OnPlayerJoin(PlayerEvent.PlayerLoggedInEvent evt) {
        if(!evt.getEntity().level.isClientSide()) {
            evt.getEntity().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                .ifPresent(sm -> {
                    int currentHealth = sm.getCurrentHealth();
                    sm.setMaxHealth(200);
                    sm.setCurrentHealth(currentHealth);

                    SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(sm),
                        (ServerPlayer) evt.getEntity());
                });

        }
    }

    @SubscribeEvent
    public static void OnLivingEntityDeath(LivingDeathEvent event) {
        if(event.getEntity() instanceof EntityKrawl) {
            KrawlProperties krawlProperties = ((EntityKrawl)event.getEntity()).krawlProperties;
            if(event.getEntity().getKillCredit() instanceof EntitySpectrobe) {
                EntitySpectrobe spectrobe = (EntitySpectrobe) event.getEntity().getKillCredit();
                spectrobe.awardKillStats(krawlProperties);
            }
            if(event.getEntity().getKillCredit() instanceof ServerPlayer) {
                ServerPlayer player = (ServerPlayer)event.getEntity().getKillCredit();

                player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                    sm.addXp(krawlProperties.getXpWorth());
                    sm.addGura(((EntityKrawl)event.getEntity()).krawlProperties.getGuraWorth());
                    SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(sm), player);
                });
            }
        }
    }

    @SubscribeEvent
    public static void OnLivingEntityUpdate(LivingEvent.LivingTickEvent event) {
        if(event.getEntity() instanceof Player) {
            if(event.getEntity().getY() > 150) {
                //give aurora damage effect.
            }
        }
    }

    @SubscribeEvent
    public static void OnLivingEntityUpdate(LivingHurtEvent event) {
        if(event.getEntity() instanceof Player) {
            if(event.getSource().getDirectEntity() instanceof EntityKrawl) {
                int beneficialArmours = 0;
                int detrimentalArmours = 0;
                Iterable<ItemStack> armourItems = event.getEntity().getArmorSlots();

                for (ItemStack armourItem :
                        armourItems) {
                    if(armourItem.getItem() instanceof IHasNature) {
                        beneficialArmours = beneficialArmours + 10; //A base addition just for having armour from the mod.
                        SpectrobeProperties.Nature attackerNature = ((EntityKrawl) event.getSource().getDirectEntity()).krawlProperties.getNature();
                        if(DamageUtils.hasAdvantage(attackerNature, ((IHasNature) armourItem.getItem()).getNature())) detrimentalArmours+=15;
                        if(DamageUtils.hasDisadvantage(attackerNature, ((IHasNature) armourItem.getItem()).getNature())) beneficialArmours+=15;
                    }
                }
                if(!event.getEntity().level.isClientSide()) {
                    SpectrobesInfo.LOGGER.debug("beneficialArmours: " + beneficialArmours);
                    SpectrobesInfo.LOGGER.debug("detrimentalArmours: " + detrimentalArmours);
                    float armourBenefitVal = (beneficialArmours - detrimentalArmours) / 100f;
                    SpectrobesInfo.LOGGER.debug("armourBenefitVal 1: " + armourBenefitVal);
                    if(armourBenefitVal <= 0) armourBenefitVal = 0;
                    SpectrobesInfo.LOGGER.debug("armount: " + event.getAmount());
                    SpectrobesInfo.LOGGER.debug("armourBenefitVal 2: " + armourBenefitVal);
                    float finalAmount = (event.getAmount() - ((event.getAmount() * armourBenefitVal))) / 4;/*TODO REMOVE THIS DIVISION WHEN BETTER ARMOUR IS POSSIBLE */
                    SpectrobesInfo.LOGGER.debug("finalAmount: " + finalAmount);
                    event.getEntity().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(playerSpectrobeMaster -> {
                        int newHealth = playerSpectrobeMaster.getCurrentHealth() - new Double(finalAmount).intValue();
                        if(newHealth < 0) newHealth = 0;
                        if(newHealth == 0) {
                            event.setAmount(event.getEntity().getMaxHealth());
                        } else {
                            event.setAmount(0);
                        }
                        playerSpectrobeMaster.setCurrentHealth(newHealth);
                        SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(playerSpectrobeMaster), (ServerPlayer) event.getEntity());
                    });
                } else {
                    event.setAmount(0);
                }
                //TODO: When adding more armours, framework a way to increase the armours benefit for higher tiers
            }
        }
    }

    @SubscribeEvent
    public static void OnLivingArmourEquip(LivingEquipmentChangeEvent event) {
        if(!event.getEntity().level.isClientSide()
                && event.getEntity() instanceof ServerPlayer) {
            PlayerSpectrobeMaster psm = event.getEntity().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).orElseThrow(() -> new NullPointerException("No player capability found"));
            if(event.getFrom().getItem()  instanceof ISpectrobeArmour) {
                ISpectrobeArmour armourItem = (ISpectrobeArmour) event.getFrom().getItem();
                psm.setMaxHealth(psm.getMaxHealth() - armourItem.GetHealthBonus());
                SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(psm), (ServerPlayer) event.getEntity());
            }
            if(event.getTo().getItem() instanceof ISpectrobeArmour) {
                ISpectrobeArmour armourItem = (ISpectrobeArmour) event.getTo().getItem();
                psm.setMaxHealth(psm.getMaxHealth() + armourItem.GetHealthBonus());
                SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(psm), (ServerPlayer) event.getEntity());
            }

        }
    }
}
