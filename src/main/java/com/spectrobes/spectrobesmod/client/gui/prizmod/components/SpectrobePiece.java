package com.spectrobes.spectrobesmod.client.gui.prizmod.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.gui.utils.GuiUtils;
import com.spectrobes.spectrobesmod.client.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.items.tools.PrizmodItem;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeIconInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Stage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import java.util.List;

public class SpectrobePiece extends AbstractGui {

    public Spectrobe spell;
    private int x, y;
    public int posX;
    public int posY;
    public boolean selected;

    public SpectrobePiece(Spectrobe spell, int x ,int y) {
        this.spell = spell;
        this.x = x;
        this.y = y;
        this.posX = (x+3) * 32;
        this.posY = (y+2) * 32;
        selected = false;
    }

    public String getUnlocalizedName() {
        return SpectrobesInfo.MOD_ID + ".spectrobe." + spell.name;
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
        ResourceLocation bg = selected? PrizmodScreen.SPECTROBE_SLOT_SELECTED_TEXTURE : PrizmodScreen.SPECTROBE_SLOT_TEXTURE;

        GuiUtils.drawTexture(bg, posX, posY, 32, 32,75);
        GuiUtils.blit(posX, posY,32,0,0,32, 32, 32, 32);
    }

    /**
     * Draws any additional stuff for this piece. Used for the spectrobes icon
     */
    @OnlyIn(Dist.CLIENT)
    public void drawAdditional() {
        if(spell != null) {
            SpectrobeIconInfo iconInfo = spell.getIcon();

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
        if(spell != null)
            tooltip.add(new TranslationTextComponent(getUnlocalizedName()));
        //tooltip.add(new TranslationTextComponent(getUnlocalizedDesc()).setStyle(Style.EMPTY.withColor(TextFormatting.GRAY)));
        //TooltipHelper.tooltipIfShift(tooltip, () -> addToTooltipAfterShift(tooltip));
    }

    @OnlyIn(Dist.CLIENT)
    public void addToTooltipAfterShift(List<ITextComponent> tooltip) {
        tooltip.add(new StringTextComponent(""));

    }

    @OnlyIn(Dist.CLIENT)
    public void getShownPieces(List<SpectrobePiece> pieces) {
        pieces.add(this);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
