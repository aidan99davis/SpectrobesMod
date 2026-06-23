package com.spectrobes.spectrobesmod.client.gui.cyrus_shop;

import com.spectrobes.spectrobesmod.common.items.minerals.IWorthGura;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;

public class BuySellItemWidget implements Renderable, GuiEventListener {
    private static final int SUCCESS_COLOUR = 0xFF55FF55;
    private static final int FAILURE_COLOUR = 0xFFFF5555;

    private final MineralPreviewSlot itemPreview;
    private final ShopButton buyItemButton;
    private final ShopButton sellItemButton;

    private boolean focused;

    public BuySellItemWidget(CyrusShopScreen parent, int x, int y, int width, int height, Item mineralItem) {
        IWorthGura worthGura = (IWorthGura) mineralItem;

        this.itemPreview = new MineralPreviewSlot(mineralItem, parent.getMenu(), x, y);

        this.buyItemButton = new ShopButton(
                worthGura.getGuraWorth(),
                true,
                x,
                y + 32,
                width,
                10,
                Component.empty(),
                button -> {
                    if (!parent.getMenu().buyMineral(worthGura)) {
                        this.setFontColour(true, FAILURE_COLOUR);
                    } else {
                        this.setFontColour(true, SUCCESS_COLOUR);
                    }
                }
        );

        this.sellItemButton = new ShopButton(
                worthGura.getGuraWorth() / 3,
                false,
                x,
                y + 42,
                width,
                10,
                Component.empty(),
                button -> {
                    if (!parent.getMenu().sellMineral(mineralItem)) {
                        this.setFontColour(false, FAILURE_COLOUR);
                    } else {
                        this.setFontColour(false, SUCCESS_COLOUR);
                    }
                }
        );
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.buyItemButton.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }

        if (this.sellItemButton.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }

        return false;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.sellItemButton.render(guiGraphics, mouseX, mouseY, partialTick);
        this.buyItemButton.render(guiGraphics, mouseX, mouseY, partialTick);
        this.itemPreview.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return this.sellItemButton.isMouseOver(mouseX, mouseY)
                || this.buyItemButton.isMouseOver(mouseX, mouseY)
                || this.itemPreview.isMouseOver(mouseX, mouseY);
    }

    public void setFontColour(boolean isBuyButton, int fontColour) {
        if (isBuyButton) {
            this.buyItemButton.setFontColour(fontColour);
        } else {
            this.sellItemButton.setFontColour(fontColour);
        }
    }

    @Override
    public boolean isFocused() {
        return focused;
    }

    @Override
    public void setFocused(boolean focused) {
        this.focused = focused;
    }
}