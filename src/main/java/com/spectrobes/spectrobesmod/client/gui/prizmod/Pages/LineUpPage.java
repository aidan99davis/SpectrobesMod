package com.spectrobes.spectrobesmod.client.gui.prizmod.Pages;

import com.spectrobes.spectrobesmod.client.gui.prizmod.Components.AllSpectrobesList;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Components.SpectrobePiece;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Components.TeamSpectrobesList;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.networking.server.SSpawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.Map;
import java.util.UUID;

public class LineUpPage extends PrizmodPage {

    private final AllSpectrobesList allSpectrobesGrid;
    private final TeamSpectrobesList teamSpectrobesGrid;

    private SpectrobePiece selectedSlot;

    public LineUpPage(PrizmodScreen parent) {
        super(parent);

        this.allSpectrobesGrid = new AllSpectrobesList(this, this::onAllSpectrobeSlotPressed);
        this.teamSpectrobesGrid = new TeamSpectrobesList(this, this::onTeamSpectrobeSlotPressed);
    }

    @Override
    public void init() {
        super.init();

        this.selectedSlot = null;
        this.clearButtons();

        this.addButton(this.allSpectrobesGrid);
        this.addButton(this.teamSpectrobesGrid);

        this.addButton(
                Button.builder(Component.literal("Prev"), button -> {
                            this.allSpectrobesGrid.previousPage();
                            clearSelection();
                        })
                        .bounds(this.parent.width / 2 - 60, 45, 60, 20)
                        .build()
        );

        this.addButton(
                Button.builder(Component.literal("Next"), button -> {
                            this.allSpectrobesGrid.nextPage();
                            clearSelection();
                        })
                        .bounds(this.parent.width / 2, 45, 60, 20)
                        .build()
        );

        refreshLineUpData();
    }

    @Override
    public void tick() {
        setFocused(true);
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.renderWidget(guiGraphics, mouseX, mouseY, partialTicks);

        if (this.selectedSlot != null) {
            this.selectedSlot.drawAdditionalAtCursor(guiGraphics, mouseX, mouseY);
        }
    }

    private void refreshLineUpData() {
        this.allSpectrobesGrid.rebuildPages(this.parent.getMenu().getOwnedSpectrobesCount());
        this.teamSpectrobesGrid.clear();
        this.allSpectrobesGrid.clear();

        Map<Integer, UUID> teamUuids = this.parent.getMenu().getCurrentTeamUUIDs();
        UUID currentSelectedUuid = this.parent.getMenu().getCurrentSelectedUUID();

        for (Spectrobe spectrobe : this.parent.getMenu().getOwnedSpectrobes()) {
            int teamIndex = getTeamIndex(teamUuids, spectrobe.SpectrobeUUID);

            if (teamIndex >= 0) {
                SpectrobePiece teamSlot = this.teamSpectrobesGrid.getSlot(teamIndex);
                teamSlot.setSpectrobe(spectrobe);
                teamSlot.setCurrent(spectrobe.SpectrobeUUID.equals(currentSelectedUuid));
            } else {
                this.allSpectrobesGrid.addSpectrobe(spectrobe);
            }
        }
    }

    private int getTeamIndex(Map<Integer, UUID> teamUuids, UUID spectrobeUuid) {
        for (int i = 0; i < TeamSpectrobesList.GRID_SIZE; i++) {
            UUID teamUuid = teamUuids.get(i);

            if (teamUuid != null && teamUuid.equals(spectrobeUuid)) {
                return i;
            }
        }

        return -1;
    }

    private void onAllSpectrobeSlotPressed(SpectrobePiece slot) {
        if (Screen.hasShiftDown()) {
            spawnSpectrobe(slot);
            return;
        }

        if (Screen.hasAltDown()) {
            releaseSpectrobe(slot);
            return;
        }

        handleSlotPressed(slot);
    }

    private void onTeamSpectrobeSlotPressed(SpectrobePiece slot) {
        handleSlotPressed(slot);
    }

