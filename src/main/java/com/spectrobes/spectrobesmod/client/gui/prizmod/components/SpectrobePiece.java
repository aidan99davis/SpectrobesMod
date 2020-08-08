package com.spectrobes.spectrobesmod.client.gui.prizmod.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodMenu;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Stage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class SpectrobePiece {

    @OnlyIn(Dist.CLIENT)
    private static RenderType layer;

    public Spectrobe spell;

    public boolean isInGrid = false;
    public int x, y;
    public String comment;


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
    public void draw(IRenderTypeBuffer buffers, int light) {
        RenderSystem.pushMatrix();
        drawBackground(buffers, light);
        RenderSystem.translatef(0F, 0F, 0.1F);
        drawAdditional(buffers, light);
//        if (isInGrid) {
//            RenderSystem.translatef(0F, 0F, 0.1F);
//            drawComment(ms, buffers, light);
//        }

        RenderSystem.popMatrix();
    }

//    @OnlyIn(Dist.CLIENT)
//    public static RenderType getLayer() {
//        if (layer == null) {
//            RenderType.State glState = RenderType.State.getBuilder()
//                    .texture(new RenderState.TextureState(PrizmodMenu.SPECTROBE_SLOT_TEXTURE, false, false))
//                    .lightmap(new RenderState.LightmapState(true))
//                    .alpha(new RenderState.AlphaState(0.004F))
//                    .cull(new RenderState.CullState(false))
//                    .build(false);
//            layer = RenderType.makeType(PrizmodMenu.SPECTROBE_SLOT_TEXTURE.toString(), DefaultVertexFormats.POSITION_COLOR_TEX_LIGHTMAP, GL11.GL_QUADS, 64, glState);
//        }
//        return layer;
//    }

    /**
     * Draws this piece's background.
     */
    @OnlyIn(Dist.CLIENT)
    public void drawBackground(IRenderTypeBuffer buffers, int light) {
        ResourceLocation bg = PrizmodMenu.SPECTROBE_SLOT_TEXTURE;

        RenderSystem.pushMatrix();

        Minecraft.getInstance().textureManager.bindTexture(bg);
        RenderSystem.enableTexture();


        RenderSystem.popMatrix();
    }

    /**
     * Draws any additional stuff for this piece. Used in connectors
     * to draw the lines.
     */
    @OnlyIn(Dist.CLIENT)
    public void drawAdditional(IRenderTypeBuffer buffers, int light) {
        SpectrobesInfo.LOGGER.info("GOT TO HERE");
        if(spell != null) {
            ResourceLocation icon = spell.getIcon();

            RenderSystem.pushMatrix();

            Minecraft.getInstance().textureManager.bindTexture(icon);
            RenderSystem.enableTexture();


            RenderSystem.popMatrix();

        }
    }

    /**
     * Draws this piece's tooltip.
     */
    @OnlyIn(Dist.CLIENT)
    public void drawTooltip(int tooltipX, int tooltipY, List<ITextComponent> tooltip, Screen screen) {
        //PsiAPI.internalHandler.renderTooltip(tooltipX, tooltipY, tooltip, 0x505000ff, 0xf0100010, screen.width, screen.height);
    }

    /**
     * Draws this piece's comment tooltip.
     */
    @OnlyIn(Dist.CLIENT)
    public void drawCommentText(int tooltipX, int tooltipY, List<ITextComponent> commentText, Screen screen) {
        //PsiAPI.internalHandler.renderTooltip(tooltipX, tooltipY - 9 - commentText.size() * 10, commentText, 0x5000a000, 0xf0001e00, screen.width, screen.height);
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
}
