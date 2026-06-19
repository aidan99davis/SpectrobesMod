package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class SpectrobeButton extends Button {
    public SpectrobePiece piece;
    final PrizmodScreen gui;
    private boolean selected;

    public SpectrobeButton(PrizmodScreen gui, SpectrobePiece piece, Button.OnPress pressable, CreateNarration nar) {
        super(piece.posX, piece.posY, 32, 32, Component.literal(""), pressable, nar);
        this.gui = gui;
        this.piece = piece;
    }

    @Override
    public void renderWidget(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
        piece.draw(stack, !selected);
        if(this.isHovered) {
//            piece.drawInfo(); Name/Custom name? or a stat sheet?
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        piece.setSelected(selected);
    }
}
