package com.spectrobes.spectrobesmod.client.prizmod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.gui.utils.GuiUtils;
import com.spectrobes.spectrobesmod.client.prizmod.Pages.MenuPage;
import com.spectrobes.spectrobesmod.client.prizmod.Pages.PrizmodPage;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.INestedGuiEventHandler;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class PrizmodScreen extends Screen implements INestedGuiEventHandler {
    public static final ResourceLocation texture = new ResourceLocation("spectrobesmod:textures/gui/prizmod_background.png");
    public static final ResourceLocation SPECTROBE_SLOT_TEXTURE = new ResourceLocation("spectrobesmod:textures/gui/spectrobe_slot.png");

    public final PlayerEntity player;
    public PlayerSpectrobeMaster playerData;
    public int pageX = width / 3;
    public int pageY = (int) (height * 0.65);

    private PrizmodPage prizmodPage;


    public PrizmodScreen(PlayerEntity player) {
        super(new StringTextComponent(""));
        this.player = player;
        this.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> this.playerData = sm);
        if(this.playerData == null) {
            Minecraft.getInstance().displayGuiScreen(null);
        }
    }

    @Override public List<Widget> children() {return buttons;}

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int partialTicks) {
        super.mouseClicked(mouseX, mouseY, partialTicks);
        //this.prizmodPage.mouseClicked(mouseX,mouseY,partialTicks);
        return false;
    }

    @Override
    public void init() {
        this.prizmodPage = new MenuPage(this);
        this.prizmodPage.init();
        this.setFocused(prizmodPage);
        this.addButton(this.prizmodPage);
        //this.addButtons(this.prizmodPage.getButtons());
    }

    @Override
    public void renderBackground() {
        super.renderBackground();
        RenderSystem.pushMatrix();
        RenderSystem.color3f(1F, 1F, 1F);
        getMinecraft().getTextureManager().bindTexture(texture);

        //RenderSystem.scalef(0.75f, 0.75f, 1);

        GuiUtils.blit(0, 0,0,0,0,
                (width),
                (height),
                height, width);

//        RenderSystem.translatef(0,0,1);
        RenderSystem.popMatrix();
    }


    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY,partialTicks);
        RenderSystem.pushMatrix();
        RenderSystem.translatef(0,0,-16);
        renderBackground();

        RenderSystem.popMatrix();
        RenderSystem.translatef(0,0,32);
        this.prizmodPage.render(mouseX,mouseY,partialTicks);

    }

    public void setMenuPage(PrizmodPage prizmodPage) {
        this.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
            this.playerData = sm;
        });

        this.buttons.clear();
        this.prizmodPage = prizmodPage;
        this.prizmodPage.init();
        this.setFocused(prizmodPage);
        this.addButton(this.prizmodPage);
    }

    @Override public void tick() {
        this.prizmodPage.tick();

        this.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
            this.playerData = sm;
        });
    }

    public void addButtons(List<Widget> buttonList) {
        buttonList.forEach(b -> {
            b.visible = true;
            b.active = true;
            this.addButton(b);
        });
    }
}
