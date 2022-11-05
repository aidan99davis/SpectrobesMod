package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class SpectrobeButton extends Button {
    public SpectrobePiece piece;
    final PrizmodScreen gui;
    private boolean selected;

    public SpectrobeButton(PrizmodScreen gui, SpectrobePiece piece, Button.OnPress pressable) {
        super(piece.posX, piece.posY, 32, 32, Component.literal(""), pressable);
        this.gui = gui;
        this.piece = piece;
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
//        this.renderButton(stack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderButton(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        piece.draw(!selected);
        if(this.isHovered) {
//            piece.drawInfo(); Name/Custom name? or a stat sheet?
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        piece.setSelected(selected);
    }
}
