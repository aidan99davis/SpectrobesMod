package com.spectrobes.spectrobesmod.client.gui.prizmod.Pages;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public abstract class PrizmodPage extends AbstractWidget {

    public PrizmodScreen parent;
    protected PlayerSpectrobeMaster playerData;
    protected List<AbstractWidget> buttons = new ArrayList<>();


    public PrizmodPage(PrizmodScreen parent) {
        super(parent.pageX, parent.pageY, 0, 0, Component.literal("test"));
        this.parent = parent;
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
    }

    public List<AbstractWidget> getButtons() {
        return buttons;
    }

    public void addButton(AbstractWidget button) {
        buttons.add(button);
    }

    public void addButtons(List<AbstractWidget> buttons) {
        this.buttons.addAll(buttons);
    }


    public void init() {
        parent.addButtons(getButtons());
    }

    public abstract void tick();

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }
}
