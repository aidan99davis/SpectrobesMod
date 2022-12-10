package com.spectrobes.spectrobesmod.client.gui.cyrus_shop;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.common.items.minerals.IWorthGura;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import software.bernie.geckolib3.core.util.Color;

public class BuySellItemWidget extends GuiComponent implements Widget, GuiEventListener {
    private final MineralPreviewSlot ItemPreview;
    private final ShopButton BuyItemButton;
    private final ShopButton SellItemButton;

    public BuySellItemWidget(CyrusShopScreen parent, int pX, int pY, int pWidth, int pHeight, Item mineralItem) {
        super();
        ItemPreview = new MineralPreviewSlot(mineralItem, parent.getMenu(), pX, pY);

        BuyItemButton = new ShopButton(((IWorthGura)mineralItem).getGuraWorth(), true, pX, pY + 32, pWidth, 10,
                Component.empty(), pButton -> {
            if(!parent.getMenu().buyMineral((IWorthGura) mineralItem)) {
                this.setFontColour(true, Color.RED.hashCode());
            } else {
                this.setFontColour(true, Color.GREEN.hashCode());
            }
        });
        SellItemButton = new ShopButton((((IWorthGura)mineralItem).getGuraWorth() / 3), false, pX, pY + 42, pWidth, 10,
                Component.empty(), pButton -> {
            if(!parent.getMenu().sellMineral(mineralItem)) {
                this.setFontColour(false, Color.RED.hashCode());
            } else {
                this.setFontColour(false, Color.GREEN.hashCode());
            }
        });
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        BuyItemButton.mouseClicked(pMouseX, pMouseY, pButton);
        SellItemButton.mouseClicked(pMouseX, pMouseY, pButton);
        return GuiEventListener.super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
//        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        SellItemButton.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        BuyItemButton.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        ItemPreview.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public boolean isMouseOver(double pMouseX, double pMouseY) {
        SellItemButton.isMouseOver(pMouseX, pMouseY);
        BuyItemButton.isMouseOver(pMouseX, pMouseY);
        return GuiEventListener.super.isMouseOver(pMouseX, pMouseY);
    }

    public void setFontColour(boolean isBuyButton, int fontColour) {
        if(isBuyButton) {
            this.BuyItemButton.setFontColour(fontColour);
        } else {
            this.SellItemButton.setFontColour(fontColour);
        }
    }
}
