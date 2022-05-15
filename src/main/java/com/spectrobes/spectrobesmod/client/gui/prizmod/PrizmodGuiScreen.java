//package com.spectrobes.spectrobesmod.client.gui.prizmod;
//
//import com.mojang.blaze3d.matrix.MatrixStack;
//import com.mojang.blaze3d.systems.RenderSystem;
//import com.mojang.datafixers.util.Pair;
//import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
//import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
//import net.minecraft.client.gui.screen.inventory.ContainerScreen;
//import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.text.ITextComponent;
//
//import javax.annotation.Nullable;
//
//public class PrizmodGuiScreen extends ContainerScreen<PrizmodContainer> {
//    public static final ResourceLocation texture = new ResourceLocation("spectrobesmod:textures/gui/prizmod_background.png");
//    /** Holds the slot currently hovered */
//    @Nullable
//    protected SpectrobeSlot hoveredSlot;
//    /** Used when touchscreen is enabled */
//    @Nullable
//    private SpectrobeSlot clickedSlot;
//    @Nullable
//    private SpectrobeSlot snapbackEnd;
//    @Nullable
//    private SpectrobeSlot quickdropSlot;
//    @Nullable
//    private SpectrobeSlot lastClickSlot;
//
//    private Spectrobe draggingSpectrobe = null;
//
//    public PrizmodGuiScreen(PrizmodContainer container, PlayerInventory playerInv, ITextComponent title) {
//        super(container, playerInv, title);
//    }
//
//    @Override
//    protected void renderBg(MatrixStack pMatrixStack, float pPartialTicks, int pX, int pY) {
//        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
//        this.minecraft.getTextureManager().bind(texture);
//        int i = (this.width - this.imageWidth) / 2;
//        int j = (this.height - this.imageHeight) / 2;
//        this.blit(pMatrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
//    }
//
//    @Override
//    public void render(MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
//        int i = this.leftPos;
//        int j = this.topPos;
//        this.renderBg(pMatrixStack, pPartialTicks, pMouseX, pMouseY);
//        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiContainerEvent.DrawBackground(this, pMatrixStack, pMouseX, pMouseY));
//        RenderSystem.disableRescaleNormal();
//        RenderSystem.disableDepthTest();
//
//        //since we're not calling super.render indirectly via
//        // ContainerScreen level we need to add what it does.
//        for(int index = 0; index < this.buttons.size(); ++index) {
//            this.buttons.get(index).render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);
//        }
//
//        //refactor everything after this into a Page object's render
//        // method so we can swap it out using state pattern.
//
//        RenderSystem.pushMatrix();
//        RenderSystem.translatef((float)i, (float)j, 0.0F);
//        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
//        RenderSystem.enableRescaleNormal();
//        this.hoveredSlot = null;
//        int k = 240;
//        int l = 240;
//        RenderSystem.glMultiTexCoord2f(33986, 240.0F, 240.0F);
//        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
//
//        //render slots.
//        for(int i1 = 0; i1 < this.menu.getOwnedSpectrobesCount(); ++i1) {
//
//            SpectrobeSlot slot = this.menu.teamSlots.get(i1);
//            this.renderSlot(pMatrixStack, slot);
////
////            if (this.isHovering(slot, (double)pMouseX, (double)pMouseY) && slot.isActive()) {}
////                this.hoveredSlot = slot;
////                RenderSystem.disableDepthTest();
////                int j1 = slot.x;
////                int k1 = slot.y;
////                RenderSystem.colorMask(true, true, true, false);
////                int slotColor = this.getSlotColor(i1);
////                this.fillGradient(pMatrixStack, j1, k1, j1 + 16, k1 + 16, slotColor, slotColor);
////                RenderSystem.colorMask(true, true, true, true);
////                RenderSystem.enableDepthTest();
////            }
//        }
//
////        this.renderLabels(pMatrixStack, pMouseX, pMouseY);
//        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiContainerEvent.DrawForeground(this, pMatrixStack, pMouseX, pMouseY));
////        PlayerInventory playerinventory = this.minecraft.player.inventory;
////        ItemStack itemstack = this.draggingItem.isEmpty() ? playerinventory.getCarried() : this.draggingItem;
////        if (!itemstack.isEmpty()) {
////            int j2 = 8;
////            int k2 = this.draggingItem.isEmpty() ? 8 : 16;
////            String s = null;
////            if (!this.draggingItem.isEmpty() && this.isSplittingStack) {
////                itemstack = itemstack.copy();
////                itemstack.setCount(MathHelper.ceil((float)itemstack.getCount() / 2.0F));
////            } else if (this.isQuickCrafting && this.quickCraftSlots.size() > 1) {
////                itemstack = itemstack.copy();
////                itemstack.setCount(this.quickCraftingRemainder);
////                if (itemstack.isEmpty()) {
////                    s = "" + TextFormatting.YELLOW + "0";
////                }
////            }
////
////            this.renderFloatingItem(itemstack, pMouseX - i - 8, pMouseY - j - k2, s);
////        }
//
////        if (!this.snapbackItem.isEmpty()) {
////            float f = (float)(Util.getMillis() - this.snapbackTime) / 100.0F;
////            if (f >= 1.0F) {
////                f = 1.0F;
////                this.snapbackItem = ItemStack.EMPTY;
////            }
////
////            int l2 = this.snapbackEnd.x - this.snapbackStartX;
////            int i3 = this.snapbackEnd.y - this.snapbackStartY;
////            int l1 = this.snapbackStartX + (int)((float)l2 * f);
////            int i2 = this.snapbackStartY + (int)((float)i3 * f);
////            this.renderFloatingItem(this.snapbackItem, l1, i2, (String)null);
////        }
//
//        RenderSystem.popMatrix();
//        RenderSystem.enableDepthTest();
//    }
//
//    private void renderSlot(MatrixStack pPoseStack, SpectrobeSlot pSlot) {
//        int i = pSlot.x;
//        int j = pSlot.y;
//        Spectrobe spectrobe = pSlot.getSpectrobe();
//        boolean flag = false;
//        boolean flag1 = pSlot == this.clickedSlot && !(this.draggingSpectrobe == null);
//        ItemStack itemstack1 = this.minecraft.player.inventory.getCarried();
//        String s = null;
//
//        this.setBlitOffset(100);
//        this.itemRenderer.blitOffset = 100.0F;
//        if ((spectrobe == null) && pSlot.isActive()) {
//            Pair<ResourceLocation, ResourceLocation> pair = pSlot.getNoItemIcon();
//            if (pair != null) {
//                TextureAtlasSprite textureatlassprite = this.minecraft.getTextureAtlas(pair.getFirst()).apply(pair.getSecond());
//                this.minecraft.getTextureManager().bind(textureatlassprite.atlas().location());
//                blit(pPoseStack, i, j, this.getBlitOffset(), 16, 16, textureatlassprite);
//                flag1 = true;
//            }
//        }
//
//        if (!flag1) {
//            if (flag) {
//                fill(pPoseStack, i, j, i + 16, j + 16, -2130706433);
//            }
//
//            RenderSystem.enableDepthTest();
////            this.itemRenderer.renderAndDecorateItem(this.minecraft.player, spectrobe, i, j);
////            this.itemRenderer.renderGuiItemDecorations(this.font, spectrobe, i, j, s);
//        }
//
//        this.itemRenderer.blitOffset = 0.0F;
//        this.setBlitOffset(0);
//    }
//}
