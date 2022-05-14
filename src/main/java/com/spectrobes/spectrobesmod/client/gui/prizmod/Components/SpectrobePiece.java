package com.spectrobes.spectrobesmod.client.prizmod.Components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.gui.utils.GuiUtils;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeIconInfo;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import java.util.List;

import com.spectrobes.spectrobesmod.client.prizmod.Components.SpectrobePiece;

public class SpectrobePiece extends AbstractGui {

    private static ResourceLocation CROSS = new ResourceLocation("spectrobesmod:textures/gui/cross.png");

    public Spectrobe spectrobe;
    private int x, y;
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
    public void draw() {
        drawBackground();
        drawAdditional();
    }

    /**
     * Draws this piece's background.
     */
    @OnlyIn(Dist.CLIENT)
    public void drawBackground() {
        ResourceLocation bg;
        if(!current) {
            bg = selected? PrizmodScreen.SPECTROBE_SLOT_SELECTED_TEXTURE : PrizmodScreen.SPECTROBE_SLOT_TEXTURE;
        } else {
            bg = PrizmodScreen.SPECTROBE_SLOT_CURRENT_TEXTURE;
        }

        GuiUtils.drawTexture(bg, posX, posY, 32, 32,75);
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
            GuiUtils.drawTexture(iconInfo.icon(), posX + marginleft, posY + margintop, iconInfo.getWidth() * scalex, iconInfo.getHeight() * scaley, 100);

            if(Screen.hasAltDown()) {
                GuiUtils.drawTexture(CROSS, posX, posY, 32, 32, 101);
            }
        }
    }

    /**
     * Draws this piece's tooltip.
     */
    @OnlyIn(Dist.CLIENT)
    public void drawTooltip(int tooltipX, int tooltipY, List<ITextComponent> tooltip, Screen screen) {
        //PsiAPI.internalHandler.renderTooltip(tooltipX, tooltipY, tooltip, 0x505000ff, 0xf0100010, screen.width, screen.height);
    }

    @OnlyIn(Dist.CLIENT)
    public void getTooltip(List<ITextComponent> tooltip) {
        if(spectrobe != null)
            tooltip.add(new TranslationTextComponent(getUnlocalizedName()));
        //tooltip.add(new TranslationTextComponent(getUnlocalizedDesc()).setStyle(Style.EMPTY.withColor(TextFormatting.GRAY)));
//        TooltipHelper.tooltipIfShift(tooltip, () -> addToTooltipAfterShift(tooltip));
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
