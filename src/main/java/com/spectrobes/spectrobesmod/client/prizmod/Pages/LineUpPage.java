package com.spectrobes.spectrobesmod.client.prizmod.Pages;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.SpectrobesEntities;
import com.spectrobes.spectrobesmod.client.prizmod.Components.AllSpectrobesList;
import com.spectrobes.spectrobesmod.client.gui.prizmod.components.SpectrobePiece;
import com.spectrobes.spectrobesmod.client.prizmod.Components.MenuButton;
import com.spectrobes.spectrobesmod.client.prizmod.Components.SpectrobeButton;
import com.spectrobes.spectrobesmod.client.prizmod.Components.TeamSpectrobesList;
import com.spectrobes.spectrobesmod.client.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.entities.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LineUpPage extends PrizmodPage {

    private AllSpectrobesList AllSpectrobesGrid;
    private TeamSpectrobesList TeamSpectrobesGrid;
    private List<Widget> actionButtons = new ArrayList<>();
    private SpectrobeButton selectedButton;
    int gridPaddingLeft = parent.width / 3;
    int gridPaddingTop = parent.height / 2;

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
        //populateGrid();
        RenderSystem.pushMatrix();
        RenderSystem.translatef(parent.width / 3, 0, 1);
        AllSpectrobesGrid.draw();
        TeamSpectrobesGrid.draw();
        if(selectedButton != null) {
            selectedButton.renderActions();
        }
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
        this.TeamSpectrobesGrid.clear();
        this.AllSpectrobesGrid.clear();
        UUID[] teamUuids =  parent.playerData.getCurrentTeamUuids();
        for(Spectrobe s : parent.playerData.getOwnedSpectrobes()) {
            boolean dontAdd = false;
            for (int i = 0; i < 7; i++) {
                if(teamUuids[i] == s.SpectrobeUUID) {
                    TeamSpectrobesGrid.addSpectrobe(i, s);
                    dontAdd = true;
                }
            }
            if(!dontAdd) {
                AllSpectrobesGrid.addSpectrobe(s);
            }
        }

        for (SpectrobePiece sp : TeamSpectrobesGrid.getAll()) {
            addButton(addSpectrobeButton(sp));
        }

        for(SpectrobePiece sp : AllSpectrobesGrid.getAll()) {
            addButton(addSpectrobeButton(sp));
        }
    }

    private SpectrobeButton addSpectrobeButton(SpectrobePiece sp) {
        SpectrobeButton button = new SpectrobeButton(this.parent, sp,
                onClick -> {
                    setSelectedSpectrobe(((SpectrobeButton)onClick));
                    SpectrobesInfo.LOGGER.info("SPECTROBE SLOT CLICKED M8");
                    if(sp.spell != null && sp.spell.active == false) {
                        try {
                            if(!parent.player.world.isRemote) {
                                Spectrobe spectrobe = sp.spell;
                                EntitySpectrobe spectrobe1 = SpectrobesEntities.getByName(spectrobe.name).spawn(
                                        parent.player.world,
                                        spectrobe.write(),
                                        new StringTextComponent(spectrobe.name),
                                        parent.player,
                                        parent.player.getPosition(),
                                        SpawnReason.MOB_SUMMONED,
                                        true,true);
                                spectrobe1.setSpectrobeData(spectrobe);
                                spectrobe1.setOwnerId(parent.player.getUniqueID());
                                parent.playerData.spawnSpectrobe(spectrobe);
                                //Minecraft.getInstance().displayGuiScreen(null);
                            }
                        } catch (ClassNotFoundException e) {
                            SpectrobesInfo.LOGGER.info("Couldnt find spectrobes registry.\n" + e.getMessage());
                        }
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
                        selectedButton.piece.spell)) {
                    populateGrid();
                }

                selectedButton = null;
                return;
            }
        }
        selectedButton = button;
        selectedButton.setSelected(true);

    }

    private void removeButton(Widget b) {
        this.buttons.remove(b);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        return AllSpectrobesGrid.mouseClicked(mouseX,mouseY,mouseButton);
    }
}
