package com.spectrobes.spectrobesmod.client.gui.prizmod;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Pages.LineUpPage;
import com.spectrobes.spectrobesmod.client.gui.utils.GuiUtils;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Pages.PrizmodPage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class PrizmodScreen extends ContainerScreen<PrizmodContainer> {
    public static final ResourceLocation texture = new ResourceLocation("spectrobesmod:textures/gui/prizmod_background.png");
    public static final ResourceLocation SPECTROBE_SLOT_TEXTURE = new ResourceLocation("spectrobesmod:textures/gui/spectrobe_slot.png");
    public static final ResourceLocation SPECTROBE_SLOT_SELECTED_TEXTURE = new ResourceLocation("spectrobesmod:textures/gui/spectrobe_slot_selected.png");
    public static final ResourceLocation SPECTROBE_SLOT_CURRENT_TEXTURE = new ResourceLocation("spectrobesmod:textures/gui/spectrobe_slot_current.png");

    public PlayerEntity player;
    public int pageX = imageWidth / 3;
    public int pageY = (int) (imageHeight * 0.65);
    private PrizmodPage prizmodPage;

    public PrizmodScreen(PrizmodContainer container, PlayerInventory playerInv, ITextComponent text) {
        super(container, playerInv, text);
        this.player = playerInv.player;
        this.imageWidth = Minecraft.getInstance().getWindow().getScreenWidth();
        this.imageHeight = Minecraft.getInstance().getWindow().getScreenHeight();
    }

    @Override
    public void resize(Minecraft mc, int p_resize_2_, int p_resize_3_) {
        super.resize(mc, p_resize_2_, p_resize_3_);
        this.imageWidth = mc.getWindow().getScreenWidth();
        this.imageHeight = mc.getWindow().getScreenHeight();
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
        this.addButton(this.prizmodPage);
    }


    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY,partialTicks);
        this.prizmodPage.render(stack, mouseX,mouseY,partialTicks);
    }

    @Override
    protected void renderLabels(MatrixStack p_230451_1_, int p_230451_2_, int p_230451_3_) {
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
    protected void renderBg(MatrixStack stack, float partialTicks, int mouseX, int mouseY) {
        getMinecraft().getTextureManager().bind(texture);

        GuiUtils.blit(0, 0,0,0,0,
                (width),
                (height),
                height, width);
    }

    public void setMenuPage(PrizmodPage prizmodPage) {
        this.buttons.clear();
        this.prizmodPage = prizmodPage;
        this.prizmodPage.init();
        this.prizmodPage.changeFocus(true);
        this.addButton(this.prizmodPage);
    }

    @Override public void tick() {
        this.prizmodPage.tick();
        this.getMenu().tick();
    }

    public void addButtons(List<Widget> buttonList) {
        buttonList.forEach(b -> {
            b.visible = true;
            b.active = true;
            this.addButton(b);
        });
    }

    public void removeButtons(List<Widget> buttonList) {
        buttonList.forEach(b -> {
            b.visible = false;
            b.active = false;
            this.buttons.remove(b);
            this.children.remove(b);
        });
    }
}
