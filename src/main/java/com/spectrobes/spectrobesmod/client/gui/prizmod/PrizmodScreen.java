package com.spectrobes.spectrobesmod.client.gui.prizmod;

import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Pages.LineUpPage;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Pages.PrizmodPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class PrizmodScreen extends AbstractContainerScreen<PrizmodContainer> {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            "spectrobesmod",
            "textures/gui/prizmod_background.png"
    );

    public static final ResourceLocation SPECTROBE_SLOT_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            "spectrobesmod",
            "textures/gui/spectrobe_slot.png"
    );

    public static final ResourceLocation SPECTROBE_SLOT_SELECTED_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            "spectrobesmod",
            "textures/gui/spectrobe_slot_selected.png"
    );

    public final Player player;

    public int pageX;
    public int pageY;

    private PrizmodPage prizmodPage;

    public PrizmodScreen(PrizmodContainer container, Inventory playerInventory, Component title) {
        super(container, playerInventory, title);

        this.player = playerInventory.player;

        Minecraft minecraft = Minecraft.getInstance();
        this.imageWidth = minecraft.getWindow().getGuiScaledWidth();
        this.imageHeight = minecraft.getWindow().getGuiScaledHeight();

        updatePagePosition();
    }

    @Override
    protected void init() {
        super.init();

        this.imageWidth = this.width;
        this.imageHeight = this.height;
        this.leftPos = 0;
        this.topPos = 0;

        updatePagePosition();

        this.prizmodPage = new LineUpPage(this);
        this.prizmodPage.init();

        this.addRenderableWidget(this.prizmodPage);
        this.setFocused(this.prizmodPage);
        this.prizmodPage.setFocused(true);
    }

    @Override
    public void resize(Minecraft minecraft, int width, int height) {
        super.resize(minecraft, width, height);

        this.imageWidth = width;
        this.imageHeight = height;
        this.leftPos = 0;
        this.topPos = 0;

        updatePagePosition();
    }

    private void updatePagePosition() {
        this.pageX = this.imageWidth / 3;
        this.pageY = (int) (this.imageHeight * 0.65D);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        guiGraphics.drawCenteredString(
                this.font,
                "Gura Balance: " + getMenu().getGuraBalance(),
                this.width / 2,
                this.height / 10,
                0x00A0A0A0
        );

        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // Intentionally blank: this screen draws its own labels in render().
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        guiGraphics.blit(
                TEXTURE,
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

    public void setMenuPage(PrizmodPage prizmodPage) {
        this.clearWidgets();

        this.prizmodPage = prizmodPage;
        this.prizmodPage.init();

        this.addRenderableWidget(this.prizmodPage);
        this.setFocused(this.prizmodPage);
        this.prizmodPage.setFocused(true);
    }

    @Override
    protected void containerTick() {
        super.containerTick();

        if (this.prizmodPage != null) {
            this.prizmodPage.tick();
        }

        this.getMenu().tick();
    }

    public void addButtons(List<AbstractWidget> buttonList) {
        buttonList.forEach(this::addRenderableWidget);
    }

    public void removeButtons(List<AbstractWidget> buttonList) {
        buttonList.forEach(button -> {
            button.visible = false;
            button.active = false;
            this.removeWidget(button);
        });
    }
}