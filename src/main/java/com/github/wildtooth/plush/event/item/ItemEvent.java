package com.github.wildtooth.plush.event.item;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

/**
 * The type Item event.
 */
public abstract class ItemEvent extends Event {
    /**
     * The Item.
     */
    protected ItemStack item;

    /**
     * Instantiates a new Item event.
     *
     * @param theItem the item
     */
    public ItemEvent(ItemStack theItem) {
        this.item = theItem;
    }

    /**
     * Gets item.
     *
     * @return the item
     */
    public final ItemStack getItem() {
        return item;
    }
}
