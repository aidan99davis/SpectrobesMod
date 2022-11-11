package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class MenuButton extends Button {

    public MenuButton(int xIn, int yIn, int width, int height, String text, Button.OnPress onPress) {
        super(xIn, yIn, width, height, Component.literal(text), onPress);

    }

    @Override
    public void renderButton(PoseStack stack, int mouseX, int mouseY, float pTicks) {
        stack.translate(0,0,16);
        super.renderButton(stack, mouseX, mouseY, pTicks);
    }
}
