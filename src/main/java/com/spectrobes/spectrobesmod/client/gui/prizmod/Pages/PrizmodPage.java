package com.spectrobes.spectrobesmod.client.gui.prizmod.Pages;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class PrizmodPage extends Widget {

    public PrizmodScreen parent;
    protected PlayerSpectrobeMaster playerData;
    protected List<Widget> buttons = new ArrayList<>();


    public PrizmodPage(PrizmodScreen parent) {
        super(parent.pageX, parent.pageY, 0, 0, new StringTextComponent("test"));
        this.parent = parent;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
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
