package com.spectrobes.spectrobesmod.client.prizmod.Components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.gui.prizmod.components.SpectrobePiece;
import com.spectrobes.spectrobesmod.client.prizmod.Pages.PrizmodPage;
import com.spectrobes.spectrobesmod.client.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

public class TeamSpectrobesList extends Widget {

    public static final int GRID_SIZE = 7;

    public SpectrobePiece[] gridData;

    private PrizmodPage parent;

    public TeamSpectrobesList(PrizmodPage parent) {
        super(parent.x, parent.y, 64, 128,  new StringTextComponent(""));
        this.parent = parent;
        gridData = new SpectrobePiece[GRID_SIZE];
        gridData[0] = new SpectrobePiece(null, 6, 0);
        gridData[1] = new SpectrobePiece(null, 7, 0);
        gridData[2] = new SpectrobePiece(null, 6, 1);
        gridData[3] = new SpectrobePiece(null, 7, 1);
        gridData[4] = new SpectrobePiece(null, 6, 2);
        gridData[5] = new SpectrobePiece(null, 7, 2);
        gridData[6] = new SpectrobePiece(null, 6, 3);
    }

    @OnlyIn(Dist.CLIENT)
    public void draw() {
        for (int i = 0; i < GRID_SIZE; i++) {
            SpectrobePiece p = gridData[i];

            RenderSystem.pushMatrix();
            Minecraft.getInstance().textureManager.bindTexture(PrizmodScreen.SPECTROBE_SLOT_TEXTURE);
            RenderSystem.enableAlphaTest();
            RenderSystem.translatef(i * 32, p.posY, 2);
            //p.draw();
            RenderSystem.popMatrix();

        }
    }

    public static boolean exists(int x) {
        return x >= 0 && x < GRID_SIZE;
    }

    public void populateSlot(int index, Spectrobe piece) {
        gridData[index].spectrobe = piece;
    }

    public boolean addSpectrobe(int index, Spectrobe piece) {

        if(index  >= 0
                && index < 6
                && piece.properties.getStage()
                    != SpectrobeProperties.Stage.CHILD) {
            gridData[index].spectrobe = piece;
            parent.parent.getContainer().setTeamMember(index, piece.SpectrobeUUID);
            return true;
        } else if(index == 6 && piece.properties.getStage() == SpectrobeProperties.Stage.CHILD) {
            gridData[index].spectrobe = piece;
            parent.parent.getContainer().setTeamMember(index, piece.SpectrobeUUID);
            return true;
        } else {
            SpectrobesInfo.LOGGER.info("unknown index, wtf?" + index);
            return false;
        }
    }

    public void clear() {
        for (int i = 0; i < GRID_SIZE; i++) {
            gridData[i].spectrobe = null;
        }
    }

    public List<SpectrobePiece> getAll() {
        List<SpectrobePiece> toReturn = new ArrayList<>();
        for (int i = 0; i < GRID_SIZE; i++) {
                toReturn.add(gridData[i]);
        }
        return toReturn;
    }

    public boolean swapSpectrobes(int i, int j) {
        SpectrobePiece s1 = gridData[i];
        SpectrobePiece s2 = gridData[j];

        if(i  >= 0
                && i < 6
                && j  >= 0
                && j < 6
                && i != j) {

            Spectrobe temp;
            temp = s1.spectrobe;
            gridData[i].spectrobe = s2.spectrobe;
            gridData[j].spectrobe = temp;
            parent.parent.getContainer().setTeamMember(i, gridData[i].spectrobe != null? gridData[i].spectrobe.SpectrobeUUID : null);
            parent.parent.getContainer().setTeamMember(j, gridData[j].spectrobe != null? gridData[j].spectrobe.SpectrobeUUID : null);
            return true;
        }

        return false;
    }

    public void setSlotCurrent(int i) {
        gridData[i].toggleCurrent();
    }
}
