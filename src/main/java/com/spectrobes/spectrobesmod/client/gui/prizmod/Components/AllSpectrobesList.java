package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import com.spectrobes.spectrobesmod.client.gui.prizmod.Pages.PrizmodPage;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class AllSpectrobesList extends AbstractWidget {

    public static final int GRID_SIZE = 5;
    private static final int PAGE_CAPACITY = GRID_SIZE * GRID_SIZE;

    private final Consumer<SpectrobePiece> slotPressed;
    private final List<List<SpectrobePiece>> pages = new ArrayList<>();

    public int currentPage = 0;

    public AllSpectrobesList(PrizmodPage parent) {
        this(parent, null);
    }

    public AllSpectrobesList(PrizmodPage parent, Consumer<SpectrobePiece> slotPressed) {
        super(3 * SpectrobePiece.SLOT_SIZE, 2 * SpectrobePiece.SLOT_SIZE, GRID_SIZE * SpectrobePiece.SLOT_SIZE, GRID_SIZE * SpectrobePiece.SLOT_SIZE, Component.empty());
        this.slotPressed = slotPressed;
        rebuildPages(parent.getParentScreen().getMenu().getOwnedSpectrobesCount());
    }

    public void rebuildPages(int spectrobeCount) {
        int pageCount = Math.max(1, (spectrobeCount + PAGE_CAPACITY - 1) / PAGE_CAPACITY);

        pages.clear();

        for (int page = 0; page < pageCount; page++) {
            List<SpectrobePiece> pageSlots = new ArrayList<>(PAGE_CAPACITY);

            for (int row = 0; row < GRID_SIZE; row++) {
                for (int column = 0; column < GRID_SIZE; column++) {
                    SpectrobePiece slot = new SpectrobePiece(null, column, row, slotPressed);
                    slot.setSlotIndex(pageSlots.size());
                    pageSlots.add(slot);
                }
            }

            pages.add(pageSlots);
        }

        if (currentPage >= pages.size()) {
            currentPage = pages.size() - 1;
        }

        if (currentPage < 0) {
            currentPage = 0;
        }
    }

    public int getPageCount() {
        return pages.size();
    }

    public void addSpectrobe(Spectrobe spectrobe) {
        for (List<SpectrobePiece> page : pages) {
            for (SpectrobePiece slot : page) {
                if (slot.getSpectrobe() == null) {
                    slot.setSpectrobe(spectrobe);
                    return;
                }
            }
        }
    }

    public void clear() {
        for (List<SpectrobePiece> page : pages) {
            for (SpectrobePiece slot : page) {
                slot.resetState();
            }
        }
    }

    public List<SpectrobePiece> getAll() {
        if (pages.isEmpty()) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(pages.get(currentPage));
    }

    public boolean contains(SpectrobePiece slot) {
        for (List<SpectrobePiece> page : pages) {
            if (page.contains(slot)) {
                return true;
            }
        }

        return false;
    }

    public void previousPage() {
        if (pages.isEmpty()) {
            currentPage = 0;
            return;
        }

        currentPage = currentPage == 0 ? pages.size() - 1 : currentPage - 1;
    }

    public void nextPage() {
        if (pages.isEmpty()) {
            currentPage = 0;
            return;
        }

        currentPage = currentPage + 1 >= pages.size() ? 0 : currentPage + 1;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        for (SpectrobePiece slot : getAll()) {
            slot.render(graphics, mouseX, mouseY, partialTick);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (SpectrobePiece slot : getAll()) {
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
