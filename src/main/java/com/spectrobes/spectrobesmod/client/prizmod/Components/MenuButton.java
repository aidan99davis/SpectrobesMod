package com.spectrobes.spectrobesmod.client.prizmod.Components;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.widget.button.Button;

public class MenuButton extends Button {

    public MenuButton(int xIn, int yIn, int width, int height, String text, IPressable onPress) {
        super(xIn, yIn, width, height, text, onPress);

    }

    @Override
    public void renderButton(int mouseX, int mouseY, float pTicks) {
        RenderSystem.translatef(0,0,16);
        super.renderButton(mouseX, mouseY, pTicks);
    }
}
