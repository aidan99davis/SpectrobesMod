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
import java.util.function.Consumer;

public class TeamSpectrobesList extends AbstractWidget {

    public static final int GRID_SIZE = 7;
    public static final int CHILD_SLOT = 6;

    public final SpectrobePiece[] gridData;

    public TeamSpectrobesList(PrizmodPage parent) {
        this(parent, null);
    }

    public TeamSpectrobesList(PrizmodPage parent, Consumer<SpectrobePiece> slotPressed) {
        super(6 * SpectrobePiece.SLOT_SIZE, 2 * SpectrobePiece.SLOT_SIZE, 2 * SpectrobePiece.SLOT_SIZE, 4 * SpectrobePiece.SLOT_SIZE, Component.empty());

        this.gridData = new SpectrobePiece[GRID_SIZE];
        this.gridData[0] = createSlot(0, 6, 0, slotPressed);
        this.gridData[1] = createSlot(1, 7, 0, slotPressed);
        this.gridData[2] = createSlot(2, 6, 1, slotPressed);
        this.gridData[3] = createSlot(3, 7, 1, slotPressed);
        this.gridData[4] = createSlot(4, 6, 2, slotPressed);
        this.gridData[5] = createSlot(5, 7, 2, slotPressed);
        this.gridData[6] = createSlot(6, 6, 3, slotPressed);
    }

    private SpectrobePiece createSlot(int slotIndex, int gridX, int gridY, Consumer<SpectrobePiece> slotPressed) {
        SpectrobePiece slot = new SpectrobePiece(null, gridX, gridY, slotPressed);
        slot.setSlotIndex(slotIndex);
        return slot;
    }

    public SpectrobePiece getSlot(int index) {
        return gridData[index];
    }

    public void populateSlot(int index, Spectrobe spectrobe) {
        setSpectrobe(index, spectrobe);
    }

    public void setSpectrobe(int index, Spectrobe spectrobe) {
        gridData[index].setSpectrobe(spectrobe);
    }

    public boolean canAccept(int index, Spectrobe spectrobe) {
        if (index < 0 || index >= GRID_SIZE) {
            return false;
        }

        if (index == CHILD_SLOT) {
            return spectrobe != null && spectrobe.properties.getStage() == SpectrobeProperties.Stage.CHILD;
        }

        return spectrobe == null || spectrobe.properties.getStage() != SpectrobeProperties.Stage.CHILD;
    }

    public boolean addSpectrobe(int index, Spectrobe spectrobe) {
        if (!canAccept(index, spectrobe)) {
            return false;
        }

        setSpectrobe(index, spectrobe);
        return true;
    }

    public void clear() {
        for (SpectrobePiece slot : gridData) {
            slot.resetState();
        }
    }

    public List<SpectrobePiece> getAll() {
        return new ArrayList<>(Arrays.asList(gridData));
    }

    public boolean contains(SpectrobePiece slot) {
        for (SpectrobePiece teamSlot : gridData) {
            if (teamSlot == slot) {
                return true;
            }
        }

        return false;
    }

    public int indexOf(SpectrobePiece slot) {
        for (int i = 0; i < gridData.length; i++) {
            if (gridData[i] == slot) {
                return i;
            }
        }

        return -1;
    }

    public boolean canSwap(int firstIndex, int secondIndex) {
        return firstIndex >= 0
                && firstIndex < CHILD_SLOT
                && secondIndex >= 0
                && secondIndex < CHILD_SLOT
                && firstIndex != secondIndex;
    }

    public boolean swapSpectrobes(int firstIndex, int secondIndex) {
        if (!canSwap(firstIndex, secondIndex)) {
            return false;
        }

        Spectrobe first = gridData[firstIndex].getSpectrobe();
        Spectrobe second = gridData[secondIndex].getSpectrobe();

        gridData[firstIndex].setSpectrobe(second);
        gridData[secondIndex].setSpectrobe(first);

        return true;
    }

    public void setSlotCurrent(int index) {
        gridData[index].toggleCurrent();
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        for (SpectrobePiece slot : gridData) {
            slot.render(graphics, mouseX, mouseY, partialTick);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (SpectrobePiece slot : gridData) {
            if (slot.mouseClicked(mouseX, mouseY, button)) {
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        defaultButtonNarrationText(narrationElementOutput);
    }
}
