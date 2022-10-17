package com.spectrobes.spectrobesmod.common.event;


import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.DamageUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvents {

    @SubscribeEvent
    public static void OnPlayerJoin(PlayerEvent.PlayerLoggedInEvent evt) {
        if(!evt.getPlayer().level.isClientSide()) {
            evt.getPlayer().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                    .ifPresent(sm ->
                            SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(sm),
                                (ServerPlayerEntity) evt.getPlayer()));
        }
    }

    @SubscribeEvent
    public static void OnLivingEntityDeath(LivingDeathEvent event) {
        if(event.getEntityLiving() instanceof EntityKrawl) {
            if(event.getEntityLiving().getKillCredit() instanceof EntitySpectrobe) {
                EntitySpectrobe spectrobe = (EntitySpectrobe) event.getEntityLiving().getKillCredit();
                spectrobe.awardKillStats(((EntityKrawl)event.getEntityLiving()).krawlProperties);
            }
            if(event.getEntityLiving().getKillCredit() instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity)event.getEntityLiving().getKillCredit();

                player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                    sm.addGura(((EntityKrawl)event.getEntityLiving()).krawlProperties.getGuraWorth());
                    SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(sm), player);
                });
            }
        }
    }


    @SubscribeEvent
    public static void OnLivingEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        if(event.getEntityLiving() instanceof PlayerEntity) {
            if(event.getEntityLiving().getY() > 150) {
                //give aurora damage effect.
            }
        }
    }

    @SubscribeEvent
    public static void OnLivingEntityUpdate(LivingHurtEvent event) {
        if(event.getEntityLiving() instanceof PlayerEntity) {
            if(event.getSource().getDirectEntity() instanceof EntityKrawl) {
                int beneficialArmours = 0;
                int detrimentalArmours = 0;
                Iterable<ItemStack> armourItems = event.getEntityLiving().getArmorSlots();

                for (ItemStack armourItem :
                        armourItems) {
                    if(armourItem.getItem() instanceof IHasNature) {
                        beneficialArmours = beneficialArmours + 10; //A base addition just for having armour from the mod.
                        SpectrobeProperties.Nature attackerNature = ((EntityKrawl) event.getSource().getDirectEntity()).krawlProperties.getNature();
                        if(DamageUtils.hasAdvantage(attackerNature, ((IHasNature) armourItem.getItem()).getNature())) detrimentalArmours+=15;
                        if(DamageUtils.hasDisadvantage(attackerNature, ((IHasNature) armourItem.getItem()).getNature())) beneficialArmours+=15;
                    }
                }

                int armourBenefitVal = beneficialArmours - detrimentalArmours;
                SpectrobesInfo.LOGGER.debug("Armour Benefit Val: " + armourBenefitVal);
                //TODO: When adding more armours, framework a way to increase the armours benefit for higher tiers
                event.setAmount(event.getAmount() + ((event.getAmount() * armourBenefitVal) / 10));
            }
        }
    }
}
