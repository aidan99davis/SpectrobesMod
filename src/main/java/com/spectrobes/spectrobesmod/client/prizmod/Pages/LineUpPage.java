package com.spectrobes.spectrobesmod.client.prizmod.Pages;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.prizmod.Components.AllSpectrobesList;
import com.spectrobes.spectrobesmod.client.gui.prizmod.components.SpectrobePiece;
import com.spectrobes.spectrobesmod.client.prizmod.Components.MenuButton;
import com.spectrobes.spectrobesmod.client.prizmod.Components.SpectrobeButton;
import com.spectrobes.spectrobesmod.client.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

public class LineUpPage extends PrizmodPage {

    private AllSpectrobesList AllSpectrobesGrid;
    int gridPaddingLeft = parent.width / 3;
    int gridPaddingTop = parent.height / 2;

    public LineUpPage(PrizmodScreen parent) {
        super(parent);
        AllSpectrobesGrid = new AllSpectrobesList(this);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
        populateGrid();
        RenderSystem.pushMatrix();
        RenderSystem.translatef(parent.width / 3, 0, 1);
        AllSpectrobesGrid.draw();
        RenderSystem.popMatrix();
    }

    @Override
    public void init() {
        this.addButton(new MenuButton(parent.width / 2 - 60, 20, 60, 20, "Menu", button -> {
            parent.setMenuPage(new MenuPage(parent));
        }));

        populateGrid();

        super.init();
    }

    private void populateGrid() {
        this.AllSpectrobesGrid.clear();
        for(Spectrobe s : parent.playerData.getOwnedSpectrobes()) {
            AllSpectrobesGrid.addSpectrobe(s);
        }

        for(SpectrobePiece sp : AllSpectrobesGrid.getAll()) {
            SpectrobeButton button = new SpectrobeButton(this.parent, sp,
                    onClick -> {
                        SpectrobesInfo.LOGGER.info("SPECTROBE SLOT CLICKED M8");
                        if(sp.spell != null) {
                            SpectrobesInfo.LOGGER.info("THIS ONE HAS ONE IN IT");
                        }
                    });
            addButton(button);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
//        int flooredX = (int)mouseX / 32 - 1;
//        int flooredY = (int)mouseY / 32 - 1;
//        SpectrobesInfo.LOGGER.info("GOT HERE 1");
//        SpectrobesInfo.LOGGER.info("floored X:" + flooredX);
//        SpectrobesInfo.LOGGER.info("floored Y:" + flooredY);
//        if(AllSpectrobesList.exists(flooredX, flooredY)) {
//            if(AllSpectrobesGrid.gridData[flooredX][flooredY].spell != null && mouseButton == 0) {
//                getButtons().forEach(button -> {
//                    if(button instanceof SpectrobeButton && ((SpectrobeButton) button).getPiece().spell != null) {
//                        button.mouseClicked(mouseX, mouseY, mouseButton);
//                    }
//
//                });
//            }
//
//        }
//        getButtons().forEach(b -> {});
        SpectrobesInfo.LOGGER.info("GOT TO HERE MY G:");
        return AllSpectrobesGrid.mouseClicked(mouseX,mouseY,mouseButton);
    }
}
