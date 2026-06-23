package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class SpectrobeButton extends Button {

    public final SpectrobePiece piece;
    private final PrizmodScreen gui;
    private boolean selected;

    public SpectrobeButton(PrizmodScreen gui, SpectrobePiece piece, Button.OnPress pressable, CreateNarration narration) {
        super(
                piece.getX(),
                piece.getY(),
                SpectrobePiece.SLOT_SIZE,
                SpectrobePiece.SLOT_SIZE,
                Component.empty(),
                pressable,
                narration == null ? DEFAULT_NARRATION : narration
        );

        this.gui = gui;
        this.piece = piece;
    }

    public PrizmodScreen getGui() {
        return gui;
    }

    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        setX(piece.getX());
        setY(piece.getY());
        setWidth(SpectrobePiece.SLOT_SIZE);
        setHeight(SpectrobePiece.SLOT_SIZE);

        piece.draw(graphics, !selected);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        this.piece.setSelected(selected);
    }
}
