package com.spectrobes.spectrobesmod.client.gui.prizmod.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodMenu;
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
        super(x, y, 16, 16, "", pressable);
        this.gui = gui;
        this.piece = piece;
    }

    @Override
    public void renderButton(int mouseX, int mouseY, float pTicks) {
        if (active && visible) {
            boolean hover = mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;

            IRenderTypeBuffer.Impl buffers = IRenderTypeBuffer.getImpl(Tessellator.getInstance().getBuffer());

            RenderSystem.pushMatrix();
//            RenderSystem.translatef(x * 16, y * 16, 0);
//            piece.draw();

            //Minecraft.getInstance().getTextureManager().bindTexture(PrizmodMenu.SPECTROBE_SLOT_TEXTURE);
            //blit(x, y, 16, gui.ySize, 16, 16);
            if (hover) {
                piece.getTooltip(gui.tooltip);
            }

            RenderSystem.popMatrix();
            buffers.finish();

        }
    }

    //render actions i.e. assign to slot 1, 2, 3, 4, 5, 6 or child slot
    public void renderActions() {

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
