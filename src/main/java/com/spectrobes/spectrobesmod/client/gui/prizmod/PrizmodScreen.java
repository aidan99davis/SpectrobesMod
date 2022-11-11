package com.spectrobes.spectrobesmod.client.gui.prizmod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Pages.LineUpPage;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Pages.PrizmodPage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class PrizmodScreen extends AbstractContainerScreen<PrizmodContainer> {
    public static final ResourceLocation texture = new ResourceLocation("spectrobesmod:textures/gui/prizmod_background.png");
    public static final ResourceLocation SPECTROBE_SLOT_TEXTURE = new ResourceLocation("spectrobesmod:textures/gui/spectrobe_slot.png");
    public static final ResourceLocation SPECTROBE_SLOT_SELECTED_TEXTURE = new ResourceLocation("spectrobesmod:textures/gui/spectrobe_slot_selected.png");

    public Player player;
    public int pageX = imageWidth / 3;
    public int pageY = (int) (imageHeight * 0.65);
    private PrizmodPage prizmodPage;

    public PrizmodScreen(PrizmodContainer container, Inventory playerInv, Component text) {
        super(container, playerInv, text);
        this.player = playerInv.player;
        this.imageWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        this.imageHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();
    }

    @Override
    public void resize(Minecraft mc, int p_resize_2_, int p_resize_3_) {
        super.resize(mc, p_resize_2_, p_resize_3_);
        this.imageWidth = mc.getWindow().getGuiScaledWidth();
        this.imageHeight = mc.getWindow().getGuiScaledHeight();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int partialTicks) {
        super.mouseClicked(mouseX, mouseY, partialTicks);
        return false;
    }

    @Override
    public void init() {
        this.changeFocus(true);
        this.prizmodPage = new LineUpPage(this);
        this.prizmodPage.init();
        this.prizmodPage.changeFocus(true);
        this.addRenderableWidget(this.prizmodPage);
    }


    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY,partialTicks);
        this.prizmodPage.render(stack, mouseX,mouseY,partialTicks);
    }

    @Override
    protected void renderLabels(PoseStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
//
//        this.font.draw(pMatrixStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
//        this.font.draw(pMatrixStack, this.inventory.getDisplayName(), (float)this.inventoryLabelX, (float)this.inventoryLabelY, 4210752);
    }

    /**
     * Draws the background layer of this container (behind the items).
     *
     * @param partialTicks
     * @param mouseX
     * @param mouseY
     */
    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, texture);
        blit(stack, 0, 0, 0, 0, 600, 400, imageWidth, imageHeight);
    }

    public void setMenuPage(PrizmodPage prizmodPage) {
        this.clearWidgets();
        this.prizmodPage = prizmodPage;
        this.prizmodPage.init();
        this.prizmodPage.changeFocus(true);
        this.addRenderableWidget(this.prizmodPage);
    }

    @Override
    public void containerTick() {
        this.prizmodPage.tick();
        this.getMenu().tick();
    }

    public void addButtons(List<AbstractWidget> buttonList) {
        buttonList.forEach(b -> {
            this.addRenderableWidget(b);
        });
    }

    public void removeButtons(List<AbstractWidget> buttonList) {
        buttonList.forEach(b -> {
            b.visible = false;
            b.active = false;
            this.removeWidget(b);
        });
    }
}
