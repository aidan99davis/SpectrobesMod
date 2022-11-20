package com.spectrobes.spectrobesmod.client.gui.prizmod.Pages;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Components.*;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSpawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.Map;
import java.util.UUID;

public class LineUpPage extends PrizmodPage {

    private AllSpectrobesList AllSpectrobesGrid;
    private TeamSpectrobesList TeamSpectrobesGrid;
    private SpectrobeButton selectedButton;

    public LineUpPage(PrizmodScreen parent) {
        super(parent);
        AllSpectrobesGrid = new AllSpectrobesList(this);
        TeamSpectrobesGrid = new TeamSpectrobesList(this);
    }

    @Override
    public void tick() {
        this.changeFocus(true);
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
        if(selectedButton != null) {
            selectedButton.piece.drawAdditionalAtCursor(stack, mouseX, mouseY);
        }
    }

    @Override
    public void init() {
        buttons.clear();
//        this.addButton(new MenuButton(parent.width / 2 - 30, 25, 60, 20, "Menu", button -> {
//            parent.setMenuPage(new MenuPage(parent));
//        }));

        this.addButton(new Button(parent.width / 2 - 60, 45, 60, 20, Component.literal("Prev"), button -> {
            this.AllSpectrobesGrid.previousPage();
            this.parent.removeButtons(getButtons());
            this.init();
            this.changeFocus(true);
        }));

        this.addButton(new Button(parent.width / 2, 45, 60, 20, Component.literal("Next"), button -> {
            this.AllSpectrobesGrid.nextPage();
            this.parent.removeButtons(getButtons());
            this.init();
            this.changeFocus(true);
        }));

        populateGrid();

        super.init();
    }

    private void populateGrid() {
        this.TeamSpectrobesGrid.clear();
        this.AllSpectrobesGrid.clear();
        Map<Integer, UUID> teamUuids =  parent.getMenu().getCurrentTeamUUIDs();
        for(Spectrobe s : parent.getMenu().getOwnedSpectrobes()) {
            boolean dontAdd = false;
            for (int i = 0; i < 7; i++) {
                if(teamUuids.get(i) != null && teamUuids.get(i).equals(s.SpectrobeUUID)) {
                    if(teamUuids.get(i).equals(parent.getMenu().getCurrentSelectedUUID())) {
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
                    if(Screen.hasShiftDown() && !teamSpectrobe) {
                        if(sp.spectrobe != null && sp.spectrobe.active == false) {
                            if(parent.player.level.isClientSide()) {
                                Spectrobe spectrobe = sp.spectrobe;
                                SpectrobesNetwork.sendToServer(new SSpawnSpectrobePacket(spectrobe));
                                parent.getMenu().spawnSpectrobe(spectrobe);
                            }
                        }
                    } else if (Screen.hasAltDown() && !teamSpectrobe) {
                        if(sp.spectrobe != null) {
                            if(parent.player.level.isClientSide()) {
                                Spectrobe spectrobe = sp.spectrobe;
                                parent.getMenu().releaseSpectrobe(spectrobe);
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
            } else if(TeamSpectrobesGrid.getAll().contains(selectedButton.piece)
                        && AllSpectrobesGrid.getAll().contains(button.piece)) {
                if(TeamSpectrobesGrid.addSpectrobe(
                        TeamSpectrobesGrid.getAll().indexOf(selectedButton.piece),
                        button.piece.spectrobe)) {

                    populateGrid();
                    selectedButton = null;
                    return;
                }
            }
        }
        if(button.piece.spectrobe != null) {
            selectedButton = button;
            selectedButton.setSelected(true);
            return;
        }
        selectedButton = null;
    }

    private void removeButton(AbstractWidget b) {
        this.buttons.remove(b);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        return AllSpectrobesGrid.mouseClicked(mouseX,mouseY,mouseButton);
    }
}
