package com.spectrobes.spectrobesmod.client.gui.prizmod.Components;

import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;
import java.util.function.LongSupplier;

/**
 * One case slot: an icon of its item type and how many the player has stored.
 */
public class CaseSlot extends AbstractWidget {

    public static final int SLOT_SIZE = 32;

    private static final int EMPTY_OVERLAY = 0x99202020;
    private static final int HOVER_OVERLAY = 0x30FFFFFF;

    private final Item item;
    private final ItemStack displayStack;
    private final LongSupplier count;
    private final BiConsumer<CaseSlot, Integer> pressHandler;

    public CaseSlot(int x, int y, Item item, LongSupplier count, BiConsumer<CaseSlot, Integer> pressHandler) {
        super(x, y, SLOT_SIZE, SLOT_SIZE, item.getDescription());
        this.item = item;
        this.displayStack = new ItemStack(item);
        this.count = count;
        this.pressHandler = pressHandler;
    }

    public Item getItem() {
        return item;
    }

    public long getCount() {
        return count.getAsLong();
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        Font font = Minecraft.getInstance().font;
        long stored = getCount();
        int x = getX();
        int y = getY();

        graphics.blit(PrizmodScreen.SPECTROBE_SLOT_TEXTURE, x, y, 0, 0, SLOT_SIZE, SLOT_SIZE, SLOT_SIZE, SLOT_SIZE);

        graphics.pose().pushPose();
        graphics.pose().translate(x + SLOT_SIZE / 2.0F - 12.0F, y + 2.0F, 0.0F);
        graphics.pose().scale(1.5F, 1.5F, 1.0F);
        graphics.renderFakeItem(displayStack, 0, 0);
        graphics.pose().popPose();

        graphics.pose().pushPose();
        graphics.pose().translate(0.0F, 0.0F, 200.0F);

        // Fade the item out when none are stored, so the slot still shows what goes there.
        if (stored <= 0) {
            graphics.fill(x + 2, y + 2, x + SLOT_SIZE - 2, y + SLOT_SIZE - 2, EMPTY_OVERLAY);
        }

        if (isHovered()) {
            graphics.fill(x, y, x + SLOT_SIZE, y + SLOT_SIZE, HOVER_OVERLAY);
        }

        String label = formatCount(stored);
        graphics.drawString(font, label, x + SLOT_SIZE - 2 - font.width(label), y + SLOT_SIZE - 10,
                stored > 0 ? 0xFFFFFFFF : 0xFF808080, true);

        graphics.pose().popPose();
    }

    @Override
    protected boolean isValidClickButton(int button) {
        return button == 0 || button == 1;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!this.active || !this.visible || !isValidClickButton(button) || !clicked(mouseX, mouseY)) {
            return false;
        }

        playDownSound(Minecraft.getInstance().getSoundManager());
        if (pressHandler != null) {
            pressHandler.accept(this, button);
        }
        return true;
    }

    /**
     * Shortens large counts: 999 stays as is, 1000 becomes 1k, 1500 becomes 1.5k, and so on
     * through M and B. The decimal is truncated so the label never overstates the count.
     */
    public static String formatCount(long count) {
        if (count < 1000) {
            return Long.toString(count);
        }

        String[] suffixes = {"k", "M", "B", "T"};
        long divisor = 1000;
        int index = 0;

        while (index < suffixes.length - 1 && count >= divisor * 1000) {
            divisor *= 1000;
            index++;
        }

        long whole = count / divisor;
        long tenth = (count % divisor) * 10 / divisor;

        return tenth == 0 || whole >= 100
                ? whole + suffixes[index]
                : whole + "." + tenth + suffixes[index];
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        defaultButtonNarrationText(narrationElementOutput);
    }
}
