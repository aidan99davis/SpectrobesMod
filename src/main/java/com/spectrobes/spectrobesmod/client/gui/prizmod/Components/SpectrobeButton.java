package com.spectrobes.spectrobesmod.client.prizmod.Components;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

import com.spectrobes.spectrobesmod.client.prizmod.Components.SpectrobeButton;
import com.spectrobes.spectrobesmod.client.prizmod.Components.SpectrobePiece;
import net.minecraft.client.gui.widget.button.Button.IPressable;

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
        this.renderButton(stack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderButton(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        piece.draw();
        if(selected) {
            renderInfo();
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        piece.setSelected(selected);
    }

    //render actions i.e. assign to slot 1, 2, 3, 4, 5, 6 or child slot
    public void renderInfo() {
//        piece.drawInfo();
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
