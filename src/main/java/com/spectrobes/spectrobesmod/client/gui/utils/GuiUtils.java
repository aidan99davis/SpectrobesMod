package com.spectrobes.spectrobesmod.client.gui.utils;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import cpw.mods.modlauncher.api.INameMappingService;
import net.minecraft.block.BlockState;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GuiUtils {

    int blitOffset;

    public static void blit(int x0, int y0, int z, int destWidth, int destHeight, TextureAtlasSprite sprite) {
        innerBlit(x0, x0 + destWidth, y0, y0 + destHeight, z, sprite.getU0(), sprite.getU1(), sprite.getV0(), sprite.getV1());
    }

    public void blit(int x0, int y0, int u0, int v0, int width, int height) {
        blit(x0, y0, this.blitOffset, (float)u0, (float)v0, width, height, 256, 256);
    }

    public static void blit(int x0, int y0, int z, float u0, float v0, int width, int height, int textureHeight, int textureWidth) {
        innerBlit(x0, x0 + width, y0, y0 + height, z, width, height, u0, v0, textureWidth, textureHeight);
    }

    public static void blit(int x0, int y0, int destWidth, int destHeight, float u0, float v0, int srcWidth, int srcHeight, int textureWidth, int textureHeight) {
        innerBlit(x0, x0 + destWidth, y0, y0 + destHeight, 0, srcWidth, srcHeight, u0, v0, textureWidth, textureHeight);
    }

    public static void blit(int x0, int y0, float u0, float v0, int destWidth, int destHeight, int textureWidth, int textureHeight) {
        blit(x0, y0, destWidth, destHeight, u0, v0, destWidth, destHeight, textureWidth, textureHeight);
    }

    private static void innerBlit(int x0, int x1, int y0, int y1, int z, int width, int height, float u0, float v0, int textureWidth, int textureHeight) {
        innerBlit(x0, x1, y0, y1, z, (u0 + 0.0F) / (float)textureWidth, (u0 + (float)width) / (float)textureWidth, (v0 + 0.0F) / (float)textureHeight, (v0 + (float)height) / (float)textureHeight);
    }

    protected static void innerBlit(int x0, int x1, int y0, int y1, int z, float u0, float u1, float v0, float v1) {
        Tessellator lvt_9_1_ = Tessellator.getInstance();
        BufferBuilder lvt_10_1_ = lvt_9_1_.getBuilder();
        lvt_10_1_.begin(7, DefaultVertexFormats.POSITION_TEX);
        lvt_10_1_.vertex((double)x0, (double)y1, (double)z).uv(u0, v1).endVertex();
        lvt_10_1_.vertex((double)x1, (double)y1, (double)z).uv(u1, v1).endVertex();
        lvt_10_1_.vertex((double)x1, (double)y0, (double)z).uv(u1, v0).endVertex();
        lvt_10_1_.vertex((double)x0, (double)y0, (double)z).uv(u0, v0).endVertex();
        lvt_9_1_.end();
    }

    public static void renderModel(IBakedModel modelIn, ItemStack stack, int combinedLightIn, int combinedOverlayIn, MatrixStack matrixStackIn, IVertexBuilder bufferIn) {
        Random random = new Random();
        long i = 42L;

        for(Direction direction : Direction.values()) {
            random.setSeed(42L);
            renderQuads(matrixStackIn, bufferIn, modelIn.getQuads((BlockState)null, direction, random), stack, combinedLightIn, combinedOverlayIn);
        }

        random.setSeed(42L);
        renderQuads(matrixStackIn, bufferIn, modelIn.getQuads((BlockState)null, (Direction)null, random), stack, combinedLightIn, combinedOverlayIn);
    }

    private static void renderQuads(MatrixStack matrixStackIn, IVertexBuilder bufferIn, List<BakedQuad> quadsIn, ItemStack itemStackIn, int combinedLightIn, int combinedOverlayIn) {
        boolean flag = !itemStackIn.isEmpty();
        MatrixStack.Entry matrixstack$entry = matrixStackIn.last();

        for(BakedQuad bakedquad : quadsIn) {
            int i = -1;
            if (flag && bakedquad.isTinted()) {
                i = Minecraft.getInstance().getItemColors().getColor(itemStackIn, bakedquad.getTintIndex());
            }

            float f = (float)(i >> 16 & 255) / 255.0F;
            float f1 = (float)(i >> 8 & 255) / 255.0F;
            float f2 = (float)(i & 255) / 255.0F;
            bufferIn.addVertexData(matrixstack$entry, bakedquad, f, f1, f2, combinedLightIn, combinedOverlayIn, true);
        }

    }

    public static void renderBakedModel(IBakedModel modelIn, ItemStack itemStackIn)
    {
        renderBakedModel(modelIn, itemStackIn, null);
    }

    public static void renderBakedModel(IBakedModel modelIn, ItemStack itemStackIn, RenderType renderTypeOverride)
    {
        renderBakedModel(modelIn, itemStackIn, renderTypeOverride, new MatrixStack(), Minecraft.getInstance().renderBuffers().bufferSource());
    }

    public static void renderBakedModel(IBakedModel modelIn, ItemStack itemStackIn, RenderType renderTypeOverride, MatrixStack matrixStackIn, IRenderTypeBuffer buffer)
    {
        Minecraft mc = Minecraft.getInstance();

        boolean flag4 = !modelIn.isGui3d();
        if(renderTypeOverride != null)
        {
            //ItemRenderer.renderItemModelIntoGUI
            RenderSystem.pushMatrix();
            mc.getTextureManager().bind(AtlasTexture.LOCATION_BLOCKS);
            mc.getTextureManager().getTexture(AtlasTexture.LOCATION_BLOCKS).setFilter(false, false);
            RenderSystem.enableRescaleNormal();
            RenderSystem.enableAlphaTest();
            RenderSystem.defaultAlphaFunc();
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            //setupGuiTransform removed
            if (flag4) {
                net.minecraft.client.renderer.RenderHelper.setupForFlatItems();
            }
        }

        //renderitem
        if (!itemStackIn.isEmpty()) {
            matrixStackIn.pushPose();
            if (itemStackIn.getItem() == Items.TRIDENT) {
                modelIn = mc.getItemRenderer().getItemModelShaper().getModelManager().getModel(new ModelResourceLocation("minecraft:trident#inventory"));
            }

            modelIn = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(matrixStackIn, modelIn, ItemCameraTransforms.TransformType.NONE, false);
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
            if (!modelIn.isCustomRenderer()) {
                RenderType rendertype = RenderTypeLookup.getRenderType(itemStackIn, true);
                RenderType rendertype1;
                if(renderTypeOverride != null)
                {
                    rendertype1 = renderTypeOverride;
                }
                else if (Objects.equals(rendertype, Atlases.translucentCullBlockSheet())) {
                    rendertype1 = Atlases.translucentCullBlockSheet();
                } else {
                    rendertype1 = rendertype;
                }

                IVertexBuilder ivertexbuilder = ItemRenderer.getFoilBuffer(buffer, rendertype1, true, itemStackIn.hasFoil());
                //renderModel
                renderModel(modelIn, itemStackIn, 0xf000f0, OverlayTexture.NO_OVERLAY, matrixStackIn, ivertexbuilder);
                //end renderModel
            } else {
                itemStackIn.getItem().getItemStackTileEntityRenderer().renderByItem(itemStackIn, ItemCameraTransforms.TransformType.GUI, matrixStackIn, buffer, 15728880, OverlayTexture.NO_OVERLAY);
            }

            matrixStackIn.popPose();
        }
        //end renderitem

        if(buffer instanceof IRenderTypeBuffer.Impl)
        {
            ((IRenderTypeBuffer.Impl)buffer).endBatch();
        }
        if(renderTypeOverride != null)
        {
            RenderSystem.enableDepthTest();
            if(flag4)
            {
                net.minecraft.client.renderer.RenderHelper.setupFor3DItems();
            }

            RenderSystem.disableAlphaTest();
            RenderSystem.disableRescaleNormal();
            RenderSystem.popMatrix();
        }
    }

    public static void drawTexture(ResourceLocation resource, double posX, double posY, double width, double height, double zLevel)
    {
        Minecraft.getInstance().getTextureManager().bind(resource);
        draw(posX, posY, width, height, zLevel);
    }

    public static void draw(double posX, double posY, double width, double height, double zLevel)
    {
        draw(posX, posY, width, height, zLevel, 0D, 1D, 0D, 1D);
    }

    public static void draw(double posX, double posY, double width, double height, double zLevel, double u1, double u2, double v1, double v2)
    {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.vertex(posX, posY + height, zLevel).uv((float)u1, (float)v2).endVertex();
        bufferbuilder.vertex(posX + width, posY + height, zLevel).uv((float)u2, (float)v2).endVertex();
        bufferbuilder.vertex(posX + width, posY, zLevel).uv((float)u2, (float)v1).endVertex();
        bufferbuilder.vertex(posX, posY, zLevel).uv((float)u1, (float)v1).endVertex();
        tessellator.end();
    }

    public static void drawColour(int colour, int alpha, double posX, double posY, double width, double height, double zLevel)
    {
        int r = (colour >> 16 & 0xff);
        int g = (colour >> 8 & 0xff);
        int b = (colour & 0xff);
        drawColour(r, g, b, alpha, posX, posY, width, height, zLevel);
    }

    public static void drawColour(int r, int g, int b, int alpha, double posX, double posY, double width, double height, double zLevel)
    {
        if(width <= 0 || height <= 0)
        {
            return;
        }
        RenderSystem.disableTexture();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.vertex(posX, posY + height, zLevel).color(r, g, b, alpha).endVertex();
        bufferbuilder.vertex(posX + width, posY + height, zLevel).color(r, g, b, alpha).endVertex();
        bufferbuilder.vertex(posX + width, posY, zLevel).color(r, g, b, alpha).endVertex();
        bufferbuilder.vertex(posX, posY, zLevel).color(r, g, b, alpha).endVertex();
        tessellator.end();
        RenderSystem.enableTexture();
    }

    public static void colour(int color)
    {
        float r = (color >> 16 & 255) / 255.0F;
        float g = (color >> 8 & 255) / 255.0F;
        float b = (color & 255) / 255.0F;
        RenderSystem.color4f(r, g, b, 1.0F);
    }

    public static void colour(int color, float alpha)
    {
        float r = (color >> 16 & 255) / 255.0F;
        float g = (color >> 8 & 255) / 255.0F;
        float b = (color & 255) / 255.0F;
        RenderSystem.color4f(r, g, b, alpha);
    }

    public static int getRandomColourFromString(String s)
    {
        Random rand = new Random();
        rand.setSeed(Math.abs(s.hashCode() * 1000));
        int clr = Math.round(0xffffff * rand.nextFloat());
        float[] hsb = new float[3];
        Color.RGBtoHSB(clr >> 16 & 0xff, clr >> 8 & 0xff, clr & 0xff, hsb);
        hsb[2] = 0.65F + 0.25F * hsb[2];
        clr = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
        return clr;
    }

    public static void startGlScissor(int x, int y, int width, int height)//From top left corner, like how Minecraft guis are. Don't forget to call endGlScissor after rendering
    {
        Minecraft mc = Minecraft.getInstance();

        double scaleW = (double)mc.getWindow().getWidth() / mc.getWindow().getGuiScaledWidth();
        double scaleH = (double)mc.getWindow().getHeight() / mc.getWindow().getGuiScaledHeight();

        if(width <= 0 || height <= 0)
        {
            return;
        }
        if(x < 0)
        {
            x = 0;
        }
        if(y < 0)
        {
            y = 0;
        }

        GL11.glEnable(GL11.GL_SCISSOR_TEST);

        GL11.glScissor((int)Math.floor((double)x * scaleW), (int)Math.floor((double)mc.getWindow().getHeight() - ((double)(y + height) * scaleH)), (int)Math.floor((double)(x + width) * scaleW) - (int)Math.floor((double)x * scaleW), (int)Math.floor((double)mc.getWindow().getHeight() - ((double)y * scaleH)) - (int)Math.floor((double)mc.getWindow().getHeight() - ((double)(y + height) * scaleH))); //starts from lower left corner (minecraft starts from upper left)
    }

    public static void endGlScissor()
    {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

    public static void renderTestScissor()
    {
        //Basic scissor test
        Minecraft mc = Minecraft.getInstance();

        GuiUtils.startGlScissor(mc.getWindow().getGuiScaledWidth() / 2 - 50, mc.getWindow().getGuiScaledHeight() / 2 - 50, 100, 100);
        //        RenderHelper.startGlScissor(10, 10, mc.getMainWindow().getScaledWidth() - 20, mc.getMainWindow().getScaledHeight() - 20);

        RenderSystem.pushMatrix();

        //        RenderSystem.translatef(-15F, 15F, 0F);

        GuiUtils.drawColour(0xffffff, 255, 0, 0, mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight(), 0);

        RenderSystem.popMatrix();

        GuiUtils.endGlScissor();
    }

    public static void renderTestStencil()
    {
        //Basic stencil test
        Minecraft mc = Minecraft.getInstance();
        MainWindow reso = mc.getWindow();

        GL11.glEnable(GL11.GL_STENCIL_TEST);

        GlStateManager._colorMask(false, false, false, false);
        GlStateManager._depthMask(false);

        GL11.glStencilFunc(GL11.GL_NEVER, 1, 0xFF);
        GL11.glStencilOp(GL11.GL_REPLACE, GL11.GL_KEEP, GL11.GL_KEEP);

        GL11.glStencilMask(0xFF);
        GlStateManager._clear(GL11.GL_STENCIL_BUFFER_BIT, Minecraft.ON_OSX);

        GuiUtils.drawColour(0xffffff, 255, 0, 0, 60, 60, 0);

        GlStateManager._colorMask(true, true, true, true);
        GlStateManager._depthMask(true);

        GL11.glStencilMask(0x00);

        GL11.glStencilFunc(GL11.GL_EQUAL, 0, 0xFF);

        GL11.glStencilFunc(GL11.GL_EQUAL, 1, 0xFF);

        GuiUtils.drawColour(0xffffff, 255, 0, 0, reso.getGuiScaledWidth(), reso.getGuiScaledHeight(), 0);

        GL11.glDisable(GL11.GL_STENCIL_TEST);
    }

    public static HashSet<Framebuffer> frameBuffers = new HashSet<>();

    public static Framebuffer createFrameBuffer()
    {
        Minecraft mc = Minecraft.getInstance();
        Framebuffer render = new Framebuffer(mc.getWindow().getWidth(), mc.getWindow().getHeight(), true, Minecraft.ON_OSX);
        if(mc.getMainRenderTarget().isStencilEnabled()) //if the main framebuffer is using a stencil, we might as well, too.
        {
            render.enableStencil();
        }
        frameBuffers.add(render);
        return render;
    }

    public static void deleteFrameBuffer(Framebuffer buffer)
    {
        if(buffer.frameBufferId >= 0)
        {
            buffer.destroyBuffers();
        }
        frameBuffers.remove(buffer);
    }

    //REFLECTIVE METHODS
    @OnlyIn(Dist.CLIENT)
    @Nullable
    public static <T extends EntityRenderer<?>, V extends Entity> ResourceLocation getEntityTexture(T rend, V ent)
    {
        return getEntityTexture(rend, rend.getClass(), ent);
    }

    @OnlyIn(Dist.CLIENT)
    public static <T extends LivingRenderer<?, ?>, V extends LivingEntity> void invokePreRenderCallback(T rend, V ent, MatrixStack stack, float rendTick)
    {
        invokePreRenderCallback(rend, rend.getClass(), ent, stack, rendTick);
    }

    @OnlyIn(Dist.CLIENT)
    @Nullable
    private static <T extends EntityRenderer<?>, V extends Entity> ResourceLocation getEntityTexture(T rend, Class clz, V ent)
    {
        try
        {
            Method m = clz.getDeclaredMethod(ObfuscationReflectionHelper.remapName(INameMappingService.Domain.METHOD, ObfHelper.getEntityTexture), Entity.class);
            m.setAccessible(true);
            return (ResourceLocation)m.invoke(rend, ent);
        }
        catch(NoSuchMethodException e)
        {
            if(clz != EntityRenderer.class)
            {
                return getEntityTexture(rend, clz.getSuperclass(), ent);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    private static <T extends LivingRenderer<?, ?>, V extends LivingEntity> void invokePreRenderCallback(T rend, Class clz, V ent, MatrixStack stack, float rendTick)
    {
        try
        {
            Method m = clz.getDeclaredMethod(ObfuscationReflectionHelper.remapName(INameMappingService.Domain.METHOD, ObfHelper.preRenderCallback), LivingEntity.class, MatrixStack.class, float.class);
            m.setAccessible(true);
            m.invoke(rend, ent, stack, rendTick);
        }
        catch(NoSuchMethodException e)
        {
            if(clz != LivingRenderer.class)
            {
                invokePreRenderCallback(rend, clz.getSuperclass(), ent, stack, rendTick);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
