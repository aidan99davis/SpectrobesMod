//package com.spectrobes.spectrobesmod.client.gui.prizmod.components;
//
//import com.mojang.blaze3d.systems.RenderSystem;
//import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodMenu;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.widget.button.Button;
//import net.minecraft.util.text.TranslationTextComponent;
//
//public class GuiButtonPage extends Button {
//
//    public final boolean right;
//    final PrizmodMenu gui;
//
//    public GuiButtonPage(int x, int y, boolean right, PrizmodMenu gui) {
//        super(x, y, 18, 10, "", button -> {});
//        this.gui = gui;
//        this.right = right;
//    }
//
//    public GuiButtonPage(int x, int y, boolean right, PrizmodMenu gui, Button.IPressable pressable) {
//        super(x, y, 18, 10, "", pressable);
//        this.gui = gui;
//        this.right = right;
//    }
//
//    @Override
//    public void renderButton(int par2, int par3, float pTicks) {
//        if (active) {
//            boolean hover = par2 >= x && par3 >= y && par2 < x + width && par3 < y + height;
//
//            Minecraft.getInstance().textureManager.bindTexture(PrizmodMenu.texture);
//            RenderSystem.color4f(1F, 1F, 1F, 1F);
//            blit(x, y, hover ? 216 : 198, right ? 145 : 155, width, height);
//
//            if (hover) {
//                gui.tooltip.add(new TranslationTextComponent(right ? "psimisc.next_page" : "psimisc.prev_page"));
//            }
//        }
//    }
//
//    public boolean isRight() {
//        return right;
//    }
//}