    private void spawnSpectrobe(SpectrobePiece slot) {
        Spectrobe spectrobe = slot.getSpectrobe();

        if (spectrobe == null || spectrobe.active || !this.parent.player.level().isClientSide()) {
            return;
        }

        SpectrobesNetwork.sendToServer(new SSpawnSpectrobePacket(spectrobe));
        this.parent.getMenu().spawnSpectrobe(spectrobe);
        clearSelection();
    }

    private void releaseSpectrobe(SpectrobePiece slot) {
        Spectrobe spectrobe = slot.getSpectrobe();

        if (spectrobe == null || !this.parent.player.level().isClientSide()) {
            return;
        }

        this.parent.getMenu().releaseSpectrobe(spectrobe);
        clearSelection();
        refreshLineUpData();
    }

    private void handleSlotPressed(SpectrobePiece targetSlot) {
        if (selectedSlot == null) {
            selectSlot(targetSlot);
            return;
        }

        if (selectedSlot == targetSlot) {
            clearSelection();
            return;
        }

        SpectrobePiece sourceSlot = selectedSlot;
        clearSelection();

        if (tryMoveOrSwap(sourceSlot, targetSlot)) {
            refreshLineUpData();
            return;
        }

        selectSlot(targetSlot);
    }

    private boolean tryMoveOrSwap(SpectrobePiece sourceSlot, SpectrobePiece targetSlot) {
        if (this.allSpectrobesGrid.contains(sourceSlot) && this.teamSpectrobesGrid.contains(targetSlot)) {
            return moveAllSlotToTeamSlot(sourceSlot, targetSlot);
        }

        if (this.teamSpectrobesGrid.contains(sourceSlot) && this.teamSpectrobesGrid.contains(targetSlot)) {
            return swapTeamSlots(sourceSlot, targetSlot);
        }

        if (this.teamSpectrobesGrid.contains(sourceSlot) && this.allSpectrobesGrid.contains(targetSlot)) {
            return replaceTeamSlotFromAllSlot(sourceSlot, targetSlot);
        }

        return false;
    }

    private boolean moveAllSlotToTeamSlot(SpectrobePiece sourceSlot, SpectrobePiece targetSlot) {
        int targetIndex = this.teamSpectrobesGrid.indexOf(targetSlot);
        Spectrobe spectrobe = sourceSlot.getSpectrobe();

        if (spectrobe == null || !this.teamSpectrobesGrid.canAccept(targetIndex, spectrobe)) {
            return false;
        }

        setTeamMember(targetIndex, spectrobe);
        return true;
    }

    private boolean swapTeamSlots(SpectrobePiece sourceSlot, SpectrobePiece targetSlot) {
        int sourceIndex = this.teamSpectrobesGrid.indexOf(sourceSlot);
        int targetIndex = this.teamSpectrobesGrid.indexOf(targetSlot);

        if (!this.teamSpectrobesGrid.canSwap(sourceIndex, targetIndex)) {
            return false;
        }

        Spectrobe sourceSpectrobe = sourceSlot.getSpectrobe();
        Spectrobe targetSpectrobe = targetSlot.getSpectrobe();

        setTeamMember(sourceIndex, targetSpectrobe);
        setTeamMember(targetIndex, sourceSpectrobe);
        return true;
    }

    private boolean replaceTeamSlotFromAllSlot(SpectrobePiece sourceSlot, SpectrobePiece targetSlot) {
        int sourceIndex = this.teamSpectrobesGrid.indexOf(sourceSlot);
        Spectrobe replacement = targetSlot.getSpectrobe();

        if (!this.teamSpectrobesGrid.canAccept(sourceIndex, replacement)) {
            return false;
        }

        setTeamMember(sourceIndex, replacement);
        return true;
    }

    private void setTeamMember(int index, Spectrobe spectrobe) {
        this.parent.getMenu().setTeamMember(index, spectrobe == null ? null : spectrobe.SpectrobeUUID);
    }

    private void selectSlot(SpectrobePiece slot) {
        clearSelection();

        if (slot.getSpectrobe() == null) {
            return;
        }

        this.selectedSlot = slot;
        this.selectedSlot.setSelected(true);
    }

    private void clearSelection() {
        if (this.selectedSlot != null) {
            this.selectedSlot.setSelected(false);
            this.selectedSlot = null;
        }
    }
}
