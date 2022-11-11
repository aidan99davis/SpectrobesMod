package com.spectrobes.spectrobesmod.client.gui.healer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.container.HealerContainer;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HealerScreen extends AbstractContainerScreen<HealerContainer> {

    private static final ResourceLocation HEALER_GUI_BACKGROUND = new ResourceLocation("spectrobesmod:textures/gui/prizmod_background.png");

    private static final int HEAL_COST = 200;

    public HealerScreen(HealerContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        inventoryLabelX = width;
        inventoryLabelY = height;
    }

    @Override
    protected void init() {
        super.init();
        addWidget(new Button(width / 2 - 30, (height / 5) * 3, 60, 20, Component.literal("Heal"), button -> {
            if(getMenu().spendGura(HEAL_COST)) {
                getMenu().healTeam();
            } else {

            }
        }));
    }

    @Override
    protected void renderBg(PoseStack pMatrixStack, float pPartialTicks, int pX, int pY) {
        int guraBalance = getMenu().getCurrentGuraBalance();
        String text = "Gura Balance: " + guraBalance;

//        GuiUtils.drawTexture(HEALER_GUI_BACKGROUND, 0, 0, width, height, 1);
        this.font.draw(pMatrixStack, text, width / 2 - (this.font.width(text) / 2), height / 5, FastColor.ARGB32.color(255, 0, 0, 0));
    }
}
