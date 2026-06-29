package com.spectrobes.spectrobesmod.client.keybindings;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.networking.server.SSpectrobeAttackPacket;
import com.spectrobes.spectrobesmod.common.networking.server.SDespawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.networking.server.SSpawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesToolsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.UUID;

public class SpectrobesKeybindings {

    public static final KeyMapping OPEN_TOOL_MENU_KEYBIND =
            new KeyMapping(
                    "key.prizmod.open",
                    GLFW.GLFW_KEY_R,
                    "key.prizmod.category"
            );

    public static final KeyMapping CYCLE_TOOL_MENU_LEFT_KEYBIND =
            new KeyMapping(
                    "key.prizmod.cycle.left",
                    GLFW.GLFW_KEY_LEFT_BRACKET,
                    "key.prizmod.category"
            );

    public static final KeyMapping CYCLE_TOOL_MENU_RIGHT_KEYBIND =
            new KeyMapping(
                    "key.prizmod.cycle.right",
                    GLFW.GLFW_KEY_RIGHT_BRACKET,
                    "key.prizmod.category"
            );

    public static final KeyMapping ATTACK_KEYBIND =
            new KeyMapping(
                    "key.prizmod.attack",
                    GLFW.GLFW_KEY_F,
                    "key.prizmod.category"
            );

    private static LivingEntity Last_Attack_Target = null;

    private static void SummonPlayerSpectrobe(Minecraft mc, Spectrobe currentMember, UUID oldUUID, List<EntitySpectrobe> spectrobes) {
        if (mc.player == null) {
            return;
        }

        if (oldUUID != null) {
            for (EntitySpectrobe spectrobe : spectrobes) {
                if (spectrobe.getOwner() != null
                        && spectrobe.getOwnerUUID() != null
                        && spectrobe.getOwnerUUID().equals(mc.player.getUUID())) {
                    spectrobe.despawn();
                }
            }

            if (mc.player.level().isClientSide()) {
                SpectrobesNetwork.sendToServer(new SDespawnSpectrobePacket(mc.player.blockPosition()));
            }
        }
    }

    @EventBusSubscriber(
            value = Dist.CLIENT,
            modid = SpectrobesInfo.MOD_ID
    )
    public static class KeybindingForgeEvents {

        @SubscribeEvent
        public static void handleKeys(InputEvent.Key event) {
            Minecraft mc = Minecraft.getInstance();

            if (mc.player == null || mc.level == null || mc.screen != null) {
                return;
            }

            if (!mc.player.getInventory().contains(new ItemStack(SpectrobesToolsRegistry.prizmod_item.get()))) {
                return;
            }

            if (ATTACK_KEYBIND.consumeClick()) {
                handleAttackKey(mc);
            }

            if (OPEN_TOOL_MENU_KEYBIND.consumeClick()) {
                handleOpenToolMenuKey(mc);
            }

            if (CYCLE_TOOL_MENU_LEFT_KEYBIND.consumeClick()) {
                handleCycleToolMenuKey(mc, -1);
            }

            if (CYCLE_TOOL_MENU_RIGHT_KEYBIND.consumeClick()) {
                handleCycleToolMenuKey(mc, 1);
            }
        }

        private static void handleAttackKey(Minecraft mc) {
            if (mc.player == null) {
                return;
            }

            Vec3 eyePosition = mc.player.getEyePosition(1.0F);
            Vec3 viewVector = mc.player.getViewVector(1.0F);

            double range = 15.0D;
            double rangeSquared = range * range;

            Vec3 endPosition = eyePosition.add(
                    viewVector.x * range,
                    viewVector.y * range,
                    viewVector.z * range
            );

            AABB hitBox = mc.player.getBoundingBox()
                    .expandTowards(viewVector.scale(range))
                    .inflate(1.0D, 1.0D, 1.0D);

            EntityHitResult result = ProjectileUtil.getEntityHitResult(
                    mc.player,
                    eyePosition,
                    endPosition,
                    hitBox,
                    entity -> entity instanceof LivingEntity,
                    rangeSquared
            );

            if (result == null || result.getEntity() == null) {
                return;
            }

            PlayerSpectrobeMaster spectrobeMaster = mc.player.getCapability(SpectrobeMaster.INSTANCE);

            if (spectrobeMaster == null) {
                return;
            }

            Spectrobe currentTeamMember = spectrobeMaster.getCurrentTeamMember();

            if (currentTeamMember == null
                    || !currentTeamMember.active
                    || currentTeamMember.properties.getStage() == SpectrobeProperties.Stage.CHILD) {
                return;
            }

            if (result.getEntity() instanceof EntityKrawl krawl && !(result.getEntity() instanceof EntityVortex)) {
                attackKrawl(mc, currentTeamMember, krawl);
                return;
            }

            if (result.getEntity() instanceof EntitySpectrobe targetSpectrobe) {
                attackWildSpectrobe(mc, currentTeamMember, targetSpectrobe);
            }
        }

