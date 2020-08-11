package com.spectrobes.spectrobesmod.client.prizmod.Components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;

public class MenuButton extends Button {

    public MenuButton(int widthIn, int heightIn, int width, int height, String text, IPressable onPress) {
        super(widthIn, heightIn, width, height, text, onPress);

    }

    @Override
    public void renderButton(int mouseX, int mouseY, float pTicks) {
        RenderSystem.translatef(0,0,16);
        Minecraft.getInstance().getTextureManager().bindTexture(PrizmodMenu.SPECTROBE_SLOT_TEXTURE);
        super.renderButton(mouseX, mouseY, pTicks);
//        if (active) {
//            boolean hover = par2 >= x && par3 >= y && par2 < x + width && par3 < y + height;
//
//            Minecraft.getInstance().textureManager.bindTexture(WIDGETS_LOCATION);
//            RenderSystem.color4f(1F, 1F, 1F, 1F);
//            blit(x, y, hover ? 216 : 198, right ? 145 : 155, width, height);
//
//            if (hover) {
//            }
//        }
    }
}
