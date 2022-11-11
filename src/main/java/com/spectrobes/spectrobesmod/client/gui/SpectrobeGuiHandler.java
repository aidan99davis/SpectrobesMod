package com.spectrobes.spectrobesmod.client.gui;

import com.spectrobes.spectrobesmod.client.container.SpectrobeDetailsContainer;
import com.spectrobes.spectrobesmod.client.gui.spectrobes_details.SpectrobeDetailsScreen;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class SpectrobeGuiHandler {
    public static void openDetails(Spectrobe spectrobe) {
        Player player = Minecraft.getInstance().player;
        Minecraft.getInstance()
                .setScreen(
                        new SpectrobeDetailsScreen(
                                new SpectrobeDetailsContainer(
                                        0,
                                        spectrobe),
                                player.getInventory(),
                                Component.literal("")));
    }
}