        private static void attackKrawl(Minecraft mc, Spectrobe currentTeamMember, EntityKrawl krawl) {
            if (mc.player == null) {
                return;
            }

            List<EntitySpectrobe> spectrobes = mc.player.level().getEntitiesOfClass(
                    EntitySpectrobe.class,
                    mc.player.getBoundingBox().inflate(30.0D, 30.0D, 30.0D)
            );

            for (EntitySpectrobe spectrobe : spectrobes) {
                if (!spectrobe.getSpectrobeData().SpectrobeUUID.equals(currentTeamMember.SpectrobeUUID)) {
                    continue;
                }

                SpectrobesNetwork.sendToServer(new SSpectrobeAttackPacket(spectrobe.getId(), krawl.getId()));

                clearPreviousTargetGlow(spectrobe);

                spectrobe.setTarget(krawl);
                Last_Attack_Target = krawl;

                spectrobe.getNavigation().moveTo(krawl, 1.0D);
                krawl.setGlowingTag(true);
            }
        }

        private static void attackWildSpectrobe(Minecraft mc, Spectrobe currentTeamMember, EntitySpectrobe targetSpectrobe) {
            if (mc.player == null) {
                return;
            }

            if (targetSpectrobe.getOwner() != null
                    || targetSpectrobe.getStage() == SpectrobeProperties.Stage.CHILD) {
                return;
            }

            List<EntitySpectrobe> spectrobes = mc.player.level().getEntitiesOfClass(
                    EntitySpectrobe.class,
                    mc.player.getBoundingBox().inflate(16.0D, 16.0D, 16.0D)
            );

            for (EntitySpectrobe spectrobe : spectrobes) {
                if (!spectrobe.getSpectrobeData().SpectrobeUUID.equals(currentTeamMember.SpectrobeUUID)) {
                    continue;
                }

                SpectrobesNetwork.sendToServer(new SSpectrobeAttackPacket(spectrobe.getId(), targetSpectrobe.getId()));

                clearPreviousTargetGlow(spectrobe);

                Last_Attack_Target = targetSpectrobe;

                spectrobe.setTarget(targetSpectrobe);
                spectrobe.getNavigation().moveTo(targetSpectrobe, 1.0D);
                targetSpectrobe.setGlowingTag(true);
            }
        }

        private static void clearPreviousTargetGlow(EntitySpectrobe spectrobe) {
            if (Last_Attack_Target == null || !Last_Attack_Target.isAlive()) {
                return;
            }

            if (spectrobe.getTarget() instanceof EntityKrawl krawl) {
                krawl.setGlowingTag(false);
            }

            if (spectrobe.getTarget() instanceof EntitySpectrobe targetSpectrobe) {
                targetSpectrobe.setGlowingTag(false);
            }
        }

        private static void handleOpenToolMenuKey(Minecraft mc) {
            if (mc.player == null || mc.screen != null) {
                return;
            }

            mc.setScreen(new PrizmodScreen(
                    PrizmodContainer.PRIZMOD.get().create(0, mc.player.getInventory()),
                    mc.player.getInventory(),
                    Component.empty()
            ));
        }

        private static void handleCycleToolMenuKey(Minecraft mc, int direction) {
            if (mc.player == null) {
                return;
            }

            PlayerSpectrobeMaster spectrobeMaster = mc.player.getCapability(SpectrobeMaster.INSTANCE);

            if (spectrobeMaster == null) {
                return;
            }

            Spectrobe currentMember = spectrobeMaster.getCurrentTeamMember();
            UUID oldUUID = currentMember != null ? currentMember.SpectrobeUUID : null;

            spectrobeMaster.changeSelected(direction);

            List<EntitySpectrobe> spectrobes = mc.player.level().getEntitiesOfClass(
                    EntitySpectrobe.class,
                    mc.player.getBoundingBox().inflate(30.0D, 30.0D, 30.0D)
            );

            SummonPlayerSpectrobe(mc, currentMember, oldUUID, spectrobes);

            if (spectrobeMaster.getCurrentTeamMember() != null
                    && spectrobeMaster.getCurrentTeamMember().currentHealth > 0) {
                spectrobeMaster.spawnCurrent();
                SpectrobesNetwork.sendToServer(new SSpawnSpectrobePacket(spectrobeMaster.getCurrentTeamMember()));
            }
        }
    }

    @EventBusSubscriber(
            value = Dist.CLIENT,
            modid = SpectrobesInfo.MOD_ID
    )
    public static class KeybindingModBusEvents {

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(ATTACK_KEYBIND);
            event.register(OPEN_TOOL_MENU_KEYBIND);
            event.register(CYCLE_TOOL_MENU_LEFT_KEYBIND);
            event.register(CYCLE_TOOL_MENU_RIGHT_KEYBIND);
        }
    }
}