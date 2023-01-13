package com.github.wildtooth.plush.event.item;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

/**
 * The type Illegal item use event.
 */
public class IllegalItemUseEvent extends ItemEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;

    /**
     * Instantiates a new Illegal item use event.
     *
     * @param theItem   the item
     * @param thePlayer the player
     */
    public IllegalItemUseEvent(ItemStack theItem, Player thePlayer) {
        super(theItem);
        this.player = thePlayer;
    }

    /**
     * Gets handler list.
     *
     * @return the handler list
     */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
}

