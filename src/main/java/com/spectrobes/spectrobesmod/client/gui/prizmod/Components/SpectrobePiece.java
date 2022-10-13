package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.gui.utils.GuiUtils;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeIconInfo;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpectrobePiece extends AbstractGui {

    private static ResourceLocation DELETE_BACKGROUND = new ResourceLocation("spectrobesmod:textures/gui/spectrobe_slot_delete.png");

    public Spectrobe spectrobe;
    private final int x, y;
    public int posX;
    public int posY;
    public boolean selected;
    public boolean current;

    public SpectrobePiece(Spectrobe spell, int x ,int y) {
        this.spectrobe = spell;
        this.x = x;
        this.y = y;
        this.posX = (x+3) * 32;
        this.posY = (y+2) * 32;
        selected = false;
        current = false;
    }

    public void toggleCurrent() {
        current = !current;
    }

    public String getUnlocalizedName() {
        return SpectrobesInfo.MOD_ID + ".spectrobe." + spectrobe.name;
    }

    public String getSortingName() {
        return new TranslationTextComponent(getUnlocalizedName()).getString();
    }

    @OnlyIn(Dist.CLIENT)
    public void draw(boolean withAdditional) {
        drawBackground();
        if(withAdditional)
            drawAdditional();
    }

    /**
     * Draws this piece's background.
     */
    @OnlyIn(Dist.CLIENT)
    public void drawBackground() {
        ResourceLocation bg;
        if(Screen.hasAltDown()) {
            GuiUtils.drawTexture(DELETE_BACKGROUND, posX, posY, 32, 32, 28);
        } else if(!current) {
            bg = selected? PrizmodScreen.SPECTROBE_SLOT_SELECTED_TEXTURE : PrizmodScreen.SPECTROBE_SLOT_TEXTURE;
            GuiUtils.drawTexture(bg, posX, posY, 32, 32,0);
        } else {
            bg = PrizmodScreen.SPECTROBE_SLOT_SELECTED_TEXTURE;
            GuiUtils.drawTexture(bg, posX - 2, posY - 2, 36, 36,1);
        }

    }

    /**
     * Draws any additional stuff for this piece. Used for the spectrobes icon
     */
    @OnlyIn(Dist.CLIENT)
    public void drawAdditional() {
        if(spectrobe != null) {
            SpectrobeIconInfo iconInfo = spectrobe.getIcon();

            float scalex = 32 / iconInfo.getWidth();
            float scaley = 32 / iconInfo.getHeight();

            int marginleft = iconInfo.getWidth() < 31
                    ? ((32 - iconInfo.getWidth())/2)
                    : 0;
            int margintop = iconInfo.getHeight() < 31
                    ? ((32 - iconInfo.getHeight())/2)
                    : 0;

            RenderSystem.enableAlphaTest();
            GuiUtils.drawTexture(iconInfo.icon(), posX + marginleft, posY + margintop, iconInfo.getWidth() * scalex, iconInfo.getHeight() * scaley, 26);

            //draw red health bar.
            GuiUtils.drawColour(245, 66, 66, 100, posX + 1, posY + 30, 30, 2, 27);

            //draw green for health bar, only fill a % of 30 pixels based on the % of health remaining.
            float widthScaled = ((float)spectrobe.currentHealth / (float)spectrobe.stats.getHpLevel()) * 30f;
            GuiUtils.drawColour(55, 179, 41, 100, posX + 1, posY + 30, Math.round(widthScaled), 2, 28);

            //draw indicator that you're deleting a spectrobe.


        }
    }

    @OnlyIn(Dist.CLIENT)
    public void drawAdditionalAtCursor(int mouseX, int mouseY) {
        if(spectrobe != null) {
            SpectrobeIconInfo iconInfo = spectrobe.getIcon();

            float scalex = 32 / iconInfo.getWidth();
            float scaley = 32 / iconInfo.getHeight();

            RenderSystem.enableAlphaTest();
            GuiUtils.drawTexture(iconInfo.icon(), mouseX, mouseY, iconInfo.getWidth() * scalex, iconInfo.getHeight() * scaley, 100);
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
