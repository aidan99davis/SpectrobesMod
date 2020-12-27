package com.spectrobes.spectrobesmod.client.keybindings;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SDespawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSpawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
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

    private static boolean toolMenuKeyWasDown = false;
    private static boolean cycleLeftKeyWasDown = false;
    private static boolean cycleRightKeyWasDown = false;

    public static void initKeybinds()
    {
        ClientRegistry.registerKeyBinding(OPEN_TOOL_MENU_KEYBIND =
                new KeyBinding("key.prizmod.open", GLFW.GLFW_KEY_R, "key.prizmod.category"));

        ClientRegistry.registerKeyBinding(CYCLE_TOOL_MENU_LEFT_KEYBIND =
                new KeyBinding("key.prizmod.cycle.left",  InputMappings.INPUT_INVALID.getKeyCode(), "key.prizmod.category"));

        ClientRegistry.registerKeyBinding(CYCLE_TOOL_MENU_RIGHT_KEYBIND =
                new KeyBinding("key.prizmod.cycle.right",  InputMappings.INPUT_INVALID.getKeyCode(), "key.prizmod.category"));

    }

    @SubscribeEvent
    public static void handleKeys(TickEvent.ClientTickEvent ev)
    {
        Minecraft mc = Minecraft.getInstance();

        if (mc.currentScreen == null && mc.player.inventory
                .hasItemStack(new ItemStack(SpectrobesItems.prizmod_item)))
        {
            boolean toolMenuKeyIsDown = OPEN_TOOL_MENU_KEYBIND.isKeyDown();
            if (toolMenuKeyIsDown && !toolMenuKeyWasDown)
            {
                while (OPEN_TOOL_MENU_KEYBIND.isPressed())
                {
                    if (mc.currentScreen == null)
                    {
                            mc.displayGuiScreen(new PrizmodScreen(
                                    PrizmodContainer.PRIZMOD.get()
                                            .create(0, mc.player.inventory),
                                    mc.player.inventory, new StringTextComponent("")));

                    }
                }
            }
            toolMenuKeyWasDown = toolMenuKeyIsDown;

            boolean cycleLeftKeyIsDown = CYCLE_TOOL_MENU_LEFT_KEYBIND.isKeyDown();
            if (cycleLeftKeyIsDown && !cycleLeftKeyWasDown)
            {
                while (CYCLE_TOOL_MENU_LEFT_KEYBIND.isPressed())
                {
                    mc.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                            .ifPresent(sm -> {
                                Spectrobe currentMember = sm.getCurrentTeamMember();

                                UUID oldUUID = currentMember != null? currentMember.SpectrobeUUID : null;
                                sm.changeSelected(-1);
                                List<EntitySpectrobe> spectrobes = mc.player.world
                                        .getEntitiesWithinAABB(EntitySpectrobe.class, mc.player.getBoundingBox().grow(30, 30, 30));
                                SummonPlayerSpectrobe(mc, currentMember, oldUUID, spectrobes);
                                if(sm.getCurrentTeamMember() != null) {
                                    sm.spawnCurrent();
                                    SpectrobesNetwork.sendToServer(new SSpawnSpectrobePacket(sm.getCurrentTeamMember()));
                                }
                            });
                }
            }
            cycleLeftKeyIsDown = cycleLeftKeyWasDown;


            boolean cycleRightKeyIsDown = CYCLE_TOOL_MENU_RIGHT_KEYBIND.isKeyDown();
            if (cycleRightKeyIsDown && !cycleRightKeyWasDown)
            {
                while (CYCLE_TOOL_MENU_RIGHT_KEYBIND.isPressed())
                {
                    mc.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                            .ifPresent(sm -> {
                                Spectrobe currentMember = sm.getCurrentTeamMember();

                                UUID oldUUID = currentMember != null? currentMember.SpectrobeUUID : null;
                                sm.changeSelected(1);
                                List<EntitySpectrobe> spectrobes = mc.player.world
                                        .getEntitiesWithinAABB(EntitySpectrobe.class, mc.player.getBoundingBox().grow(30, 30, 30));

                                SummonPlayerSpectrobe(mc, currentMember, oldUUID, spectrobes);

                                if(sm.getCurrentTeamMember() != null) {
                                    SpectrobesNetwork.sendToServer(new SSpawnSpectrobePacket(sm.getCurrentTeamMember()));
                                }
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

    private static void SummonPlayerSpectrobe(Minecraft mc, Spectrobe currentMember, UUID oldUUID, List<EntitySpectrobe> spectrobes) {
        if(oldUUID != null) {
            for(EntitySpectrobe spectrobe : spectrobes) {
                if(spectrobe.getOwner() != null && spectrobe.getOwnerId().equals(mc.player.getUniqueID())) {
                    spectrobe.despawn();
                }
            }
            if(mc.player.world.isRemote()) {
                SpectrobesNetwork.sendToServer(new SDespawnSpectrobePacket(currentMember));
            }

        }
    }

    public static boolean isKeyDown(KeyBinding keybind)
    {
        if (keybind.isInvalid())
            return false;

        boolean isDown = false;
        switch (keybind.getKey().getType())
        {
            case KEYSYM:
                isDown = InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), keybind.getKey().getKeyCode());
                break;
            case MOUSE:
                isDown = GLFW.glfwGetMouseButton(Minecraft.getInstance().getMainWindow().getHandle(), keybind.getKey().getKeyCode()) == GLFW.GLFW_PRESS;
                break;
        }
        return isDown && keybind.getKeyConflictContext().isActive() && keybind.getKeyModifier().isActive(keybind.getKeyConflictContext());
    }
}
