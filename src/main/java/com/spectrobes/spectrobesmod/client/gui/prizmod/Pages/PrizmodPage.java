package com.spectrobes.spectrobesmod.client.gui.prizmod.Pages;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import net.minecraft.client.gui.components.Widget;

import java.util.ArrayList;
import java.util.List;

public abstract class PrizmodPage implements Widget {

    public PrizmodScreen parent;
    protected PlayerSpectrobeMaster playerData;
    protected List<Widget> buttons = new ArrayList<>();


    public PrizmodPage(PrizmodScreen parent) {
        super(parent.pageX, parent.pageY, 0, 0, new StringTextComponent("test"));
        this.parent = parent;
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
    }

    public List<Widget> getButtons() {
        return buttons;
    }

    public void addButton(Widget button) {
        buttons.add(button);
    }

    public void addButtons(List<Widget> buttons) {
        this.buttons.addAll(buttons);
    }


    public void init() {
        parent.addButtons(getButtons());
    }

    public abstract void tick();
}
