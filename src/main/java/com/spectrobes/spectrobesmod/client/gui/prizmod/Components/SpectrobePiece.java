package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.gui.utils.GuiUtils;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeIconInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpectrobePiece extends AbstractWidget {

    private static ResourceLocation DELETE_BACKGROUND = new ResourceLocation("spectrobesmod:textures/gui/spectrobe_slot_delete.png");

    public Spectrobe spectrobe;
    private final int x, y;
    public int posX;
    public int posY;
    public boolean selected;
    public boolean current;

    public SpectrobePiece(Spectrobe spell, int x ,int y) {
        super(x, y, 32, 32, Component.empty());
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
        return getUnlocalizedName();
    }

    @OnlyIn(Dist.CLIENT)
    public void draw(PoseStack stack, boolean withAdditional) {
        drawBackground(stack);
        if(withAdditional)
            drawAdditional(stack);
    }

    /**
     * Draws this piece's background.
     */
    @OnlyIn(Dist.CLIENT)
    public void drawBackground(PoseStack stack) {
        ResourceLocation bg;
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);

        if(Screen.hasAltDown()) {
            bg = DELETE_BACKGROUND;
//            GuiUtils.drawTexture(, posX, posY, 32, 32, 28);
        } else if(!current) {
            bg = selected? PrizmodScreen.SPECTROBE_SLOT_SELECTED_TEXTURE : PrizmodScreen.SPECTROBE_SLOT_TEXTURE;
//            GuiUtils.drawTexture(bg, posX, posY, 32, 32,0);
        } else {
            bg = PrizmodScreen.SPECTROBE_SLOT_SELECTED_TEXTURE;
//            GuiUtils.drawTexture(bg, posX - 2, posY - 2, 36, 36,1);
        }
        RenderSystem.setShaderTexture(0, bg);

        blit(stack,
                posX,
                posY,
                0,
                0,
                32,
                32,
                32,
                32);

    }

    /**
     * Draws any additional stuff for this piece. Used for the spectrobes icon
     */
    @OnlyIn(Dist.CLIENT)
    public void drawAdditional(PoseStack stack) {
        if(spectrobe != null) {
            SpectrobeIconInfo iconInfo = spectrobe.getIcon();

            float scalex = 32f / iconInfo.getWidth();
            float scaley = 32f / iconInfo.getHeight();

            int marginleft = iconInfo.getWidth() < 31
                    ? ((32 - iconInfo.getWidth())/2)
                    : 0;
            int margintop = iconInfo.getHeight() < 31
                    ? ((32 - iconInfo.getHeight())/2)
                    : 0;

            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
            RenderSystem.setShaderTexture(0, iconInfo.icon());

            blit(stack,
                    posX + marginleft,
                    posY + margintop,
                    0,
                    0,
                    Float.valueOf(iconInfo.getWidth() * scalex).intValue(),
                    Float.valueOf(iconInfo.getHeight() * scaley).intValue(),
                    32,
                    32);

            //draw red health bar.
            GuiUtils.drawColour(245, 66, 66, 100, posX + 1, posY + 30, 30, 2, 27);

            //draw green for health bar, only fill a % of 30 pixels based on the % of health remaining.
            float widthScaled = ((float)spectrobe.currentHealth / (float)spectrobe.stats.getHpLevel()) * 30f;
            GuiUtils.drawColour(55, 179, 41, 100, posX + 1, posY + 30, Math.round(widthScaled), 2, 28);

            //draw indicator that you're deleting a spectrobe.


        }
    }

    @OnlyIn(Dist.CLIENT)
    public void drawAdditionalAtCursor(PoseStack stack, int mouseX, int mouseY) {
        if(spectrobe != null) {
            SpectrobeIconInfo iconInfo = spectrobe.getIcon();

            float scalex = 32f / iconInfo.getWidth();
            float scaley = 32f / iconInfo.getHeight();

            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
            RenderSystem.setShaderTexture(0, iconInfo.icon());

            blit(stack,
                    mouseX - (iconInfo.getWidth() / 2),
                    mouseY - (iconInfo.getHeight() / 2),
                    0,
                    0,
                    Float.valueOf(iconInfo.getWidth() * scalex).intValue(),
                    Float.valueOf(iconInfo.getHeight() * scaley).intValue(),
                    32,
                    32);
//            GuiUtils.drawTexture(iconInfo.icon(), mouseX, mouseY, iconInfo.getWidth() * scalex, iconInfo.getHeight() * scaley, 100);
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
