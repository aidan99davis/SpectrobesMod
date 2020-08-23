//package com.spectrobes.spectrobesmod.client.gui.prizmod;
//
//import com.mojang.blaze3d.systems.RenderSystem;
//import com.spectrobes.spectrobesmod.SpectrobesInfo;
//import com.spectrobes.spectrobesmod.client.gui.prizmod.components.*;
//import com.spectrobes.spectrobesmod.client.prizmod.Components.AllSpectrobesList;
//import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
//import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
//import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
//import net.minecraft.client.gui.IGuiEventListener;
//import net.minecraft.client.gui.IRenderable;
//import net.minecraft.client.gui.widget.Widget;
//import net.minecraft.client.gui.widget.button.Button;
//import org.lwjgl.glfw.GLFW;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//
//public class LineUpMenu extends Widget implements IRenderable, IGuiEventListener {
//
//    public final PrizmodMenu parent;
//    public boolean panelEnabled = false;
//    public final List<Button> panelButtons = new ArrayList<>();
//    public int panelCursor;
//    public AllSpectrobesList allSpectrobesList;
//    public SpectrobesTeamList spectrobesTeamList;
//    PlayerSpectrobeMaster playerData;
//    private SpectrobePiece childForm;
//    public int page = 0;
//    public final List<GuiButtonSpectrobePiece> visibleButtons = new ArrayList<>();
//
//    public LineUpMenu(int xIn, int yIn, String msg, PrizmodMenu parent) {
//        super(xIn, yIn, msg);
//        this.parent = parent;
//        allSpectrobesList = new AllSpectrobesList();
//    }
//
//    public LineUpMenu(int xIn, int yIn, int widthIn, int heightIn, String msg, PrizmodMenu parent) {
//        super(xIn, yIn, widthIn, heightIn, msg);
//        this.parent = parent;
//        if(parent.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).isPresent()) {
//            playerData = parent.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).orElse(null);
//        }
//
//        allSpectrobesList = new AllSpectrobesList();
//        spectrobesTeamList = new SpectrobesTeamList();
//
//    }
//
//    @Override
//    public void render(int mouseX, int mouseY, float pTicks) {
//        for(Button b : visibleButtons) {
//            b.render(mouseX,mouseY,pTicks);
//        }
//        RenderSystem.pushMatrix();
//        populatePanelButtons();
////        allSpectrobesList.draw();
//
//        parent.getMinecraft().getTextureManager().bindTexture(PrizmodMenu.SPECTROBE_SLOT_TEXTURE);
//
////        fill(x, y, x + width, y + height, 0x88000000);
//
////        if (visibleButtons.size() > 0) {
////            Button button = visibleButtons.get(Math.max(0, Math.min(panelCursor, visibleButtons.size() - 1)));
////            int panelPieceX = button.x;
////            int panelPieceY = button.y;
////            fill(panelPieceX - 1, panelPieceY - 1, panelPieceX + 17, panelPieceY + 17, 0x559999FF);
////        }
////
////        String s = Math.min(Math.max(getPageCount(), 1), page + 1) + "/" + Math.max(getPageCount(), 1);
////        parent.getMinecraft().fontRenderer.drawStringWithShadow(s, x + width / 2f - parent.getMinecraft().fontRenderer.getStringWidth(s) / 2f, y + height - 12, 0xFFFFFF);
//
//        RenderSystem.popMatrix();
//    }
//
//
//    @Override
//    public boolean mouseScrolled(double par1, double par2, double par3) {
//        if (panelEnabled && par3 != 0) {
//            int next = (int) (page - par3 / Math.abs(par3));
////            if (next >= 0 && next < getPageCount()) {
////                page = next;
////                updatePanelButtons();
////            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
//        if (panelEnabled) {
//            switch (keyCode) {
//                case GLFW.GLFW_KEY_ESCAPE:
//                    closePanel();
//                    return true;
//            }
//        }
//        return false;
//    }
//
//    public void populatePanelButtons() {
//        //populate the 6 team spectrobes pieces
//        //populate the child spectrobe piece
//
//        //populate the all spectrobes grid
//        allSpectrobesList.clear();
//        int maxCount = (int) Math.pow(AllSpectrobesList.GRID_SIZE, 2);
//        int index = 0;
//        for (Spectrobe spectrobe : playerData.getOwnedSpectrobes()) {
//            SpectrobesInfo.LOGGER.info("POPULATING SPECTROBE SLOT #" + index);
//            if(index < maxCount) {
////                SpectrobePiece piece = allSpectrobesList.addSpectrobe(spectrobe);
////                GuiButtonSpectrobePiece spectrobeButton = new GuiButtonSpectrobePiece(parent, piece, piece.x * 32 + 32, piece.y * 32 + 32, button -> {
////                    //((GuiButtonSpectrobePiece) button).renderActions();
////                    //parent.onSpellChanged(false);
////                    //closePanel();
////
////                    SpectrobesInfo.LOGGER.info("SPECTROBE SLOT CLICKED");
////                });
////                //spellPieceButton.visible = false;
////                spectrobeButton.active = true;
////                spectrobeButton.visible = true;
////                panelButtons.add(spectrobeButton);
////                visibleButtons.add(spectrobeButton);
////                index++;
//            }else {
//                break;
//            }
//        }
////
////            GuiButtonPage right = new GuiButtonPage(0, 0, true, parent, button -> {
////                int max = getPageCount();
////                int next = page + (((GuiButtonPage) button).right ? 1 : -1);
////
////                if (next >= 0 && next < max) {
////                    page = next;
////                    updatePanelButtons();
////                }
////            });
////
////            GuiButtonPage left = new GuiButtonPage(0, 0, false, parent, button -> {
////                int max = getPageCount();
////                int next = page + (((GuiButtonPage) button).right ? 1 : -1);
////
////                if (next >= 0 && next < max) {
////                    page = next;
////                    updatePanelButtons();
////                }
////            });
////            left.visible = true;
////            left.active = false;
////            right.visible = true;
////            right.active = false;
////            panelButtons.add(left);
////            panelButtons.add(right);
//        parent.addButtons(panelButtons);
//
//    }
//
//    public void updatePanelButtons() {
//        panelCursor = 0;
//        visibleButtons.clear();
//        parent.getButtons().forEach(button -> {
//            if (button instanceof GuiButtonPage || button instanceof GuiButtonSpectrobePiece) {
//                button.active = false;
//                button.visible = false;
//            }
//        });
//
//        parent.getButtons().forEach(button -> {
//            if (button instanceof GuiButtonSpectrobePiece) {
//                button.visible = true;
//                button.active = true;
//                visibleButtons.add((GuiButtonSpectrobePiece) button);
//
//            } else if (button instanceof GuiButtonPage) {
//                GuiButtonPage page = (GuiButtonPage) button;
//                if (page.isRight() /*&& this.page < getPageCount() - 1*/) {
//                    button.x = x + width - 22;
//                    button.y = y + height - 15;
//                    button.visible = true;
//                    button.active = true;
//
//                } else if (!page.isRight() && this.page > 0) {
//                    button.x = x + 4;
//                    button.y = y + height - 15;
//                    button.visible = true;
//                    button.active = true;
//                }
//            }
//        });
//
//        Comparator<GuiButtonSpectrobePiece> comparator;
//
//        comparator = Comparator.comparing(GuiButtonSpectrobePiece::getPieceSortingName);
//
//
//        visibleButtons.sort(comparator);
//
////        for (int i = 0; i < visibleButtons.size(); i++) {
////            int c = i;
////
////            GuiButtonSpectrobePiece piece = visibleButtons.get(i);
////            GuiButtonSpectrobePiece buttonSpellPiece = (GuiButtonSpectrobePiece) parent.getButtons().stream().filter(el -> el.equals(piece)).findFirst().orElse(null);
////            buttonSpellPiece.x = x + 5 + c % 5 * 18;
////            buttonSpellPiece.y = y + 20 + c / 5 * 18;
////            buttonSpellPiece.visible = true;
////            buttonSpellPiece.active = true;
////        }
//    }
//
////    @Override
////    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
////        int flooredX = (int)mouseX / 32 - 1;
////        int flooredY = (int)mouseY / 32 - 1;
////        SpectrobesInfo.LOGGER.info("GOT HERE 1");
////        SpectrobesInfo.LOGGER.info("floored X:" + flooredX);
////        SpectrobesInfo.LOGGER.info("floored Y:" + flooredY);
////        if(AllSpectrobesList.exists(flooredX, flooredY)) {
////            if(allSpectrobesList.gridData[flooredX][flooredY].spectrobe != null && mouseButton == 0) {
////            visibleButtons.forEach(button -> {
////                SpectrobesInfo.LOGGER.info("GOT HERE 2");
////                if(button.piece != null && button.x == flooredX && button.y == flooredY) {
////                    button.onPress();
////                    SpectrobesInfo.LOGGER.info("GOT HERE 3");
////                }
////            });
////        }
////
////        }
////        allSpectrobesList.mouseClicked(mouseX,mouseY,mouseButton);
////        if (parent.cursorX != -1 && parent.cursorY != -1 && mouseButton == 1 && !panelEnabled) {
////            openPanel();
////            return true;
////        }
////
////        if (panelEnabled && (mouseX < x || mouseY < y || mouseX > x + width || mouseY > y + height)) {
////            closePanel();
////            return true;
////        }
////        return false;
////    }
//
//
//    public void closePanel() {
//        panelEnabled = false;
//        parent.getButtons().forEach(button -> {
//            if (button instanceof GuiButtonSpectrobePiece || button instanceof GuiButtonPage) {
//                button.visible = false;
//                button.active = false;
//            }
//        });
//        parent.changeFocus(true);
//    }
//
//    public void openPanel() {
////        closePanel();
//        panelEnabled = true;
//        //page = Math.min(page, Math.max(0, getPageCount() - 1));
//        x = parent.gridLeft + (PrizmodMenu.selectedX + 1) * 32;
//        y = parent.gridTop;
//        parent.getButtons().forEach(button -> {
//            if (button instanceof GuiButtonSpectrobePiece || button instanceof GuiButtonPage) {
//                button.visible = true;
//                button.active = true;
//            }
//        });
//        parent.changeFocus(true);
//        populatePanelButtons();
////        updatePanelButtons();
//    }
//}
