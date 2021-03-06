package com.spectrobes.spectrobesmod.client.prizmod.Pages;

import com.spectrobes.spectrobesmod.client.prizmod.Components.AllSpectrobesList;
import com.spectrobes.spectrobesmod.client.gui.prizmod.components.SpectrobePiece;
import com.spectrobes.spectrobesmod.client.prizmod.Components.MenuButton;
import com.spectrobes.spectrobesmod.client.prizmod.Components.SpectrobeButton;
import com.spectrobes.spectrobesmod.client.prizmod.Components.TeamSpectrobesList;
import com.spectrobes.spectrobesmod.client.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSpawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LineUpPage extends PrizmodPage {

    private AllSpectrobesList AllSpectrobesGrid;
    private TeamSpectrobesList TeamSpectrobesGrid;
    private List<Widget> actionButtons = new ArrayList<>();
    private SpectrobeButton selectedButton;

    public LineUpPage(PrizmodScreen parent) {
        super(parent);
        AllSpectrobesGrid = new AllSpectrobesList(this);
        TeamSpectrobesGrid = new TeamSpectrobesList(this);
    }

    @Override
    public void tick() {
        //this.populateGrid();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
        AllSpectrobesGrid.draw();
        TeamSpectrobesGrid.draw();
        if(selectedButton != null) {
            selectedButton.renderInfo();
        }
    }

    @Override
    public void init() {
        this.addButton(new MenuButton(parent.width / 2 - 30, 25, 60, 20, "Menu", button -> {
            parent.setMenuPage(new MenuPage(parent));
        }));

        this.addButton(new Button(parent.width / 2 - 60, 45, 60, 20, "Prev", button -> {
            this.AllSpectrobesGrid.previousPage();
            this.populateGrid();
        }));

        this.addButton(new Button(parent.width / 2, 45, 60, 20, "Next", button -> {
            this.AllSpectrobesGrid.nextPage();
            this.populateGrid();
        }));

        populateGrid();

        super.init();
    }

    private void populateGrid() {
//        buttons.clear();
        this.TeamSpectrobesGrid.clear();
        this.AllSpectrobesGrid.clear();
        Map<Integer, UUID> teamUuids =  parent.getContainer().getCurrentTeamUUIDs();
        for(Spectrobe s : parent.getContainer().getOwnedSpectrobes()) {
            boolean dontAdd = false;
            for (int i = 0; i < 7; i++) {
                if(teamUuids.get(i) != null && teamUuids.get(i).equals(s.SpectrobeUUID)) {
                    if(teamUuids.get(i).equals(parent.getContainer().getCurrentSelectedUUID())) {
                        TeamSpectrobesGrid.setSlotCurrent(i);
                    }
                    TeamSpectrobesGrid.populateSlot(i, s);
                    dontAdd = true;
                }
            }
            if(!dontAdd) {
                AllSpectrobesGrid.addSpectrobe(s);
            }
        }

        for (SpectrobePiece sp : TeamSpectrobesGrid.getAll()) {
            addButton(addSpectrobeButton(sp, true));
        }

        for(SpectrobePiece sp : AllSpectrobesGrid.getAll()) {
            addButton(addSpectrobeButton(sp, false));
        }
    }

    private SpectrobeButton addSpectrobeButton(SpectrobePiece sp, boolean teamSpectrobe) {
        SpectrobeButton button = new SpectrobeButton(this.parent, sp,
                onClick -> {
                    if(Screen.hasShiftDown() && teamSpectrobe) {
                        if(sp.spectrobe != null && sp.spectrobe.active == false) {
                            if(parent.player.world.isRemote()) {
                                Spectrobe spectrobe = sp.spectrobe;
                                SpectrobesNetwork.sendToServer(new SSpawnSpectrobePacket(spectrobe));
                                parent.getContainer().spawnSpectrobe(spectrobe);
                            }
                        }
                    } else if (Screen.hasAltDown()) {
                        if(sp.spectrobe != null) {
                            if(parent.player.world.isRemote()) {
                                Spectrobe spectrobe = sp.spectrobe;
                                parent.getContainer().releaseSpectrobe(spectrobe);
                                populateGrid();
                            }
                        }
                    } else {
                        setSelectedSpectrobe(((SpectrobeButton)onClick));
                    }

                });
        return button;
    }

    private void setSelectedSpectrobe(SpectrobeButton button) {
        if(selectedButton != null) {
            selectedButton.setSelected(false);
            if(AllSpectrobesGrid.getAll().contains(selectedButton.piece)
                    && TeamSpectrobesGrid.getAll().contains(button.piece)) {

                if(TeamSpectrobesGrid.addSpectrobe(
                        TeamSpectrobesGrid.getAll().indexOf(button.piece),
                        selectedButton.piece.spectrobe)) {
                    populateGrid();
                }

                selectedButton = null;
                return;
            } else if(TeamSpectrobesGrid.getAll().contains(selectedButton.piece)
                    && TeamSpectrobesGrid.getAll().contains(button.piece)) {
                if(TeamSpectrobesGrid.swapSpectrobes(
                        TeamSpectrobesGrid.getAll().indexOf(button.piece),
                        TeamSpectrobesGrid.getAll().indexOf(selectedButton.piece))) {
                    populateGrid();
                }

                selectedButton = null;
                return;
            }
        }
        if(button.piece.spectrobe != null) {
            selectedButton = button;
            selectedButton.setSelected(true);
            return;
        }

        selectedButton = null;

    }

    private void removeButton(Widget b) {
        this.buttons.remove(b);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        return AllSpectrobesGrid.mouseClicked(mouseX,mouseY,mouseButton);
    }
}
