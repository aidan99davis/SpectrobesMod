package com.spectrobes.spectrobesmod.client.keybindings;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SChangeDimensionPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SDespawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSpawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SpectrobesInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpectrobesKeybindings {
    public static KeyBinding OPEN_TOOL_MENU_KEYBIND;
    public static KeyBinding CYCLE_TOOL_MENU_LEFT_KEYBIND;
    public static KeyBinding CYCLE_TOOL_MENU_RIGHT_KEYBIND;
//    public static KeyBinding TP_TO_GENSHI_KEYBIND;

    private static boolean toolMenuKeyWasDown = false;
    private static boolean cycleLeftKeyWasDown = false;
    private static boolean cycleRightKeyWasDown = false;
//    private static boolean tpKeyWasDown = false;

    public static void initKeybinds()
    {
        ClientRegistry.registerKeyBinding(OPEN_TOOL_MENU_KEYBIND =
                new KeyBinding("key.prizmod.open", GLFW.GLFW_KEY_R, "key.prizmod.category"));

        ClientRegistry.registerKeyBinding(CYCLE_TOOL_MENU_LEFT_KEYBIND =
                new KeyBinding("key.prizmod.cycle.left",  InputMappings.UNKNOWN.getValue(), "key.prizmod.category"));

        ClientRegistry.registerKeyBinding(CYCLE_TOOL_MENU_RIGHT_KEYBIND =
                new KeyBinding("key.prizmod.cycle.right",  InputMappings.UNKNOWN.getValue(), "key.prizmod.category"));

//        ClientRegistry.registerKeyBinding(TP_TO_GENSHI_KEYBIND =
//                new KeyBinding("key.prizmod.tp.genshi",  InputMappings.UNKNOWN.getValue(), "key.prizmod.category"));

    }

    @SubscribeEvent
    public static void handleKeys(TickEvent.ClientTickEvent ev)
    {
        Minecraft mc = Minecraft.getInstance();

//        boolean tpKeyDown = TP_TO_GENSHI_KEYBIND.isDown();
//        if (tpKeyDown && !tpKeyWasDown)
//        {
//            while (TP_TO_GENSHI_KEYBIND.consumeClick())
//            {
//                teleportToGenshi(mc);
//            }
//        }
//        tpKeyWasDown = tpKeyDown;

        if (mc.screen == null && mc.player.inventory
                .contains(new ItemStack(SpectrobesItems.prizmod_item)))
        {
            boolean toolMenuKeyIsDown = OPEN_TOOL_MENU_KEYBIND.isDown();
            if (toolMenuKeyIsDown && !toolMenuKeyWasDown)
            {
                while (OPEN_TOOL_MENU_KEYBIND.consumeClick())
                {
                    if (mc.screen == null)
                    {
                            mc.setScreen(new PrizmodScreen(
                                    PrizmodContainer.PRIZMOD.get()
                                            .create(0, mc.player.inventory),
                                    mc.player.inventory, new StringTextComponent("")));

                    }
                }
            }
            toolMenuKeyWasDown = toolMenuKeyIsDown;

            boolean cycleLeftKeyIsDown = CYCLE_TOOL_MENU_LEFT_KEYBIND.isDown();
            if (cycleLeftKeyIsDown && !cycleLeftKeyWasDown)
            {
                while (CYCLE_TOOL_MENU_LEFT_KEYBIND.consumeClick())
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
            }
            cycleLeftKeyIsDown = cycleLeftKeyWasDown;


            boolean cycleRightKeyIsDown = CYCLE_TOOL_MENU_RIGHT_KEYBIND.isDown();
            if (cycleRightKeyIsDown && !cycleRightKeyWasDown)
            {
                while (CYCLE_TOOL_MENU_RIGHT_KEYBIND.consumeClick())
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
            cycleRightKeyIsDown = cycleRightKeyWasDown;
        }
        else
        {
            toolMenuKeyWasDown = true;
        }

    }
//
//    private static void teleportToGenshi(Minecraft mc) {
//        World worldIn = mc.player.level;
//        if(worldIn.isClientSide()) {
//            SpectrobesNetwork.sendToServer(new SChangeDimensionPacket());
//        }
//    }

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
}
