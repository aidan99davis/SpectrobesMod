//package com.spectrobes.spectrobesmod.client.prizmod.Components;
//
//import com.mojang.blaze3d.systems.RenderSystem;
//import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodMenu;
//import com.spectrobes.spectrobesmod.client.prizmod.Components.SpectrobePiece;
//import com.spectrobes.spectrobesmod.client.gui.prizmod.Pages.PrizmodPage;
//import com.spectrobes.spectrobesmod.container.capability.PlayerSpectrobeMaster;
//import com.spectrobes.spectrobesmod.container.spectrobes.Spectrobe;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.widget.Widget;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class SpectrobesGrid extends Widget {
//
//    public static final int GRID_SIZE = 5;
//
//    public Map<Integer,SpectrobePiece[][]> gridData;
//    private int page = 0;
//
//    private PrizmodPage parent;
//
//    public SpectrobesGrid(PrizmodPage parent) {
//        super(parent.x, parent.y, "");
//        this.parent = parent;
//    }
//
//    @OnlyIn(Dist.CLIENT)
//    public void draw() {
//        for (int i = 0; i < GRID_SIZE; i++) {
//            for (int j = 0; j < GRID_SIZE; j++) {
//                SpectrobePiece p = gridData.get(page)[i][j];
//
//                RenderSystem.pushMatrix();
//                Minecraft.getInstance().textureManager.bindTexture(PrizmodMenu.SPECTROBE_SLOT_TEXTURE);
//                RenderSystem.enableAlphaTest();
//                RenderSystem.translatef(i * 32, j * 32, 2);
//                //p.draw();
//                RenderSystem.popMatrix();
//
//            }
//        }
//    }
//
//    public static boolean exists(int x, int y) {
//        return x >= 0 && y >= 0 && x < GRID_SIZE && y < GRID_SIZE;
//    }
//
//
//    public SpectrobesGrid(PlayerSpectrobeMaster playerData) {
//        super(32, 32, "");
//        gridData = new HashMap<>();
////        gridData = new SpectrobePiece[GRID_SIZE][GRID_SIZE];
//        int pagesNeeded = 1;
//        int spectrobeCount = playerData.getOwnedSpectrobesCount();
//        if(spectrobeCount > Math.pow(GRID_SIZE, 2)) {
//            int pagesNeeded = Math.round(spectrobeCount / Math.pow(GRID_SIZE, 2).);
//        } else {
//            gridData.put(0, new SpectrobePiece[GRID_SIZE][GRID_SIZE]);
//        }
//        for (int i = 0; i < GRID_SIZE; i++) {
//            for (int j = 0; j < GRID_SIZE; j++) {
//                gridData[i][j] = new SpectrobePiece(null, i, j);
//                gridData[i][j].x = i;
//                gridData[i][j].y = j;
//            }
//        }
//    }
//
//
//    public SpectrobePiece addSpectrobe(Spectrobe piece) {
//        int i = 0;
//        int j = 0;
//        boolean added = false;
//
//        int ir = 0;
//        int jr = 0;
//
//        for (i = 0; i < GRID_SIZE && !added; i++) {
//            for (j = 0; j < GRID_SIZE && !added; j++) {
//                SpectrobePiece p = gridData[i][j];
//                if (p.spectrobe == null) {
//                    gridData[i][j].spectrobe = piece;
//                    gridData[i][j].x = i;
//                    gridData[i][j].y = j;
//                    added = true;
//                    ir = i;
//                    jr = j;
//                }
//            }
//        }
//        return gridData[ir][jr];
//    }
//
//    public void clear() {
//        gridData = new SpectrobePiece[GRID_SIZE][GRID_SIZE];
//        for (int i = 0; i < GRID_SIZE; i++) {
//            for (int j = 0; j < GRID_SIZE; j++) {
//                gridData[i][j] = new SpectrobePiece(null, i, j);
//                gridData[i][j].x = i;
//                gridData[i][j].y = j;
//            }
//        }
//    }
//
//    public List<SpectrobePiece> getAll() {
//        List<SpectrobePiece> toReturn = new ArrayList<>();
//        for (int i = 0; i < GRID_SIZE; i++) {
//            for (int j = 0; j < GRID_SIZE; j++) {
//                toReturn.add(gridData[i][j]);
//            }
//        }
//        return toReturn;
//    }
//}
