package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeIconInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.function.Consumer;

public class SpectrobePiece extends AbstractWidget {

    public static final int SLOT_SIZE = 32;

    private static final ResourceLocation DELETE_BACKGROUND = ResourceLocation.fromNamespaceAndPath(
            SpectrobesInfo.MOD_ID,
            "textures/gui/spectrobe_slot_delete.png"
    );

    private static final int HEALTH_BAR_LEFT = 2;
    private static final int HEALTH_BAR_RIGHT = 30;
    private static final int HEALTH_BAR_TOP = 28;
    private static final int HEALTH_BAR_BOTTOM = 30;
    private static final int HEALTH_BAR_WIDTH = HEALTH_BAR_RIGHT - HEALTH_BAR_LEFT;

    private static final int HEALTH_BAR_RED = 0xFFFF0000;
    private static final int HEALTH_BAR_GREEN = 0xFF00FF00;

    public Spectrobe spectrobe;

    private final int gridX;
    private final int gridY;

    public int posX;
    public int posY;

    private int slotIndex = -1;
    private boolean selected;
    private boolean current;
    private Consumer<SpectrobePiece> pressHandler;

    public SpectrobePiece(Spectrobe spectrobe, int gridX, int gridY) {
        this(spectrobe, gridX, gridY, null);
    }

    public SpectrobePiece(Spectrobe spectrobe, int gridX, int gridY, Consumer<SpectrobePiece> pressHandler) {
        super(toScreenX(gridX), toScreenY(gridY), SLOT_SIZE, SLOT_SIZE, Component.empty());

        this.spectrobe = spectrobe;
        this.gridX = gridX;
        this.gridY = gridY;
        this.posX = toScreenX(gridX);
        this.posY = toScreenY(gridY);
        this.pressHandler = pressHandler;
    }

    private static int toScreenX(int gridX) {
        return (gridX + 3) * SLOT_SIZE;
    }

    private static int toScreenY(int gridY) {
        return (gridY + 2) * SLOT_SIZE;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public int getSlotIndex() {
        return slotIndex;
    }

    public void setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
    }

    public Spectrobe getSpectrobe() {
        return spectrobe;
    }

    public void setSpectrobe(Spectrobe spectrobe) {
        this.spectrobe = spectrobe;
    }

    public void setPressHandler(Consumer<SpectrobePiece> pressHandler) {
        this.pressHandler = pressHandler;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public void toggleCurrent() {
        this.current = !this.current;
    }

    public void resetState() {
        this.spectrobe = null;
        this.selected = false;
        this.current = false;
    }

    public String getUnlocalizedName() {
        if (spectrobe == null) {
            return SpectrobesInfo.MOD_ID + ".spectrobe.empty";
        }

        return SpectrobesInfo.MOD_ID + ".spectrobe." + spectrobe.name;
    }

    public String getSortingName() {
        return getUnlocalizedName();
    }

    public void draw(GuiGraphics graphics, boolean withAdditional) {
        drawBackground(graphics);

        if (withAdditional) {
            drawAdditional(graphics);
        }
    }

    public void drawBackground(GuiGraphics graphics) {
        ResourceLocation background;

        if (Screen.hasAltDown() && isHovered()) {
            background = DELETE_BACKGROUND;
        } else if (current || selected) {
            background = PrizmodScreen.SPECTROBE_SLOT_SELECTED_TEXTURE;
        } else {
            background = PrizmodScreen.SPECTROBE_SLOT_TEXTURE;
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        graphics.blit(
                background,
                getX(),
                getY(),
                0.0F,
                0.0F,
                SLOT_SIZE,
                SLOT_SIZE,
                SLOT_SIZE,
                SLOT_SIZE
        );
    }

    public void drawAdditional(GuiGraphics graphics) {
        if (spectrobe == null || selected) {
            return;
        }

        SpectrobeIconInfo iconInfo = spectrobe.getIcon();
        int drawWidth = getScaledIconWidth(iconInfo);
        int drawHeight = getScaledIconHeight(iconInfo);
        int marginLeft = (SLOT_SIZE - drawWidth) / 2;
        int marginTop = (SLOT_SIZE - drawHeight) / 2;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        graphics.blit(
                iconInfo.icon(),
                getX() + marginLeft,
                getY() + marginTop,
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
        if (spectrobe == null) {
            return;
        }

        graphics.fill(
                getX() + HEALTH_BAR_LEFT,
                getY() + HEALTH_BAR_TOP,
                getX() + HEALTH_BAR_RIGHT,
                getY() + HEALTH_BAR_BOTTOM,
                HEALTH_BAR_RED
        );

        int maxHealth = Math.max(1, spectrobe.stats.getHpLevel());
        float healthPercent = Mth.clamp((float) spectrobe.currentHealth / (float) maxHealth, 0.0F, 1.0F);
        int greenWidth = Math.round(healthPercent * HEALTH_BAR_WIDTH);

        graphics.fill(
                getX() + HEALTH_BAR_LEFT,
                getY() + HEALTH_BAR_TOP,
                getX() + HEALTH_BAR_LEFT + greenWidth,
                getY() + HEALTH_BAR_BOTTOM,
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
        float scale = getIconScale(iconInfo);
        return Math.round(iconInfo.getWidth() * scale);
    }

    private int getScaledIconHeight(SpectrobeIconInfo iconInfo) {
        float scale = getIconScale(iconInfo);
        return Math.round(iconInfo.getHeight() * scale);
    }

    private float getIconScale(SpectrobeIconInfo iconInfo) {
        int maxDimension = Math.max(iconInfo.getWidth(), iconInfo.getHeight());

        if (maxDimension <= SLOT_SIZE) {
            return 1.0F;
        }

        return (float) SLOT_SIZE / (float) maxDimension;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!this.active || !this.visible || !this.isValidClickButton(button) || !this.clicked(mouseX, mouseY)) {
            return false;
        }

        this.playDownSound(Minecraft.getInstance().getSoundManager());

        if (pressHandler != null) {
            pressHandler.accept(this);
        }

        return true;
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
