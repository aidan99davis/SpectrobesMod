package com.spectrobes.spectrobesmod.client.gui.prizmod.Pages;

import com.spectrobes.spectrobesmod.client.gui.prizmod.Components.MenuButton;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;

public class MenuPage extends PrizmodPage {

    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_SPACING = 24;

    public MenuPage(PrizmodScreen prizmodScreen) {
        super(prizmodScreen);
    }

    @Override
    public void init() {
        super.init();
        clearButtons();

        int x = parent.width / 2 - BUTTON_WIDTH / 2;
        int y = parent.height / 3;
        Runnable backToMenu = () -> parent.setMenuPage(new MenuPage(parent));

        // The Prizmod line up only rearranges the existing party.
        addButton(new MenuButton(x, y, BUTTON_WIDTH, BUTTON_HEIGHT, "Line Up", button ->
                parent.setMenuPage(new LineUpPage(parent, true, backToMenu))));

        addButton(new MenuButton(x, y + BUTTON_SPACING, BUTTON_WIDTH, BUTTON_HEIGHT, "Case", button ->
                parent.setMenuPage(new CasePage(parent, backToMenu))));
    }

    @Override
    public void tick() {
        setFocused(true);
    }
}
