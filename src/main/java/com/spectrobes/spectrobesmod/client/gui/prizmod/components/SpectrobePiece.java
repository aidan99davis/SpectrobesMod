package com.spectrobes.spectrobesmod.client.gui.prizmod.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodMenu;
import com.spectrobes.spectrobesmod.client.gui.utils.GuiUtils;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeIconInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Stage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

import java.security.interfaces.RSAKey;
import java.util.List;

public class SpectrobePiece extends AbstractGui {

    public Spectrobe spell;
    public int x, y;
    public int posX = x * 32 + 32;
    public int posY = y * 32 + 32;

    public SpectrobePiece(Spectrobe spell) {
        this.spell = spell;
    }

    /**
     * Gets what type of piece this is.
     */
    public Stage getPieceType() {
        return spell.properties.getStage();
    }

    public String getUnlocalizedName() {
        return SpectrobesInfo.MOD_ID + ".spectrobe." + spell.name;
    }

    public String getSortingName() {
        return new TranslationTextComponent(getUnlocalizedName()).getString();
    }

    /**
     * Draws this piece onto the programmer GUI or the programmer TE projection.<br>
     * All appropriate transformations are already done. Canvas is 16x16 starting from (0, 0, 0).<br>
     * To avoid z-fighting in the TE projection, translations are applied every step.
     */
    @OnlyIn(Dist.CLIENT)
    public void draw() {
        RenderSystem.pushMatrix();
        drawBackground();
//        RenderSystem.translatef(x, y, 0.1F);
        drawAdditional();
//        if (isInGrid) {
//            RenderSystem.translatef(0F, 0F, 0.1F);
//            drawComment(ms, buffers, light);
//        }

        RenderSystem.popMatrix();
    }

    /**
     * Draws this piece's background.
     */
    @OnlyIn(Dist.CLIENT)
    public void drawBackground() {
        ResourceLocation bg = PrizmodMenu.SPECTROBE_SLOT_TEXTURE;

        RenderSystem.pushMatrix();
        Minecraft.getInstance().textureManager.bindTexture(bg);
        RenderSystem.enableTexture();

        GuiUtils.blit(posX, posY,32,0,0,32, 32, 32, 32);

        RenderSystem.popMatrix();
    }

    /**
     * Draws any additional stuff for this piece. Used in connectors
     * to draw the lines.
     */
    @OnlyIn(Dist.CLIENT)
    public void drawAdditional() {
        if(spell != null) {
            SpectrobeIconInfo iconInfo = spell.getIcon();
            ResourceLocation icon = iconInfo.icon();

            RenderSystem.pushMatrix();

            Minecraft.getInstance().textureManager.bindTexture(icon);

            RenderSystem.enableTexture();
            //RenderSystem.scalef(0.125f, 0.125f, 0.125f);
            float scalex = 32 / iconInfo.getWidth();
            float scaley = 32 / iconInfo.getHeight();

            int marginleft = iconInfo.getWidth() < 31
                    ? ((32 - iconInfo.getWidth())/2)
                    : 0;
            int margintop = iconInfo.getHeight() < 31
                    ? ((32 - iconInfo.getHeight())/2)
                    : 0;

            RenderSystem.scalef(scalex, scaley, 0);

            GuiUtils.blit(posX + marginleft, posY + margintop,32,0,0,iconInfo.getWidth(), iconInfo.getHeight(), iconInfo.getHeight(), iconInfo.getWidth());
            RenderSystem.popMatrix();

//            RenderSystem.disableTexture();

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

    @OnlyIn(Dist.CLIENT)
    public void displayButtonMenu() {
        RenderSystem.pushMatrix();

        RenderSystem.translatef(posX + 32, posY + 32, 128);
        RenderSystem.color3f(255,255,255);
        fill(100, 100, 128, 64, 100);

        RenderSystem.popMatrix();
    }
}
