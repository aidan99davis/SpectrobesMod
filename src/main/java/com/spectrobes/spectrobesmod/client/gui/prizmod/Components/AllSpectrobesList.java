package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Pages.PrizmodPage;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.text.StringTextComponent;
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
        super(parent.x, parent.y, 0, 0, new StringTextComponent(""));
        this.parent = parent;
        gridData_paged = new HashMap<>();
        int specCount = this.parent.parent.getMenu().getOwnedSpectrobesCount();
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

    @OnlyIn(Dist.CLIENT)
    public void draw() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
//                SpectrobePiece p = gridData[i][j];
//
//                RenderSystem.pushMatrix();
//                Minecraft.getInstance().textureManager.bind(PrizmodScreen.SPECTROBE_SLOT_TEXTURE);
//                RenderSystem.enableAlphaTest();
//                RenderSystem.translatef(i * 32, j * 32, 0);
//                //p.draw();
//                RenderSystem.popMatrix();
            }
        }
    }

    public void addSpectrobe(Spectrobe piece) {
        int i = 0;
        int j = 0;
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
}
