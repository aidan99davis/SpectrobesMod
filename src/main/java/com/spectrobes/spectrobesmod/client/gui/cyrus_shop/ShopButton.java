package com.spectrobes.spectrobesmod.client.gui.cyrus_shop;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import software.bernie.geckolib3.core.util.Color;

public class ShopButton extends Button {
    private final int guraPrice;
    private final boolean isBuyButton;
    private int fontColour = Color.GRAY.hashCode();

    public ShopButton(int guraPrice, boolean isBuyButton, int pX, int pY, int pWidth, int pHeight, Component pMessage, OnPress pOnPress) {
        super(pX, pY, pWidth, pHeight, pMessage, pOnPress);
        this.guraPrice = guraPrice;
        this.isBuyButton = isBuyButton;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        if (this.visible) {
//            this.isHovered = pMouseX >= this.x && pMouseY >= this.y && pMouseX < this.x + this.width && pMouseY < this.y + this.height;
            this.renderButton(pPoseStack, pMouseX, pMouseY, pPartialTick);
        }
    }

    public void setFontColour(int colour) { this.fontColour = colour; }

    @Override
    public void renderButton(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        Minecraft minecraft = Minecraft.getInstance();
        Font font = minecraft.font;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, WIDGETS_LOCATION);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        int i = this.getYImage(this.isHoveredOrFocused());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        this.blit(pPoseStack, this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
        this.blit(pPoseStack, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
        this.renderBg(pPoseStack, minecraft, pMouseX, pMouseY);
        int j = getFGColor();
        drawCenteredString(pPoseStack, font, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, fontColour);
    }

    @Override
    public boolean isMouseOver(double pMouseX, double pMouseY) {
        this.isHovered = super.isMouseOver(pMouseX, pMouseY);
        return super.isMouseOver(pMouseX, pMouseY);
    }

    @Override
    public Component getMessage() {
        return !this.isHoveredOrFocused() ? Component.literal(isBuyButton? "Buy" : "Sell") : Component.literal("" + guraPrice);
    }
}
