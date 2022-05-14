package com.spectrobes.spectrobesmod.client.prizmod.Components;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

import com.spectrobes.spectrobesmod.client.prizmod.Components.MenuButton;
import net.minecraft.client.gui.widget.button.Button.IPressable;

public class MenuButton extends Button {

    public MenuButton(int xIn, int yIn, int width, int height, String text, IPressable onPress) {
        super(xIn, yIn, width, height, new StringTextComponent(text), onPress);

    }

    @Override
    public void renderButton(MatrixStack stack, int mouseX, int mouseY, float pTicks) {
        stack.translate(0,0,16);
        super.renderButton(stack, mouseX, mouseY, pTicks);
    }
}
