package com.spectrobes.spectrobesmod.client.gui.prizmod.Pages;

import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class PrizmodPage extends AbstractWidget {

    protected final PrizmodScreen parent;
    protected PlayerSpectrobeMaster playerData;

    private final List<AbstractWidget> children = new ArrayList<>();

    protected PrizmodPage(PrizmodScreen parent) {
        super(parent.pageX, parent.pageY, parent.width, parent.height, Component.empty());
        this.parent = parent;
    }

    public PrizmodScreen getParentScreen() {
        return parent;
    }

    public List<AbstractWidget> getButtons() {
        return Collections.unmodifiableList(children);
    }

    public <T extends AbstractWidget> T addButton(T widget) {
        children.add(widget);
        return widget;
    }

    public void addButtons(List<? extends AbstractWidget> widgets) {
        children.addAll(widgets);
    }

    protected void clearButtons() {
        children.clear();
    }

    /**
     * Called when the page is assigned to the screen.
     *
     * Concrete pages should build their child tree here. The child widgets are
     * owned by the page, not registered directly with the Minecraft screen.
     */
    public void init() {
        setX(parent.pageX);
        setY(parent.pageY);
        setWidth(parent.width);
        setHeight(parent.height);
    }

    public abstract void tick();

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        for (AbstractWidget child : children) {
            child.render(guiGraphics, mouseX, mouseY, partialTick);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (int i = children.size() - 1; i >= 0; i--) {
            AbstractWidget child = children.get(i);

            if (child.visible && child.active && child.mouseClicked(mouseX, mouseY, button)) {
                setFocused(true);
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (int i = children.size() - 1; i >= 0; i--) {
            AbstractWidget child = children.get(i);

            if (child.visible && child.mouseReleased(mouseX, mouseY, button)) {
                return true;
            }
        }

        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        for (int i = children.size() - 1; i >= 0; i--) {
            AbstractWidget child = children.get(i);

            if (child.visible && child.mouseDragged(mouseX, mouseY, button, dragX, dragY)) {
                return true;
            }
        }

        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        for (int i = children.size() - 1; i >= 0; i--) {
            AbstractWidget child = children.get(i);

            if (child.visible && child.active && child.keyPressed(keyCode, scanCode, modifiers)) {
                return true;
            }
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        for (int i = children.size() - 1; i >= 0; i--) {
            AbstractWidget child = children.get(i);

            if (child.visible && child.active && child.charTyped(codePoint, modifiers)) {
                return true;
            }
        }

        return super.charTyped(codePoint, modifiers);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        defaultButtonNarrationText(narrationElementOutput);
    }
}
