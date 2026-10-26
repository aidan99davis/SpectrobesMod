package com.spectrobes.spectrobesmod.client.gui.prizmod.Pages;

import com.spectrobes.spectrobesmod.client.gui.prizmod.Components.CaseSlot;
import com.spectrobes.spectrobesmod.client.gui.prizmod.Components.MenuButton;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.networking.server.SCaseTransferPacket;
import com.spectrobes.spectrobesmod.common.storage.PrizmodCaseItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The Prizmod case: unlimited storage for fossils, minerals and rare minerals, one slot per item type.
 */
public class CasePage extends PrizmodPage {

    private static final int SLOT_SPACING = CaseSlot.SLOT_SIZE + 2;
    private static final int TAB_WIDTH = 90;
    private static final int TAB_HEIGHT = 20;
    private static final int MAX_COLUMNS = 10;

    private final Runnable onBack;

    private PrizmodCaseItems.Category category = PrizmodCaseItems.Category.FOSSILS;
    private int page;

    private final List<CaseSlot> slots = new ArrayList<>();

    public CasePage(PrizmodScreen parent, Runnable onBack) {
        super(parent);
        this.onBack = onBack;
    }

    @Override
    public void init() {
        super.init();
        clearButtons();
        slots.clear();

        addButton(new MenuButton(10, 10, 60, 20, "Back", button -> onBack.run()));

        PrizmodCaseItems.Category[] categories = PrizmodCaseItems.Category.values();
        int tabsY = parent.height / 10 + 14;
        int tabsX = parent.width / 2 - (categories.length * TAB_WIDTH) / 2;

        for (int i = 0; i < categories.length; i++) {
            PrizmodCaseItems.Category tab = categories[i];
            Button tabButton = addButton(new MenuButton(tabsX + i * TAB_WIDTH, tabsY, TAB_WIDTH - 2, TAB_HEIGHT,
                    tab.displayName, button -> {
                        this.category = tab;
                        this.page = 0;
                        init();
                    }));
            tabButton.active = tab != this.category;
        }

        int gridTop = tabsY + TAB_HEIGHT + 8;
        int footerHeight = 30;
        int columns = Math.max(1, Math.min(MAX_COLUMNS, (parent.width - 40) / SLOT_SPACING));
        int rows = Math.max(1, (parent.height - gridTop - footerHeight) / SLOT_SPACING);
        int perPage = columns * rows;

        List<Item> items = PrizmodCaseItems.getItems(this.category);
        int pageCount = Math.max(1, (items.size() + perPage - 1) / perPage);
        this.page = Math.min(this.page, pageCount - 1);

        int start = this.page * perPage;
        int end = Math.min(items.size(), start + perPage);
        int usedColumns = Math.min(columns, Math.max(1, end - start));
        int gridLeft = parent.width / 2 - (usedColumns * SLOT_SPACING) / 2;

        for (int i = start; i < end; i++) {
            int index = i - start;
            Item item = items.get(i);
            String key = PrizmodCaseItems.getKey(item);

            CaseSlot slot = new CaseSlot(
                    gridLeft + (index % columns) * SLOT_SPACING,
                    gridTop + (index / columns) * SLOT_SPACING,
                    item,
                    () -> parent.getMenu().getCaseCount(key),
                    this::onSlotPressed
            );
            slots.add(slot);
            addButton(slot);
        }

        int footerY = parent.height - footerHeight + 5;

        if (pageCount > 1) {
            Button prev = addButton(new MenuButton(parent.width / 2 - 125, footerY, 40, 20, "<", button -> {
                this.page--;
                init();
            }));
            prev.active = this.page > 0;

            Button next = addButton(new MenuButton(parent.width / 2 + 85, footerY, 40, 20, ">", button -> {
                this.page++;
                init();
            }));
            next.active = this.page < pageCount - 1;
        }

        addButton(new MenuButton(parent.width / 2 - 60, footerY, 120, 20, "Deposit All", button ->
                SpectrobesNetwork.sendToServer(
                        new SCaseTransferPacket(SCaseTransferPacket.Action.DEPOSIT_EVERYTHING, "", 0))));
    }

    private void onSlotPressed(CaseSlot slot, int button) {
        String key = PrizmodCaseItems.getKey(slot.getItem());

        if (button == 1) {
            SpectrobesNetwork.sendToServer(new SCaseTransferPacket(SCaseTransferPacket.Action.DEPOSIT, key, 0));
            return;
        }

        int amount = Screen.hasShiftDown() ? 1 : slot.getItem().getDefaultMaxStackSize();
        SpectrobesNetwork.sendToServer(new SCaseTransferPacket(SCaseTransferPacket.Action.WITHDRAW, key, amount));
    }

    @Override
    public void tick() {
        setFocused(true);
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        super.renderWidget(guiGraphics, mouseX, mouseY, partialTicks);

        for (CaseSlot slot : slots) {
            if (slot.isHovered()) {
                guiGraphics.pose().pushPose();
                guiGraphics.pose().translate(0.0F, 0.0F, 300.0F);
                guiGraphics.renderComponentTooltip(Minecraft.getInstance().font, getTooltip(slot), mouseX, mouseY);
                guiGraphics.pose().popPose();
                break;
            }
        }
    }

    private List<Component> getTooltip(CaseSlot slot) {
        NumberFormat format = NumberFormat.getIntegerInstance();
        List<Component> lines = new ArrayList<>();

        lines.add(slot.getItem().getDescription());
        lines.add(Component.literal("Stored: " + format.format(slot.getCount())).withStyle(ChatFormatting.AQUA));
        lines.add(Component.literal("In inventory: " + format.format(countInInventory(slot.getItem())))
                .withStyle(ChatFormatting.GRAY));
        lines.add(Component.literal("Left-click: take a stack").withStyle(ChatFormatting.DARK_GRAY));
        lines.add(Component.literal("Shift + left-click: take one").withStyle(ChatFormatting.DARK_GRAY));
        lines.add(Component.literal("Right-click: store all from inventory").withStyle(ChatFormatting.DARK_GRAY));

        return lines;
    }

    private int countInInventory(Item item) {
        Inventory inventory = parent.player.getInventory();
        int total = 0;

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.is(item)) {
                total += stack.getCount();
            }
        }

        return total;
    }
}
