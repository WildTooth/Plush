package com.github.wildtooth.plush.event.item;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class IllegalItemUseEvent extends ItemEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;

    public IllegalItemUseEvent(ItemStack theItem, Player thePlayer) {
        super(theItem);
        this.player = thePlayer;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }
}

