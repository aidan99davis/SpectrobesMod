package com.spectrobes.spectrobesmod.client.gui.prizmod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.client.gui.prizmod.components.*;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LineUpMenu extends Widget implements IRenderable, IGuiEventListener {

    public final PrizmodMenu parent;
    public boolean panelEnabled = false;
    public final List<Button> panelButtons = new ArrayList<>();
    public int panelCursor;
    public TextFieldWidget searchField;
    public AllSpectrobesList allSpectrobesList;
    public SpectrobesTeamList spectrobesTeamList;
    private SpectrobePiece childForm;
    public int page = 0;
    private static final int PIECES_PER_PAGE = 12;
    public final List<GuiButtonSpectrobePiece> visibleButtons = new ArrayList<>();

    public LineUpMenu(int xIn, int yIn, String msg, PrizmodMenu parent) {
        super(xIn, yIn, msg);
        this.parent = parent;
        allSpectrobesList = new AllSpectrobesList();
    }

    public LineUpMenu(int xIn, int yIn, int widthIn, int heightIn, String msg, PrizmodMenu parent) {
        super(xIn, yIn, widthIn, heightIn, msg);
        this.parent = parent;
        allSpectrobesList = new AllSpectrobesList();

    }

    @Override
    public void renderButton(int mouseX, int mouseY, float pTicks) {
        if (panelEnabled) {
            parent.getMinecraft().getTextureManager().bindTexture(PrizmodMenu.BACKGROUND_LOCATION);

            fill(x, y, x + width, y + height, 0x88000000);

            if (visibleButtons.size() > 0) {
                Button button = visibleButtons.get(Math.max(0, Math.min(panelCursor, visibleButtons.size() - 1)));
                int panelPieceX = button.x;
                int panelPieceY = button.y;
                fill(panelPieceX - 1, panelPieceY - 1, panelPieceX + 17, panelPieceY + 17, 0x559999FF);
            }

            RenderSystem.color3f(1f, 1f, 1f);
            blit(searchField.x - 14, searchField.y - 2, 0, parent.ySize + 16, 12, 12);

            String s = Math.min(Math.max(getPageCount(), 1), page + 1) + "/" + Math.max(getPageCount(), 1);
            parent.getMinecraft().fontRenderer.drawStringWithShadow(s, x + width / 2f - parent.getMinecraft().fontRenderer.getStringWidth(s) / 2f, y + height - 12, 0xFFFFFF);
        }
    }


    @Override
    public boolean mouseScrolled(double par1, double par2, double par3) {
        if (panelEnabled && par3 != 0) {
            int next = (int) (page - par3 / Math.abs(par3));
            if (next >= 0 && next < getPageCount()) {
                page = next;
                updatePanelButtons();
            }
        }
        return false;
    }

    @Override
    public boolean charTyped(char p_charTyped_1_, int p_charTyped_2_) {
        if (panelEnabled) {
            return searchField.charTyped(p_charTyped_1_, p_charTyped_2_);
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (panelEnabled) {
            switch (keyCode) {
                case GLFW.GLFW_KEY_ESCAPE:
                    closePanel();
                    return true;
                case GLFW.GLFW_KEY_ENTER:
                    if (visibleButtons.size() >= 1) {
                        visibleButtons.get(panelCursor).onPress();
                        return true;
                    }
                    return false;
                case GLFW.GLFW_KEY_TAB:
                    if (visibleButtons.size() >= 1) {
                        int newCursor = panelCursor + (Screen.hasAltDown() ? -1 : 1);
                        if (newCursor >= (Math.min(visibleButtons.size(), 25))) {
                            panelCursor = 0;

                            return true;
                        }

                        panelCursor = Math.max(0, Math.min(newCursor, (Math.min(visibleButtons.size(), 25)) - 1));
                        return true;
                    }
            }
        }
        return false;
    }

    public int getPageCount() {
        return (visibleButtons.size() / PIECES_PER_PAGE) + 1;
    }

    public void populatePanelButtons() {
        parent.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent((sm) -> {
            //populate the 6 team spectrobes pieces
            //populate the child spectrobe piece

            //populate the all spectrobes grid
            for (Spectrobe spectrobe : sm.getOwnedSpectrobes()) {

                SpectrobePiece piece = new SpectrobePiece(spectrobe);

                allSpectrobesList.addSpectrobe(piece);


                GuiButtonSpectrobePiece spellPieceButton = new GuiButtonSpectrobePiece(parent, piece, 0, 0, button -> {
                    ((GuiButtonSpectrobePiece) button).renderActions();
                    parent.onSpellChanged(false);
                    //closePanel();
                });
                spellPieceButton.visible = false;
                spellPieceButton.active = false;
                panelButtons.add(spellPieceButton);
                visibleButtons.add(spellPieceButton);

            }


            GuiButtonPage right = new GuiButtonPage(0, 0, true, parent, button -> {
                int max = getPageCount();
                int next = page + (((GuiButtonPage) button).right ? 1 : -1);

                if (next >= 0 && next < max) {
                    page = next;
                    updatePanelButtons();
                }
            });

            GuiButtonPage left = new GuiButtonPage(0, 0, false, parent, button -> {
                int max = getPageCount();
                int next = page + (((GuiButtonPage) button).right ? 1 : -1);

                if (next >= 0 && next < max) {
                    page = next;
                    updatePanelButtons();
                }
            });
            left.visible = false;
            left.active = false;
            right.visible = false;
            right.active = false;
            panelButtons.add(left);
            panelButtons.add(right);
            parent.addButtons(panelButtons);
        });

    }

    public void updatePanelButtons() {
        panelCursor = 0;
        visibleButtons.clear();
        parent.getButtons().forEach(button -> {
            if (button instanceof GuiButtonPage || button instanceof GuiButtonSpectrobePiece) {
                button.active = false;
                button.visible = false;
            }
        });

        parent.getButtons().forEach(button -> {
            if (button instanceof GuiButtonSpectrobePiece) {

                visibleButtons.add((GuiButtonSpectrobePiece) button);

            } else if (button instanceof GuiButtonPage) {
                GuiButtonPage page = (GuiButtonPage) button;
                if (page.isRight() && this.page < getPageCount() - 1) {
                    button.x = x + width - 22;
                    button.y = y + height - 15;
                    button.visible = true;
                    button.active = true;

                } else if (!page.isRight() && this.page > 0) {
                    button.x = x + 4;
                    button.y = y + height - 15;
                    button.visible = true;
                    button.active = true;
                }
            }
        });

        Comparator<GuiButtonSpectrobePiece> comparator;

        comparator = Comparator.comparing(GuiButtonSpectrobePiece::getPieceSortingName);


        visibleButtons.sort(comparator);

        int start = page * PIECES_PER_PAGE;
        for (int i = start; i < visibleButtons.size(); i++) {
            int c = i - start;
            if (c >= PIECES_PER_PAGE) {
                break;
            }

            GuiButtonSpectrobePiece piece = visibleButtons.get(i);
            GuiButtonSpectrobePiece buttonSpellPiece = (GuiButtonSpectrobePiece) parent.getButtons().stream().filter(el -> el.equals(piece)).findFirst().orElse(null);
            buttonSpellPiece.x = x + 5 + c % 5 * 18;
            buttonSpellPiece.y = y + 20 + c / 5 * 18;
            buttonSpellPiece.visible = true;
            buttonSpellPiece.active = true;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (parent.cursorX != -1 && parent.cursorY != -1 && !parent.commentEnabled && mouseButton == 1 && !panelEnabled) {
            openPanel();
            return true;
        }

        if (panelEnabled && (mouseX < x || mouseY < y || mouseX > x + width || mouseY > y + height)) {
            //closePanel();
            return true;
        }
        return false;
    }


    public void closePanel() {
        panelEnabled = false;
        parent.getButtons().forEach(button -> {
            if (button instanceof GuiButtonSpectrobePiece || button instanceof GuiButtonPage) {
                button.visible = false;
                button.active = false;
            }
        });
        parent.changeFocus(true);
    }

    public void openPanel() {
        closePanel();
        panelEnabled = true;
        page = Math.min(page, Math.max(0, getPageCount() - 1));
        x = parent.gridLeft + (PrizmodMenu.selectedX + 1) * 18;
        y = parent.gridTop;

//        searchField.x = x + 18;
//        searchField.y = y + 4;
//        searchField.setText("");
//        searchField.setVisible(true);
//        searchField.active = true;
//        searchField.setEnabled(true);
//        searchField.setFocused2(true);
//        parent.setFocused(searchField);
        updatePanelButtons();
    }
}
