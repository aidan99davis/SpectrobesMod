package com.spectrobes.spectrobesmod.client.gui.cyrus_shop;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.container.CyrusShopContainer;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
import com.spectrobes.spectrobesmod.common.items.minerals.chroma.ChromaMineralItem;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class MineralPreviewSlot implements Widget {
    private final ResourceLocation backgroundTexture = PrizmodScreen.SPECTROBE_SLOT_TEXTURE;
    public final CyrusShopContainer container;
    private final Item mineralItem;
    public final int x;
    public final int y;

    public MineralPreviewSlot(Item mineralItem, CyrusShopContainer pContainer, int pX, int pY) {
        this.container = pContainer;
        this.mineralItem = mineralItem;
        this.x = pX;
        this.y = pY;
    }

    /**
     * Helper function to get the stack in the slot.
     */
    public Item getItem() {
        return this.mineralItem;
    }

    /**
     * Actually only call when we want to render the white square effect over the slots. Return always True, except for
     * the armor slot of the Donkey/Mule (we can't interact with the Undead and Skeleton horses)
     */
    public boolean isActive() {
        return true;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        if(isActive()) {
            RenderSystem.setShaderTexture(0, backgroundTexture);
            GuiComponent.blit(pPoseStack, x, y, 0, 0, 32, 32, 32, 32);

            if(mineralItem instanceof MineralItem mineral) {
                RenderSystem.setShaderTexture(0,
                        new ResourceLocation("spectrobesmod", "textures/items/" + mineral.mineral.name + ".png"));

            } else if(mineralItem instanceof ChromaMineralItem chromaMineral) {
                ResourceLocation mineralTexture;
                switch (chromaMineral.getVariantNumber()) {
                    case 1 -> mineralTexture = new ResourceLocation("spectrobesmod", "textures/items/chroma_mineral_item_one.png");
                    case 2 -> mineralTexture = new ResourceLocation("spectrobesmod", "textures/items/chroma_mineral_item_two.png");
                    default -> mineralTexture = new ResourceLocation("spectrobesmod", "textures/items/chroma_mineral_item_zero.png");
                }
                RenderSystem.setShaderTexture(0, mineralTexture);
            }
            GuiComponent.blit(pPoseStack, x, y, 0, 0, 32, 32, 32, 32);
        }
    }
}
