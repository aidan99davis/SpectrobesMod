package com.spectrobes.spectrobesmod.client.gui.cyrus_shop;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class ShopButton extends Button {
    private static final int DEFAULT_FONT_COLOUR = 0xFFA0A0A0;

    private final int guraPrice;
    private final boolean isBuyButton;

    private int fontColour = DEFAULT_FONT_COLOUR;

    public ShopButton(
            int guraPrice,
            boolean isBuyButton,
            int x,
            int y,
            int width,
            int height,
            Component message,
            OnPress onPress
    ) {
        this(guraPrice, isBuyButton, x, y, width, height, message, onPress, DEFAULT_NARRATION);
    }

    public ShopButton(
            int guraPrice,
            boolean isBuyButton,
            int x,
            int y,
            int width,
            int height,
            Component message,
            OnPress onPress,
            CreateNarration narration
    ) {
        super(x, y, width, height, message, onPress, narration);

        this.guraPrice = guraPrice;
        this.isBuyButton = isBuyButton;
    }

    public void setFontColour(int colour) {
        this.fontColour = colour;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderWidget(guiGraphics, mouseX, mouseY, partialTick);

        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;

        Component label = getDisplayMessage();

        guiGraphics.drawCenteredString(
                font,
                label,
                this.getX() + this.width / 2,
                this.getY() + (this.height - 8) / 2,
                this.fontColour
        );
    }

    private Component getDisplayMessage() {
        if (!this.isHoveredOrFocused()) {
            return Component.literal(this.isBuyButton ? "Buy" : "Sell");
        }

        return Component.literal(Integer.toString(this.guraPrice));
    }

    @Override
    public Component getMessage() {
        return getDisplayMessage();
    }
}