package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeIconInfo;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SpectrobePiece extends AbstractWidget {

    private static final ResourceLocation DELETE_BACKGROUND =
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/gui/spectrobe_slot_delete.png");

    private static final int SLOT_SIZE = 32;
    private static final int HEALTH_BAR_LEFT = 2;
    private static final int HEALTH_BAR_RIGHT = 30;
    private static final int HEALTH_BAR_TOP = 28;
    private static final int HEALTH_BAR_BOTTOM = 30;
    private static final int HEALTH_BAR_WIDTH = HEALTH_BAR_RIGHT - HEALTH_BAR_LEFT;

    private static final int HEALTH_BAR_RED = 0xFFFF0000;
    private static final int HEALTH_BAR_GREEN = 0xFF00FF00;

    public Spectrobe spectrobe;

    private final int x;
    private final int y;

    public int posX;
    public int posY;

    public boolean selected;
    public boolean current;

    public SpectrobePiece(Spectrobe spectrobe, int x, int y) {
        super(toScreenX(x), toScreenY(y), SLOT_SIZE, SLOT_SIZE, Component.empty());

        this.spectrobe = spectrobe;
        this.x = x;
        this.y = y;
        this.posX = toScreenX(x);
        this.posY = toScreenY(y);
        this.selected = false;
        this.current = false;
    }

    private static int toScreenX(int x) {
        return (x + 3) * SLOT_SIZE;
    }

    private static int toScreenY(int y) {
        return (y + 2) * SLOT_SIZE;
    }

    private void syncWidgetBounds() {
        setX(posX);
        setY(posY);
        setWidth(SLOT_SIZE);
        setHeight(SLOT_SIZE);
    }

    public void toggleCurrent() {
        current = !current;
    }

    public String getUnlocalizedName() {
        return SpectrobesInfo.MOD_ID + ".spectrobe." + spectrobe.name;
    }

    public String getSortingName() {
        return getUnlocalizedName();
    }

    public void draw(GuiGraphics graphics, boolean withAdditional) {
        syncWidgetBounds();

        drawBackground(graphics);

        if (withAdditional) {
            drawAdditional(graphics);
        }
    }

    /**
     * Draws this piece's background.
     */
    public void drawBackground(GuiGraphics graphics) {
        syncWidgetBounds();

        ResourceLocation background;

        if (Screen.hasAltDown()) {
            background = DELETE_BACKGROUND;
        } else if (!current) {
            background = selected ? PrizmodScreen.SPECTROBE_SLOT_SELECTED_TEXTURE : PrizmodScreen.SPECTROBE_SLOT_TEXTURE;
        } else {
            background = PrizmodScreen.SPECTROBE_SLOT_SELECTED_TEXTURE;
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        graphics.blit(
                background,
                posX,
                posY,
                0.0F,
                0.0F,
                SLOT_SIZE,
                SLOT_SIZE,
                SLOT_SIZE,
                SLOT_SIZE
        );
    }

    /**
     * Draws any additional stuff for this piece. Used for the spectrobe icon.
     */
    public void drawAdditional(GuiGraphics graphics) {
        if (spectrobe == null) {
            return;
        }

        SpectrobeIconInfo iconInfo = spectrobe.getIcon();

        int drawWidth = getScaledIconWidth(iconInfo);
        int drawHeight = getScaledIconHeight(iconInfo);

        int marginLeft = iconInfo.getWidth() < 31
                ? (SLOT_SIZE - iconInfo.getWidth()) / 2
                : 0;

        int marginTop = iconInfo.getHeight() < 31
                ? (SLOT_SIZE - iconInfo.getHeight()) / 2
                : 0;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        graphics.blit(
                iconInfo.icon(),
                posX + marginLeft,
                posY + marginTop,
                0.0F,
                0.0F,
                drawWidth,
                drawHeight,
                drawWidth,
                drawHeight
        );

        RenderSystem.disableBlend();

        drawHealthBar(graphics);
    }

    private void drawHealthBar(GuiGraphics graphics) {
        graphics.fill(
                posX + HEALTH_BAR_LEFT,
                posY + HEALTH_BAR_TOP,
                posX + HEALTH_BAR_RIGHT,
                posY + HEALTH_BAR_BOTTOM,
                HEALTH_BAR_RED
        );

        int maxHealth = Math.max(1, spectrobe.stats.getHpLevel());
        float healthPercent = Mth.clamp((float) spectrobe.currentHealth / (float) maxHealth, 0.0F, 1.0F);
        int greenWidth = Math.round(healthPercent * HEALTH_BAR_WIDTH);

        graphics.fill(
                posX + HEALTH_BAR_LEFT,
                posY + HEALTH_BAR_TOP,
                posX + HEALTH_BAR_LEFT + greenWidth,
                posY + HEALTH_BAR_BOTTOM,
                HEALTH_BAR_GREEN
        );
    }

    public void drawAdditionalAtCursor(GuiGraphics graphics, int mouseX, int mouseY) {
        if (spectrobe == null) {
            return;
        }

        SpectrobeIconInfo iconInfo = spectrobe.getIcon();

        int drawWidth = getScaledIconWidth(iconInfo);
        int drawHeight = getScaledIconHeight(iconInfo);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        graphics.blit(
                iconInfo.icon(),
                mouseX - (drawWidth / 2),
                mouseY - (drawHeight / 2),
                0.0F,
                0.0F,
                drawWidth,
                drawHeight,
                drawWidth,
                drawHeight
        );

        RenderSystem.disableBlend();
    }

    private int getScaledIconWidth(SpectrobeIconInfo iconInfo) {
        float scaleX = iconInfo.getWidth() <= SLOT_SIZE ? 1.0F : (float) SLOT_SIZE / (float) iconInfo.getWidth();
        return Math.round(iconInfo.getWidth() * scaleX);
    }

    private int getScaledIconHeight(SpectrobeIconInfo iconInfo) {
        float scaleY = iconInfo.getHeight() <= SLOT_SIZE ? 1.0F : (float) SLOT_SIZE / (float) iconInfo.getHeight();
        return Math.round(iconInfo.getHeight() * scaleY);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        draw(graphics, true);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        defaultButtonNarrationText(narrationElementOutput);
    }
}