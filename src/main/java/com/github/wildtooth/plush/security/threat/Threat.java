package com.github.wildtooth.plush.security.threat;

import com.github.wildtooth.plush.util.ColorUtils;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * The type Threat.
 */
public abstract class Threat implements IThreat {
    private final String description;
    private final ThreatLevel threatLevel;
    private final ThreatType type;
    private final Player cause;

    /**
     * Instantiates a new Threat.
     *
     * @param type        the type
     * @param description the description
     * @param threatLevel the threat level
     */
    protected Threat(ThreatType type,
                     String description,
                     ThreatLevel threatLevel,
                     Player cause) {
        this.type = type;
        this.description = description;
        this.threatLevel = threatLevel;
        this.cause = cause;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ThreatLevel getThreatLevel() {
        return threatLevel;
    }

    @Override
    public ThreatType getType() {
        return type;
    }

    @Override
    public Player getCause() {
        return cause;
    }

    @Override
    public void execute(@NotNull ThreatLevel severity) {
        if (severity.equals(ThreatLevel.LOW)) return;

        if (severity.equals(ThreatLevel.MEDIUM)) {
            if (this instanceof IllegalUseThreat) {
                itemThreat(severity);
            }
            if (this instanceof WrongCodeThreat) {
                codeThreat(severity);
            }
            return;
        }

        if (severity.equals(ThreatLevel.HIGH)) {
            banPlayer(severity);
        }
    }

    private void itemThreat(@NotNull ThreatLevel severity) {
        if (!severity.equals(ThreatLevel.MEDIUM)) return;

        final Inventory inventory = getCause().getInventory();
        inventory.remove(((IllegalUseThreat) this).getItem());

        final Player cause = getCause();
        getCause().sendMessage(
                ColorUtils.getColored(
                        "&cAn item has been removed from your inventory! &4&lError Code: &c&l"
                                + getType().name()
                )
        );
    }
    private void codeThreat(@NotNull ThreatLevel severity) {
        if (!severity.equals(ThreatLevel.MEDIUM)) return;

        getCause().kickPlayer(ColorUtils.getColored("&cYou have been kicked, use the right code!"));
    }

    private void banPlayer(@NotNull ThreatLevel severity) {
        if (!severity.equals(ThreatLevel.HIGH)) return;

        getCause().getServer().getBanList(BanList.Type.NAME).addBan(
                ChatColor.stripColor(getCause().getName()),
                ColorUtils.getColored(
                        "&cYou have been banned, contact server-administration! &4&lError Code: &c&l"
                                + getType().name()
                ),
                null,
                null);
        getCause().kickPlayer(
                ColorUtils.getColored(
                        "&cYou have been banned, contact server-administration! &4&lError Code: &c&l"
                                + getType().name()
                )
        );
    }

    /**
     * The enum Threat type.
     */
    public enum ThreatType {
        /**
         * Wrong code threat type.
         */
        WRONG_CODE,
        /**
         * Illegal command threat type.
         */
        ILLEGAL_COMMAND,
        /**
         * Illegal item threat type.
         */
        ILLEGAL_ITEM,
        /**
         * Illegal block threat type.
         */
        ILLEGAL_BLOCK,
        /**
         * Illegal entity threat type.
         */
        ILLEGAL_ENTITY
        ;

        ThreatType() {
        }
    }
}
