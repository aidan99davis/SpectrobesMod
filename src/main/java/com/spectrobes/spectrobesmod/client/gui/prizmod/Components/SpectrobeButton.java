package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

public class SpectrobeButton extends Button {
    public SpectrobePiece piece;
    final PrizmodScreen gui;
    private boolean selected;

    public SpectrobeButton(PrizmodScreen gui, SpectrobePiece piece, IPressable pressable) {
        super(piece.posX, piece.posY, 32, 32, new StringTextComponent(""), pressable);
        this.gui = gui;
        this.piece = piece;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
//        this.renderButton(stack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderButton(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        piece.draw(!selected);
        if(this.isHovered()) {
//            piece.drawInfo(); Name/Custom name? or a stat sheet?
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        piece.setSelected(selected);
    }
}
