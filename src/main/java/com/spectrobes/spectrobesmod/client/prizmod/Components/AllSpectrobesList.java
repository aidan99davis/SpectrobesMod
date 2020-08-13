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
import java.util.List;

public class AllSpectrobesList extends Widget {

    public static final int GRID_SIZE = 5;

    public SpectrobePiece[][] gridData;

    private PrizmodPage parent;

    public AllSpectrobesList(PrizmodPage parent) {
        super(parent.x, parent.y, "");
        this.parent = parent;
        gridData = new SpectrobePiece[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gridData[i][j] = new SpectrobePiece(null, i, j);
            }
        }
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


        for (i = 0; i < GRID_SIZE && !added; i++) {
            for (j = 0; j < GRID_SIZE && !added; j++) {
                SpectrobePiece p = gridData[i][j];
                if (p.spell == null) {
                    gridData[i][j].spell = piece;
                    added = true;
                }
            }
        }
    }

    public void clear() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gridData[i][j].spell = null;
            }
        }
    }

    public List<SpectrobePiece> getAll() {
        List<SpectrobePiece> toReturn = new ArrayList<>();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                toReturn.add(gridData[i][j]);
            }
        }
        return toReturn;
    }
}
