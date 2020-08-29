package com.spectrobes.spectrobesmod.client.prizmod.Components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.client.gui.prizmod.components.SpectrobePiece;
import com.spectrobes.spectrobesmod.client.prizmod.Pages.PrizmodPage;
import com.spectrobes.spectrobesmod.client.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllSpectrobesList extends Widget {

    public static final int GRID_SIZE = 5;

    public SpectrobePiece[][] gridData;

    public Map<Integer,SpectrobePiece[][]> gridData_paged;
    private int pages;

    public int currentPage = 0;

    private PrizmodPage parent;

    public AllSpectrobesList(PrizmodPage parent) {
        super(parent.x, parent.y, "");
        this.parent = parent;
        gridData_paged = new HashMap<>();
        int specCount = this.parent.parent.getContainer().getOwnedSpectrobesCount();
        int remainder = specCount % 25;
        this.pages = specCount / 25;
        if(remainder > 0) {
            this.pages += 1;
        }
        if(pages == 0)
            pages++;
        for(int a = 0; a < this.pages; a ++) {
            SpectrobePiece[][] newGridData = new SpectrobePiece[GRID_SIZE][GRID_SIZE];
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    newGridData[i][j] = new SpectrobePiece(null, i, j);
                }
            }
            gridData_paged.put(a, newGridData);
        }

        gridData = gridData_paged.get(currentPage);
    }

    @OnlyIn(Dist.CLIENT)
    public void draw() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                SpectrobePiece p = gridData[i][j];

                RenderSystem.pushMatrix();
                Minecraft.getInstance().textureManager.bindTexture(PrizmodScreen.SPECTROBE_SLOT_TEXTURE);
                RenderSystem.enableAlphaTest();
                RenderSystem.translatef(i * 32, j * 32, 2);
                //p.draw();
                RenderSystem.popMatrix();

            }
        }
    }

    public static boolean exists(int x, int y) {
        return x >= 0 && y >= 0 && x < GRID_SIZE && y < GRID_SIZE;
    }


    public AllSpectrobesList() {
        super(32, 32, "");

    }

    public void addSpectrobe(Spectrobe piece) {
        int i = 0;
        int j = 0;
        boolean added = false;

        for(int a = 0; a < this.pages && !added; a++) {
            for (i = 0; i < GRID_SIZE && !added; i++) {
                for (j = 0; j < GRID_SIZE && !added; j++) {
                    SpectrobePiece p = gridData_paged.get(a)[i][j];
                    if (p.spectrobe == null) {
                        gridData_paged.get(a)[i][j].spectrobe = piece;
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
                toReturn.add(gridData_paged.get(currentPage)[i][j]);
            }
        }
        return toReturn;
    }

    public void previousPage() {
        if(currentPage > 0) {
            currentPage --;
        }
    }

    public void nextPage() {
        if(currentPage + 1 < pages) {
            currentPage ++;
        } else {
            currentPage = 0;
        }
    }
}
