package com.spectrobes.spectrobesmod.client.gui.prizmod.Pages;

import com.spectrobes.spectrobesmod.client.gui.prizmod.Components.MenuButton;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;

public class MenuPage extends PrizmodPage {

    public MenuPage(PrizmodScreen prizmodScreen) {
        super(prizmodScreen);
    }

    @Override
    public void init() {
        super.init();
        clearButtons();

        addButton(new MenuButton(parent.width / 2 - 60, 40, 60, 20, "Line Up", button -> {
            parent.setMenuPage(new LineUpPage(parent));
        }));
    }

    @Override
    public void tick() {
        setFocused(true);
    }
}
