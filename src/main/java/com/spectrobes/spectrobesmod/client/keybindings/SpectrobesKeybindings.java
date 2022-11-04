package com.spectrobes.spectrobesmod.client.keybindings;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.*;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SpectrobesInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpectrobesKeybindings {
    public static KeyBinding OPEN_TOOL_MENU_KEYBIND;
    public static KeyBinding CYCLE_TOOL_MENU_LEFT_KEYBIND;
    public static KeyBinding CYCLE_TOOL_MENU_RIGHT_KEYBIND;
    public static KeyBinding ATTACK_KEYBIND;
//    public static KeyBinding TP_TO_GENSHI_KEYBIND;

    private static boolean toolMenuKeyWasDown = false;
    private static boolean cycleLeftKeyWasDown = false;
    private static boolean cycleRightKeyWasDown = false;
    private static boolean attackKeyWasDown = false;
//    private static boolean tpKeyWasDown = false;

    public static void initKeybinds()
    {
        ClientRegistry.registerKeyBinding(OPEN_TOOL_MENU_KEYBIND =
                new KeyBinding("key.prizmod.open", GLFW.GLFW_KEY_R, "key.prizmod.category"));

        ClientRegistry.registerKeyBinding(CYCLE_TOOL_MENU_LEFT_KEYBIND =
                new KeyBinding("key.prizmod.cycle.left",  InputMappings.UNKNOWN.getValue(), "key.prizmod.category"));

        ClientRegistry.registerKeyBinding(CYCLE_TOOL_MENU_RIGHT_KEYBIND =
                new KeyBinding("key.prizmod.cycle.right",  InputMappings.UNKNOWN.getValue(), "key.prizmod.category"));

        ClientRegistry.registerKeyBinding(ATTACK_KEYBIND =
                new KeyBinding("key.prizmod.attack",  GLFW.GLFW_KEY_F, "key.prizmod.category"));

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
            boolean attackKeyIsDown = ATTACK_KEYBIND.isDown();
            if (attackKeyIsDown && !attackKeyWasDown)
            {
                while (ATTACK_KEYBIND.consumeClick())
                {
                    Vector3d vector3d = mc.player.getEyePosition(1.0F);
                    Vector3d vector3d1 = mc.player.getViewVector(1.0F);
                    double d0 = 15;
                    double d1 = d0 * d0;
                    Vector3d vector3d2 = vector3d.add(vector3d1.x * d0, vector3d1.y * d0, vector3d1.z * d0);
                    AxisAlignedBB axisalignedbb = mc.player.getBoundingBox().expandTowards(vector3d1.scale(d0)).inflate(1.0D, 1.0D, 1.0D);

                    SpectrobesInfo.LOGGER.debug("STARTED RAYTRACTING");
                    EntityRayTraceResult result = ProjectileHelper.getEntityHitResult(mc.player,
                            vector3d, vector3d2, axisalignedbb,
                            entity -> entity instanceof EntityKrawl,
                            d1);
                    SpectrobesInfo.LOGGER.debug("FINISHED RAYTRACTING");

                    if (result != null) {
                        SpectrobesInfo.LOGGER.debug("RESULT NOT NULL");
                        if (result.getEntity() != null) {
                            SpectrobesInfo.LOGGER.debug("RESULT.getEntity() NOT NULL");
                            if (result.getEntity() instanceof EntityKrawl) {
                                EntityKrawl krawl = (EntityKrawl) result.getEntity();
                                SpectrobesInfo.LOGGER.debug("FOUND A KRAWL");
                                mc.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                                    if (sm.getCurrentTeamMember() != null && sm.getCurrentTeamMember().active && sm.getCurrentTeamMember().properties.getStage() != SpectrobeProperties.Stage.CHILD) {
                                        List<EntitySpectrobe> spectrobes = mc.player.level
                                                .getEntitiesOfClass(EntitySpectrobe.class, mc.player.getBoundingBox().inflate(30, 30, 30));
                                        SpectrobesInfo.LOGGER.debug("PLAYER SPECTROBE ACTIVE AND NOT A CHILD");
                                        for (EntitySpectrobe spectrobe :
                                                spectrobes) {
                                            SpectrobesInfo.LOGGER.debug("CHECKING SPECTROBE: " + spectrobe.getSpectrobeData().name);
                                            if (spectrobe.getSpectrobeData().SpectrobeUUID.equals(sm.getCurrentTeamMember().SpectrobeUUID)) {
                                                SpectrobesInfo.LOGGER.debug("SENDING ATTACK PACKET");
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
            }
            attackKeyWasDown = attackKeyIsDown;

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
