package com.spectrobes.spectrobesmod.client.gui.prizmod.Pages;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Components.MenuButton;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;

public class MenuPage extends PrizmodPage {

    public MenuPage(PrizmodScreen prizmodScreen) {
        super(prizmodScreen);
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void init() {
        this.addButton(new MenuButton(parent.width / 2 - 60, 40, 60, 20, "Line Up", button -> {
            parent.setMenuPage(new LineUpPage(parent));
        }));
        //call super.init last cos it needs buttons to be populated.
        super.init();
    }

    @Override
    public void tick() {

    }
}
