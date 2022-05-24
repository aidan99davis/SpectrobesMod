package com.spectrobes.spectrobesmod.client.gui.healer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.client.container.HealerContainer;
import com.spectrobes.spectrobesmod.client.gui.utils.GuiUtils;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ColorHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HealerScreen extends ContainerScreen<HealerContainer> {

    private static final ResourceLocation HEALER_GUI_BACKGROUND = new ResourceLocation("spectrobesmod:textures/gui/prizmod_background.png");

    private static final int HEAL_COST = 200;

    public HealerScreen(HealerContainer pMenu, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        inventoryLabelX = width;
        inventoryLabelY = height;
    }

    @Override
    protected void init() {
        super.init();
        addButton(new Button(width / 2 - 30, (height / 5) * 3, 60, 20, new StringTextComponent("Heal"), button -> {
            if(getMenu().spendGura(HEAL_COST)) {
                getMenu().healTeam();
            } else {

            }
        }));
    }

    @Override
    protected void renderBg(MatrixStack pMatrixStack, float pPartialTicks, int pX, int pY) {
        int guraBalance = getMenu().getCurrentGuraBalance();
        String text = "Gura Balance: " + guraBalance;
        GuiUtils.drawTexture(HEALER_GUI_BACKGROUND, 0, 0, width, height, 1);
        this.font.draw(pMatrixStack, text, width / 2 - (this.font.width(text) / 2), height / 5, ColorHelper.PackedColor.color(255, 0, 0, 0));
    }
}
