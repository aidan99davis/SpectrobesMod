package com.spectrobes.spectrobesmod.client.gui.cyrus_shop;

import com.spectrobes.spectrobesmod.client.container.CyrusShopContainer;
import com.spectrobes.spectrobesmod.common.items.minerals.IWorthGura;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CyrusShopScreen extends AbstractContainerScreen<CyrusShopContainer> {
    public static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            "spectrobesmod",
            "textures/gui/cyrus_shop_background.png"
    );

    private ShopScrollList shopScrollList;

    public CyrusShopScreen(CyrusShopContainer menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);

        Minecraft minecraft = Minecraft.getInstance();

        this.imageWidth = minecraft.getWindow().getGuiScaledWidth();
        this.imageHeight = minecraft.getWindow().getGuiScaledHeight();
    }

    @Override
    protected void init() {
        super.init();

        this.imageWidth = this.width;
        this.imageHeight = this.height;
        this.leftPos = 0;
        this.topPos = 0;

        this.shopScrollList = this.addRenderableWidget(
                new ShopScrollList(
                        this,
                        this.width / 5,
                        this.height / 8,
                        32 * 9,
                        (this.height / 5) * 4,
                        Component.literal("Minerals")
                )
        );

        List<Item> mineralItems = new ArrayList<>();
        SpectrobesMineralsRegistry.all_minerals.values().forEach(mineralItems::addAll);

        mineralItems.stream()
                .filter(item -> item instanceof IWorthGura)
                .sorted(Comparator.comparingInt(item -> ((IWorthGura) item).getGuraWorth()))
                .forEach(this.shopScrollList::addMineralToSell);

        this.shopScrollList.addMineralToSell(SpectrobesMineralsRegistry.chroma_mineral_item_zero.get());
        this.shopScrollList.addMineralToSell(SpectrobesMineralsRegistry.chroma_mineral_item_one.get());
        this.shopScrollList.addMineralToSell(SpectrobesMineralsRegistry.chroma_mineral_item_two.get());

        this.setFocused(this.shopScrollList);
        this.shopScrollList.setFocused(true);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (this.shopScrollList != null && this.shopScrollList.mouseDragged(mouseX, mouseY, button, dragX, dragY)) {
            return true;
        }

        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public void resize(Minecraft minecraft, int width, int height) {
        super.resize(minecraft, width, height);

        this.imageWidth = width;
        this.imageHeight = height;
        this.leftPos = 0;
        this.topPos = 0;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(
                BACKGROUND_TEXTURE,
                0,
                0,
                0,
                0,
                this.width,
                this.height,
                this.width,
                this.height
        );
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.drawCenteredString(
                this.font,
                "Gura Balance: " + getMenu().getGuraBalance(),
                this.width / 2,
                this.height / 11,
                0x00A0A0A0
        );

        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}