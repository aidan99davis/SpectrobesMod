package com.spectrobes.spectrobesmod.client.gui.utils;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class GuiUtils {

    int blitOffset;

    public static void blit(int x0, int y0, int z, int destWidth, int destHeight, TextureAtlasSprite sprite) {
        innerBlit(x0, x0 + destWidth, y0, y0 + destHeight, z, sprite.getMinU(), sprite.getMaxU(), sprite.getMinV(), sprite.getMaxV());
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
        BufferBuilder lvt_10_1_ = lvt_9_1_.getBuffer();
        lvt_10_1_.begin(7, DefaultVertexFormats.POSITION_TEX);
        lvt_10_1_.pos((double)x0, (double)y1, (double)z).tex(u0, v1).endVertex();
        lvt_10_1_.pos((double)x1, (double)y1, (double)z).tex(u1, v1).endVertex();
        lvt_10_1_.pos((double)x1, (double)y0, (double)z).tex(u1, v0).endVertex();
        lvt_10_1_.pos((double)x0, (double)y0, (double)z).tex(u0, v0).endVertex();
        lvt_9_1_.draw();
    }
}
