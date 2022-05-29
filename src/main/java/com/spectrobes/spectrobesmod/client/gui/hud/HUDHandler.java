package com.spectrobes.spectrobesmod.client.gui.hud;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.gui.utils.GuiUtils;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeIconInfo;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.PlayerData;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SpectrobesInfo.MOD_ID)
public class HUDHandler {
    public static final ResourceLocation SPECTROBE_SLOT_TEXTURE = new ResourceLocation("spectrobesmod:textures/gui/spectrobe_slot.png");
    public static final ResourceLocation SPECTROBE_SLOT_CURRENT_TEXTURE = new ResourceLocation("spectrobesmod:textures/gui/spectrobe_slot_current.png");

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onDraw(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            MainWindow resolution = event.getWindow();
            float partialTicks = event.getPartialTicks();
            drawSpectrobeTeamBar(event.getMatrixStack(), resolution, partialTicks);

//            if (!MinecraftForge.EVENT_BUS.post(new RenderPsiHudEvent(PsiHudElementType.PSI_BAR))) {
//                drawSpectrobeTeamBar(event.getMatrixStack(), resolution, partialTicks);
//            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void drawSpectrobeTeamBar(MatrixStack ms, MainWindow res, float partialTicks) {
        Minecraft mc = Minecraft.getInstance();
        if (!mc.player.inventory
                .contains(SpectrobesItems.prizmod_item.getDefaultInstance())) {
            return;
        }

        ms.pushPose();
        RenderSystem.enableAlphaTest();


        boolean right = true;

        int pad = 3;
        int width = 64;
        int height = 128;

        int x = -pad;
        if (right) {
            x = res.getGuiScaledWidth() + pad - width;
        }
        int y = res.getGuiScaledHeight() / 2 - height / 2;
        int finalX = x;

        PlayerSpectrobeMaster sm = mc.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                .orElseThrow(IllegalStateException::new);

        Map<Integer, UUID> currentTeamUuids = sm.getCurrentTeamUuids();
        int currentSelected = sm.getCurrentTeamMemberSlot();
        currentTeamUuids.forEach((integer, uuid) -> {
            ResourceLocation slotBackground;
            boolean leftHandColumn = integer.intValue() % 2 == 0;
            int row = (integer.intValue() / 2);

            if(currentSelected == integer.intValue()) {
                slotBackground = SPECTROBE_SLOT_CURRENT_TEXTURE;
            } else {
                slotBackground = SPECTROBE_SLOT_TEXTURE;
            }

            if(integer.intValue() == 6) {
                GuiUtils.drawTexture(slotBackground, finalX + 16, y + 96, 32, 32, 26);
            } else {
                GuiUtils.drawTexture(slotBackground, finalX + (leftHandColumn? 0 : 32),
                        y + (32 * row), 32, 32, 26);
            }

            //draw spectrobes into the slots.
            if(uuid != null) {
                Spectrobe spectrobe = sm.getSpectrobeByUuid(uuid);
                SpectrobeIconInfo iconInfo = spectrobe.getIcon();
                float scalex = 32 / iconInfo.getWidth();
                float scaley = 32 / iconInfo.getHeight();

                int marginleft = iconInfo.getWidth() < 31
                        ? ((32 - iconInfo.getWidth())/2)
                        : 0;
                int margintop = iconInfo.getHeight() < 31
                        ? ((32 - iconInfo.getHeight())/2)
                        : 0;
                if(integer.intValue() == 6) {
                    GuiUtils.drawTexture(iconInfo.icon(), finalX + marginleft + 16, y + margintop + 96, iconInfo.getWidth() * scalex, iconInfo.getHeight() * scaley, 26);
                    //draw red health bar.
                    GuiUtils.drawColour(245, 66, 66, 100, finalX + 17, y + 126, 30, 2, 27);

                    //draw green for health bar, only fill a % of 30 pixels based on the % of health remaining.
                    float widthScaled = ((float)spectrobe.currentHealth / (float)spectrobe.stats.getHpLevel()) * 30f;
                    GuiUtils.drawColour(55, 179, 41, 100, finalX + 17, y + 126, Math.round(widthScaled), 2, 28);

                } else {
                    GuiUtils.drawTexture(iconInfo.icon(),
                            finalX + marginleft + (leftHandColumn? 0 : 32),
                            y + margintop + (row * 32), iconInfo.getWidth() * scalex, iconInfo.getHeight() * scaley, 26);
                    //draw red health bar.
                    GuiUtils.drawColour(245, 66, 66, 100, finalX + (leftHandColumn? 0 : 32), y + (row * 32) + 30, 30, 2, 27);

                    //draw green for health bar, only fill a % of 30 pixels based on the % of health remaining.
                    float widthScaled = ((float)spectrobe.currentHealth / (float)spectrobe.stats.getHpLevel()) * 30f;
                    GuiUtils.drawColour(55, 179, 41, 100, finalX + (leftHandColumn? 0 : 32), y + (row * 32) + 30, Math.round(widthScaled), 2, 28);

                }
            }
        });
        RenderSystem.disableAlphaTest();
        ms.popPose();
    }
}
