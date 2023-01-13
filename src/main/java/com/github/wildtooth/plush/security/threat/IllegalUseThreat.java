package com.github.wildtooth.plush.security.threat;

import com.github.wildtooth.plush.util.ColorUtils;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * The type Illegal use threat.
 */
public final class IllegalUseThreat extends Threat {

    private final ItemStack item;
    private final Player cause;

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
        super(ThreatType.ILLEGAL_ITEM, description, threatLevel);
        this.item = theItem;
        this.cause = thePlayer;
    }

    @Override
    public void execute() {
        if (severity().equals(ThreatLevel.LOW)) return;

        if (severity().equals(ThreatLevel.MEDIUM)) {
            cause.kickPlayer(ColorUtils.getColored("&cYou have been kicked, use the right code!"));
            return;
        }

        if (severity().equals(ThreatLevel.HIGH)) {
            cause.getServer().getBanList(BanList.Type.NAME).addBan(ChatColor.stripColor(
                            cause.getName()),
                    ColorUtils.getColored(
                            "&cYou have been banned, contact server-administration! &4&lError Code: &c&lWRONG_CODE"
                    ),
                    null,
                    null);
            cause.kickPlayer("&cYou have been banned, contact server-administration! &4&lError Code: &c&lWRONG_CODE");
        }
    }

    /**
     * Gets item.
     *
     * @return the item
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * Gets cause.
     *
     * @return the cause
     */
    public Player getCause() {
        return cause;
    }

    /**
     * Severity threat level.
     *
     * @return the threat level
     */
    public ThreatLevel severity() {
        return getThreatLevel();
    }
}

