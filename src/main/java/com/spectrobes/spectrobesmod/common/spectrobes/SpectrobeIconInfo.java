package com.spectrobes.spectrobesmod.common.spectrobes;

import net.minecraft.resources.ResourceLocation;

public class SpectrobeIconInfo {
    private final ResourceLocation iconRl;
    private int width;
    private int height;

    public SpectrobeIconInfo(String name, int width, int height) {
        iconRl = new ResourceLocation("spectrobesmod:textures/gui/" + name + ".png");
        this.height = height;
        this.width = width;
    }

    public ResourceLocation icon() {
        return iconRl;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
