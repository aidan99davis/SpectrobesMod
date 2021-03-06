package com.spectrobes.spectrobesmod.client.prizmod.Pages;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.client.prizmod.Components.MenuButton;
import com.spectrobes.spectrobesmod.client.prizmod.PrizmodScreen;

public class MenuPage extends PrizmodPage {

    public MenuPage(PrizmodScreen prizmodScreen) {
        super(prizmodScreen);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
        RenderSystem.pushMatrix();

        RenderSystem.popMatrix();
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
