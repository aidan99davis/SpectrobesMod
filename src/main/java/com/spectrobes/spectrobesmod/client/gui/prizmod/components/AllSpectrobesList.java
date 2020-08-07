package com.spectrobes.spectrobesmod.client.gui.prizmod.components;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AllSpectrobesList {

    public static final int GRID_SIZE = 10;

    public SpectrobePiece[][] gridData;

    private boolean empty;
    private int leftmost, rightmost, topmost, bottommost;

    @OnlyIn(Dist.CLIENT)
    public void draw(IRenderTypeBuffer buffers, int light) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                SpectrobePiece p = gridData[i][j];
                if (p != null) {
                    RenderSystem.pushMatrix();
                    RenderSystem.translatef(i * 18, j * 18, 0);
                    p.draw(buffers, light);
                    RenderSystem.popMatrix();
                }
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

    public int getSize() {
        recalculateBoundaries();

        if (empty) {
            return 0;
        }

        return Math.max(rightmost - leftmost + 1, bottommost - topmost + 1);
    }

    public void mirrorVertical() {
        recalculateBoundaries();
        if (empty) {
            return;
        }

        SpectrobePiece[][] newGrid = new SpectrobePiece[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                SpectrobePiece p = gridData[i][j];

                if (p != null) {
                    int newY = (GRID_SIZE  / 2)- j - 1;

                    newGrid[i][newY] = p;
                    p.y = newY;
                }
            }
        }

        gridData = newGrid;
    }


    public static boolean exists(int x, int y) {
        return x >= 0 && y >= 0 && x < GRID_SIZE && y < GRID_SIZE / 2;
    }


    public AllSpectrobesList() {
        gridData = new SpectrobePiece[GRID_SIZE][GRID_SIZE / 2];
    }

    public boolean isEmpty() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE / 2; j++) {
                SpectrobePiece piece = gridData[i][j];
                if (piece != null) {
                    return false;
                }
            }
        }

        return true;
    }

    public void addSpectrobe(SpectrobePiece piece) {

    }
}
