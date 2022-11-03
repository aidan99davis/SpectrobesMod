package com.spectrobes.spectrobesmod.client.gui.spectrobes_details;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.client.container.SpectrobeDetailsContainer;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.client.gui.utils.GuiUtils;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeIconInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class SpectrobeDetailsScreen extends ContainerScreen<SpectrobeDetailsContainer> {

    public SpectrobeDetailsScreen(SpectrobeDetailsContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = Minecraft.getInstance().getWindow().getScreenWidth();
        this.imageHeight = Minecraft.getInstance().getWindow().getScreenHeight();
    }

    @Override
    protected void renderBg(MatrixStack pMatrixStack, float pPartialTicks, int pX, int pY) {
        getMinecraft().getTextureManager().bind(PrizmodScreen.texture);

        GuiUtils.blit(0, 0,0,0,0,
                (width),
                (height),
                height, width);
    }

    @Override
    public void render(MatrixStack pMatrixStack, int pMouseX, int pMouseY, float pPartialTicks) {
        super.render(pMatrixStack, pMouseX, pMouseY, pPartialTicks);

        //draw spectrobe icon centre top.
        int iconWidth = 64;
        int iconX = width / 2 - (iconWidth / 2);
        int iconY = height / 5;
        GuiUtils.drawTexture(PrizmodScreen.SPECTROBE_SLOT_TEXTURE, iconX, iconY, iconWidth, iconWidth,0);

        //draw spectrobe into slot.
        if(menu.getSpectrobe() != null) {
            SpectrobeIconInfo iconInfo = menu.getSpectrobe().getIcon();
            float scalex = 64 / iconInfo.getWidth();
            float scaley = 64 / iconInfo.getHeight();
            int marginleft = iconInfo.getWidth() < 31
                    ? ((32 - iconInfo.getWidth())/2)
                    : 0;
            int margintop = iconInfo.getHeight() < 31
                    ? ((32 - iconInfo.getHeight())/2)
                    : 0;

            RenderSystem.enableAlphaTest();
            GuiUtils.drawTexture(iconInfo.icon(), iconX + marginleft, iconY + margintop, iconInfo.getWidth() * scalex, iconInfo.getHeight() * scaley, 26);
        }

        //draw hp, hp bar, lvl and xp bar
        drawHpBar(pMatrixStack);
        drawXpBar(pMatrixStack);
        drawNames(pMatrixStack);

        //draw stats.
        drawStats(pMatrixStack);

        //draw evolution requirements.
        drawEvolutionRequirements(pMatrixStack);
    }

    private void drawEvolutionRequirements(MatrixStack pMatrixStack) {
        int x = (width / 2);
        int y = (height / 3) * 2;
        Spectrobe spec = menu.getSpectrobe();
        SpectrobeStats stats = spec.stats;
        String levelString = "Level: " + stats.getLevel() + "/" + spec.evolutionRequirements.getLevelRequired();
        String mineralsString = "Minerals: " + stats.getMineralsEaten() + "/" + spec.evolutionRequirements.getMineralsRequired();
        String battlesString = "Battles: " + stats.getBattlesWon() + "/" + spec.evolutionRequirements.getBattlesRequired();

        Minecraft mc = Minecraft.getInstance();

        mc.font.draw(pMatrixStack, "Evolution Requirements", x, y, Color.BLACK.hashCode());
        mc.font.draw(pMatrixStack, levelString, x, y + 18, Color.BLACK.hashCode());
        mc.font.draw(pMatrixStack, mineralsString, x, y + 27, Color.BLACK.hashCode());
        mc.font.draw(pMatrixStack, battlesString, x, y + 36, Color.BLACK.hashCode());
    }

    private void drawStats(MatrixStack pMatrixStack) {
        int x = width / 3;
        int y = (height / 3) * 2;

        Minecraft mc = Minecraft.getInstance();

        mc.font.draw(pMatrixStack, "Stats", x, y, Color.BLACK.hashCode());
        mc.font.draw(pMatrixStack, "HP: " + menu.getSpectrobe().stats.getHpLevel(), x, y + 18, Color.BLACK.hashCode());
        mc.font.draw(pMatrixStack, "Atk: " + menu.getSpectrobe().stats.getAtkLevel(), x, y + 27, Color.BLACK.hashCode());
        mc.font.draw(pMatrixStack, "Def: " + menu.getSpectrobe().stats.getDefLevel(), x, y + 36, Color.BLACK.hashCode());
    }

    private void drawNames(MatrixStack pMatrixStack) {
        Minecraft mc = Minecraft.getInstance();
        String speciesNameText = "Species: " + menu.getSpectrobe().name;
        String customNameText = "Custom: " + menu.getSpectrobe().custom_name;
        int xSpecies = width / 2 - (mc.font.width(speciesNameText) / 2);
        int xCustom = width / 2 - (mc.font.width(customNameText) / 2);

        int ySpecies = height / 2;
        int yCustom = height / 2 + mc.font.lineHeight;

        mc.font.draw(pMatrixStack, speciesNameText, xSpecies, ySpecies, Color.BLACK.hashCode());
        mc.font.draw(pMatrixStack, customNameText, xCustom, yCustom, Color.BLACK.hashCode());
    }

    private void drawHpBar(MatrixStack pMatrixStack) {
        int barWidth = 10;
        int barHeight = 32;
        Minecraft mc = Minecraft.getInstance();
        MainWindow res = mc.getWindow();
        int x = width / 2 - (barWidth) - 64;
        Spectrobe sm = menu.getSpectrobe();
        int y = (res.getGuiScaledHeight() / 5) + 16;// - (height / 2)) - bottomPadding;
        String healthText = sm.currentHealth + "/" + sm.stats.getHpLevel();
        int finalX = x + (Minecraft.getInstance().font.width(healthText) / 2) - (barWidth / 2);

        //draw red health bar.
        GuiUtils.drawColour(107, 0, 0, 100, finalX, y, barWidth, barHeight, 27);

        //draw green for health bar, only fill a % of 30 pixels based on the % of health remaining.
        float heightScaled = ((float)sm.currentHealth / (float)sm.stats.getHpLevel()) * barHeight;
        GuiUtils.drawColour(33, 252, 13, 100, finalX, y + (barHeight-Math.round(heightScaled)), barWidth, Math.round(heightScaled), 28);
        mc.font.draw(pMatrixStack, "HP", finalX, y - 10, Color.BLACK.hashCode());
        mc.font.draw(pMatrixStack, healthText, x, y + barHeight + 10, Color.BLACK.hashCode());
        RenderSystem.disableAlphaTest();
    }

    private void drawXpBar(MatrixStack pMatrixStack) {
        int barWidth = 10;
        int barHeight = 32;
        Minecraft mc = Minecraft.getInstance();
        MainWindow res = mc.getWindow();
        int x = width / 2 + (barWidth / 2) + 32;
        Spectrobe sm = menu.getSpectrobe();
        int y = (res.getGuiScaledHeight() / 5) + 16;// - (height / 2)) - bottomPadding;
        String xpText = sm.stats.getXp() + "/" + sm.stats.getXp_required();
        int finalX = x + (Minecraft.getInstance().font.width(xpText) / 2) - (barWidth / 2);

        //draw red health bar.
        GuiUtils.drawColour(0, 128, 129, 100, finalX, y, barWidth, barHeight, 27);

        //draw green for health bar, only fill a % of 30 pixels based on the % of health remaining.
        float heightScaled = ((float)sm.stats.getXp() / (float)sm.stats.getXp_required()) * barHeight;
        GuiUtils.drawColour(0, 255, 255, 100, finalX, y + (barHeight-Math.round(heightScaled)), barWidth, Math.round(heightScaled), 28);
        mc.font.draw(pMatrixStack, "Lvl: " + sm.stats.getLevel(), finalX - (barWidth/2), y - 10, Color.BLACK.hashCode());
        mc.font.draw(pMatrixStack, xpText, x, y + barHeight + 10, Color.BLACK.hashCode());
        RenderSystem.disableAlphaTest();
    }

    @Override
    public void init(Minecraft pMinecraft, int pWidth, int pHeight) {
        super.init(pMinecraft, pWidth, pHeight);

    }
}
