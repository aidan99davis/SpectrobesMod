package com.spectrobes.spectrobesmod.client.gui.prizmod.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodMenu;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Stage;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.security.interfaces.RSAKey;
import java.util.List;

public class SpectrobePiece {

    @OnlyIn(Dist.CLIENT)
    private static RenderType layer;
    private static final String TAG_KEY_LEGACY = "spellKey";

    private static final String TAG_KEY = "key";
    private static final String TAG_PARAMS = "params";
    private static final String TAG_COMMENT = "comment";

    private static final String PSI_PREFIX = "psi.spellparam.";

    public final ResourceLocation registryKey;
    public final Spectrobe spell;

    public boolean isInGrid = false;
    public int x, y;
    public String comment;


    public SpectrobePiece(Spectrobe spell) {
        this.spell = spell;
        registryKey = PsiAPI.getSpellPieceKey(getClass());
    }


    /**
     * Gets what type of piece this is.
     */
    public Stage getPieceType() {
        return spell.properties.getStage();
    }

    public String getUnlocalizedName() {
        return registryKey.getNamespace() + ".spellpiece." + registryKey.getPath();
    }

    public String getSortingName() {
        return new TranslationTextComponent(getUnlocalizedName()).getString();
    }

    public String getUnlocalizedDesc() {
        return registryKey.getNamespace() + ".spellpiece." + registryKey.getPath() + ".desc";
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

    @OnlyIn(Dist.CLIENT)
    public static RenderType getLayer() {
        if (layer == null) {
            RenderType.State glState = RenderType.State.getBuilder()
                    .texture(new RenderState.TextureState(PrizmodMenu.SPECTROBE_SLOT_TEXTURE, false, false))
                    .lightmap(new RenderState.LightmapState(true))
                    .alpha(new RenderState.AlphaState(0.004F))
                    .cull(new RenderState.CullState(false))
                    .build(false);
            layer = RenderType.of(PsiAPI.PSI_PIECE_TEXTURE_ATLAS.toString(), DefaultVertexFormats.POSITION_COLOR_TEXTURE_LIGHT, GL11.GL_QUADS, 64, glState);
        }
        return layer;
    }

    /**
     * Draws this piece's background.
     */
    @OnlyIn(Dist.CLIENT)
    public void drawBackground(IRenderTypeBuffer buffers, int light) {
        RenderMaterial material = PsiAPI.getSpellPieceMaterial(registryKey);
        IVertexBuilder buffer = material.getVertexConsumer(buffers, ignored -> getLayer());
        Matrix4f mat = ms.peek().getModel();
        // Cannot call .texture() on the chained object because SpriteAwareVertexBuilder is buggy
        // and does not return itself, it returns the inner buffer
        // This leads to .texture() using the implementation of the inner buffer,
        // not of the SpriteAwareVertexBuilder, which is not what we want.
        // Split the chain apart so that .texture() is called on the original buffer
        buffer.vertex(mat, 0, 16, 0).color(1F, 1F, 1F, 1F);
        buffer.texture(0, 1).light(light).endVertex();

        buffer.vertex(mat, 16, 16, 0).color(1F, 1F, 1F, 1F);
        buffer.texture(1, 1).light(light).endVertex();

        buffer.vertex(mat, 16, 0, 0).color(1F, 1F, 1F, 1F);
        buffer.texture(1, 0).light(light).endVertex();

        buffer.vertex(mat, 0, 0, 0).color(1F, 1F, 1F, 1F);
        buffer.texture(0, 0).light(light).endVertex();
    }

    /**
     * Draws any additional stuff for this piece. Used in connectors
     * to draw the lines.
     */
    @OnlyIn(Dist.CLIENT)
    public void drawAdditional(IRenderTypeBuffer buffers, int light) {
        // NO-OP
    }

    /**
     * Draws this piece's tooltip.
     */
    @OnlyIn(Dist.CLIENT)
    public void drawTooltip(int tooltipX, int tooltipY, List<ITextComponent> tooltip, Screen screen) {
        PsiAPI.internalHandler.renderTooltip(tooltipX, tooltipY, tooltip, 0x505000ff, 0xf0100010, screen.width, screen.height);
    }

    /**
     * Draws this piece's comment tooltip.
     */
    @OnlyIn(Dist.CLIENT)
    public void drawCommentText(int tooltipX, int tooltipY, List<ITextComponent> commentText, Screen screen) {
        PsiAPI.internalHandler.renderTooltip(tooltipX, tooltipY - 9 - commentText.size() * 10, commentText, 0x5000a000, 0xf0001e00, screen.width, screen.height);
    }

    @OnlyIn(Dist.CLIENT)
    public void getTooltip(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent(getUnlocalizedName()));
        tooltip.add(new TranslationTextComponent(getUnlocalizedDesc()).setStyle(Style.EMPTY.withColor(TextFormatting.GRAY)));
        TooltipHelper.tooltipIfShift(tooltip, () -> addToTooltipAfterShift(tooltip));

        String addon = registryKey.getNamespace();
    }

    @OnlyIn(Dist.CLIENT)
    public void addToTooltipAfterShift(List<ITextComponent> tooltip) {
        tooltip.add(new StringTextComponent(""));

    }

    /**
     * Checks whether this piece should intercept keystrokes in the programmer interface.
     * This is used for the number constant piece to change its value.
     */
    @OnlyIn(Dist.CLIENT)
    public boolean interceptKeystrokes() {
        return false;
    }

    /**
     * Due to changes on LWJGL, it is no longer easily possible to get a key from a keycode.
     * It is technically possible but it is unadvisable.
     */

    @OnlyIn(Dist.CLIENT)
    public boolean onCharTyped(char character, int keyCode, boolean doit) {
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean onKeyPressed(int keyCode, int scanCode, boolean doit) {
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    public void getShownPieces(List<SpectrobePiece> pieces) {
        pieces.add(this);
    }

    public static SpectrobePiece create(Class<? extends SpectrobePiece> clazz, Spectrobe spell) {
        try {
            return clazz.getConstructor(Spectrobe.class).newInstance(spell);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
