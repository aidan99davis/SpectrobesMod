package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeIconInfo;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.util.Color;

public class SpectrobePiece extends AbstractWidget {

    private static final ResourceLocation DELETE_BACKGROUND
            = new ResourceLocation("spectrobesmod:textures/gui/spectrobe_slot_delete.png");

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
        } else if(!current) {
            bg = selected? PrizmodScreen.SPECTROBE_SLOT_SELECTED_TEXTURE : PrizmodScreen.SPECTROBE_SLOT_TEXTURE;
        } else {
            bg = PrizmodScreen.SPECTROBE_SLOT_SELECTED_TEXTURE;
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

            float scalex = iconInfo.getWidth() <= 32? 1 : 32f / iconInfo.getWidth();
            float scaley = iconInfo.getHeight() <= 32? 1 : 32f / iconInfo.getHeight();

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
                    Math.round(iconInfo.getWidth() * scalex),
                    Math.round(iconInfo.getHeight() * scaley),
                    Math.round(iconInfo.getWidth() * scalex),
                    Math.round(iconInfo.getHeight() * scaley));
            //draw red health bar.
            fill(stack, posX + 2, posY + 28, posX + 30, posY+30, Color.RED.hashCode());

            //draw green for health bar, only fill a % of 30 pixels based on the % of health remaining.
            float widthScaled = ((float)spectrobe.currentHealth / (float)spectrobe.stats.getHpLevel()) * 30f;
//            SpectrobesInfo.LOGGER.info("WIDTH: " + widthScaled);
            fill(stack, posX + 2, posY + 28, posX + Math.round(widthScaled), posY+30, Color.GREEN.hashCode());
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void drawAdditionalAtCursor(PoseStack stack, int mouseX, int mouseY) {
        if(spectrobe != null) {
            SpectrobeIconInfo iconInfo = spectrobe.getIcon();

            float scalex = iconInfo.getWidth() <= 32? 1 : 32f / iconInfo.getWidth();
            float scaley = iconInfo.getHeight() <= 32? 1 : 32f / iconInfo.getHeight();

            RenderSystem.enableBlend();
            RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
            RenderSystem.setShaderTexture(0, iconInfo.icon());

            blit(stack,
                    mouseX - (iconInfo.getWidth() / 2),
                    mouseY - (iconInfo.getHeight() / 2),
                    0,
                    0,
                    Math.round(iconInfo.getWidth() * scalex),
                    Math.round(iconInfo.getHeight() * scaley),
                    Math.round(iconInfo.getWidth() * scalex),
                    Math.round(iconInfo.getHeight() * scaley));
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {
    }
}
