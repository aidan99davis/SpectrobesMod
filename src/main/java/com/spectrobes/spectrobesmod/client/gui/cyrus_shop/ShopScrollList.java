package com.spectrobes.spectrobesmod.client.gui.cyrus_shop;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractScrollWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ShopScrollList extends AbstractScrollWidget {
    private final List<BuySellItemWidget> mineralsToSell = new ArrayList<>();
    private final CyrusShopScreen parent;
    private final int columns = 9;

    public ShopScrollList(CyrusShopScreen parent, int x, int y, int width, int height, Component message) {
        super(x, y, width, height, message);
        this.parent = parent;
    }

    public void addMineralToSell(Item mineralItem) {
        int xPos = (getNextColumn() * 32) + getX();
        int yPos = (getNextRow() * 52) + getY();

        BuySellItemWidget buySellWidget = new BuySellItemWidget(parent, xPos, yPos, 32, 52, mineralItem);
        mineralsToSell.add(buySellWidget);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        double adjustedMouseY = mouseY + scrollAmount();

        for (BuySellItemWidget buySellItemWidget : mineralsToSell) {
            if (buySellItemWidget.mouseClicked(mouseX, adjustedMouseY, button)) {
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    protected int getNextRow() {
        return mineralsToSell.size() / columns;
    }

    protected int getNextColumn() {
        return mineralsToSell.size() % columns;
    }

    @Override
    protected int getInnerHeight() {
        int rows = (int) Math.ceil(mineralsToSell.size() / (double) columns);
        return Math.max(52, rows * 52);
    }

    @Override
    protected boolean scrollbarVisible() {
        return getInnerHeight() > getHeight();
    }

    @Override
    protected double scrollRate() {
        return 9.0D / 2.0D;
    }

    @Override
    protected void renderContents(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int adjustedMouseY = (int) (mouseY + scrollAmount());

        for (BuySellItemWidget buySellItemWidget : mineralsToSell) {
            buySellItemWidget.render(guiGraphics, mouseX, adjustedMouseY, partialTick);
        }
    }

    @Override
    public void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }
}