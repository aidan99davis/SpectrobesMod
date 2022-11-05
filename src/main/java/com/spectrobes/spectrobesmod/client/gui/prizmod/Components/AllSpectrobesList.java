package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import com.spectrobes.spectrobesmod.client.gui.prizmod.Pages.PrizmodPage;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllSpectrobesList extends AbstractWidget {

    public static final int GRID_SIZE = 5;

    public SpectrobePiece[][] gridData;

    public Map<Integer,SpectrobePiece[][]> gridData_paged;
    private int pages;

    public int currentPage = 0;

    public AllSpectrobesList(PrizmodPage parent) {
        super(parent.x, parent.y, 0, 0, Component.literal(""));
        gridData_paged = new HashMap<>();
        int specCount = parent.parent.getMenu().getOwnedSpectrobesCount();
        int remainder = specCount % 25;
        this.pages = specCount / 25;
        if(remainder > 0) {
            this.pages += 1;
        }
        if(pages == 0)
            pages++;
        for(int a = 0; a < this.pages; a++) {
            SpectrobePiece[][] newGridData = new SpectrobePiece[GRID_SIZE][GRID_SIZE];
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    newGridData[j][i] = new SpectrobePiece(null, j, i);
                }
            }
            gridData_paged.put(a, newGridData);
        }

        gridData = gridData_paged.get(currentPage);
    }

    public void addSpectrobe(Spectrobe piece) {
        int i;
        int j;
        boolean added = false;

        for(int a = 0; a < this.pages && !added; a++) {
            for (i = 0; i < GRID_SIZE && !added; i++) {
                for (j = 0; j < GRID_SIZE && !added; j++) {
                    SpectrobePiece p = gridData_paged.get(a)[j][i];
                    if (p.spectrobe == null) {
                        gridData_paged.get(a)[j][i].spectrobe = piece;
                        added = true;
                    }
                }
            }
        }
    }

    public void clear() {
        for(int a = 0; a < this.pages; a++) {
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    gridData_paged.get(a)[i][j].spectrobe = null;
                }
            }
        }
    }

    public List<SpectrobePiece> getAll() {
        List<SpectrobePiece> toReturn = new ArrayList<>();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                toReturn.add(gridData_paged.get(currentPage)[j][i]);
            }
        }
        return toReturn;
    }

    public void previousPage() {
        if(currentPage - 1 >= 0) {
            currentPage --;
        } else {
            currentPage = pages - 1;
        }
    }

    public void nextPage() {
        if(currentPage + 1 < pages) {
            currentPage ++;
        } else {
            currentPage = 0;
        }
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
