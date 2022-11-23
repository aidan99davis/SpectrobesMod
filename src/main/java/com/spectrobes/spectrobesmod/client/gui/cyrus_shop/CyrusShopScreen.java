package com.spectrobes.spectrobesmod.client.gui.cyrus_shop;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.container.CyrusShopContainer;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CyrusShopScreen extends AbstractContainerScreen<CyrusShopContainer> {
    public static final ResourceLocation background_texture = new ResourceLocation("spectrobesmod:textures/gui/cyrus_shop_background.png");
    ShopScrollList shopScrollList;

    public CyrusShopScreen(CyrusShopContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.imageHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
    }

    @Override
    protected void init() {
        shopScrollList = addWidget(
                new ShopScrollList(this,
                                width / 5,
                                height / 8,
                                    32 * 9,
                        (height / 5) * 4,
                                    Component.literal("Minerals")));

        List<Item> mineralItems = new ArrayList<>();
        SpectrobesMineralsRegistry.all_minerals.values().forEach(mineralItems::addAll);
        List<Item> listSorted = mineralItems.stream().sorted((o1, o2) -> {
            if (((MineralItem)o1).getGuraWorth() > ((MineralItem)o2).getGuraWorth()) {
                return 1;
            } else if (((MineralItem)o1).getGuraWorth() < ((MineralItem)o2).getGuraWorth()){
                return -1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList());
        listSorted.forEach(mineral -> shopScrollList.addMineralToSell(mineral));

        shopScrollList.addMineralToSell(SpectrobesMineralsRegistry.chroma_mineral_item_zero.get());
        shopScrollList.addMineralToSell(SpectrobesMineralsRegistry.chroma_mineral_item_one.get());
        shopScrollList.addMineralToSell(SpectrobesMineralsRegistry.chroma_mineral_item_two.get());
        shopScrollList.changeFocus(true);
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        shopScrollList.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    public void resize(Minecraft pMinecraft, int pWidth, int pHeight) {
        super.resize(pMinecraft, pWidth, pHeight);
        this.imageWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.imageHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
    }

    @Override
    protected void renderBg(PoseStack pMatrixStack, float pPartialTicks, int pX, int pY) {
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, background_texture);
        blit(pMatrixStack, 0, 0, 0, 0, 600, 400, imageWidth, imageHeight);
    }

    @Override
    protected boolean isHovering(int pX, int pY, int pWidth, int pHeight, double pMouseX, double pMouseY) {
        return super.isHovering(pX, pY, pWidth, pHeight, pMouseX, pMouseY);
    }

    @Override
    public void render(PoseStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        super.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
        drawCenteredString(pMatrixStack, font, "Gura Balance: " + getMenu().getGuraBalance(), this.width / 2, height / 11, 10526880);
        shopScrollList.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
    }
}
