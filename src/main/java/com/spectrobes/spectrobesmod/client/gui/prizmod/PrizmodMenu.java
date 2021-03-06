//package com.spectrobes.spectrobesmod.client.gui.prizmod;
//
//import com.mojang.blaze3d.systems.RenderSystem;
//import com.spectrobes.spectrobesmod.SpectrobesInfo;
//import com.spectrobes.spectrobesmod.client.gui.prizmod.components.SpectrobePiece;
//import com.spectrobes.spectrobesmod.client.gui.utils.GuiUtils;
//import com.spectrobes.spectrobesmod.container.capability.PlayerProperties;
//import net.minecraft.client.gui.FontRenderer;
//import net.minecraft.client.gui.screen.Screen;
//import net.minecraft.client.gui.widget.Widget;
//import net.minecraft.client.gui.widget.button.Button;
//import net.minecraft.client.renderer.IRenderTypeBuffer;
//import net.minecraft.client.renderer.RenderState;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.Tessellator;
//import net.minecraft.client.resources.I18n;
//import net.minecraft.client.util.ITooltipFlag;
//import net.minecraft.client.util.InputMappings;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.text.ITextComponent;
//import net.minecraft.util.text.StringTextComponent;
//import net.minecraft.util.text.TextFormatting;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PrizmodMenu extends Screen {
//    public static final ResourceLocation texture = new ResourceLocation("spectrobesmod:textures/gui/prizmod_background.png");
//    public static final ResourceLocation SPECTROBE_SLOT_TEXTURE = new ResourceLocation("spectrobesmod:textures/gui/spectrobe_slot.png");
//
//    public final PlayerEntity player;
//    public List<ITextComponent> tooltip = new ArrayList<>();
//
//
//    public int xSize, ySize, padLeft, padTop, left, top, gridLeft, gridTop;
//    public int cursorX, cursorY;
//    public static int selectedX, selectedY;
//
//    public LineUpMenu panelWidget;
//    public ITooltipFlag tooltipFlag;
//
//
//    public PrizmodMenu(PlayerEntity player) {
//        super(new StringTextComponent(""));
//        this.player = player;
//    }
//
//    @Override
//    protected void init() {
//        xSize = 600;
//        ySize = 400;
//        padLeft = 7;
//        padTop = 7;
//        left = (width - xSize) / 2;
//        top = (height - ySize) / 2;
//        gridLeft = left + padLeft;
//        gridTop = top + padTop;
//        cursorX = cursorY = -1;
//        tooltipFlag = getMinecraft().gameSettings.advancedItemTooltips ? ITooltipFlag.TooltipFlags.ADVANCED : ITooltipFlag.TooltipFlags.NORMAL;
//
//        //statusWidget = addButton(new StatusWidget(left - 48, top + 5, 48, 30, ITextComponent.func_241827_a_(""), this));
//        panelWidget = addButton(new LineUpMenu(0, 0, 100, 125, "", this));
//        //helpButton = addButton(new GuiButtonHelp(left + xSize + 2, top + ySize - (spectator ? 32 : 48), this));
//        //configWidget = addButton(new SideConfigWidget(left - 81, top + 55, 81, 115, this));
//
//        panelWidget.populatePanelButtons();
//
////        onSelectedChanged();
//    }
//
//    @Override
//    public void tick() {
//        panelWidget.populatePanelButtons();
//    }
//
//    @Override
//    public void render(int mouseX, int mouseY, float partialTicks) {
//        super.render(mouseX, mouseY, partialTicks);
//        FontRenderer textRenderer = getMinecraft().fontRenderer;
//        if (player == null || (player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER) == null)) {
//            getMinecraft().displayGuiScreen(null);
//            return;
//        }
//
//        RenderSystem.pushMatrix();
//        renderBackground();
//
//        RenderSystem.color3f(1F, 1F, 1F);
//        getMinecraft().getTextureManager().bindTexture(texture);
//        //RenderSystem.translatef(0,0,-1);
//
//        GuiUtils.blit(left, top,16,0,0,
//                xSize,
//                ySize - top,
//                400, 600);
//        panelWidget.allSpectrobesList.draw();
//
//        cursorX = (mouseX - gridLeft) / 18;
//        cursorY = (mouseY - gridTop) / 18;
//
//        RenderSystem.pushMatrix();
//
//        tooltip.clear();
//
//        RenderSystem.translatef(gridLeft, gridTop, 0);
//
//        panelWidget.render(mouseX, mouseY, partialTicks);
//
//        RenderSystem.popMatrix();
//
//        RenderSystem.color3f(1f, 1f, 1f);
//        RenderSystem.translatef(0, 0, 1);
//        //getMinecraft().getTextureManager().bindTexture(texture);
//
////        if (selectedX != -1 && selectedY != -1) {
////            blit(gridLeft + selectedX * 18, gridTop + selectedY * 18, 32, ySize, 16, 16);
////        }
//
//        int topY = top - 22;
//        SpectrobePiece pieceAtCursor = null;
//        if (cursorX > -1 && cursorY > -1) {
//            String name;
//            if (panelWidget.allSpectrobesList.exists(cursorX, cursorY)) {
//                pieceAtCursor = panelWidget.allSpectrobesList.gridData[cursorX][cursorY];
////                if (pieceAtCursor.spectrobe != null) {
////                    RenderSystem.pushMatrix();
////                    name = pieceAtCursor.spectrobe.name;
////                    textRenderer.drawString(name, left + 4, topY + ySize + 24, 0x44FFFFFF);
////                    RenderSystem.popMatrix();
////                }
//            }
////            pieceAtCursor = panelWidget.allSpectrobesList.gridData[cursorX][cursorY];
////            if (pieceAtCursor != null) {
////                pieceAtCursor.getTooltip(tooltip);
////            }
//
////            if (cursorX == selectedX && cursorY == selectedY) {
////                blit(gridLeft + cursorX * 18, gridTop + cursorY * 18, 16, ySize, 8, 16);
////            } else {
////                blit(gridLeft + cursorX * 18, gridTop + cursorY * 18, 16, ySize, 16, 16);
////            }
//        }
//
//        textRenderer.drawStringWithShadow("Prizmod", left + padLeft, topY + 10, 1);
//
//        List<ITextComponent> legitTooltip = null;
//        if (hasAltDown()) {
//            legitTooltip = new ArrayList<>(tooltip);
//        }
//
//        if (hasAltDown()) {
//            tooltip = legitTooltip;
//        }
//
//        if (pieceAtCursor != null) {
//            if (tooltip != null && !tooltip.isEmpty()) {
//                pieceAtCursor.drawTooltip(mouseX, mouseY, tooltip, this);
//            }
//        }
//
//        RenderSystem.popMatrix();
//    }
//
//    public void removeButtons(List<Button> list) {
//        removeButtonList(list);
//    }
//
//    private void removeButtonList(List<Button> list) {
//        buttons.removeAll(list);
//        children.removeAll(list);
//    }
//
//    public void addButtons(List<Button> list) {
//        list.forEach(this::addButton);
//    }
//
//
//    public void onSpellChanged(boolean nameOnly) {
////        if (player != null) {
////            if (!spectator) {
////                MessageSpellModified message = new MessageSpellModified(programmer.getPos(), spectrobe);
////                MessageRegister.HANDLER.sendToServer(message);
////            }
////
////            programmer.onSpellChanged();
////        }
//
//        onSelectedChanged();
//
//    }
//
//    public void onSelectedChanged() {
////        buttons.removeAll(configWidget.configButtons);
////        children.removeAll(configWidget.configButtons);
////        configWidget.configButtons.clear();
////
////        if (selectedX != -1 && selectedY != -1) {
////            SpectrobePiece piece = spectrobe.grid.gridData[selectedX][selectedY];
////            if (piece != null) {
////
////                if (piece.hasConfig()) {
////                    int i = 0;
////                    for (String paramName : piece.params.keySet()) {
////                        SpellParam<?> param = piece.params.get(paramName);
////                        int x = left - 17;
////                        int y = top + 70 + i * 26;
////                        for (SpellParam.Side side : ImmutableSet.of(SpellParam.Side.TOP, SpellParam.Side.BOTTOM, SpellParam.Side.LEFT, SpellParam.Side.RIGHT, SpellParam.Side.OFF)) {
////
////
////                            int xp = x + side.offx * 8;
////                            int yp = y + side.offy * 8;
////                            configWidget.configButtons.add(new GuiButtonSideConfig(this, selectedX, selectedY, i, paramName, side, xp, yp, button -> {
////                                if (!spectator) {
////                                    GuiButtonSideConfig.performAction(this, selectedX, selectedY, paramName, side);
////                                    onSpellChanged(false);
////                                }
////                            }));
////                        }
////                        i++;
////                    }
////                    configWidget.configButtons.forEach(this::addButton);
////                    configWidget.configEnabled = true;
////                    return;
////                }
////            }
////        }
////        configWidget.configEnabled = false;
//    }
//
//    @Override
//    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
//        panelWidget.mouseClicked(mouseX,mouseY,mouseButton);
//        return super.mouseClicked(mouseX, mouseY, mouseButton);
//    }
//
//    //reuse for renaming spectrobes?
////    private void closeComment(boolean save) {
////        Spectrobe piece = null;
////        if (selectedX != -1 && selectedY != -1) {
////            piece = spectrobe.grid.gridData[selectedX][selectedY];
////        }
////
////        if (save && piece != null) {
////            String text = commentField.getText();
////            pushState(true);
////            piece.comment = text;
////            onSpellChanged(false);
////        }
////
////        spellNameField.setEnabled(!spectator && (piece == null || !piece.interceptKeystrokes()));
////        commentField.setFocused2(false);
////        commentField.setVisible(false);
////        commentField.setEnabled(false);
////        setFocusedDefault(null);
////        commentField.setText("");
////        commentEnabled = false;
////    }
//
//    @Override
//    public boolean shouldCloseOnEsc() {
//        return !panelWidget.panelEnabled;
//    }
//
//    public List<Widget> getButtons() {
//        return this.buttons;
//    }
//
//}
