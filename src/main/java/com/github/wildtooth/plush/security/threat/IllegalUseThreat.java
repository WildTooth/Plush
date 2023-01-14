package com.github.wildtooth.plush.security.threat;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * The type Illegal use threat.
 */
public final class IllegalUseThreat extends Threat {

    private final ItemStack item;

    /**
     * Instantiates a new Illegal use threat.
     *
     * @param description the description
     * @param threatLevel the threat level
     * @param theItem     the item
     * @param thePlayer   the player
     */
    public IllegalUseThreat(String description,
                            ThreatLevel threatLevel,
                            ItemStack theItem,
                            Player thePlayer) {
        super(ThreatType.ILLEGAL_ITEM, description, threatLevel, thePlayer);
        this.item = theItem;
    }

    /**
     * Gets item.
     *
     * @return the item
     */
    public ItemStack getItem() {
        return item;
    }
}

