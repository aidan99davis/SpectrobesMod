package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class MenuButton extends Button {

    public MenuButton(int x, int y, int width, int height, String text, Button.OnPress onPress) {
        super(x, y, width, height, Component.literal(text), onPress, DEFAULT_NARRATION);
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.pose().pushPose();
        graphics.pose().translate(0.0F, 0.0F, 16.0F);

        super.renderWidget(graphics, mouseX, mouseY, partialTick);

        graphics.pose().popPose();
    }
}
