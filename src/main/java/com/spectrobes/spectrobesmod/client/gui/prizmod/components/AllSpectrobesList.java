package com.spectrobes.spectrobesmod.client.gui.prizmod.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodMenu;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AllSpectrobesList extends Widget {

    public static final int GRID_SIZE = 5;

    public SpectrobePiece[][] gridData;

    private boolean empty;
    private int leftmost, rightmost, topmost, bottommost;

    @OnlyIn(Dist.CLIENT)
    public void draw() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                SpectrobePiece p = gridData[i][j];

                RenderSystem.pushMatrix();
                Minecraft.getInstance().textureManager.bindTexture(PrizmodMenu.SPECTROBE_SLOT_TEXTURE);
                RenderSystem.translatef(i * 32, j * 32, 2);
                p.draw();
                RenderSystem.popMatrix();

            }
        }
    }

    private void recalculateBoundaries() {
        empty = true;
        leftmost = GRID_SIZE;
        rightmost = -1;
        topmost = GRID_SIZE;
        bottommost = -1;

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                SpectrobePiece p = gridData[i][j];
                if (p != null) {
                    empty = false;
                    if (i < leftmost) {
                        leftmost = i;
                    }
                    if (i > rightmost) {
                        rightmost = i;
                    }
                    if (j < topmost) {
                        topmost = j;
                    }
                    if (j > bottommost) {
                        bottommost = j;
                    }
                }
            }
        }
    }


    public static boolean exists(int x, int y) {
        return x >= 0 && y >= 0 && x < GRID_SIZE && y < GRID_SIZE;
    }


    public AllSpectrobesList() {
        super(16, 16, "test");
        gridData = new SpectrobePiece[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gridData[i][j] = new SpectrobePiece(null);
                gridData[i][j].x = i;
                gridData[i][j].y = j;
            }
        }
    }


    public SpectrobePiece addSpectrobe(Spectrobe piece) {
        int i = 0;
        int j = 0;
        boolean added = false;

        int ir = 0;
        int jr = 0;

        for (i = 0; i < GRID_SIZE && !added; i++) {
            for (j = 0; j < GRID_SIZE && !added; j++) {
                SpectrobePiece p = gridData[i][j];
                if (p.spell == null) {
                    gridData[i][j].spell = piece;
                    added = true;
                    ir = i;
                    jr = j;
                }
            }
        }
        return gridData[ir][jr];
    }
}
