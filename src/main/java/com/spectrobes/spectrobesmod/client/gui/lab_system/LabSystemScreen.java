package com.spectrobes.spectrobesmod.client.gui.lab_system;

import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Pages.PrizmodPage;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class LabSystemScreen extends PrizmodScreen {

    public LabSystemScreen(PrizmodContainer container, Inventory playerInventory, Component title) {
        super(container, playerInventory, title);
    }

    @Override
    protected PrizmodPage createInitialPage() {
        return new LabMenuPage(this);
    }
}
