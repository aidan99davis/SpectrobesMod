package com.spectrobes.spectrobesmod.common.spectrobes;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.resources.ResourceLocation;

public class SpectrobeIconInfo {
    private final ResourceLocation iconRl;
    private int width;
    private int height;

    public SpectrobeIconInfo(String name, int width, int height) {
        iconRl = ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/gui/" + name + ".png");
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
