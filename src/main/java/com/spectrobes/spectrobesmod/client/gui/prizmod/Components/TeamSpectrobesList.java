package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import com.spectrobes.spectrobesmod.client.gui.prizmod.Pages.PrizmodPage;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamSpectrobesList extends AbstractWidget {

    public static final int GRID_SIZE = 7;

    public SpectrobePiece[] gridData;

    private final PrizmodPage parent;

    public TeamSpectrobesList(PrizmodPage parent) {
        super(parent.getX(), parent.getY(), 64, 128, Component.empty());

        this.parent = parent;

        gridData = new SpectrobePiece[GRID_SIZE];
        gridData[0] = new SpectrobePiece(null, 6, 0);
        gridData[1] = new SpectrobePiece(null, 7, 0);
        gridData[2] = new SpectrobePiece(null, 6, 1);
        gridData[3] = new SpectrobePiece(null, 7, 1);
        gridData[4] = new SpectrobePiece(null, 6, 2);
        gridData[5] = new SpectrobePiece(null, 7, 2);
        gridData[6] = new SpectrobePiece(null, 6, 3);
    }

    public void populateSlot(int index, Spectrobe piece) {
        gridData[index].spectrobe = piece;
    }

    public boolean addSpectrobe(int index, Spectrobe piece) {
        if (index >= 0
                && index < 6
                && (piece == null || piece.properties.getStage() != SpectrobeProperties.Stage.CHILD)) {

            gridData[index].spectrobe = piece;
            parent.parent.getMenu().setTeamMember(index, piece == null ? null : piece.SpectrobeUUID);
            return true;
        }

        if (index == 6 && piece != null && piece.properties.getStage() == SpectrobeProperties.Stage.CHILD) {
            gridData[index].spectrobe = piece;
            parent.parent.getMenu().setTeamMember(index, piece.SpectrobeUUID);
            return true;
        }

        return false;
    }

    public void clear() {
        for (int i = 0; i < GRID_SIZE; i++) {
            gridData[i].spectrobe = null;
        }
    }

    public List<SpectrobePiece> getAll() {
        return new ArrayList<>(Arrays.asList(gridData).subList(0, GRID_SIZE));
    }

    public boolean swapSpectrobes(int i, int j) {
        SpectrobePiece s1 = gridData[i];
        SpectrobePiece s2 = gridData[j];

        if (i < 6 && j < 6 && i != j) {
            Spectrobe temp = s1.spectrobe;

            gridData[i].spectrobe = s2.spectrobe;
            gridData[j].spectrobe = temp;

            parent.parent.getMenu().setTeamMember(
                    i,
                    gridData[i].spectrobe != null ? gridData[i].spectrobe.SpectrobeUUID : null
            );

            parent.parent.getMenu().setTeamMember(
                    j,
                    gridData[j].spectrobe != null ? gridData[j].spectrobe.SpectrobeUUID : null
            );

            return true;
        }

        return false;
    }

    public void setSlotCurrent(int i) {
        gridData[i].toggleCurrent();
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        /*
         * Intentionally empty.
         *
         * TeamSpectrobesList acts as a logical container for SpectrobePiece slots.
         * The original 1.19.1 class did not render the slots directly, so rendering
         * should continue to be handled by the parent PrizmodPage / PrizmodScreen
         * code that iterates over getAll() and draws the SpectrobePiece instances.
         */
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        defaultButtonNarrationText(narrationElementOutput);
    }
}