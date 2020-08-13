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

import java.util.List;
import java.util.UUID;

public class LineUpPage extends PrizmodPage {

    private AllSpectrobesList AllSpectrobesGrid;
    private TeamSpectrobesList TeamSpectrobesGrid;
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
        List<UUID> teamUuids =  parent.playerData.getCurrentTeamUuids();
        for(Spectrobe s : parent.playerData.getOwnedSpectrobes()) {

            SpectrobesInfo.LOGGER.info("spectrobe name: " + s.name);
            SpectrobesInfo.LOGGER.info("spectrobe active?: " + s.active);
            if(teamUuids.contains(s.SpectrobeUUID)) {
                TeamSpectrobesGrid.addSpectrobe(teamUuids.indexOf(s.SpectrobeUUID), s);
            } else {
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
                    SpectrobesInfo.LOGGER.info("SPECTROBE SLOT CLICKED M8");
                    if(sp.spell != null && sp.spell.active == false) {
                        SpectrobesInfo.LOGGER.info("THIS ONE HAS ONE IN IT THAT ISNT IN THE WORLD");
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
                                Minecraft.getInstance().displayGuiScreen(null);
                            }
                        } catch (ClassNotFoundException e) {
                            SpectrobesInfo.LOGGER.info("Couldnt find spectrobes registry.\n" + e.getMessage());
                        }
                    }
                });
        return button;
    }

    private void removeButton(Widget b) {
        this.buttons.remove(b);
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
