package com.spectrobes.spectrobesmod.client.gui.prizmod.components;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpectrobesTeamList {

    public static final int SIZE = 6;

    public SpectrobePiece[] data;

    private boolean empty;
    private int leftmost, rightmost, topmost, bottommost;

    @OnlyIn(Dist.CLIENT)
    public void draw() {
        for (int j = 0; j < SIZE; j++) {
            SpectrobePiece p = data[j];
            if (p != null) {
                RenderSystem.pushMatrix();
                RenderSystem.translatef(j * 10, 0, 0);
                p.draw();
                RenderSystem.popMatrix();
            } else {

            }
        }
    }


    public static boolean exists(int x) {
        return x >= 0 && x < SIZE;
    }

    public SpectrobesTeamList() {
        data = new SpectrobePiece[SIZE];
    }

    public boolean isEmpty() {
        for (int i = 0; i < SIZE; i++) {
            SpectrobePiece piece = data[i];
            if (piece != null) {
                return false;
            }
        }
        return true;
    }
}
