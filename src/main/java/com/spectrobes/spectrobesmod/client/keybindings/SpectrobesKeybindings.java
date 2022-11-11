package com.spectrobes.spectrobesmod.client.keybindings;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.*;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesToolsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.UUID;

public class SpectrobesKeybindings {
    public static KeyMapping OPEN_TOOL_MENU_KEYBIND =
            new KeyMapping("key.prizmod.open",
                    GLFW.GLFW_KEY_R,
                    "key.prizmod.category");

    public static KeyMapping CYCLE_TOOL_MENU_LEFT_KEYBIND =
            new KeyMapping("key.prizmod.cycle.left",
                    GLFW.GLFW_KEY_LEFT_BRACKET,
                    "key.prizmod.category");

    public static KeyMapping CYCLE_TOOL_MENU_RIGHT_KEYBIND =
            new KeyMapping("key.prizmod.cycle.right",
                    GLFW.GLFW_KEY_RIGHT_BRACKET,
                    "key.prizmod.category");

    public static KeyMapping ATTACK_KEYBIND =
            new KeyMapping("key.prizmod.attack",
                    GLFW.GLFW_KEY_F,
                    "key.prizmod.category");

    private static void SummonPlayerSpectrobe(Minecraft mc, Spectrobe currentMember, UUID oldUUID, List<EntitySpectrobe> spectrobes) {
        if(oldUUID != null) {
            for(EntitySpectrobe spectrobe : spectrobes) {
                if(spectrobe.getOwner() != null && spectrobe.getOwnerUUID().equals(mc.player.getUUID())) {
                    spectrobe.despawn();
                }
            }
            if(mc.player.level.isClientSide()) {
                SpectrobesNetwork.sendToServer(new SDespawnSpectrobePacket(mc.player.blockPosition()));
            }

        }
    }

    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SpectrobesInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public class KeybindingForgeEvents {
        @SubscribeEvent
        public static void handleKeys(InputEvent.Key ev)
        {
            Minecraft mc = Minecraft.getInstance();

            if (mc.screen == null && mc.player.getInventory()
                    .contains(new ItemStack(SpectrobesToolsRegistry.prizmod_item.get())))
            {
                if (ATTACK_KEYBIND.consumeClick())
                {
                    Vec3 vector3d = mc.player.getEyePosition(1.0F);
                    Vec3 vector3d1 = mc.player.getViewVector(1.0F);
                    double d0 = 15;
                    double d1 = d0 * d0;
                    Vec3 vector3d2 = vector3d.add(vector3d1.x * d0, vector3d1.y * d0, vector3d1.z * d0);
                    AABB axisalignedbb = mc.player.getBoundingBox().expandTowards(vector3d1.scale(d0)).inflate(1.0D, 1.0D, 1.0D);

                    EntityHitResult result = ProjectileUtil.getEntityHitResult(mc.player,
                            vector3d, vector3d2, axisalignedbb,
                            entity -> entity instanceof EntityKrawl,
                            d1);

                    if (result != null) {
                        if (result.getEntity() != null) {
                            if (result.getEntity() instanceof EntityKrawl) {
                                EntityKrawl krawl = (EntityKrawl) result.getEntity();
                                mc.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                                    if (sm.getCurrentTeamMember() != null && sm.getCurrentTeamMember().active && sm.getCurrentTeamMember().properties.getStage() != SpectrobeProperties.Stage.CHILD) {
                                        List<EntitySpectrobe> spectrobes = mc.player.level
                                                .getEntitiesOfClass(EntitySpectrobe.class, mc.player.getBoundingBox().inflate(30, 30, 30));
                                        for (EntitySpectrobe spectrobe :
                                                spectrobes) {
                                            if (spectrobe.getSpectrobeData().SpectrobeUUID.equals(sm.getCurrentTeamMember().SpectrobeUUID)) {
                                                SpectrobesNetwork.sendToServer(new CSpectrobeAttackPacket(spectrobe.getId(), krawl.getId()));
                                                spectrobe.setTarget(krawl);
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                }

                if (OPEN_TOOL_MENU_KEYBIND.consumeClick())
                {
                    if (mc.screen == null)
                    {
                        mc.setScreen(new PrizmodScreen(
                                PrizmodContainer.PRIZMOD.get()
                                        .create(0, mc.player.getInventory()),
                                mc.player.getInventory(), Component.empty()));

                    }
                }

                if (CYCLE_TOOL_MENU_LEFT_KEYBIND.consumeClick())
                {
                    mc.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                            .ifPresent(sm -> {
                                Spectrobe currentMember = sm.getCurrentTeamMember();

                                UUID oldUUID = currentMember != null? currentMember.SpectrobeUUID : null;
                                sm.changeSelected(-1);
                                List<EntitySpectrobe> spectrobes = mc.player.level
                                        .getEntitiesOfClass(EntitySpectrobe.class, mc.player.getBoundingBox().inflate(30, 30, 30));
                                SummonPlayerSpectrobe(mc, currentMember, oldUUID, spectrobes);
                                if(sm.getCurrentTeamMember() != null && sm.getCurrentTeamMember().currentHealth > 0) {
                                    sm.spawnCurrent();
                                    SpectrobesNetwork.sendToServer(new SSpawnSpectrobePacket(sm.getCurrentTeamMember()));
                                }
                                SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(sm));
                            });
                }

                if (CYCLE_TOOL_MENU_RIGHT_KEYBIND.consumeClick())
                {
                    mc.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                            .ifPresent(sm -> {
                                Spectrobe currentMember = sm.getCurrentTeamMember();

                                UUID oldUUID = currentMember != null? currentMember.SpectrobeUUID : null;
                                sm.changeSelected(1);
                                List<EntitySpectrobe> spectrobes = mc.player.level
                                        .getEntitiesOfClass(EntitySpectrobe.class, mc.player.getBoundingBox().inflate(30, 30, 30));

                                SummonPlayerSpectrobe(mc, currentMember, oldUUID, spectrobes);

                                if(sm.getCurrentTeamMember() != null && sm.getCurrentTeamMember().currentHealth > 0) {
                                    sm.spawnCurrent();
                                    SpectrobesNetwork.sendToServer(new SSpawnSpectrobePacket(sm.getCurrentTeamMember()));
                                }
                                SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(sm));

                            });
                }
            }

        }
    }

    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SpectrobesInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public class KeybindingModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(ATTACK_KEYBIND);
            event.register(OPEN_TOOL_MENU_KEYBIND);
            event.register(CYCLE_TOOL_MENU_LEFT_KEYBIND);
            event.register(CYCLE_TOOL_MENU_RIGHT_KEYBIND);
        }
    }
}
