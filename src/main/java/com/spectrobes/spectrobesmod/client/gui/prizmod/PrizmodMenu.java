package com.spectrobes.spectrobesmod.client.gui.prizmod;

import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.gui.prizmod.components.SpectrobePiece;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class PrizmodMenu extends Screen {
    public static final ResourceLocation texture = new ResourceLocation(SpectrobesInfo.MOD_ID, "");
    public static final ResourceLocation SPECTROBE_SLOT_TEXTURE = new ResourceLocation(SpectrobesInfo.MOD_ID, "");

    static {
        RenderState.TransparencyState translucent = ObfuscationReflectionHelper.getPrivateValue(RenderState.class, null, "field_228515_g_");
        RenderType.State glState = RenderType.State.getBuilder()
                .texture(new RenderState.TextureState(texture, false, false))
                .lightmap(new RenderState.LightmapState(true))
                .cull(new RenderState.CullState(false))
                .alpha(new RenderState.AlphaState(0.004F))
                .transparency(translucent)
                .build(false);
    }

    public final PlayerEntity player;
    public List<ITextComponent> tooltip = new ArrayList<>();


    public int xSize, ySize, padLeft, padTop, left, top, gridLeft, gridTop;
    public int cursorX, cursorY;
    public static int selectedX, selectedY;
    public boolean commentEnabled;

    //public GuiButtonHelp helpButton;
    //public TextFieldWidget spellNameField;
    //public TextFieldWidget commentField;
    public LineUpMenu panelWidget;
    public ITooltipFlag tooltipFlag;

    public boolean takingScreenshot = false;
    public boolean shareToReddit = false;
    boolean spectator;

    public PrizmodMenu(PlayerEntity player) {
        super(new StringTextComponent(""));
        this.player = player;
    }

    @Override
    protected void init() {
        xSize = 174;
        ySize = 184;
        padLeft = 7;
        padTop = 7;
        left = (width - xSize) / 2;
        top = (height - ySize) / 2;
        gridLeft = left + padLeft;
        gridTop = top + padTop;
        cursorX = cursorY = -1;
        tooltipFlag = getMinecraft().gameSettings.advancedItemTooltips ? ITooltipFlag.TooltipFlags.ADVANCED : ITooltipFlag.TooltipFlags.NORMAL;


        //statusWidget = addButton(new StatusWidget(left - 48, top + 5, 48, 30, ITextComponent.func_241827_a_(""), this));
        panelWidget = addButton(new LineUpMenu(0, 0, 100, 125, "", this));
        //helpButton = addButton(new GuiButtonHelp(left + xSize + 2, top + ySize - (spectator ? 32 : 48), this));
        //configWidget = addButton(new SideConfigWidget(left - 81, top + 55, 81, 115, this));

//        spellNameField = addButton(new CallbackTextFieldWidget(textRenderer, left + xSize - 130, top + ySize - 14, 120, 10, button -> {
//            spell.name = spellNameField.getText();
//            onSpellChanged(true);
//        }));
//        spellNameField.setEnableBackgroundDrawing(false);
//        spellNameField.setMaxStringLength(20);
//        spellNameField.setEnabled(!spectator);

//        commentField = addButton(new CallbackTextFieldWidget(textRenderer, left, top + ySize / 2 - 10, xSize, 20, button -> {
//
//        }));
//        commentField.setEnabled(false);
//        commentField.setVisible(false);
//        commentField.setMaxStringLength(500);

        //TODO: [AD] - Search for spectrobes by species and/or custom name?
//        panelWidget.searchField = addButton(new CallbackTextFieldWidget(textRenderer, 0, 0, 70, 10, button -> {
//            panelWidget.page = 0;
//            panelWidget.updatePanelButtons();
//        }));

        panelWidget.searchField.setEnabled(false);
        panelWidget.searchField.setVisible(false);
        panelWidget.searchField.setEnableBackgroundDrawing(false);

        //spellNameField.setText(player.getDisplayName().getFormattedText());

        panelWidget.populatePanelButtons();

        onSelectedChanged();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        if (player != null && (player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER) != null)) {
            getMinecraft().displayGuiScreen(null);
            return;
        }

        RenderSystem.pushMatrix();
        renderBackground();

        RenderSystem.color3f(1F, 1F, 1F);
        getMinecraft().getTextureManager().bindTexture(texture);

        blit(left, top, 0, 0, xSize, ySize);

        //Currently selected piece
        SpectrobePiece piece = null;
        if (SpellGrid.exists(selectedX, selectedY)) {
            piece = spell.grid.gridData[selectedX][selectedY];
        }

        cursorX = (mouseX - gridLeft) / 18;
        cursorY = (mouseY - gridTop) / 18;
        if (panelWidget.panelEnabled || cursorX > 8 || cursorY > 8 || cursorX < 0 || cursorY < 0 || mouseX < gridLeft || mouseY < gridTop) {
            cursorX = -1;
            cursorY = -1;
        }

        RenderSystem.pushMatrix();
        tooltip.clear();
        RenderSystem.translatef(gridLeft, gridTop, 0);
        IRenderTypeBuffer.Impl buffers = IRenderTypeBuffer.immediate(Tessellator.getInstance().getBuffer());
        spell.draw(buffers, 0xF000F0);
        buffers.draw();

        RenderSystem.popMatrix();
        RenderSystem.color3f(1f, 1f, 1f);
        RenderSystem.translatef(0, 0, 1);
        getMinecraft().getTextureManager().bindTexture(texture);

        if (selectedX != -1 && selectedY != -1 && !takingScreenshot) {
            blit(gridLeft + selectedX * 18, gridTop + selectedY * 18, 32, ySize, 16, 16);
        }

        if (hasAltDown()) {
            tooltip.clear();
            cursorX = selectedX;
            cursorY = selectedY;
            mouseX = gridLeft + cursorX * 18 + 10;
            mouseY = gridTop + cursorY * 18 + 8;
        }

        SpectrobePiece pieceAtCursor = null;
        if (cursorX != -1 && cursorY != -1) {
            pieceAtCursor = spell.grid.gridData[cursorX][cursorY];
            if (pieceAtCursor != null) {
                pieceAtCursor.getTooltip(tooltip);
            }

            if (!takingScreenshot) {
                if (cursorX == selectedX && cursorY == selectedY) {
                    blit(gridLeft + cursorX * 18, gridTop + cursorY * 18, 16, ySize, 8, 16);
                } else {
                    blit(gridLeft + cursorX * 18, gridTop + cursorY * 18, 16, ySize, 16, 16);
                }
            }
        }

        int topY = top - 22;

        if (!takingScreenshot) {
            int topYText = topY;
            if (spectator) {
                String spectator = TextFormatting.RED + I18n.format("psimisc.spectator");
                textRenderer.drawWithShadow(spectator, left + xSize / 2f - textRenderer.getStringWidth(spectator) / 2f, topYText, 0xFFFFFF);
                topYText -= 10;
            }
            if (piece != null) {
                String pieceName = I18n.format(piece.getUnlocalizedName());
                textRenderer.drawWithShadow(pieceName, left + xSize / 2f - textRenderer.getStringWidth(pieceName) / 2f, topYText, 0xFFFFFF);
                topYText -= 10;
            }
            if (LibMisc.BETA_TESTING) {
                String betaTest = TextFormatting.GOLD + I18n.format("psimisc.wip");
                textRenderer.drawWithShadow(betaTest, left + xSize / 2f - textRenderer.getStringWidth(betaTest) / 2f, topYText, 0xFFFFFF);

            }

            String coords;
            if (SpellGrid.exists(cursorX, cursorY)) {
                coords = I18n.format("psimisc.programmer_coords", selectedX + 1, selectedY + 1, cursorX + 1, cursorY + 1);
            } else {
                coords = I18n.format("psimisc.programmer_coords_no_cursor", selectedX + 1, selectedY + 1);
            }
            textRenderer.draw(coords, left + 4, topY + ySize + 24, 0x44FFFFFF);
        }

        textRenderer.drawWithShadow(I18n.format("psimisc.name"), left + padLeft, spellNameField.y + 1, color);

        List<ITextComponent> legitTooltip = null;
        if (hasAltDown()) {
            legitTooltip = new ArrayList<>(tooltip);
        }

        if (hasAltDown()) {
            tooltip = legitTooltip;
        }

        super.render(mouseX, mouseY, partialTicks);

        if (!takingScreenshot && tooltip != null && !tooltip.isEmpty() && pieceAtCursor == null) {
            GuiUtils.drawHoveringText(tooltip, mouseX, mouseY, width, height, -1, textRenderer);

        }
        if (!takingScreenshot && pieceAtCursor != null) {
            if (tooltip != null && !tooltip.isEmpty()) {
                pieceAtCursor.drawTooltip(mouseX, mouseY, tooltip, this);
            }
        }

        RenderSystem.popMatrix();
    }

    public void removeButtons(List<Button> list) {
        removeButtonList(list);
    }

    private void removeButtonList(List<Button> list) {
        buttons.removeAll(list);
        children.removeAll(list);
    }

    public void addButtons(List<Button> list) {
        list.forEach(this::addButton);
    }


    public void onSpellChanged(boolean nameOnly) {
        if (player != null) {
            if (!spectator) {
                MessageSpellModified message = new MessageSpellModified(programmer.getPos(), spell);
                MessageRegister.HANDLER.sendToServer(message);
            }

            programmer.onSpellChanged();
        }

        onSelectedChanged();

        if (!nameOnly || compiler != null && compiler.getError() != null && compiler.getError().equals(SpellCompilationException.NO_NAME) || spell.name.isEmpty()) {
            compiler = new SpellCompiler(spell);
        }
    }

    public void onSelectedChanged() {
        buttons.removeAll(configWidget.configButtons);
        children.removeAll(configWidget.configButtons);
        configWidget.configButtons.clear();

        if (selectedX != -1 && selectedY != -1) {
            SpectrobePiece piece = spell.grid.gridData[selectedX][selectedY];
            if (piece != null) {

                if (piece.hasConfig()) {
                    int i = 0;
                    for (String paramName : piece.params.keySet()) {
                        SpellParam<?> param = piece.params.get(paramName);
                        int x = left - 17;
                        int y = top + 70 + i * 26;
                        for (SpellParam.Side side : ImmutableSet.of(SpellParam.Side.TOP, SpellParam.Side.BOTTOM, SpellParam.Side.LEFT, SpellParam.Side.RIGHT, SpellParam.Side.OFF)) {
                            if (!side.isEnabled() && !param.canDisable) {
                                continue;
                            }

                            int xp = x + side.offx * 8;
                            int yp = y + side.offy * 8;
                            configWidget.configButtons.add(new GuiButtonSideConfig(this, selectedX, selectedY, i, paramName, side, xp, yp, button -> {
                                if (!spectator) {
                                    pushState(true);
                                    GuiButtonSideConfig.performAction(this, selectedX, selectedY, paramName, side);
                                    onSpellChanged(false);
                                }
                            }));
                        }
                        i++;
                    }
                    configWidget.configButtons.forEach(this::addButton);
                    configWidget.configEnabled = true;
                    return;
                }
            }
        }
        configWidget.configEnabled = false;
    }

    @Override
    public boolean charTyped(char character, int keyCode) {
        if (player != null) {
            spell = programmer.spell;
        }
        if (spectator) {
            return false;
        }
        super.charTyped(character, keyCode);
        if (!commentEnabled && !spellNameField.isFocused()) {
            SpectrobePiece piece;
            if (selectedX != -1 && selectedY != -1) {
                piece = spell.grid.gridData[selectedX][selectedY];
                if (piece != null && piece.interceptKeystrokes()) {
                    if (piece.onCharTyped(character, keyCode, false)) {
                        pushState(true);
                        piece.onCharTyped(character, keyCode, true);
                        onSpellChanged(false);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        getMinecraft().keyboardListener.enableRepeatEvents(true);
        if (programmer != null) {
            spell = programmer.spell;
        }
        if (keyCode == GLFW.GLFW_KEY_ESCAPE && shouldCloseOnEsc()) {
            this.onClose();
            return true;
        }
        if (spectator) {
            return true;
        }

        if (commentEnabled) {
            switch (keyCode) {
                case GLFW.GLFW_KEY_ENTER:
                    closeComment(true);
                    return true;
                case GLFW.GLFW_KEY_ESCAPE:
                    closeComment(false);
                    return true;
            }
        }
        SpectrobePiece piece = null;
        if (selectedX != -1 && selectedY != -1) {
            piece = spell.grid.gridData[selectedX][selectedY];
            if (piece != null && piece.interceptKeystrokes()) {
                if (piece.onKeyPressed(keyCode, scanCode, false)) {
                    pushState(true);
                    piece.onKeyPressed(keyCode, scanCode, true);
                    onSpellChanged(false);
                    return true;
                }
            }
        }
        if (spellNameField.isFocused() && keyCode == GLFW.GLFW_KEY_TAB) {
            spellNameField.setFocused2(false);
            setFocusedDefault(null);
            return true;
        }
        if (!spellNameField.isFocused() && !panelWidget.panelEnabled && !commentEnabled) {
            int param = -1;
            for (int i = 0; i < 4; i++) {
                if (InputMappings.isKeyDown(getMinecraft().getMainWindow().getHandle(), GLFW.GLFW_KEY_1 + i)) {
                    param = i;
                }
            }
            switch (keyCode) {
                case GLFW.GLFW_KEY_DELETE:
                case GLFW.GLFW_KEY_BACKSPACE:
                    if (hasControlDown() && hasShiftDown()) {
                        if (!spell.grid.isEmpty()) {
                            pushState(true);
                            spell = new Spell();
                            spellNameField.setText("");
                            onSpellChanged(false);
                            return true;
                        }
                    }
                    if (piece != null) {
                        pushState(true);
                        spell.grid.gridData[selectedX][selectedY] = null;
                        onSpellChanged(false);
                        return true;
                    }
                    break;
                case GLFW.GLFW_KEY_TAB:
                    spellNameField.setFocused2(!spellNameField.isFocused());
                    setFocusedDefault(spellNameField);
                    return true;
                case GLFW.GLFW_KEY_UP:
                    if (hasControlDown()) {
                        if (hasShiftDown()) {
                            pushState(true);
                            spell.grid.mirrorVertical();
                            onSpellChanged(false);
                            return true;
                        } else if (spell.grid.shift(SpellParam.Side.TOP, false)) {
                            pushState(true);
                            spell.grid.shift(SpellParam.Side.TOP, true);
                            onSpellChanged(false);
                            return true;
                        }
                    } else {
                        if (!onSideButtonKeybind(piece, param, SpellParam.Side.TOP) && selectedY > 0) {
                            selectedY--;
                            onSelectedChanged();
                            return true;
                        }
                    }
                    break;
                case GLFW.GLFW_KEY_LEFT:
                    if (hasControlDown()) {
                        if (hasShiftDown()) {
                            pushState(true);
                            spell.grid.rotate(false);
                            onSpellChanged(false);
                            return true;
                        } else if (spell.grid.shift(SpellParam.Side.LEFT, false)) {
                            pushState(true);
                            spell.grid.shift(SpellParam.Side.LEFT, true);
                            onSpellChanged(false);
                            return true;
                        }
                    } else {
                        if (!onSideButtonKeybind(piece, param, SpellParam.Side.LEFT) && selectedX > 0) {
                            selectedX--;
                            onSelectedChanged();
                            return true;
                        }
                    }
                    break;
                case GLFW.GLFW_KEY_RIGHT:
                    if (hasControlDown()) {
                        if (hasShiftDown()) {
                            pushState(true);
                            spell.grid.rotate(true);
                            onSpellChanged(false);
                            return true;
                        } else if (spell.grid.shift(SpellParam.Side.RIGHT, false)) {
                            pushState(true);
                            spell.grid.shift(SpellParam.Side.RIGHT, true);
                            onSpellChanged(false);
                            return true;
                        }
                    } else {
                        if (!onSideButtonKeybind(piece, param, SpellParam.Side.RIGHT) && selectedX < SpellGrid.GRID_SIZE - 1) {
                            selectedX++;
                            onSelectedChanged();
                            return true;
                        }
                    }
                    break;
                case GLFW.GLFW_KEY_DOWN:
                    if (hasControlDown()) {
                        if (hasShiftDown()) {
                            pushState(true);
                            spell.grid.mirrorVertical();
                            onSpellChanged(false);
                            return true;
                        } else if (spell.grid.shift(SpellParam.Side.BOTTOM, false)) {
                            pushState(true);
                            spell.grid.shift(SpellParam.Side.BOTTOM, true);
                            onSpellChanged(false);
                            return true;
                        }
                    } else {
                        if (!onSideButtonKeybind(piece, param, SpellParam.Side.BOTTOM) && selectedY < SpellGrid.GRID_SIZE - 1) {
                            selectedY++;
                            onSelectedChanged();
                            return true;
                        }
                    }
                    break;
                case GLFW.GLFW_KEY_D:
                    if (piece != null && hasControlDown()) {
                        commentField.setVisible(true);
                        commentField.setFocused2(true);
                        commentField.setEnabled(true);
                        spellNameField.setEnabled(false);
                        commentField.setText(piece.comment);
                        commentField.setFocused2(true);
                        setFocusedDefault(commentField);
                        commentEnabled = true;
                        return true;
                    }
                    break;
                case GLFW.GLFW_KEY_G:
                    if (hasControlDown()) {
                        shareToReddit = false;
                        if (hasShiftDown() && hasAltDown()) {
                            takingScreenshot = true;
                        }
                        return true;
                    }
                    break;
                case GLFW.GLFW_KEY_R:
                    if (hasControlDown()) {
                        shareToReddit = true;
                        if (hasShiftDown() && hasAltDown()) {
                            takingScreenshot = true;
                        }
                        return true;
                    }
                    break;
                case GLFW.GLFW_KEY_ENTER:
                    panelWidget.openPanel();
                    return true;
            }
        }
        if (panelWidget.panelEnabled) {
            panelWidget.keyPressed(keyCode, scanCode, modifiers);
        }
        if (commentField.isFocused()) {
            commentField.keyPressed(keyCode, scanCode, modifiers);
        }
        if (spellNameField.isFocused()) {
            spellNameField.keyPressed(keyCode, scanCode, modifiers);
        }
        return false;
    }
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (programmer != null) {
            spell = programmer.spell;
        }

        if (!commentEnabled) {
            spellNameField.mouseClicked(mouseX, mouseY, mouseButton);
            if (commentField.getVisible()) {
                commentField.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public boolean isSpectator() {
        return spectator;
    }

    //reuse for renaming spectrobes?
//    private void closeComment(boolean save) {
//        Spectrobe piece = null;
//        if (selectedX != -1 && selectedY != -1) {
//            piece = spell.grid.gridData[selectedX][selectedY];
//        }
//
//        if (save && piece != null) {
//            String text = commentField.getText();
//            pushState(true);
//            piece.comment = text;
//            onSpellChanged(false);
//        }
//
//        spellNameField.setEnabled(!spectator && (piece == null || !piece.interceptKeystrokes()));
//        commentField.setFocused2(false);
//        commentField.setVisible(false);
//        commentField.setEnabled(false);
//        setFocusedDefault(null);
//        commentField.setText("");
//        commentEnabled = false;
//    }

    @Override
    public boolean shouldCloseOnEsc() {
        return !panelWidget.panelEnabled && !commentEnabled;
    }

    public List<Widget> getButtons() {
        return this.buttons;
    }

}
