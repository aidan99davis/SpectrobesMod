package com.spectrobes.spectrobesmod.client.gui.spectrobes_details;

import com.spectrobes.spectrobesmod.client.container.SpectrobeDetailsContainer;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.spectrobes.EvolutionRequirements;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeIconInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class SpectrobeDetailsScreen extends AbstractContainerScreen<SpectrobeDetailsContainer> {
    private static final int BLACK = 0xFF000000;
    private static final int RED = 0xFFFF0000;
    private static final int GREEN = 0xFF00FF00;
    private static final int BLUE = 0xFF0000FF;
    private static final int CYAN = 0xFF00FFFF;

    public SpectrobeDetailsScreen(SpectrobeDetailsContainer menu, Inventory playerInventory, Component title) {
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
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        guiGraphics.blit(
                PrizmodScreen.TEXTURE,
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
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // Intentionally blank: this screen draws its own labels in render().
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        Spectrobe spectrobe = this.menu.getSpectrobe();

        if (spectrobe == null) {
            guiGraphics.drawCenteredString(
                    this.font,
                    "No Spectrobe selected.",
                    this.width / 2,
                    this.height / 2,
                    BLACK
            );

            this.renderTooltip(guiGraphics, mouseX, mouseY);
            return;
        }

        drawSpectrobeIcon(guiGraphics, spectrobe);
        drawHpBar(guiGraphics, spectrobe);
        drawXpBar(guiGraphics, spectrobe);
        drawNames(guiGraphics, spectrobe);
        drawStats(guiGraphics, spectrobe);
        drawEvolutionRequirements(guiGraphics, spectrobe);

        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private void drawSpectrobeIcon(GuiGraphics guiGraphics, Spectrobe spectrobe) {
        int iconWidth = 64;
        int iconX = this.width / 2 - (iconWidth / 2);
        int iconY = this.height / 5;

        guiGraphics.blit(
                PrizmodScreen.SPECTROBE_SLOT_TEXTURE,
                iconX,
                iconY,
                0,
                0,
                64,
                64,
                64,
                64
        );

        SpectrobeIconInfo iconInfo = spectrobe.getIcon();

        int scaledWidth = Math.round(iconInfo.getWidth() * (64.0F / iconInfo.getWidth()));
        int scaledHeight = Math.round(iconInfo.getHeight() * (64.0F / iconInfo.getHeight()));

        guiGraphics.blit(
                iconInfo.icon(),
                iconX,
                iconY,
                0,
                0,
                scaledWidth,
                scaledHeight,
                scaledWidth,
                scaledHeight
        );
    }

    private void drawEvolutionRequirements(GuiGraphics guiGraphics, Spectrobe spectrobe) {
        int x = this.width / 2;
        int y = (this.height / 3) * 2;

        EvolutionRequirements requirements = spectrobe.evolutionRequirements;

        guiGraphics.drawString(this.font, "Evolution Requirements", x, y, BLACK);

        if (requirements == null) {
            guiGraphics.drawString(this.font, "None", x, y + 18, BLACK);
            return;
        }

        SpectrobeStats stats = spectrobe.stats;

        String levelString = "Level: " + stats.getLevel() + "/" + requirements.getLevelRequired();
        String mineralsString = "Minerals: " + stats.getMineralsEaten() + "/" + requirements.getMineralsRequired();
        String battlesString = "Battles: " + stats.getBattlesWon() + "/" + requirements.getBattlesRequired();

        guiGraphics.drawString(this.font, levelString, x, y + 18, BLACK);
        guiGraphics.drawString(this.font, mineralsString, x, y + 27, BLACK);
        guiGraphics.drawString(this.font, battlesString, x, y + 36, BLACK);
    }

    private void drawStats(GuiGraphics guiGraphics, Spectrobe spectrobe) {
        int x = this.width / 3;
        int y = (this.height / 3) * 2;

        guiGraphics.drawString(this.font, "Stats", x, y, BLACK);
        guiGraphics.drawString(this.font, "HP: " + spectrobe.stats.getHpLevel(), x, y + 18, BLACK);
        guiGraphics.drawString(this.font, "Atk: " + spectrobe.stats.getAtkLevel(), x, y + 27, BLACK);
        guiGraphics.drawString(this.font, "Def: " + spectrobe.stats.getDefLevel(), x, y + 36, BLACK);
    }

    private void drawNames(GuiGraphics guiGraphics, Spectrobe spectrobe) {
        String speciesNameText = "Species: " + spectrobe.name;
        String customNameText = "Custom: " + spectrobe.custom_name;

        int xSpecies = this.width / 2 - (this.font.width(speciesNameText) / 2);
        int xCustom = this.width / 2 - (this.font.width(customNameText) / 2);

        int ySpecies = this.height / 2;
        int yCustom = this.height / 2 + this.font.lineHeight;

        guiGraphics.drawString(this.font, speciesNameText, xSpecies, ySpecies, BLACK);
        guiGraphics.drawString(this.font, customNameText, xCustom, yCustom, BLACK);
    }

    private void drawHpBar(GuiGraphics guiGraphics, Spectrobe spectrobe) {
        int barWidth = 10;
        int barHeight = 32;

        int x = this.width / 2 - barWidth - 64;
        int y = (this.height / 5) + 16;

        String healthText = spectrobe.currentHealth + "/" + spectrobe.stats.getHpLevel();
        int finalX = x + (this.font.width(healthText) / 2) - (barWidth / 2);

        guiGraphics.fill(finalX, y, finalX + barWidth, y + barHeight, RED);

        float healthPercent = spectrobe.stats.getHpLevel() <= 0
                ? 0.0F
                : (float) spectrobe.currentHealth / (float) spectrobe.stats.getHpLevel();

        int heightScaled = Math.round(Math.max(0.0F, Math.min(1.0F, healthPercent)) * barHeight);

        guiGraphics.fill(
                finalX,
                y + (barHeight - heightScaled),
                finalX + barWidth,
                y + barHeight,
                GREEN
        );

        guiGraphics.drawString(this.font, "HP", finalX, y - 10, BLACK);
        guiGraphics.drawString(this.font, healthText, x, y + barHeight + 10, BLACK);
    }

    private void drawXpBar(GuiGraphics guiGraphics, Spectrobe spectrobe) {
        int barWidth = 10;
        int barHeight = 32;

        int x = this.width / 2 + (barWidth / 2) + 32;
        int y = (this.height / 5) + 16;

        String xpText = spectrobe.stats.getXp() + "/" + spectrobe.stats.getXp_required();
        int finalX = x + (this.font.width(xpText) / 2) - (barWidth / 2);

        guiGraphics.fill(finalX, y, finalX + barWidth, y + barHeight, BLUE);

        float xpPercent = spectrobe.stats.getXp_required() <= 0
                ? 0.0F
                : (float) spectrobe.stats.getXp() / (float) spectrobe.stats.getXp_required();

        int heightScaled = Math.round(Math.max(0.0F, Math.min(1.0F, xpPercent)) * barHeight);

        guiGraphics.fill(
                finalX,
                y + (barHeight - heightScaled),
                finalX + barWidth,
                y + barHeight,
                CYAN
        );

        guiGraphics.drawString(this.font, "Lvl: " + spectrobe.stats.getLevel(), finalX - (barWidth / 2), y - 10, BLACK);
        guiGraphics.drawString(this.font, xpText, x, y + barHeight + 10, BLACK);
    }
}