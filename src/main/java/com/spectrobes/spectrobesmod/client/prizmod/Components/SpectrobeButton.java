package com.spectrobes.spectrobesmod.client.prizmod.Components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.gui.prizmod.components.SpectrobePiece;
import com.spectrobes.spectrobesmod.client.prizmod.PrizmodScreen;
import net.minecraft.client.gui.widget.button.Button;

public class SpectrobeButton extends Button {
    public SpectrobePiece piece;
    final PrizmodScreen gui;


    public SpectrobeButton(PrizmodScreen gui, SpectrobePiece piece, IPressable pressable) {
        super(piece.posX, piece.posY, 32, 32, "", pressable);
        this.gui = gui;
        this.piece = piece;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
        this.renderButton(mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderButton(int mouseX, int mouseY, float partialTicks) {
        //super.renderButton(mouseX, mouseY, partialTicks);
//        RenderSystem.popMatrix();
        piece.draw();
//        RenderSystem.pushMatrix();
    }

    //render actions i.e. assign to slot 1, 2, 3, 4, 5, 6 or child slot
    public void renderActions() {
        RenderSystem.pushMatrix();
        //piece.displayButtonMenu();
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
