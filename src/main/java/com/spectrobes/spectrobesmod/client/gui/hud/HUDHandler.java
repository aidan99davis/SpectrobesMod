package com.spectrobes.spectrobesmod.client.gui.hud;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesToolsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeIconInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.Map;
import java.util.UUID;

@EventBusSubscriber(value = Dist.CLIENT, modid = SpectrobesInfo.MOD_ID)
public class HUDHandler {
    private static final int RED = 0xFFFF0000;
    private static final int GREEN = 0xFF00FF00;
    private static final int BLUE = 0xFF0000FF;
    private static final int CYAN = 0xFF00FFFF;
    private static final int BLACK = 0xFF000000;

    public static final ResourceLocation SPECTROBE_SLOT_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            SpectrobesInfo.MOD_ID,
            "textures/gui/spectrobe_slot.png"
    );

    public static final ResourceLocation SPECTROBE_SLOT_CURRENT_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            SpectrobesInfo.MOD_ID,
            "textures/gui/spectrobe_slot_selected.png"
    );

    @SubscribeEvent
    public static void onDraw(RenderGuiLayerEvent.Pre event) {
        if (!event.getName().equals(VanillaGuiLayers.EXPERIENCE_BAR)) {
            return;
        }

        GuiGraphics guiGraphics = event.getGuiGraphics();

        drawSpectrobeTeamBar(guiGraphics);

        int finalWidth = drawSpectrobeMasterHealthBar(guiGraphics);

        drawSpectrobeMasterXpBar(guiGraphics, finalWidth);
    }

    private static void drawSpectrobeTeamBar(GuiGraphics guiGraphics) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        if (!minecraft.player.getInventory().contains(SpectrobesToolsRegistry.prizmod_item.get().getDefaultInstance())) {
            return;
        }

        PlayerSpectrobeMaster spectrobeMaster = minecraft.player.getCapability(SpectrobeMaster.INSTANCE);

        if (spectrobeMaster == null) {
            return;
        }

        boolean right = true;

        int pad = 3;
        int width = 64;
        int height = 128;

        int x = -pad;

        if (right) {
            x = guiGraphics.guiWidth() + pad - width;
        }

        int y = guiGraphics.guiHeight() / 2 - height / 2;
        int finalX = x;

        Map<Integer, UUID> currentTeamUuids = spectrobeMaster.getCurrentTeamUuids();
        int currentSelected = spectrobeMaster.getCurrentTeamMemberSlot();

        currentTeamUuids.forEach((slot, uuid) -> {
            int slotIndex = slot;
            boolean leftHandColumn = slotIndex % 2 == 0;
            int row = slotIndex / 2;

            ResourceLocation slotBackground = currentSelected == slotIndex
                    ? SPECTROBE_SLOT_CURRENT_TEXTURE
                    : SPECTROBE_SLOT_TEXTURE;

            int slotX;
            int slotY;

            if (slotIndex == 6) {
                slotX = finalX + 16;
                slotY = y + 96;
            } else {
                slotX = finalX + (leftHandColumn ? 0 : 32);
                slotY = y + (32 * row);
            }

            guiGraphics.blit(slotBackground, slotX, slotY, 0, 0, 32, 32, 32, 32);

            if (uuid == null) {
                return;
            }

            Spectrobe spectrobe = spectrobeMaster.getSpectrobeByUuid(uuid);

            if (spectrobe == null) {
                return;
            }

            SpectrobeIconInfo iconInfo = spectrobe.getIcon();

            float scaleX = 32.0F / iconInfo.getWidth();
            float scaleY = 32.0F / iconInfo.getHeight();

            int iconWidth = Math.round(iconInfo.getWidth() * scaleX);
            int iconHeight = Math.round(iconInfo.getHeight() * scaleY);

            int marginLeft = iconInfo.getWidth() < 31
                    ? (32 - iconInfo.getWidth()) / 2
                    : 0;

            int marginTop = iconInfo.getHeight() < 31
                    ? (32 - iconInfo.getHeight()) / 2
                    : 0;

            guiGraphics.blit(
                    iconInfo.icon(),
                    slotX + marginLeft,
                    slotY + marginTop,
                    0,
                    0,
                    iconWidth,
                    iconHeight,
                    iconWidth,
                    iconHeight
            );

            int healthBarX = slotIndex == 6 ? finalX + 17 : slotX;
            int healthBarY = slotIndex == 6 ? y + 126 : slotY + 30;
            int healthBarWidth = 30;

            guiGraphics.fill(
                    healthBarX,
                    healthBarY,
                    healthBarX + healthBarWidth,
                    healthBarY + 2,
                    RED
            );

            float healthPercent = spectrobe.stats.getHpLevel() <= 0
                    ? 0.0F
                    : (float) spectrobe.currentHealth / (float) spectrobe.stats.getHpLevel();

            int scaledHealthWidth = Math.round(Math.max(0.0F, Math.min(1.0F, healthPercent)) * healthBarWidth);

            guiGraphics.fill(
                    healthBarX,
                    healthBarY,
                    healthBarX + scaledHealthWidth,
                    healthBarY + 2,
                    GREEN
            );
        });
    }

    private static int drawSpectrobeMasterHealthBar(GuiGraphics guiGraphics) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return 0;
        }

        PlayerSpectrobeMaster spectrobeMaster = minecraft.player.getCapability(SpectrobeMaster.INSTANCE);

        if (spectrobeMaster == null) {
            return 0;
        }

        int pad = 5;
        int width = 10;
        int height = 32;
        int bottomPadding = 40;

        int x = pad;
        int y = guiGraphics.guiHeight() - (height / 2) - bottomPadding;

        String healthText = spectrobeMaster.getCurrentHealth() + "/" + spectrobeMaster.getMaxHealth();

        int finalX = x + (minecraft.font.width(healthText) / 2) - (width / 2);

        guiGraphics.fill(
                finalX,
                y,
                finalX + width,
                y + height,
                RED
        );

        float healthPercent = spectrobeMaster.getMaxHealth() <= 0
                ? 0.0F
                : (float) spectrobeMaster.getCurrentHealth() / (float) spectrobeMaster.getMaxHealth();

        int heightScaled = Math.round(Math.max(0.0F, Math.min(1.0F, healthPercent)) * height);

        guiGraphics.fill(
                finalX,
                y + (height - heightScaled),
                finalX + width,
                y + height,
                GREEN
        );

        guiGraphics.drawString(
                minecraft.font,
                "HP",
                finalX,
                y - 10,
                BLACK
        );

        guiGraphics.drawString(
                minecraft.font,
                healthText,
                x,
                y + height + 10,
                BLACK
        );

        return finalX + minecraft.font.width(healthText);
    }

    private static void drawSpectrobeMasterXpBar(GuiGraphics guiGraphics, int basePadding) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        PlayerSpectrobeMaster spectrobeMaster = minecraft.player.getCapability(SpectrobeMaster.INSTANCE);

        if (spectrobeMaster == null) {
            return;
        }

        int pad = 10 + basePadding;
        int width = 10;
        int height = 32;
        int bottomPadding = 40;

        int x = pad;
        int y = guiGraphics.guiHeight() - (height / 2) - bottomPadding;

        String xpText = spectrobeMaster.getCurrentXp() + "/" + spectrobeMaster.getXp_required();
        String levelText = "Lvl: " + spectrobeMaster.getLevel();

        int finalX = x + (minecraft.font.width(xpText) / 2) - (width / 2);

        guiGraphics.fill(
                finalX,
                y,
                finalX + width,
                y + height,
                BLUE
        );

        float xpPercent = spectrobeMaster.getXp_required() <= 0
                ? 0.0F
                : (float) spectrobeMaster.getCurrentXp() / (float) spectrobeMaster.getXp_required();

        int heightScaled = Math.round(Math.max(0.0F, Math.min(1.0F, xpPercent)) * height);

        guiGraphics.fill(
                finalX,
                y + (height - heightScaled),
                finalX + width,
                y + height,
                CYAN
        );

        guiGraphics.drawString(
                minecraft.font,
                levelText,
                finalX - (minecraft.font.width(levelText) / 2) + (width / 2),
                y - 10,
                BLACK
        );

        guiGraphics.drawString(
                minecraft.font,
                xpText,
                x,
                y + height + 10,
                BLACK
        );
    }
}