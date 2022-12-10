package com.spectrobes.spectrobesmod.client.gui.cyrus_shop;

import com.mojang.blaze3d.vertex.PoseStack;
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

    public ShopScrollList(CyrusShopScreen parent, int pX, int pY, int pWidth, int pHeight, Component pMessage) {
        super(pX, pY, pWidth, pHeight, pMessage);
        this.parent = parent;
    }

    public void addMineralToSell(Item mineralItem) {
        int xPos = (getNextColumn() * 32) + x;
        int yPos = (getNextRow() * 52) + y;
        BuySellItemWidget buySellWidget =
                new BuySellItemWidget(parent, xPos, yPos, 32, 52, mineralItem);
        mineralsToSell.add(buySellWidget);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        mineralsToSell.forEach(buySellItemWidget -> buySellItemWidget.mouseClicked(pMouseX, pMouseY + scrollAmount(), pButton));
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean isMouseOver(double pMouseX, double pMouseY) {
        mineralsToSell.forEach(buySellItemWidget -> buySellItemWidget.isMouseOver(pMouseX, pMouseY + scrollAmount()));
        return super.isMouseOver(pMouseX, pMouseY);
    }

    protected int getNextRow() {
        int numMinerals = mineralsToSell.size();
        int rows = 0;
        while(numMinerals >= columns) {
            rows++;
            numMinerals = numMinerals - columns;
        }
        return rows;
    }

    protected int getNextColumn() {
        return mineralsToSell.size() % columns;
    }

    @Override
    protected int getInnerHeight() {
        return 52 * getNextRow() + 52;
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
    protected void renderContents(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        mineralsToSell.forEach(buySellItemWidget -> {
            buySellItemWidget.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
            buySellItemWidget.isMouseOver(pMouseX, pMouseY + scrollAmount());
        });
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
