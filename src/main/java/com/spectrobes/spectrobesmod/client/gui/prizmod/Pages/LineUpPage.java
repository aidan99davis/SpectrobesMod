package com.spectrobes.spectrobesmod.client.gui.prizmod.Pages;

import com.spectrobes.spectrobesmod.client.gui.prizmod.Components.AllSpectrobesList;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Components.SpectrobeButton;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Components.SpectrobePiece;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Components.TeamSpectrobesList;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSpawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.Map;
import java.util.UUID;

public class LineUpPage extends PrizmodPage {
    private final AllSpectrobesList allSpectrobesGrid;
    private final TeamSpectrobesList teamSpectrobesGrid;

    private SpectrobeButton selectedButton;

    public LineUpPage(PrizmodScreen parent) {
        super(parent);

        this.allSpectrobesGrid = new AllSpectrobesList(this);
        this.teamSpectrobesGrid = new TeamSpectrobesList(this);
    }

    @Override
    public void tick() {
        this.setFocused(true);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.renderWidget(guiGraphics, mouseX, mouseY, partialTicks);

        if (this.selectedButton != null) {
            this.selectedButton.piece.drawAdditionalAtCursor(guiGraphics, mouseX, mouseY);
        }
    }

    @Override
    public void init() {
        this.buttons.clear();

        this.addButton(
                Button.builder(
                                Component.literal("Prev"),
                                button -> {
                                    this.allSpectrobesGrid.previousPage();
                                    this.parent.removeButtons(getButtons());
                                    this.init();
                                    this.setFocused(true);
                                }
                        )
                        .bounds(this.parent.width / 2 - 60, 45, 60, 20)
                        .build()
        );

        this.addButton(
                Button.builder(
                                Component.literal("Next"),
                                button -> {
                                    this.allSpectrobesGrid.nextPage();
                                    this.parent.removeButtons(getButtons());
                                    this.init();
                                    this.setFocused(true);
                                }
                        )
                        .bounds(this.parent.width / 2, 45, 60, 20)
                        .build()
        );

        populateGrid();

        super.init();
    }

    private void populateGrid() {
        this.teamSpectrobesGrid.clear();
        this.allSpectrobesGrid.clear();

        Map<Integer, UUID> teamUuids = this.parent.getMenu().getCurrentTeamUUIDs();

        for (Spectrobe spectrobe : this.parent.getMenu().getOwnedSpectrobes()) {
            boolean dontAdd = false;

            for (int i = 0; i < 7; i++) {
                UUID teamUuid = teamUuids.get(i);

                if (teamUuid != null && teamUuid.equals(spectrobe.SpectrobeUUID)) {
                    if (teamUuid.equals(this.parent.getMenu().getCurrentSelectedUUID())) {
                        this.teamSpectrobesGrid.setSlotCurrent(i);
                    }

                    this.teamSpectrobesGrid.populateSlot(i, spectrobe);
                    dontAdd = true;
                }
            }

            if (!dontAdd) {
                this.allSpectrobesGrid.addSpectrobe(spectrobe);
            }
        }

        for (SpectrobePiece spectrobePiece : this.teamSpectrobesGrid.getAll()) {
            this.addButton(addSpectrobeButton(spectrobePiece, true));
        }

        for (SpectrobePiece spectrobePiece : this.allSpectrobesGrid.getAll()) {
            this.addButton(addSpectrobeButton(spectrobePiece, false));
        }
    }

    private SpectrobeButton addSpectrobeButton(SpectrobePiece spectrobePiece, boolean teamSpectrobe) {
        return new SpectrobeButton(
                this.parent,
                spectrobePiece,
                onClick -> {
                    if (Screen.hasShiftDown() && !teamSpectrobe) {
                        if (spectrobePiece.spectrobe != null && !spectrobePiece.spectrobe.active) {
                            if (this.parent.player.level().isClientSide()) {
                                Spectrobe spectrobe = spectrobePiece.spectrobe;
                                SpectrobesNetwork.sendToServer(new SSpawnSpectrobePacket(spectrobe));
                                this.parent.getMenu().spawnSpectrobe(spectrobe);
                            }
                        }
                    } else if (Screen.hasAltDown() && !teamSpectrobe) {
                        if (spectrobePiece.spectrobe != null) {
                            if (this.parent.player.level().isClientSide()) {
                                Spectrobe spectrobe = spectrobePiece.spectrobe;
                                this.parent.getMenu().releaseSpectrobe(spectrobe);
                                populateGrid();
                            }
                        }
                    } else {
                        setSelectedSpectrobe((SpectrobeButton) onClick);
                    }
                },
                null
        );
    }

    private void setSelectedSpectrobe(SpectrobeButton button) {
        if (this.selectedButton != null) {
            this.selectedButton.setSelected(false);

            if (this.allSpectrobesGrid.getAll().contains(this.selectedButton.piece)
                    && this.teamSpectrobesGrid.getAll().contains(button.piece)) {
                if (this.teamSpectrobesGrid.addSpectrobe(
                        this.teamSpectrobesGrid.getAll().indexOf(button.piece),
                        this.selectedButton.piece.spectrobe
                )) {
                    populateGrid();
                }

                this.selectedButton = null;
                return;
            }

            if (this.teamSpectrobesGrid.getAll().contains(this.selectedButton.piece)
                    && this.teamSpectrobesGrid.getAll().contains(button.piece)) {
                if (this.teamSpectrobesGrid.swapSpectrobes(
                        this.teamSpectrobesGrid.getAll().indexOf(button.piece),
                        this.teamSpectrobesGrid.getAll().indexOf(this.selectedButton.piece)
                )) {
                    populateGrid();
                }

                this.selectedButton = null;
                return;
            }

            if (this.teamSpectrobesGrid.getAll().contains(this.selectedButton.piece)
                    && this.allSpectrobesGrid.getAll().contains(button.piece)) {
                if (this.teamSpectrobesGrid.addSpectrobe(
                        this.teamSpectrobesGrid.getAll().indexOf(this.selectedButton.piece),
                        button.piece.spectrobe
                )) {
                    populateGrid();
                    this.selectedButton = null;
                    return;
                }
            }
        }

        if (button.piece.spectrobe != null) {
            this.selectedButton = button;
            this.selectedButton.setSelected(true);
            return;
        }

        this.selectedButton = null;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (this.allSpectrobesGrid.mouseClicked(mouseX, mouseY, mouseButton)) {
            return true;
        }

        if (this.teamSpectrobesGrid.mouseClicked(mouseX, mouseY, mouseButton)) {
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }
}