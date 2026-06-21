package com.spectrobes.spectrobesmod.client.gui.healer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.container.HealerContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class HealerScreen extends AbstractContainerScreen<HealerContainer> {
    private static final ResourceLocation HEALER_GUI_BACKGROUND = ResourceLocation.fromNamespaceAndPath(
            SpectrobesInfo.MOD_ID,
            "textures/gui/prizmod_background.png"
    );

    private static final int HEAL_COST = 200;

    public HealerScreen(HealerContainer menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init() {
        super.init();

        addRenderableWidget(
                Button.builder(Component.literal("Heal"), button -> {
                            if (getMenu().spendGura(HEAL_COST)) {
                                getMenu().healTeam();
                            }
                        })
                        .pos(width / 2 - 30, (height / 5) * 3)
                        .size(60, 20)
                        .build()
        );
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int guraBalance = getMenu().getCurrentGuraBalance();
        String text = "Gura Balance: " + guraBalance;

        guiGraphics.drawString(
                this.font,
                text,
                width / 2 - (this.font.width(text) / 2),
                height / 5,
                0xFF000000,
                false
        );
    }
}