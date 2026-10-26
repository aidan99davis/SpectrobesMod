package com.spectrobes.spectrobesmod.client.gui.lab_system;

import com.spectrobes.spectrobesmod.client.gui.prizmod.Components.MenuButton;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Pages.PrizmodPage;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

/**
 * Placeholder Lab System page with only a title and a back button.
 */
public class LabBlankPage extends PrizmodPage {

    private final String title;
    private final Runnable onBack;

    public LabBlankPage(PrizmodScreen parent, String title, Runnable onBack) {
        super(parent);
        this.title = title;
        this.onBack = onBack;
    }

    @Override
    public void init() {
        super.init();
        clearButtons();

        addButton(new MenuButton(10, 10, 60, 20, "Back", button -> onBack.run()));
    }

    @Override
    public void tick() {
        setFocused(true);
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.renderWidget(guiGraphics, mouseX, mouseY, partialTicks);

        guiGraphics.drawCenteredString(
                Minecraft.getInstance().font,
                title,
                parent.width / 2,
                parent.height / 3,
                0xFFFFFFFF
        );
    }
}
