package com.github.wildtooth.plush.event.item;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public abstract class ItemEvent extends Event {
    protected ItemStack item;

    public ItemEvent(ItemStack theItem) {
        this.item = theItem;
    }

    public final ItemStack getItem() {
        return item;
    }
}
