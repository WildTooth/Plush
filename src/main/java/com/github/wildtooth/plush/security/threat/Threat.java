package com.github.wildtooth.plush.security.threat;

import com.github.wildtooth.plush.Plush;
import com.github.wildtooth.plush.security.handler.ThreatHandler;
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
    private ThreatHandler executor;
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
    public ThreatHandler getExecutor() {
        return executor;
    }

    @Override
    public void setExecutor(ThreatHandler executor) {
        this.executor = executor;
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
        Plush plugin = getExecutor().getPlugin();

        plugin.getLog().info("[" + getThreatLevel().name() +"] " + getType().name() + " THREAT DETECTED: ");
        plugin.getLog().info("Threat Description: " + getDescription());
        plugin.getLog().info("Player: " + getCause().getName());

        if (severity.equals(ThreatLevel.LOW)) {
            plugin.getLog().info("Handling: " + "Ignored, threat level is configured to low.");
            return;
        }

        if (severity.equals(ThreatLevel.MEDIUM)) {
            if (this instanceof IllegalUseThreat) {
                itemThreat(severity, plugin);
            }
            if (this instanceof WrongCodeThreat) {
                codeThreat(severity, plugin);
            }
            return;
        }

        if (severity.equals(ThreatLevel.HIGH)) {
            banPlayer(severity, plugin);
        }
    }

    private void itemThreat(@NotNull ThreatLevel severity, @NotNull Plush plugin) {
        if (!severity.equals(ThreatLevel.MEDIUM)) return;

        final Inventory inventory = getCause().getInventory();
        inventory.remove(((IllegalUseThreat) this).getItem());

        getCause().sendMessage(
                ColorUtils.getColored(
                        "&cAn item has been removed from your inventory! &4&lError Code: &c&l"
                                + getType().name()
                )
        );
        plugin.getLog().info("Handling: " + "Removed the item.");
    }

    private void codeThreat(@NotNull ThreatLevel severity, Plush plugin) {
        if (!severity.equals(ThreatLevel.MEDIUM)) return;

        getCause().kickPlayer(ColorUtils.getColored("&cYou have been kicked, use the right code!"));
        plugin.getLog().info("Handling: " + "Kicked the player.");
    }

    private void banPlayer(@NotNull ThreatLevel severity, @NotNull Plush plugin) {
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
        plugin.getLog().info("Handling: " + "Banned the player.");
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
