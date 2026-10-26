package com.spectrobes.spectrobesmod.common.storage;

import com.spectrobes.spectrobesmod.common.items.minerals.chroma.ChromaMineralItem;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesFossilsRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesItemsRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The item types the Prizmod case can hold, grouped into the tabs shown in the case screen.
 */
public final class PrizmodCaseItems {

    public enum Category {
        FOSSILS("Fossils"),
        MINERALS("Minerals"),
        RARE("Rare Minerals");

        public final String displayName;

        Category(String displayName) {
            this.displayName = displayName;
        }
    }

    private PrizmodCaseItems() {
    }

    public static List<Item> getItems(Category category) {
        List<Item> items = new ArrayList<>();

        switch (category) {
            case FOSSILS:
                SpectrobesFossilsRegistry.ITEMS.getEntries().forEach(holder -> items.add(holder.get()));
                break;
            case MINERALS:
                SpectrobesMineralsRegistry.ITEMS.getEntries().forEach(holder -> {
                    if (!(holder.get() instanceof ChromaMineralItem)) {
                        items.add(holder.get());
                    }
                });
                break;
            case RARE:
                SpectrobesMineralsRegistry.ITEMS.getEntries().forEach(holder -> {
                    if (holder.get() instanceof ChromaMineralItem) {
                        items.add(holder.get());
                    }
                });
                SpectrobesItemsRegistry.ITEMS.getEntries().forEach(holder -> items.add(holder.get()));
                break;
        }

        return Collections.unmodifiableList(items);
    }

    public static boolean canStore(Item item) {
        for (Category category : Category.values()) {
            if (getItems(category).contains(item)) {
                return true;
            }
        }

        return false;
    }

    public static String getKey(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString();
    }

    /**
     * Resolves a key sent by the client, returning null unless it names an item the case can hold.
     */
    public static Item fromKey(String key) {
        ResourceLocation location = ResourceLocation.tryParse(key);

        if (location == null) {
            return null;
        }

        Item item = BuiltInRegistries.ITEM.get(location);

        if (item == Items.AIR || !canStore(item)) {
            return null;
        }

        return item;
    }
}
