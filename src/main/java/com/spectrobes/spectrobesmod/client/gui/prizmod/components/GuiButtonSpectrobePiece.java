package com.spectrobes.spectrobesmod.client.gui.prizmod.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodMenu;
import com.spectrobes.spectrobesmod.client.gui.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;

public class GuiButtonSpectrobePiece extends Button {
    public SpectrobePiece piece;
    final PrizmodMenu gui;

    public GuiButtonSpectrobePiece(PrizmodMenu gui, SpectrobePiece piece, int x, int y) {
        super(x, y, 16, 16, "", button -> {});
        this.gui = gui;
        this.piece = piece;
    }

    public GuiButtonSpectrobePiece(PrizmodMenu gui, SpectrobePiece piece, int x, int y, Button.IPressable pressable) {
        super(x, y, 32, 32, "", pressable);
        this.gui = gui;
        this.piece = piece;
    }

    @Override
    public void renderButton(int mouseX, int mouseY, float pTicks) {
        if (active && visible) {
            boolean hover = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;

            RenderSystem.pushMatrix();
//            RenderSystem.translatef(x * 16, y * 16, 0);
//            piece.draw();
//            if(hover) {
//                Minecraft.getInstance().getTextureManager().bindTexture(PrizmodMenu.SPECTROBE_SLOT_TEXTURE);
//                GuiUtils.blit(piece.posX, piece.posY, 64, 0, 0, 32, 32, 16, 16);
//
//            }
            if (hover) {
                piece.getTooltip(gui.tooltip);
            }

            RenderSystem.popMatrix();

        }
    }

    //render actions i.e. assign to slot 1, 2, 3, 4, 5, 6 or child slot
    public void renderActions() {
        RenderSystem.pushMatrix();
        piece.displayButtonMenu();
        RenderSystem.popMatrix();
    }

    public SpectrobePiece getPiece() {
        return piece;
    }

    public String getPieceSortingName() {
        return piece.getSortingName();
    }

    public void setPiece(SpectrobePiece piece) {
        this.piece = piece;
    }
}
