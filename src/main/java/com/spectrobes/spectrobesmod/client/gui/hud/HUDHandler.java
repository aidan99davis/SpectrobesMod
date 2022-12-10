package com.spectrobes.spectrobesmod.client.gui.hud;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesToolsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeIconInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.core.util.Color;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static net.minecraft.client.gui.GuiComponent.fill;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = SpectrobesInfo.MOD_ID)
public class HUDHandler {
    public static final ResourceLocation SPECTROBE_SLOT_TEXTURE = new ResourceLocation("spectrobesmod:textures/gui/spectrobe_slot.png");
    public static final ResourceLocation SPECTROBE_SLOT_CURRENT_TEXTURE = new ResourceLocation("spectrobesmod:textures/gui/spectrobe_slot_selected.png");

    @SubscribeEvent
    public static void onDraw(RenderGuiOverlayEvent.Pre event) {
        if (event.getOverlay().id().equals(VanillaGuiOverlay.EXPERIENCE_BAR.id())) {
            Window resolution = event.getWindow();
            float partialTicks = event.getPartialTick();
            drawSpectrobeTeamBar(event.getPoseStack(), resolution, partialTicks);

            int finalWidth = drawSpectrobeMasterHealthBar(event.getPoseStack(), resolution);

            drawSpectrobeMasterXpBar(event.getPoseStack(), resolution, finalWidth);
        }
    }
//
//    @SubscribeEvent
//    public static void onRenderEntity(RenderLivingEvent.Pre event) {
//        SpectrobesInfo.LOGGER.info("RENDER ENTITY CALL");
//        SpectrobesInfo.LOGGER.info("RENDER ENTITY CALL: " + event.getEntity().getClass());
//        if(event.getEntity() instanceof EntityKrawl
//                || event.getEntity() instanceof EntitySpectrobe) {
//            SpectrobesInfo.LOGGER.info("RENDER ENTITY CALL - IS SPECTROBE OR KRAWL");
//            VertexConsumer consumer = event.getMultiBufferSource()
//                    .getBuffer(RenderType.outline(
//                            event.getRenderer().getTextureLocation(
//                                    event.getEntity())));
//
//            event.getRenderer().getModel().renderToBuffer(event.getPoseStack(), consumer, 15728880, LivingEntityRenderer.getOverlayCoords(event.getEntity(), 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
//        }
//    }

