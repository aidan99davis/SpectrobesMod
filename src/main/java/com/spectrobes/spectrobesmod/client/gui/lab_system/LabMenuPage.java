package com.spectrobes.spectrobesmod.client.gui.lab_system;

import com.spectrobes.spectrobesmod.client.gui.prizmod.Components.MenuButton;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Pages.LineUpPage;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Pages.PrizmodPage;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;

public class LabMenuPage extends PrizmodPage {

    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 20;
    private static final int BUTTON_SPACING = 24;

    public LabMenuPage(PrizmodScreen parent) {
        super(parent);
    }

    @Override
    public void init() {
        super.init();
        clearButtons();

        int x = parent.width / 2 - BUTTON_WIDTH / 2;
        int y = parent.height / 3;
        Runnable backToMenu = () -> parent.setMenuPage(new LabMenuPage(parent));

        addButton(new MenuButton(x, y, BUTTON_WIDTH, BUTTON_HEIGHT, "Line Up", button ->
                parent.setMenuPage(new LineUpPage(parent, false, backToMenu))));

        addButton(new MenuButton(x, y + BUTTON_SPACING, BUTTON_WIDTH, BUTTON_HEIGHT, "Awakening", button ->
                parent.setMenuPage(new LabBlankPage(parent, "Awakening", backToMenu))));

        addButton(new MenuButton(x, y + BUTTON_SPACING * 2, BUTTON_WIDTH, BUTTON_HEIGHT, "Incubator", button ->
                parent.setMenuPage(new LabBlankPage(parent, "Incubator", backToMenu))));

        addButton(new MenuButton(x, y + BUTTON_SPACING * 3, BUTTON_WIDTH, BUTTON_HEIGHT, "Library", button ->
                parent.setMenuPage(new LabBlankPage(parent, "Library", backToMenu))));
    }

    @Override
    public void tick() {
        setFocused(true);
    }
}
