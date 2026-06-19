package com.spectrobes.spectrobesmod.client.gui.cyrus_shop;

import com.spectrobes.spectrobesmod.client.container.CyrusShopContainer;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
import com.spectrobes.spectrobesmod.common.items.minerals.chroma.ChromaMineralItem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class MineralPreviewSlot implements Renderable, GuiEventListener {
    private static final ResourceLocation BACKGROUND_TEXTURE = PrizmodScreen.SPECTROBE_SLOT_TEXTURE;

    private final CyrusShopContainer container;
    private final Item mineralItem;
    private final int x;
    private final int y;
    private boolean focused;

    public MineralPreviewSlot(Item mineralItem, CyrusShopContainer container, int x, int y) {
        this.container = container;
        this.mineralItem = mineralItem;
        this.x = x;
        this.y = y;
    }

    /**
     * Helper function to get the item in this preview slot.
     */
    public Item getItem() {
        return this.mineralItem;
    }

    public CyrusShopContainer getContainer() {
        return this.container;
    }

    /**
     * Only used when we want to render the white square effect over the slots.
     */
    public boolean isActive() {
        return true;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (!isActive()) {
            return;
        }

        guiGraphics.blit(BACKGROUND_TEXTURE, this.x, this.y, 0, 0, 32, 32, 32, 32);

        ResourceLocation mineralTexture = getMineralTexture();

        if (mineralTexture != null) {
            guiGraphics.blit(mineralTexture, this.x, this.y, 0, 0, 32, 32, 32, 32);
        }
    }

    private ResourceLocation getMineralTexture() {
        if (this.mineralItem instanceof MineralItem mineral) {
            return ResourceLocation.fromNamespaceAndPath(
                    "spectrobesmod",
                    "textures/items/" + mineral.mineral.name + ".png"
            );
        }

        if (this.mineralItem instanceof ChromaMineralItem chromaMineral) {
            return switch (chromaMineral.getVariantNumber()) {
                case 1 -> ResourceLocation.fromNamespaceAndPath(
                        "spectrobesmod",
                        "textures/items/chroma_mineral_item_one.png"
                );
                case 2 -> ResourceLocation.fromNamespaceAndPath(
                        "spectrobesmod",
                        "textures/items/chroma_mineral_item_two.png"
                );
                default -> ResourceLocation.fromNamespaceAndPath(
                        "spectrobesmod",
                        "textures/items/chroma_mineral_item_zero.png"
                );
            };
        }

        return null;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= this.x
                && mouseX < this.x + 32
                && mouseY >= this.y
                && mouseY < this.y + 32;
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