    private static void drawSpectrobeTeamBar(PoseStack ms, Window res, float partialTicks) {
        Minecraft mc = Minecraft.getInstance();
        if (!mc.player.getInventory()
                .contains(SpectrobesToolsRegistry.prizmod_item.get().getDefaultInstance())) {
            return;
        }

        mc.player.getCapability(SpectrobeMaster.INSTANCE)
                .ifPresent(sm -> {
                    ms.pushPose();
                    RenderSystem.enableBlend();

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

                        RenderSystem.setShaderTexture(0, slotBackground);
                        if(integer.intValue() == 6) {

                            GuiComponent.blit(ms,
                                    finalX + 16,
                                    y + 96,
                                    0,
                                    0,
                                    0,
                                    32,
                                    32,
                                    32,
                                    32);
                        } else {
                            GuiComponent.blit(ms,
                                    finalX + (leftHandColumn? 0 : 32),
                                    y + (32 * row),
                                    0,
                                    0,
                                    0,
                                    32,
                                    32,
                                    32,
                                    32);
                        }

                        //draw spectrobes into the slots.
                        if(uuid != null) {
                            Spectrobe spectrobe = sm.getSpectrobeByUuid(uuid);
                            if(spectrobe != null) {
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
                                    RenderSystem.setShaderTexture(0, iconInfo.icon());

                                    GuiComponent.blit(ms,
                                            finalX + marginleft + 16,
                                            y + margintop + 96,
                                            0,
                                            0,
                                            Math.round(iconInfo.getWidth() * scalex),
                                            Math.round(iconInfo.getHeight() * scaley),
                                            Math.round(iconInfo.getWidth() * scalex),
                                            Math.round(iconInfo.getHeight() * scaley));
                                    //draw red health bar.
                                    fill(ms,finalX + 17,y + 126,finalX + 47,y + 128, Color.RED.hashCode());

                                    //draw green for health bar, only fill a % of 30 pixels based on the % of health remaining.
                                    float widthScaled = ((float)spectrobe.currentHealth / (float)spectrobe.stats.getHpLevel()) * 30f;
                                    fill(ms,finalX + 17,y + 126,finalX + 17 + Math.round(widthScaled),y + 128, Color.GREEN.hashCode());

                                } else {
                                    RenderSystem.setShaderTexture(0, iconInfo.icon());

                                    GuiComponent.blit(ms,
                                            finalX + marginleft + (leftHandColumn? 0 : 32),
                                            y + margintop + (row * 32),
                                            0,
                                            0,
                                            Math.round(iconInfo.getWidth() * scalex),
                                            Math.round(iconInfo.getHeight() * scaley),
                                            Math.round(iconInfo.getWidth() * scalex),
                                            Math.round(iconInfo.getHeight() * scaley));
                                    //draw red health bar.
                                    fill(ms,finalX + (leftHandColumn? 0 : 32),y + (row * 32) + 30,finalX + (leftHandColumn? 0 : 32) + 30,y + (row * 32) + 32, Color.RED.hashCode());

                                    //draw green for health bar, only fill a % of 30 pixels based on the % of health remaining.
                                    float widthScaled = ((float)spectrobe.currentHealth / (float)spectrobe.stats.getHpLevel()) * 30f;
                                    fill(ms,finalX + (leftHandColumn? 0 : 32),y + (row * 32) + 30,finalX + (leftHandColumn? 0 : 32) + Math.round(widthScaled),y + (row * 32) + 32, Color.GREEN.hashCode());

                                }
                            }
                        }
                    });
                    RenderSystem.disableBlend();
                    ms.popPose();
                });
    }

    private static int drawSpectrobeMasterHealthBar(PoseStack ms, Window res) {
        Minecraft mc = Minecraft.getInstance();
        AtomicInteger finalWidth = new AtomicInteger(0);

        mc.player.getCapability(SpectrobeMaster.INSTANCE)
            .ifPresent(sm -> {
                ms.pushPose();
                RenderSystem.enableBlend();

                int pad = 5;
                int width = 10;
                int height = 32;
                int bottomPadding = 40;

                int x = pad;
                int y = (res.getGuiScaledHeight() - (height / 2)) - bottomPadding;
                String healthText = sm.getCurrentHealth() + "/" + sm.getMaxHealth();
                int finalX = x + (Minecraft.getInstance().font.width(healthText) / 2) - (width / 2);

                //draw red health bar.
                fill(ms, finalX, y, finalX+width, y+height, Color.RED.hashCode());
//                GuiUtils.drawColour(107, 0, 0, 100, finalX, y, width, height, 27);

                //draw green for health bar, only fill a % of 30 pixels based on the % of health remaining.
                float heightScaled = ((float)sm.getCurrentHealth() / (float)sm.getMaxHealth()) * height;
                fill(ms, finalX, y + (height-Math.round(heightScaled)), finalX+width, y+height, Color.GREEN.hashCode());

                mc.font.draw(ms, "HP", finalX, y - 10, Color.BLACK.hashCode());
                mc.font.draw(ms, healthText, x, y + height + 10, Color.BLACK.hashCode());

                RenderSystem.disableBlend();
                ms.popPose();
                int completeWidth = finalX + Minecraft.getInstance().font.width(healthText);
                finalWidth.set(completeWidth);
            });

        return finalWidth.get();
    }

    private static void drawSpectrobeMasterXpBar(PoseStack ms, Window res, int basePadding) {
        Minecraft mc = Minecraft.getInstance();

        mc.player.getCapability(SpectrobeMaster.INSTANCE)
            .ifPresent(sm -> {
                ms.pushPose();
                RenderSystem.enableBlend();

                int pad = 10 + basePadding;
                int width = 10;
                int height = 32;
                int bottomPadding = 40;

                int x = pad;
                int y = (res.getGuiScaledHeight() - (height / 2)) - bottomPadding;
                String xpText = sm.getCurrentXp() + "/" + sm.getXp_required();
                String lvlText = "Lvl: " + sm.getLevel();
                int finalX = x + (Minecraft.getInstance().font.width(xpText) / 2) - (width / 2);

                //draw red health bar.
                fill(ms, finalX, y, finalX+width, y+height, Color.BLUE.hashCode());

                //draw green for health bar, only fill a % of 30 pixels based on the % of health remaining.
                float heightScaled = ((float)sm.getCurrentXp() / (float)sm.getXp_required()) * height;
                fill(ms, finalX, y + (height-Math.round(heightScaled)), finalX+width, y+height, Color.CYAN.hashCode());
                mc.font.draw(ms, lvlText, finalX - (Minecraft.getInstance().font.width(lvlText) / 2) + (width/2), y - 10, Color.BLACK.hashCode());
                mc.font.draw(ms, xpText, x, y + height + 10, Color.BLACK.hashCode());
                RenderSystem.disableBlend();
                ms.popPose();
            });
    }
}
